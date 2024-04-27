package ru.zillent.study.task5.corporateSettlement.instance;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.Getter;
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
    public ResponseEntity<CorporateSettlementInstanceResponseDTO> createInstance(@RequestBody CorporateSettlementInstanceDTO instanceDTO) throws JsonProcessingException {
        return instanceService.createInstance(instanceDTO);
    }

    @Getter
    private class CorporateSettlementInstanceControllerException extends Throwable {
        private final String message;
        public CorporateSettlementInstanceControllerException(Exception e) {
            this.message = e.getMessage();
        }
    }

    @ExceptionHandler(CorporateSettlementInstanceController.CorporateSettlementInstanceControllerException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody String handleException(
            CorporateSettlementInstanceController.CorporateSettlementInstanceControllerException e
    ) {
        return e.getMessage();
    }
}
