package ru.zillent.study.task5.corporateSettlement.instance;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;
import ru.zillent.study.task5.corporateSettlement.account.CorporateSettlementAccountDTO;

public interface CorporateSettlementInstanceService {
    public ResponseEntity<CorporateSettlementInstanceResponseDTO> createInstance(CorporateSettlementInstanceDTO requestBodyDTO) throws JsonProcessingException;
}
