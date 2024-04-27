package ru.zillent.study.task5.corporateSettlement.instance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CorporateSettlementInstanceDTO {
    private Integer instanceId;
    private String productType;
    private String productCode;
    private String registerType;
    private String mdmCode;
    private String contractNumber;
    private Date contractDate;
    private Integer priority;
    private Float interestRatePenalty;
    private Float minimalBalance;
    private Float thresholdAmount;
    private String accountingDetails;
    private String rateType;
    private Float taxPercentageRate;
    private Float technicalOverdraftLimitAmount;
    private Integer contractId;
    private String BranchCode;
    private String IsoCurrencyCode;
    private String urgencyCode;
    private Integer ReferenceCode;
    private AdditionalProperties additionalProperties;
    private List<InstanceArrangement> instanceArrangement;
}

record AdditionalProperties (List<AdditionalPropertiesData> data) {};
record AdditionalPropertiesData (String key, String value, String name) {};
record InstanceArrangement(
         String GeneralAgreementId,
         String SupplementaryAgreementId,
         String arrangementType,
         Integer shedulerJobId,
         String Number,
         Date openingDate,
         Date closingDate,
         Date CancelDate,
         Integer validityDuration,
         String cancellationReason,
         String Status,
         Date interestCalculationDate,
         Float interestRate,
         Float coefficient,
         String coefficientAction,
         Float minimumInterestRate,
         String minimumInterestRateCoefficient,
         String minimumInterestRateCoefficientAction,
         BigDecimal maximalnterestRate,
         BigDecimal maximalnterestRateCoefficient,
         String maximalnterestRateCoefficientAction
){};