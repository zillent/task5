package ru.zillent.study.task5.corporateSettlement.instance;

import lombok.Getter;

@Getter
public class CorporateSettlementInstanceBadRequestException extends RuntimeException {
    private final String message;

    public CorporateSettlementInstanceBadRequestException(String msg) {
        this.message = msg;
    }
}

