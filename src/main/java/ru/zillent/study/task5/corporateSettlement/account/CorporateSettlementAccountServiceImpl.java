package ru.zillent.study.task5.corporateSettlement.account;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.zillent.study.task5.common.model.Account;
import ru.zillent.study.task5.common.model.AccountRepository;
import ru.zillent.study.task5.common.model.TppProductRegister;
import ru.zillent.study.task5.common.model.TppProductRegisterRepository;
import ru.zillent.study.task5.dict.model.AccountPool;
import ru.zillent.study.task5.dict.model.AccountPoolRepository;
import ru.zillent.study.task5.dict.model.TppRefProductRegisterTypeRepository;

import java.util.Optional;

@Service
public class CorporateSettlementAccountServiceImpl implements CorporateSettlementAccountService {
    private final String BAD_REQUEST_MESSAGE =
            "Имя обязательного параметра %s не заполнено.";
    private final String BAD_REQUEST_DUPLICATE_RECORDS_MESSAGE =
            "Параметр registryTypeCode тип регистра %s уже существует для ЭП с ИД %s.";
    private final String NOT_FOUND_REGISTRY_TYPE_CODE =
            "Код Продукта %s не найдено в Каталоге продуктов public.tpp_ref_product_register_type для данного типа Регистра";
    private final String NOT_FOUND_ACCOUNT_POOL =
            "Не найден подходящий пул счетов";
    private final String NOT_FOUND_ACCOUNTS_IN_POOL =
            "Не найдены счета в пуле счетов";
    @Autowired
    private TppProductRegisterRepository tppProductRegisterRepository;

    @Autowired
    private TppRefProductRegisterTypeRepository tppRefProductRegisterTypeRepository;

    @Autowired
    AccountPoolRepository accountPoolRepository;

    @Autowired
    AccountRepository accountRepository;

    @Override
    public ResponseEntity<String> createAccount(CorporateSettlementAccountDTO requestBodyDTO) throws JsonProcessingException {
        if (requestBodyDTO == null)
            return new ResponseEntity<>(BAD_REQUEST_MESSAGE.formatted(""), HttpStatus.BAD_REQUEST);
        if (requestBodyDTO.getInstanceId() == null)
            return new ResponseEntity<>(BAD_REQUEST_MESSAGE.formatted("instanceId"), HttpStatus.BAD_REQUEST);
        if (requestBodyDTO.getRegistryTypeCode() == null)
            return new ResponseEntity<>(BAD_REQUEST_MESSAGE.formatted("registryTypeCode"), HttpStatus.BAD_REQUEST);
        if (requestBodyDTO.getAccountType() == null)
            return new ResponseEntity<>(BAD_REQUEST_MESSAGE.formatted("accountType"), HttpStatus.BAD_REQUEST);
        if (requestBodyDTO.getCurrencyCode() == null)
            return new ResponseEntity<>(BAD_REQUEST_MESSAGE.formatted("currencyCode"), HttpStatus.BAD_REQUEST);
        if (requestBodyDTO.getBranchCode() == null)
            return new ResponseEntity<>(BAD_REQUEST_MESSAGE.formatted("branchCode"), HttpStatus.BAD_REQUEST);
        if (requestBodyDTO.getPriorityCode() == null)
            return new ResponseEntity<>(BAD_REQUEST_MESSAGE.formatted("priorityCode"), HttpStatus.BAD_REQUEST);
        if (requestBodyDTO.getMdmCode() == null)
            return new ResponseEntity<>(BAD_REQUEST_MESSAGE.formatted("mdmCode"), HttpStatus.BAD_REQUEST);
        if (requestBodyDTO.getClientCode() == null)
            return new ResponseEntity<>(BAD_REQUEST_MESSAGE.formatted("clientCode"), HttpStatus.BAD_REQUEST);
        if (requestBodyDTO.getTrainRegion() == null)
            return new ResponseEntity<>(BAD_REQUEST_MESSAGE.formatted("trainRegion"), HttpStatus.BAD_REQUEST);
        if (requestBodyDTO.getCounter() == null)
            return new ResponseEntity<>(BAD_REQUEST_MESSAGE.formatted("counter"), HttpStatus.BAD_REQUEST);
        if (requestBodyDTO.getSalesCode() == null)
            return new ResponseEntity<>(BAD_REQUEST_MESSAGE.formatted("salesCode"), HttpStatus.BAD_REQUEST);
        if (!tppProductRegisterRepository.findByProductIdAndType(
                Long.valueOf(requestBodyDTO.getInstanceId()),
                requestBodyDTO.getRegistryTypeCode()
        ).isEmpty())
            return new ResponseEntity<>(BAD_REQUEST_DUPLICATE_RECORDS_MESSAGE.formatted(
                    requestBodyDTO.getRegistryTypeCode(), requestBodyDTO.getInstanceId()
            ), HttpStatus.BAD_REQUEST);
        if (tppRefProductRegisterTypeRepository.findByValue(requestBodyDTO.getRegistryTypeCode()).isEmpty())
            return new ResponseEntity<>(NOT_FOUND_REGISTRY_TYPE_CODE.formatted(
                    requestBodyDTO.getRegistryTypeCode()
            ), HttpStatus.NOT_FOUND);
        AccountPool accountPool = new AccountPool(
                requestBodyDTO.getBranchCode(),
                requestBodyDTO.getCurrencyCode(),
                requestBodyDTO.getMdmCode(),
                requestBodyDTO.getPriorityCode(),
                requestBodyDTO.getRegistryTypeCode()
        );
        Optional<AccountPool> foundAccountPool = accountPoolRepository.findOne(Example.of(accountPool));
        if (foundAccountPool.isEmpty())
            return new ResponseEntity<>(NOT_FOUND_ACCOUNT_POOL, HttpStatus.NOT_FOUND);
        Optional<Account> foundAccount = accountRepository.findFirstByAccountPoolId(foundAccountPool.get().getId());
        if (foundAccount.isEmpty())
            return new ResponseEntity<>(NOT_FOUND_ACCOUNTS_IN_POOL, HttpStatus.NOT_FOUND);
        TppProductRegister tppProductRegister = new TppProductRegister(
                Long.valueOf(requestBodyDTO.getInstanceId()),
                requestBodyDTO.getRegistryTypeCode(),
                foundAccount.get().getId(),
                requestBodyDTO.getCurrencyCode(),
                "OPEN",
                foundAccount.get().getAccountNumber()
        );
        TppProductRegister savedProductRegister = tppProductRegisterRepository.save(tppProductRegister);
        CorporateSettlementAccountResponseDTO responseDTO = new CorporateSettlementAccountResponseDTO(
                new DataRecord(String.valueOf(savedProductRegister.getId()))
        );
        ObjectMapper objectMapper = new ObjectMapper();
        String responseBody = objectMapper.writeValueAsString(responseDTO);
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }
}
