package ru.zillent.study.task5.dict.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tpp_ref_account_type")
public class TppRefAccountType {
    @Id
    @Column(name = "internal_id")
    private Long id;
    @Column(name = "value")
    private String value;
}
