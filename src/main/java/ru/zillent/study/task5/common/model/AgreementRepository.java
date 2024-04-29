package ru.zillent.study.task5.common.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgreementRepository extends JpaRepository<Agreement, Long> {
    public List<Agreement> findByNumberList(List<String> numbers);
}
