package ru.zillent.study.task5.corporateSettlement.instance;

import lombok.Getter;

@Getter
public class CorporateSettlementInstanceRequiredFieldsAbsentException extends Throwable {
    private final String message;

    public CorporateSettlementInstanceRequiredFieldsAbsentException(String msg) {
        this.message = msg;
    }
}

