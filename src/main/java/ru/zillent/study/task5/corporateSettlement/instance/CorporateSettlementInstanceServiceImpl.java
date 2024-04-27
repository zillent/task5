package ru.zillent.study.task5.corporateSettlement.instance;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CorporateSettlementInstanceServiceImpl implements CorporateSettlementInstanceService {
    @Override
    public ResponseEntity<CorporateSettlementInstanceResponseDTO> createInstance(CorporateSettlementInstanceDTO requestBodyDTO) throws JsonProcessingException {
        return new ResponseEntity<>(new CorporateSettlementInstanceResponseDTO(
                new DataRecord(
                        "33",
                        List.of("33"),
                        List.of("33")
                )
        ), HttpStatus.OK);
    }
}
