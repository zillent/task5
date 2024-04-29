package ru.zillent.study.task5.common.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tpp_product_register")
@Getter
@Setter
@NoArgsConstructor
public class TppProductRegister {
    @Id
    @SequenceGenerator(name = "tpp_product_register_seq", sequenceName = "tpp_product_register_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tpp_product_register_seq")
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

    public TppProductRegister(Long productId, String type, Long account, String currencyCode, String state, String accountNumber) {
        this.productId = productId;
        this.type = type;
        this.account = account;
        this.currencyCode = currencyCode;
        this.state = state;
        this.accountNumber = accountNumber;
    }
}
