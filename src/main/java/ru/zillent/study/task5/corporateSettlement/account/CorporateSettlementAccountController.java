package ru.zillent.study.task5.corporateSettlement.account;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/corporate-settlement-account")
public class CorporateSettlementAccountController {

    @Autowired
    CorporateSettlementAccountService accountService;

    @PostMapping("/create")
    public ResponseEntity<String> createAccount(@RequestBody CorporateSettlementAccountDTO accountDTO) throws JsonProcessingException {
        return accountService.createAccount(accountDTO);
    }
}
