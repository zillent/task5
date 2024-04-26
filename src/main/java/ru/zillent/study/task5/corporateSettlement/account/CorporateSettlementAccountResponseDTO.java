package ru.zillent.study.task5.corporateSettlement.account;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CorporateSettlementAccountResponseDTO {
    DataRecord data;
}

record DataRecord(String accountId) {}