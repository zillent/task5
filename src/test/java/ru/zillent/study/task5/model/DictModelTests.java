package ru.zillent.study.task5.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
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
