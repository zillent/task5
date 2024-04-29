package ru.zillent.study.task5.dict.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import javax.lang.model.element.Name;
import java.util.Date;

@Entity
@Table(name = "tpp_ref_product_register_type")
@Getter
@Setter
public class TppRefProductRegisterType {
    @Id
    @Column(name = "internal_id")
    private Long id;
    @Column(name = "value")
    String value;
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
