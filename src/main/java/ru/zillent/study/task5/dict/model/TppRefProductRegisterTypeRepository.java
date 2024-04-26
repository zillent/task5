package ru.zillent.study.task5.dict.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TppRefProductRegisterTypeRepository extends JpaRepository<TppRefProductRegisterType, Long> {
    public List<TppRefProductRegisterType> findByValue(String value);
}
