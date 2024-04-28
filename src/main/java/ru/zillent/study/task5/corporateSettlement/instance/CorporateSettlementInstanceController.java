package ru.zillent.study.task5.corporateSettlement.instance;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("corporate-settlement-instance")
public class CorporateSettlementInstanceController {
    @Autowired
    CorporateSettlementInstanceService instanceService;

    @PostMapping("/create")
    public ResponseEntity<CorporateSettlementInstanceResponseDTO> createInstance(@RequestBody CorporateSettlementInstanceDTO instanceDTO) throws JsonProcessingException, CorporateSettlementInstanceBadRequestException, CorporateSettlementInstanceNotFoundException {
        return instanceService.createInstance(instanceDTO);
    }

    @ExceptionHandler(CorporateSettlementInstanceBadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody String handleException(
            CorporateSettlementInstanceBadRequestException e
    ) {
        return e.getMessage();
    }
    @ExceptionHandler(CorporateSettlementInstanceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody String handleException(
            CorporateSettlementInstanceNotFoundException e
    ) {
        return e.getMessage();
    }
}
