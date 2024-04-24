package ru.zillent.study.task5.common.model;

import jakarta.persistence.*;

@Entity
@Table(name = "account")
public class Account {
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "account_pool_id")
    private Long accountPoolId;
    @Column(name = "account_number")
    private String accountNumber;
    @Column(name = "bussy")
    private Boolean bussy;


}
