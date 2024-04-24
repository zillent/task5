package ru.zillent.study.task5.dict.model;

import jakarta.persistence.*;

@Entity
@Table(name = "account_pool")
public class AccountPool {
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "branch_code")
    private String branchCode;
    @Column(name = "currency_code")
    private String currencyCode;
    @Column(name = "mdm_code")
    private String mdmCode;
    @Column(name = "priority_code")
    private String priorityCode;
    @Column(name = "registry_type_code")
    private String registryTypeCode;
}
