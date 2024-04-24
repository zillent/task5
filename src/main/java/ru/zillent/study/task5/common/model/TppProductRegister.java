package ru.zillent.study.task5.common.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.springframework.data.annotation.Id;

@Entity
@Table(name = "tpp_product_register")
public class TppProductRegister {
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "product_id")
    private Long productId;
    @Column(name = "type")
    private String type;
    @Column(name = "account")
    private Long account;
    @Column(name = "currency_code")
    private String currencyCode;
    @Column(name = "state")
    private String state;
    @Column(name = "account_number")
    private String accountNumber;
}
