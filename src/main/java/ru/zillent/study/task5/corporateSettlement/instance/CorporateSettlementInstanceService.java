package ru.zillent.study.task5.corporateSettlement.instance;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;

public interface CorporateSettlementInstanceService {
    public ResponseEntity<CorporateSettlementInstanceResponseDTO> createInstance(CorporateSettlementInstanceDTO requestBodyDTO) throws JsonProcessingException, CorporateSettlementInstanceBadRequestException, CorporateSettlementInstanceNotFoundException;
}
