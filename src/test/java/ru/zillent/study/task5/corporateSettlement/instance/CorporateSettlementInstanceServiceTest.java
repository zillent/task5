package ru.zillent.study.task5.corporateSettlement.instance;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.zillent.study.task5.common.model.*;
import ru.zillent.study.task5.dict.model.*;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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
    CorporateSettlementInstanceDTO requestBodyDTO = null;
    Agreement agreement = new Agreement();

    @Autowired
    CorporateSettlementInstanceService instanceService;

    @Before
    public void initData() {
        requestBodyDTO = new CorporateSettlementInstanceDTO(
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
        agreement.setId(78L);
        agreement.setNumber("22");
    }

    // Step 1
    @Test
    public void serviceCreateNotAllNeededFieldsFilledShouldReturnBadRequestTest() throws JsonProcessingException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
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

    @MockBean
    AgreementRepository agreementRepository;

    // Step 1.1
    @Test
    public void ContractNumberExistsTest() throws CorporateSettlementInstanceNotFoundException, CorporateSettlementInstanceBadRequestException, JsonProcessingException {
        requestBodyDTO.setInstanceId(null);
        TppProduct tppProduct = new TppProduct();
        tppProduct.setId(77L);
        doReturn(Optional.of(tppProduct)).when(tppProductRepository).findFirstByNumber(any());
        var e = Assertions.assertThrows(
                CorporateSettlementInstanceBadRequestException.class,
                () -> instanceService.createInstance(requestBodyDTO)
        );
        Assertions.assertEquals(
                "Параметр ContractNumber № договора %s уже существует для ЭП с ИД %s."
                        .formatted(
                                requestBodyDTO.getContractNumber(),
                                tppProduct.getId()
                        ),
                e.getMessage());
    }

    // Step 1.2
    @Test
    public void AgreementExistsTest() throws CorporateSettlementInstanceNotFoundException, CorporateSettlementInstanceBadRequestException, JsonProcessingException {
        requestBodyDTO.setInstanceId(null);
        doReturn(Optional.empty()).when(tppProductRepository).findFirstByNumber(any());
        doReturn(List.of(agreement)).when(agreementRepository).findByNumberIn(any());
        var e = Assertions.assertThrows(
                CorporateSettlementInstanceBadRequestException.class,
                () -> instanceService.createInstance(requestBodyDTO)
        );
        Assertions.assertEquals(
                "Параметр № Дополнительного соглашения (сделки) Number %s уже существует для ЭП с ИД  %s."
                        .formatted(
                                agreement.getNumber(),
                                agreement.getId()
                        ),
                e.getMessage());
    }

    @MockBean
    TppRefProductClassRepository tppRefProductClassRepository;
    @MockBean
    TppRefProductRegisterTypeRepository tppRefProductRegisterTypeRepository;

    // Step 1.3
    @Test
    public void ProductNotFoundTest() throws CorporateSettlementInstanceNotFoundException, CorporateSettlementInstanceBadRequestException, JsonProcessingException {
        requestBodyDTO.setInstanceId(null);
        doReturn(Optional.empty()).when(tppProductRepository).findFirstByNumber(any());
        doReturn(List.of()).when(agreementRepository).findByNumberIn(any());
        doReturn(List.of()).when(tppRefProductClassRepository).findByValue(any());
        var e = Assertions.assertThrows(
                CorporateSettlementInstanceNotFoundException.class,
                () -> instanceService.createInstance(requestBodyDTO)
        );
        Assertions.assertEquals(
                "КодПродукта %s не найдено в Каталоге продуктов tpp_ref_product_class"
                        .formatted(
                                requestBodyDTO.getProductCode()
                        ),
                e.getMessage());

        doReturn(List.of(new TppRefProductClass())).when(tppRefProductClassRepository).findByValue(any());
        doReturn(List.of()).when(tppRefProductRegisterTypeRepository).findByValue(any());
        e = Assertions.assertThrows(
                CorporateSettlementInstanceNotFoundException.class,
                () -> instanceService.createInstance(requestBodyDTO)
        );
        Assertions.assertEquals(
                "КодПродукта %s не найдено в Каталоге продуктов tpp_ref_product_class"
                        .formatted(
                                requestBodyDTO.getProductCode()
                        ),
                e.getMessage());
    }


    @MockBean
    TppProductRegisterRepository tppProductRegisterRepository;
    @MockBean
    AccountPoolRepository accountPoolRepository;
    @MockBean
    AccountRepository accountRepository;

    // Step 1.4 - 1.5
    @Test
    public void RequestBodyStepCreateProductRegistryTest() throws CorporateSettlementInstanceNotFoundException, CorporateSettlementInstanceBadRequestException, JsonProcessingException {
        requestBodyDTO.setInstanceId(null);
        doReturn(Optional.empty()).when(tppProductRepository).findFirstByNumber(any());
        doReturn(List.of()).when(agreementRepository).findByNumberIn(any());
        doReturn(List.of(new TppRefProductClass())).when(tppRefProductClassRepository).findByValue(any());
        doReturn(List.of(new TppRefProductRegisterType())).when(tppRefProductRegisterTypeRepository)
                .findByProductClassCodeAndAccountType(any(), any());
        TppProduct tppProduct = new TppProduct(
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
        );
        tppProduct.setId(98L);
        doReturn(tppProduct).when(tppProductRepository).save(any());

        AccountPool accountPool = new AccountPool(
                requestBodyDTO.getBranchCode(),
                requestBodyDTO.getIsoCurrencyCode(),
                requestBodyDTO.getMdmCode(),
                String.valueOf(requestBodyDTO.getPriority()),
                requestBodyDTO.getProductType()
        );
        Account account = new Account();
        account.setId(23L);
        account.setAccountNumber("333");
        TppProductRegister tppProductRegister = new TppProductRegister(
                tppProduct.getId(),
                requestBodyDTO.getProductCode(),
                account.getId(),
                requestBodyDTO.getIsoCurrencyCode(),
                "OPEN",
                account.getAccountNumber()
        );
        tppProductRegister.setId(33L);

        doReturn(List.of(new TppRefProductRegisterType())).when(tppRefProductRegisterTypeRepository).findByValue(any());
        doReturn(Optional.of(accountPool)).when(accountPoolRepository).findOne(any());
        doReturn(Optional.of(account)).when(accountRepository).findFirstByAccountPoolId(any());
        doReturn(tppProductRegister).when(tppProductRegisterRepository).save(any());

        var response = instanceService.createInstance(requestBodyDTO);
        verify(tppProductRepository).save(any());
        verify(tppProductRegisterRepository).save(any());
        Assertions.assertEquals("98", response.getBody().data.instanceId());
        Assertions.assertEquals(List.of("33"), response.getBody().data.registerId());
        //Assertions.assertEquals(List.of("33"), response.getBody().data.supplementaryAgreementId());
    }

    // Step 2.1
    @Test
    public void ProductWithProductIdNotFound() {
        doReturn(Optional.empty()).when(tppProductRepository).findById(Long.valueOf(requestBodyDTO.getInstanceId()));
        var e = Assertions.assertThrows(
                CorporateSettlementInstanceNotFoundException.class,
                () -> instanceService.createInstance(requestBodyDTO)
        );
        Assertions.assertEquals(
                "Экземпляр продукта с параметром instanceId %s не найден."
                        .formatted(
                                requestBodyDTO.getInstanceId()
                        ),
                e.getMessage());
    }

    // Step 2.1
    @Test
    public void DuplicateAgreements() {
        doReturn(Optional.of(new TppProduct())).when(tppProductRepository).findById(Long.valueOf(requestBodyDTO.getInstanceId()));
        List<String> agreementIds = new ArrayList<>();
        requestBodyDTO.getInstanceArrangement().forEach((item) -> agreementIds.add(item.Number()));

        doReturn(List.of(agreement)).when(agreementRepository).findByNumberIn(agreementIds);
        var e = Assertions.assertThrows(
                CorporateSettlementInstanceBadRequestException.class,
                () -> instanceService.createInstance(requestBodyDTO)
        );
        Assertions.assertEquals(
                "Параметр № Дополнительного соглашения (сделки) Number %s уже существует для ЭП с ИД %s."
                        .formatted(
                                agreement.getNumber(),
                                agreement.getId()
                        ),
                e.getMessage());
    }

    // Step 8
    @Test
    public void AllIsOkWithInstanceId() throws JsonProcessingException {
        TppProduct tppProduct = new TppProduct();
        tppProduct.setId(98L);
        tppProduct.setType("TEST TYPE");
        doReturn(Optional.of(tppProduct)).when(tppProductRepository).findById(Long.valueOf(requestBodyDTO.getInstanceId()));
        TppProductRegister tppProductRegister = new TppProductRegister();
        tppProductRegister.setId(367L);
        doReturn(List.of(tppProductRegister)).when(tppProductRegisterRepository).findByProductIdAndType(98L,"TEST TYPE");
        List<String> agreementIds = new ArrayList<>();
        requestBodyDTO.getInstanceArrangement().forEach((item) -> agreementIds.add(item.Number()));
        doReturn(List.of()).when(agreementRepository).findByNumberIn(agreementIds);
        doReturn(agreement).when(agreementRepository).save(any());
        var response = instanceService.createInstance(requestBodyDTO);
        verify(agreementRepository).save(any());
        Assertions.assertEquals("98", response.getBody().data.instanceId());
        Assertions.assertEquals(List.of("367"), response.getBody().data.registerId());
        Assertions.assertEquals(List.of("22"), response.getBody().data.supplementaryAgreementId());

    }

}