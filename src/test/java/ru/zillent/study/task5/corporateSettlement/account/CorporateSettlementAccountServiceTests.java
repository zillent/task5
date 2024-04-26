package ru.zillent.study.task5.corporateSettlement.account;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.zillent.study.task5.common.model.TppProductRegister;
import ru.zillent.study.task5.common.model.TppProductRegisterRepository;
import ru.zillent.study.task5.dict.model.TppRefProductRegisterTypeRepository;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CorporateSettlementAccountServiceTests{
    private Integer instanceId = 23;
    private String registryTypeCode = "22";
    private String accountType = "11";
    private String currencyCode = "33";
    private String branchCode = "44";
    private String priorityCode = "66";
    private String mdmCode = "77";
    private String clientCode = "889";
    private String trainRegion = "234";
    private String counter = "ddc";
    private String salesCode = "rrre";

    @Autowired
    CorporateSettlementAccountService accountService;

    @MockBean
    TppProductRegisterRepository tppProductRegisterRepository;

    @MockBean
    TppRefProductRegisterTypeRepository tppRefProductRegisterTypeRepository;

    // Step 1
    @Test
    public void accountCreateNotAllFieldsFilledShouldReturnBadRequestTest() throws JsonProcessingException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        CorporateSettlementAccountDTO account = new CorporateSettlementAccountDTO(
                instanceId,
                registryTypeCode,
                accountType,
                currencyCode,
                branchCode,
                priorityCode,
                mdmCode,
                clientCode,
                trainRegion,
                counter,
                salesCode
        );
        Field[] fields = account.getClass().getDeclaredFields();
        for (Field f: fields) {
            String capitalizeFieldName = f.getName().substring(0,1).toUpperCase()+f.getName().substring(1);
            Method getter = account.getClass().getDeclaredMethod("get"+capitalizeFieldName);
            Method setter = null;
            if (f.getName().equals("instanceId")) {
                setter = account.getClass().getDeclaredMethod("set"+capitalizeFieldName, Integer.class);
            } else {
                setter = account.getClass().getDeclaredMethod("set"+capitalizeFieldName, String.class);
            }
            Object oldValue = getter.invoke(account);
            setter.invoke(account, (Object) null);
            ResponseEntity<String> response = accountService.createAccount(account);
            Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
            Assertions.assertEquals("Имя обязательного параметра "+f.getName()+" не заполнено.", response.getBody());
            setter.invoke(account, oldValue);
        }
        ResponseEntity<String> response = accountService.createAccount(null);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Assertions.assertEquals("Имя обязательного параметра  не заполнено.", response.getBody());
    }

    // Step 2
    @Test
    public void accountCreateDuplicateRecordsTest() throws JsonProcessingException {
        TppProductRegister tppProductRegister = new TppProductRegister(
                (long) instanceId,
                registryTypeCode,
                100L,
                currencyCode,
                "2",
                "377449"
        );
        CorporateSettlementAccountDTO account = new CorporateSettlementAccountDTO(
                instanceId,
                registryTypeCode,
                accountType,
                currencyCode,
                branchCode,
                priorityCode,
                mdmCode,
                clientCode,
                trainRegion,
                counter,
                salesCode
        );
        doReturn(List.of(tppProductRegister)).when(tppProductRegisterRepository).findByProductIdAndType(any(), any());
//        ObjectMapper objectMapper = new ObjectMapper();
//        System.out.println(objectMapper.writeValueAsString(account));
        ResponseEntity<String> response = accountService.createAccount(account);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Assertions.assertEquals(
                "Параметр registryTypeCode тип регистра %s уже существует для ЭП с ИД %s.".formatted(registryTypeCode, instanceId),
                response.getBody()
        );
        doReturn(List.of()).when(tppProductRegisterRepository).findByProductIdAndType(any(), any());
        response = accountService.createAccount(account);
        Assertions.assertNotEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Assertions.assertNotEquals(
                "Параметр registryTypeCode тип регистра %s уже существует для ЭП с ИД %s.".formatted(registryTypeCode, instanceId),
                response.getBody()
        );
    }

    // Step 3
    @Test
    public void accountCreateNotFoundRegisterTypeCodeTest() throws JsonProcessingException {
        CorporateSettlementAccountDTO account = new CorporateSettlementAccountDTO(
                instanceId,
                registryTypeCode,
                accountType,
                currencyCode,
                branchCode,
                priorityCode,
                mdmCode,
                clientCode,
                trainRegion,
                counter,
                salesCode
        );
        doReturn(List.of()).when(tppRefProductRegisterTypeRepository).findByValue(any());
        ResponseEntity<String> response = accountService.createAccount(account);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Assertions.assertEquals(
                "Код Продукта %s не найдено в Каталоге продуктов public.tpp_ref_product_register_type для данного типа Регистра".formatted(registryTypeCode),
                response.getBody()
        );
    }

    //Step 4
    //TODO: Step 4 All Ok
    public void accountCreateAllOkTest() throws JsonProcessingException {
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
    }

}
