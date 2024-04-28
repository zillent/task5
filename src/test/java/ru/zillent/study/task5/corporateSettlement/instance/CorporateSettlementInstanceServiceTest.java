package ru.zillent.study.task5.corporateSettlement.instance;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.zillent.study.task5.common.model.TppProduct;
import ru.zillent.study.task5.common.model.TppProductRegisterRepository;
import ru.zillent.study.task5.common.model.TppProductRepository;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CorporateSettlementInstanceServiceTest {
    private Integer instanceId = 23;
    private String productType = "sdede";
    private String productCode = "sde";
    private String registerType = "dedeqe";
    private String mdmCode = "77";
    private String contractNumber = "devce";
    private Date contractDate = new Date();
    private Integer priority = 22;
    private Float interestRatePenalty = 22.2f;
    private Float minimalBalance = 324.34f;
    private Float thresholdAmount = 2343.2f;
    private String accountingDetails = "ewe";
    private String rateType = "ddr";
    private Float taxPercentageRate = 12.3f;
    private Float technicalOverdraftLimitAmount = 45.6f;
    private Integer contractId = 47;
    private String BranchCode = "rvrtv";
    private String IsoCurrencyCode = "wer";
    private String urgencyCode = "frvrt";
    private Integer ReferenceCode = 44;
    private AdditionalProperties additionalProperties = new AdditionalProperties(List.of(
            new AdditionalPropertiesData("233", "deer", "ewece")
    ));
    private List<InstanceArrangement> instanceArrangement = List.of(new InstanceArrangement(
            "11",
            "22",
            "33",
            11,
            "22",
            new Date(),
            new Date(),
            new Date(),
            3,
            "11",
            "22",
            new Date(),
            11.3f,
            22.2f,
            "33",
            11.1f,
            "22",
            "33",
            BigDecimal.valueOf(11.5),
            BigDecimal.valueOf(22.3),
            "33"
    ));

    List<String> requiredFields = List.of(
            "productType",
            "productCode",
            "registerType",
            "mdmCode",
            "contractNumber",
            "contractDate",
            "priority",
            "contractId",
            "BranchCode",
            "IsoCurrencyCode",
            "urgencyCode"
    );

    @Autowired
    CorporateSettlementInstanceService instanceService;

    // Step 1
    @Test
    public void serviceCreateNotAllNeededFieldsFilledShouldReturnBadRequestTest() throws JsonProcessingException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        CorporateSettlementInstanceDTO requestBodyDTO = new CorporateSettlementInstanceDTO(
                instanceId,
                productType,
                productCode,
                registerType,
                mdmCode,
                contractNumber,
                contractDate,
                priority,
                interestRatePenalty,
                minimalBalance,
                thresholdAmount,
                accountingDetails,
                rateType,
                taxPercentageRate,
                technicalOverdraftLimitAmount,
                contractId,
                BranchCode,
                IsoCurrencyCode,
                urgencyCode,
                ReferenceCode,
                additionalProperties,
                instanceArrangement
        );

        Field[] fields = requestBodyDTO.getClass().getDeclaredFields();
        for (Field f : fields) {
            if (requiredFields.contains(f.getName())) {
                String capitalizeFieldName = f.getName().substring(0, 1).toUpperCase() + f.getName().substring(1);
                Method getter = requestBodyDTO.getClass().getDeclaredMethod("get" + capitalizeFieldName);
                Method setter = null;
                setter = requestBodyDTO.getClass().getDeclaredMethod("set" + capitalizeFieldName, f.getType());
                Object oldValue = getter.invoke(requestBodyDTO);
                setter.invoke(requestBodyDTO, (Object) null);
                var e = Assertions.assertThrows(
                        CorporateSettlementInstanceBadRequestException.class,
                        () -> instanceService.createInstance(requestBodyDTO)
                );
                Assertions.assertEquals("Имя обязательного параметра " + f.getName() + " не заполнено.", e.getMessage());
                setter.invoke(requestBodyDTO, oldValue);
            }
        }
        var e = Assertions.assertThrows(
                CorporateSettlementInstanceBadRequestException.class,
                () -> instanceService.createInstance(null)
        );
        Assertions.assertEquals("Имя обязательного параметра  не заполнено.", e.getMessage());
    }

    @MockBean
    TppProductRepository tppProductRepository;

    // Step 2
    @Test
    public void RequestBodyInstanceIdEmptyTest() throws CorporateSettlementInstanceNotFoundException, CorporateSettlementInstanceBadRequestException, JsonProcessingException {
        CorporateSettlementInstanceDTO requestBodyDTO = new CorporateSettlementInstanceDTO(
                instanceId,
                productType,
                productCode,
                registerType,
                mdmCode,
                contractNumber,
                contractDate,
                priority,
                interestRatePenalty,
                minimalBalance,
                thresholdAmount,
                accountingDetails,
                rateType,
                taxPercentageRate,
                technicalOverdraftLimitAmount,
                contractId,
                BranchCode,
                IsoCurrencyCode,
                urgencyCode,
                ReferenceCode,
                additionalProperties,
                instanceArrangement
        );
        requestBodyDTO.setInstanceId(null);
        instanceService.createInstance(requestBodyDTO);
        verify(tppProductRepository).save(any());
        // Step 1.1
        TppProduct tppProduct = new TppProduct();
        tppProduct.setId(77L);
        doReturn(Optional.of(tppProduct)).when(tppProductRepository).findByNumber(any());
        var e = Assertions.assertThrows(
                CorporateSettlementInstanceBadRequestException.class,
                () -> instanceService.createInstance(requestBodyDTO)
        );
        Assertions.assertEquals(
                "Параметр ContractNumber № договора %s уже существует для ЭП с ИД %s.".formatted(
                        requestBodyDTO.getContractNumber(),
                        tppProduct.getId()
                ),
                e.getMessage());
    }

    @MockBean
    TppProductRegisterRepository tppProductRegisterRepository;

    // Step 2
//    @Test
//    public void accountCreateDuplicateRecordsTest() throws JsonProcessingException {
//        TppProductRegister tppProductRegister = new TppProductRegister(
//                (long) instanceId,
//                registryTypeCode,
//                100L,
//                currencyCode,
//                "OPEN",
//                "377449"
//        );
//        CorporateSettlementAccountDTO account = new CorporateSettlementAccountDTO(
//                instanceId,
//                registryTypeCode,
//                accountType,
//                currencyCode,
//                branchCode,
//                priorityCode,
//                mdmCode,
//                clientCode,
//                trainRegion,
//                counter,
//                salesCode
//        );
//        doReturn(List.of(tppProductRegister)).when(tppProductRegisterRepository).findByProductIdAndType(any(), any());
////        ObjectMapper objectMapper = new ObjectMapper();
////        System.out.println(objectMapper.writeValueAsString(account));
//        ResponseEntity<String> response = accountService.createAccount(account);
//        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
//        Assertions.assertEquals(
//                "Параметр registryTypeCode тип регистра %s уже существует для ЭП с ИД %s.".formatted(registryTypeCode, instanceId),
//                response.getBody()
//        );
//        doReturn(List.of()).when(tppProductRegisterRepository).findByProductIdAndType(any(), any());
//        response = accountService.createAccount(account);
//        Assertions.assertNotEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
//        Assertions.assertNotEquals(
//                "Параметр registryTypeCode тип регистра %s уже существует для ЭП с ИД %s.".formatted(registryTypeCode, instanceId),
//                response.getBody()
//        );
//    }
//
//    @MockBean
//    TppRefProductRegisterTypeRepository tppRefProductRegisterTypeRepository;
//
//    // Step 3
//    @Test
//    public void accountCreateNotFoundRegisterTypeCodeTest() throws JsonProcessingException {
//        CorporateSettlementAccountDTO account = new CorporateSettlementAccountDTO(
//                instanceId,
//                registryTypeCode,
//                accountType,
//                currencyCode,
//                branchCode,
//                priorityCode,
//                mdmCode,
//                clientCode,
//                trainRegion,
//                counter,
//                salesCode
//        );
//        doReturn(List.of()).when(tppRefProductRegisterTypeRepository).findByValue(any());
//        ResponseEntity<String> response = accountService.createAccount(account);
//        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//        Assertions.assertEquals(
//                "Код Продукта %s не найдено в Каталоге продуктов public.tpp_ref_product_register_type для данного типа Регистра".formatted(registryTypeCode),
//                response.getBody()
//        );
//    }
//
//    @MockBean
//    AccountPoolRepository accountPoolRepository;
//
//    @MockBean
//    AccountRepository accountRepository;
//
//    //Step 4
//    @Test
//    public void accountCreateAllOkTest() throws JsonProcessingException {
//        CorporateSettlementAccountDTO requestBodyDTO = new CorporateSettlementAccountDTO(
//                instanceId,
//                registryTypeCode,
//                accountType,
//                currencyCode,
//                branchCode,
//                priorityCode,
//                mdmCode,
//                clientCode,
//                trainRegion,
//                counter,
//                salesCode
//        );
//        AccountPool accountPool = new AccountPool(
//                requestBodyDTO.getBranchCode(),
//                requestBodyDTO.getCurrencyCode(),
//                requestBodyDTO.getMdmCode(),
//                requestBodyDTO.getPriorityCode(),
//                requestBodyDTO.getRegistryTypeCode()
//        );
//        Account account = new Account();
//        account.setId(23L);
//        account.setAccountNumber("333");
//        TppProductRegister tppProductRegister = new TppProductRegister(
//                Long.valueOf(requestBodyDTO.getInstanceId()),
//                requestBodyDTO.getRegistryTypeCode(),
//                account.getId(),
//                requestBodyDTO.getCurrencyCode(),
//                "OPEN",
//                account.getAccountNumber()
//        );
//        doReturn(List.of(new TppRefProductRegisterType())).when(tppRefProductRegisterTypeRepository).findByValue(any());
//
//        // when not found Account pool
//        doReturn(Optional.empty()).when(accountPoolRepository).findOne(any());
//        // then
//        ResponseEntity<String> response = accountService.createAccount(requestBodyDTO);
//        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//        Assertions.assertEquals("Не найден подходящий пул счетов", response.getBody());
//
//        doReturn(Optional.of(accountPool)).when(accountPoolRepository).findOne(any());
//
//        // when not found any accounts in pool
//        doReturn(Optional.empty()).when(accountRepository).findAnyByAccountPoolId(any());
//        // then
//        response = accountService.createAccount(requestBodyDTO);
//        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//        Assertions.assertEquals("Не найдены счета в пуле счетов", response.getBody());
//
//        doReturn(Optional.of(account)).when(accountRepository).findAnyByAccountPoolId(any());
//        doReturn(tppProductRegister).when(tppProductRegisterRepository).save(any());
//        // when everything is ok
//        tppProductRegister.setId(33L);
//        response = accountService.createAccount(requestBodyDTO);
//        // then
//        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
//        Assertions.assertEquals("{\"data\":{\"accountId\":\"33\"}}", response.getBody());
//        verify(tppProductRegisterRepository).save(any());
//    }

}
