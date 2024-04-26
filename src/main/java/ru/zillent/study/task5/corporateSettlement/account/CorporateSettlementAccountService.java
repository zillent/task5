package ru.zillent.study.task5.corporateSettlement.account;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;

public interface CorporateSettlementAccountService {
    public ResponseEntity<String> createAccount(CorporateSettlementAccountDTO account) throws JsonProcessingException;
}
