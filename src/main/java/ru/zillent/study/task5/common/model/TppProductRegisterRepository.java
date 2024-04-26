package ru.zillent.study.task5.common.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TppProductRegisterRepository extends JpaRepository<TppProductRegister, Long> {
    List<TppProductRegister> findByProductIdAndType(Long productId, String registerType);
}
