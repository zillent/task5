package ru.zillent.study.task5.dict.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tpp_ref_product_class")
public class TppRefProductClass {
    @Id
    @Column(name = "internal_id")
    private Long id;
    @Column(name = "value")
    private String value;
    @Column(name = "gbi_code")
    private String gbiCode;
    @Column(name = "gbi_name")
    private String gbiName;
    @Column(name = "product_row_code")
    private String productRowCode;
    @Column(name = "product_row_name")
    private String productRowName;
    @Column(name = "subclass_code")
    private String subclassCode;
    @Column(name = "subclass_name")
    private String subclassName;
}
