package ru.zillent.study.task5.common.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.springframework.data.annotation.Id;

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
