package ru.zillent.study.task5.common.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "agreement")
@Getter
@Setter
@NoArgsConstructor
public class Agreement {
    @Id
    @SequenceGenerator(name = "agreement_seq", sequenceName = "agreement_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "agreement_seq")
    @Column(name = "id")
    private Long id;
    @Column(name = "product_id")
    private Long productId;
    @Column(name = "general_agreement_id")
    private String generalAgreementId;
    @Column(name = "supplementary_agreement_id")
    private String supplementaryAgreementId;
    @Column(name = "arrangement_type")
    private String arrangementType;
    @Column(name = "sheduler_job_id")
    private Long schedulerJobId;
    @Column(name = "number")
    private String number;
    @Column(name = "opening_date")
    private Date openingDate;
    @Column(name = "closing_date")
    private Date closingDate;
    @Column(name = "cancel_date")
    private Date cancelDate;
    @Column(name = "validity_duration")
    private Long validityDuration;
    @Column(name = "cancellation_reason")
    private String cancellationReason;
    @Column(name = "status")
    private String status;
    @Column(name = "interest_calculation_date")
    private Date interestCalculationDate;
    @Column(name = "interest_rate")
    private BigDecimal interestRate;
    @Column(name = "coefficient")
    private BigDecimal coefficient;
    @Column(name = "coefficient_action")
    private String coefficientAction;
    @Column(name = "minimum_interest_rate")
    private BigDecimal minimumInterestRate;
    @Column(name = "minimum_interest_rate_coefficient")
    private BigDecimal minimumInterestRateCoefficient;
    @Column(name = "minimum_interest_rate_coefficient_action")
    private String minimumInterestRateCoefficientAction;
    @Column(name = "maximal_interest_rate")
    private BigDecimal maximalInterestRate;
    @Column(name = "maximal_interest_rate_coefficient")
    private BigDecimal maximalInterestRateCoefficient;
    @Column(name = "maximal_interest_rate_coefficient_action")
    private String maximalInterestRateCoefficientAction;

    public Agreement(Long productId, String generalAgreementId, String supplementaryAgreementId, String arrangementType, Long schedulerJobId, String number, Date openingDate, Date closingDate, Date cancelDate, Long validityDuration, String cancellationReason, String status, Date interestCalculationDate, BigDecimal interestRate, BigDecimal coefficient, String coefficientAction, BigDecimal minimumInterestRate, BigDecimal minimumInterestRateCoefficient, String minimumInterestRateCoefficientAction, BigDecimal maximalInterestRate, BigDecimal maximalInterestRateCoefficient, String maximalInterestRateCoefficientAction) {
        this.productId = productId;
        this.generalAgreementId = generalAgreementId;
        this.supplementaryAgreementId = supplementaryAgreementId;
        this.arrangementType = arrangementType;
        this.schedulerJobId = schedulerJobId;
        this.number = number;
        this.openingDate = openingDate;
        this.closingDate = closingDate;
        this.cancelDate = cancelDate;
        this.validityDuration = validityDuration;
        this.cancellationReason = cancellationReason;
        this.status = status;
        this.interestCalculationDate = interestCalculationDate;
        this.interestRate = interestRate;
        this.coefficient = coefficient;
        this.coefficientAction = coefficientAction;
        this.minimumInterestRate = minimumInterestRate;
        this.minimumInterestRateCoefficient = minimumInterestRateCoefficient;
        this.minimumInterestRateCoefficientAction = minimumInterestRateCoefficientAction;
        this.maximalInterestRate = maximalInterestRate;
        this.maximalInterestRateCoefficient = maximalInterestRateCoefficient;
        this.maximalInterestRateCoefficientAction = maximalInterestRateCoefficientAction;
    }
}
