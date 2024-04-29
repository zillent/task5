package ru.zillent.study.task5.corporateSettlement.instance;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.zillent.study.task5.common.model.*;
import ru.zillent.study.task5.corporateSettlement.account.AccountDataRecord;
import ru.zillent.study.task5.corporateSettlement.account.CorporateSettlementAccountDTO;
import ru.zillent.study.task5.corporateSettlement.account.CorporateSettlementAccountResponseDTO;
import ru.zillent.study.task5.corporateSettlement.account.CorporateSettlementAccountService;
import ru.zillent.study.task5.dict.model.TppRefProductClass;
import ru.zillent.study.task5.dict.model.TppRefProductClassRepository;
import ru.zillent.study.task5.dict.model.TppRefProductRegisterType;
import ru.zillent.study.task5.dict.model.TppRefProductRegisterTypeRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CorporateSettlementInstanceServiceImpl implements CorporateSettlementInstanceService {
    private final String BAD_REQUEST_MESSAGE =
            "Имя обязательного параметра %s не заполнено.";

    @Autowired
    TppProductRepository tppProductRepository;
    @Autowired
    AgreementRepository agreementRepository;
    @Autowired
    TppRefProductClassRepository tppRefProductClassRepository;
    @Autowired
    TppRefProductRegisterTypeRepository tppRefProductRegisterTypeRepository;
    @Autowired
    TppProductRegisterRepository tppProductRegisterRepository;
    @Autowired
    CorporateSettlementAccountService accountService;

    @Override
    public ResponseEntity<CorporateSettlementInstanceResponseDTO> createInstance(CorporateSettlementInstanceDTO requestBodyDTO) throws JsonProcessingException, CorporateSettlementInstanceBadRequestException, CorporateSettlementInstanceNotFoundException {
        if (requestBodyDTO == null)
            throw new CorporateSettlementInstanceBadRequestException(BAD_REQUEST_MESSAGE.formatted(""));
        if (requestBodyDTO.getProductType() == null)
            throw new CorporateSettlementInstanceBadRequestException(BAD_REQUEST_MESSAGE.formatted("productType"));
        if (requestBodyDTO.getProductCode() == null)
            throw new CorporateSettlementInstanceBadRequestException(BAD_REQUEST_MESSAGE.formatted("productCode"));
        if (requestBodyDTO.getRegisterType() == null)
            throw new CorporateSettlementInstanceBadRequestException(BAD_REQUEST_MESSAGE.formatted("registerType"));
        if (requestBodyDTO.getMdmCode() == null)
            throw new CorporateSettlementInstanceBadRequestException(BAD_REQUEST_MESSAGE.formatted("mdmCode"));
        if (requestBodyDTO.getContractNumber() == null)
            throw new CorporateSettlementInstanceBadRequestException(BAD_REQUEST_MESSAGE.formatted("contractNumber"));
        if (requestBodyDTO.getContractDate() == null)
            throw new CorporateSettlementInstanceBadRequestException(BAD_REQUEST_MESSAGE.formatted("contractDate"));
        if (requestBodyDTO.getPriority() == null)
            throw new CorporateSettlementInstanceBadRequestException(BAD_REQUEST_MESSAGE.formatted("priority"));
        if (requestBodyDTO.getContractId() == null)
            throw new CorporateSettlementInstanceBadRequestException(BAD_REQUEST_MESSAGE.formatted("contractId"));
        if (requestBodyDTO.getBranchCode() == null)
            throw new CorporateSettlementInstanceBadRequestException(BAD_REQUEST_MESSAGE.formatted("BranchCode"));
        if (requestBodyDTO.getIsoCurrencyCode() == null)
            throw new CorporateSettlementInstanceBadRequestException(BAD_REQUEST_MESSAGE.formatted("IsoCurrencyCode"));
        if (requestBodyDTO.getUrgencyCode() == null)
            throw new CorporateSettlementInstanceBadRequestException(BAD_REQUEST_MESSAGE.formatted("urgencyCode"));
        String instanceId = "";
        List<String> registerIds = new ArrayList<>();
        List<String> acquiredAgreementIds = new ArrayList<>();
        List<String> agreementIds = new ArrayList<>();
        if (requestBodyDTO.getInstanceId() == null) {
            tppProductRepository.findFirstByNumber(requestBodyDTO.getContractNumber()).ifPresent(
                    foundProduct -> {
                        throw new CorporateSettlementInstanceBadRequestException(
                                "Параметр ContractNumber № договора %s уже существует для ЭП с ИД %s."
                                        .formatted(
                                                requestBodyDTO.getContractNumber(),
                                                foundProduct.getId()
                                        )
                        );
                    }
            );
            List<String> arrangementNumbers = new ArrayList<>();
            requestBodyDTO.getInstanceArrangement().forEach((item) -> arrangementNumbers.add(item.Number()));
            List<Agreement> agreementList = agreementRepository.findByNumberList(arrangementNumbers);
            if (!agreementList.isEmpty())
                throw new CorporateSettlementInstanceBadRequestException(
                        "Параметр № Дополнительного соглашения (сделки) Number %s уже существует для ЭП с ИД  %s."
                                .formatted(
                                        agreementList.get(0).getNumber(),
                                        agreementList.get(0).getId()
                                )
                );
            List<TppRefProductClass> tppRefProductClassList =
                    tppRefProductClassRepository.findByValue(requestBodyDTO.getProductCode());
            if (tppRefProductClassList.isEmpty())
                throw new CorporateSettlementInstanceNotFoundException(
                        "КодПродукта %s не найдено в Каталоге продуктов tpp_ref_product_class"
                                .formatted(
                                        requestBodyDTO.getProductCode()
                                )
                );
            List<TppRefProductRegisterType> tppRefProductRegisterTypeList =
                    tppRefProductRegisterTypeRepository.findByProductClassCodeAndAccountType(
                            requestBodyDTO.getProductCode(), "Клиентский"
                    );
            if (tppRefProductRegisterTypeList.isEmpty())
                throw new CorporateSettlementInstanceNotFoundException(
                        "КодПродукта %s не найдено в Каталоге продуктов tpp_ref_product_class"
                                .formatted(
                                        requestBodyDTO.getProductCode()
                                )
                );
            TppProduct tppProduct = tppProductRepository.save(new TppProduct(
                    Long.valueOf(requestBodyDTO.getMdmCode()),
                    requestBodyDTO.getProductType(),
                    requestBodyDTO.getContractNumber(),
                    Long.valueOf(requestBodyDTO.getPriority()),
                    requestBodyDTO.getContractDate(),
                    requestBodyDTO.getContractDate(),
                    null,
                    null,
                    BigDecimal.valueOf(requestBodyDTO.getInterestRatePenalty()),
                    null,
                    BigDecimal.valueOf(requestBodyDTO.getThresholdAmount()),
                    requestBodyDTO.getAccountingDetails(),
                    requestBodyDTO.getRateType(),
                    BigDecimal.valueOf(requestBodyDTO.getTaxPercentageRate())
            ));
            instanceId = String.valueOf(tppProduct.getId());
            CorporateSettlementAccountDTO accountDTO = new CorporateSettlementAccountDTO(
                    Math.toIntExact(tppProduct.getId()),
                    requestBodyDTO.getProductType(),
                    "",
                    requestBodyDTO.getIsoCurrencyCode(),
                    requestBodyDTO.getBranchCode(),
                    String.valueOf(requestBodyDTO.getPriority()),
                    requestBodyDTO.getMdmCode(),
                    requestBodyDTO.getMdmCode(),
                    "",
                    "1",
                    "1"
            );
            ResponseEntity<String> accountServiceResponse = accountService.createAccount(accountDTO);
            if (accountServiceResponse.getStatusCode() != HttpStatus.OK) {
                throw new RuntimeException(accountServiceResponse.getBody());
            }
            ObjectMapper objectMapper = new ObjectMapper();
            CorporateSettlementAccountResponseDTO responseDTO =
                    objectMapper.readValue(accountServiceResponse.getBody(), CorporateSettlementAccountResponseDTO.class);
            registerIds = List.of(String.valueOf(responseDTO.getData().accountId()));
        } else {
            Optional<TppProduct> foundTppProduct = tppProductRepository.findById(Long.valueOf(requestBodyDTO.getInstanceId()));
            if (foundTppProduct.isEmpty())
                throw new CorporateSettlementInstanceNotFoundException(
                        "Экземпляр продукта с параметром instanceId %s не найден."
                                .formatted(
                                        requestBodyDTO.getInstanceId()
                                )
                );
            instanceId = String.valueOf(foundTppProduct.get().getId());
            requestBodyDTO.getInstanceArrangement().forEach((item) -> agreementIds.add(item.Number()));
            List<Agreement> agreementList = agreementRepository.findByNumberList(agreementIds);
            if (!agreementList.isEmpty())
                throw new CorporateSettlementInstanceBadRequestException(
                        "Параметр № Дополнительного соглашения (сделки) Number %s уже существует для ЭП с ИД %s."
                                .formatted(
                                        agreementList.get(0).getNumber(),
                                        agreementList.get(0).getId()
                                )
                );
            requestBodyDTO.getInstanceArrangement().forEach((item) -> {
                        Agreement agreement = new Agreement(
                                foundTppProduct.get().getId(),
                                item.GeneralAgreementId(),
                                item.SupplementaryAgreementId(),
                                item.arrangementType(),
                                Long.valueOf(item.shedulerJobId()),
                                item.Number(),
                                item.openingDate(),
                                item.closingDate(),
                                item.CancelDate(),
                                Long.valueOf(item.validityDuration()),
                                item.cancellationReason(),
                                item.Status(),
                                item.interestCalculationDate(),
                                BigDecimal.valueOf(item.interestRate()),
                                BigDecimal.valueOf(item.coefficient()),
                                item.coefficientAction(),
                                BigDecimal.valueOf(item.minimumInterestRate()),
                                BigDecimal.valueOf(Long.parseLong(item.minimumInterestRateCoefficient())),
                                item.minimumInterestRateCoefficientAction(),
                                item.maximalnterestRate(),
                                item.maximalnterestRateCoefficient(),
                                item.minimumInterestRateCoefficient()
                        );
                        acquiredAgreementIds.add(String.valueOf(agreementRepository.save(agreement).getId()));
                    }
            );
            for (TppProductRegister item : tppProductRegisterRepository.findByProductIdAndType(
                    foundTppProduct.get().getId(),
                    foundTppProduct.get().getType()
            )) {
                registerIds.add(String.valueOf(item.getId()));
            }
        }

        return new ResponseEntity<>(new CorporateSettlementInstanceResponseDTO(
                new DataRecord(
                        String.valueOf(instanceId),
                        registerIds,
                        agreementIds
                )
        ), HttpStatus.OK);
    }
}
