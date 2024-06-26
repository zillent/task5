package ru.zillent.study.task5.dict.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "account_pool")
@Getter
@Setter
@NoArgsConstructor
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

    public AccountPool(String branchCode, String currencyCode, String mdmCode, String registryTypeCode) {
        this.branchCode = branchCode;
        this.currencyCode = currencyCode;
        this.mdmCode = mdmCode;
        this.registryTypeCode = registryTypeCode;
    }
}
