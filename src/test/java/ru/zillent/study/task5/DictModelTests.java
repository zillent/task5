package ru.zillent.study.task5;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.MountableFile;
import ru.zillent.study.task5.dict.model.*;

import java.util.List;

public class DictModelTests extends SpringContextApplicationTest{

    @Autowired
    TppRefProductClassRepository tppRefProductClassRepository;

    @Test
    public void TppRefProductClassTest() {
        List<TppRefProductClass> tppRefProductClassList = tppRefProductClassRepository.findAll();
        Assertions.assertEquals(2, tppRefProductClassList.size());
    }

    @Autowired
    TppRefProductRegisterTypeRepository tppRefProductRegisterTypeRepository;

    @Test
    public void TppRefProductRegisterTypeTest() {
        List<TppRefProductRegisterType> tppRefProductRegisterTypeList = tppRefProductRegisterTypeRepository.findAll();
        Assertions.assertEquals(2, tppRefProductRegisterTypeList.size());
    }

    @Autowired
    TppRefAccountTypeRepository tppRefAccountTypeRepository;

    @Test
    public void TppRefAccountTypeTest() {
        List<TppRefAccountType> tppRefAccountTypeList = tppRefAccountTypeRepository.findAll();
        Assertions.assertEquals(2, tppRefAccountTypeList.size());
    }

    @Autowired
    AccountPoolRepository accountPoolRepository;
    @Test
    public void AccountPoolTest() {
        List<AccountPool> accountPoolList = accountPoolRepository.findAll();
        Assertions.assertEquals(2, accountPoolList.size());
    }
}
