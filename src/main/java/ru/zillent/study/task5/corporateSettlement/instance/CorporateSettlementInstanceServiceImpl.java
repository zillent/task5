package ru.zillent.study.task5.corporateSettlement.instance;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.zillent.study.task5.common.model.TppProduct;
import ru.zillent.study.task5.common.model.TppProductRepository;

import java.util.List;

@Service
public class CorporateSettlementInstanceServiceImpl implements CorporateSettlementInstanceService {
    private final String BAD_REQUEST_MESSAGE =
            "Имя обязательного параметра %s не заполнено.";

    @Autowired
    TppProductRepository tppProductRepository;

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
        if (requestBodyDTO.getInstanceId() == null) {
            tppProductRepository.findByNumber(requestBodyDTO.getContractNumber()).ifPresent(
                    foundProduct -> {
                        throw new CorporateSettlementInstanceBadRequestException(
                                "Параметр ContractNumber № договора %s уже существует для ЭП с ИД %s.".formatted(
                                        requestBodyDTO.getContractNumber(),
                                        foundProduct.getId()
                                )
                        );
                    }
            );
            tppProductRepository.save(new TppProduct(
            ));
        }
        if (false) throw new CorporateSettlementInstanceNotFoundException("TEST");
        return new ResponseEntity<>(new CorporateSettlementInstanceResponseDTO(
                new DataRecord(
                        "33",
                        List.of("33"),
                        List.of("33")
                )
        ), HttpStatus.OK);
    }
}
