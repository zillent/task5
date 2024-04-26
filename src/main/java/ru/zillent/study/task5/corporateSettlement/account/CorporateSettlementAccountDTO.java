package ru.zillent.study.task5.corporateSettlement.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
public class CorporateSettlementAccountDTO {
    private Integer instanceId;
    private String registryTypeCode;
    private String accountType;
    private String currencyCode;
    private String branchCode;
    private String priorityCode;
    private String mdmCode;
    private String clientCode;
    private String trainRegion;
    private String counter;
    private String salesCode;

}
