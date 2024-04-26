package ru.zillent.study.task5.model;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import ru.zillent.study.task5.common.model.*;
import java.util.List;

public class CommonModelTests extends SpringContextApplicationTest {

    @Autowired
    AccountRepository accountRepository;

    @Test
    public void AccountTest() {
        List<Account> accountList = accountRepository.findAll();
        Assertions.assertEquals(6, accountList.size());
    }

    @Autowired
    AgreementRepository agreementRepository;

    @Test
    public void AgreementTest() {
        List<Agreement> agreementList = agreementRepository.findAll();
        Assertions.assertEquals(0, agreementList.size());
    }

    @Autowired
    TppProductRepository tppProductRepository;

    @Test
    public void TppProductTest() {
        List<TppProduct> tppProductList = tppProductRepository.findAll();
        Assertions.assertEquals(0, tppProductList.size());
    }

    @Autowired
    TppProductRegisterRepository tppProductRegisterRepository;
    @Test
    public void TppProductRegisterTest() {
        List<TppProductRegister> tppProductRegisterList = tppProductRegisterRepository.findAll();
        Assertions.assertEquals(0, tppProductRegisterList.size());
    }
}
