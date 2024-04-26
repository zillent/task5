package ru.zillent.study.task5.corporateSettlement.account;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.zillent.study.task5.common.model.TppProductRegisterRepository;
import ru.zillent.study.task5.dict.model.TppRefProductRegisterTypeRepository;

@Service
public class CorporateSettlementAccountServiceImpl implements CorporateSettlementAccountService {
    private final String BAD_REQUEST_MESSAGE =
            "Имя обязательного параметра %s не заполнено.";
    private final String BAD_REQUEST_DUPLICATE_RECORDS_MESSAGE =
            "Параметр registryTypeCode тип регистра %s уже существует для ЭП с ИД %s.";
    private final String NOT_FOUND_REGISTRY_TYPE_CODE =
            "Код Продукта %s не найдено в Каталоге продуктов public.tpp_ref_product_register_type для данного типа Регистра";

    @Autowired
    private TppProductRegisterRepository tppProductRegisterRepository;

    @Autowired
    private TppRefProductRegisterTypeRepository tppRefProductRegisterTypeRepository;

    @Override
    public ResponseEntity<String> createAccount(CorporateSettlementAccountDTO account) throws JsonProcessingException {
        if (account == null)
            return new ResponseEntity<>(BAD_REQUEST_MESSAGE.formatted( ""), HttpStatus.BAD_REQUEST);
        if (account.getInstanceId() == null)
            return new ResponseEntity<>(BAD_REQUEST_MESSAGE.formatted( "instanceId"), HttpStatus.BAD_REQUEST);
        if (account.getRegistryTypeCode() == null)
            return new ResponseEntity<>(BAD_REQUEST_MESSAGE.formatted( "registryTypeCode"), HttpStatus.BAD_REQUEST);
        if (account.getAccountType() == null)
            return new ResponseEntity<>(BAD_REQUEST_MESSAGE.formatted( "accountType"), HttpStatus.BAD_REQUEST);
        if (account.getCurrencyCode() == null)
            return new ResponseEntity<>(BAD_REQUEST_MESSAGE.formatted( "currencyCode"), HttpStatus.BAD_REQUEST);
        if (account.getBranchCode() == null)
            return new ResponseEntity<>(BAD_REQUEST_MESSAGE.formatted( "branchCode"), HttpStatus.BAD_REQUEST);
        if (account.getPriorityCode() == null)
            return new ResponseEntity<>(BAD_REQUEST_MESSAGE.formatted( "priorityCode"), HttpStatus.BAD_REQUEST);
        if (account.getMdmCode() == null)
            return new ResponseEntity<>(BAD_REQUEST_MESSAGE.formatted( "mdmCode"), HttpStatus.BAD_REQUEST);
        if (account.getClientCode() == null)
            return new ResponseEntity<>(BAD_REQUEST_MESSAGE.formatted( "clientCode"), HttpStatus.BAD_REQUEST);
        if (account.getTrainRegion() == null)
            return new ResponseEntity<>(BAD_REQUEST_MESSAGE.formatted( "trainRegion"), HttpStatus.BAD_REQUEST);
        if (account.getCounter() == null)
            return new ResponseEntity<>(BAD_REQUEST_MESSAGE.formatted( "counter"), HttpStatus.BAD_REQUEST);
        if (account.getSalesCode() == null)
            return new ResponseEntity<>(BAD_REQUEST_MESSAGE.formatted( "salesCode"), HttpStatus.BAD_REQUEST);
        if (!tppProductRegisterRepository.findByProductIdAndType(
                Long.valueOf(account.getInstanceId()),
                account.getRegistryTypeCode()
        ).isEmpty())
            return new ResponseEntity<>(BAD_REQUEST_DUPLICATE_RECORDS_MESSAGE.formatted(
                    account.getRegistryTypeCode(), account.getInstanceId()
            ), HttpStatus.BAD_REQUEST);
        if (tppRefProductRegisterTypeRepository.findByValue(account.getRegistryTypeCode()).isEmpty())
            return new ResponseEntity<>(NOT_FOUND_REGISTRY_TYPE_CODE.formatted(
                    account.getRegistryTypeCode()
            ), HttpStatus.NOT_FOUND);

        CorporateSettlementAccountResponseDTO responseDTO = new CorporateSettlementAccountResponseDTO(
                new DataRecord("")
        );
        ObjectMapper objectMapper = new ObjectMapper();
        String responseBody = objectMapper.writeValueAsString(responseDTO);
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }
}
