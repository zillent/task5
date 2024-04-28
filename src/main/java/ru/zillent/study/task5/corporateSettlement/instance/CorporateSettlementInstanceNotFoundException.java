package ru.zillent.study.task5.corporateSettlement.instance;

import lombok.Getter;

@Getter
public class CorporateSettlementInstanceNotFoundException extends Throwable {

    private final String message;

    public CorporateSettlementInstanceNotFoundException(String msg) {
        this.message = msg;
    }
}
