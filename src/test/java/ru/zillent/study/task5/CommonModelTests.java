package ru.zillent.study.task5;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.MountableFile;
import ru.zillent.study.task5.common.model.*;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.TestPropertySource;

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
