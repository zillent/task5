package ru.zillent.study.task5.dict.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.springframework.data.annotation.Id;

@Entity
@Table(name = "tpp_ref_account_type")
public class TppRefAccountType {
    @Id
    @Column(name = "internal_id")
    private int id;
    @Column(name = "value")
    private String value;
}
