package ru.zillent.study.task5.dict.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TppRefProductRegisterTypeRepository extends JpaRepository<TppRefProductRegisterType, Long> {
}
