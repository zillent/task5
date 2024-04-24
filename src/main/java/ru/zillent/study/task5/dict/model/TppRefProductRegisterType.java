package ru.zillent.study.task5.dict.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Entity
@Table(name = "tpp_ref_product_register_type")
public class TppRefProductRegisterType {
    @Id
    @Column(name = "internal_id")
    private int id;
    @Column(name="register_type_name")
    private String registerTypeName;
    @Column(name = "product_class_code")
    private String productClassCode;
    @Column(name = "account_type")
    private String accountType;
    @Column(name = "register_type_start_date")
    private Date registerTypeStartDate;
    @Column(name = "register_type_end_date")
    private Date registerTypeEndDate;
}
