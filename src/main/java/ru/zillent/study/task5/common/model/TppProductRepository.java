package ru.zillent.study.task5.common.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TppProductRepository extends JpaRepository<TppProduct, Long> {
    public Optional<TppProduct> findByNumber(String number);
}
