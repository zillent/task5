package ru.zillent.study.task5.corporateSettlement.instance;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CorporateSettlementInstanceServiceImpl implements CorporateSettlementInstanceService {
    private final String BAD_REQUEST_MESSAGE =
            "Имя обязательного параметра %s не заполнено.";

    @Override
    public ResponseEntity<CorporateSettlementInstanceResponseDTO> createInstance(CorporateSettlementInstanceDTO requestBodyDTO) throws JsonProcessingException, CorporateSettlementInstanceRequiredFieldsAbsentException, CorporateSettlementInstanceNotFoundException {
        if (requestBodyDTO == null)
            throw new CorporateSettlementInstanceRequiredFieldsAbsentException(BAD_REQUEST_MESSAGE.formatted(""));
        if (requestBodyDTO.getProductType() == null)
            throw new CorporateSettlementInstanceRequiredFieldsAbsentException(BAD_REQUEST_MESSAGE.formatted("productType"));
        if (requestBodyDTO.getProductCode() == null)
            throw new CorporateSettlementInstanceRequiredFieldsAbsentException(BAD_REQUEST_MESSAGE.formatted("productCode"));
        if (requestBodyDTO.getRegisterType() == null)
            throw new CorporateSettlementInstanceRequiredFieldsAbsentException(BAD_REQUEST_MESSAGE.formatted("registerType"));
        if (requestBodyDTO.getMdmCode() == null)
            throw new CorporateSettlementInstanceRequiredFieldsAbsentException(BAD_REQUEST_MESSAGE.formatted("mdmCode"));
        if (requestBodyDTO.getContractNumber() == null)
            throw new CorporateSettlementInstanceRequiredFieldsAbsentException(BAD_REQUEST_MESSAGE.formatted("contractNumber"));
        if (requestBodyDTO.getContractDate() == null)
            throw new CorporateSettlementInstanceRequiredFieldsAbsentException(BAD_REQUEST_MESSAGE.formatted("contractDate"));
        if (requestBodyDTO.getPriority() == null)
            throw new CorporateSettlementInstanceRequiredFieldsAbsentException(BAD_REQUEST_MESSAGE.formatted("priority"));
        if (requestBodyDTO.getContractId() == null)
            throw new CorporateSettlementInstanceRequiredFieldsAbsentException(BAD_REQUEST_MESSAGE.formatted("contractId"));
        if (requestBodyDTO.getBranchCode() == null)
            throw new CorporateSettlementInstanceRequiredFieldsAbsentException(BAD_REQUEST_MESSAGE.formatted("BranchCode"));
        if (requestBodyDTO.getIsoCurrencyCode() == null)
            throw new CorporateSettlementInstanceRequiredFieldsAbsentException(BAD_REQUEST_MESSAGE.formatted("IsoCurrencyCode"));
        if (requestBodyDTO.getUrgencyCode() == null)
            throw new CorporateSettlementInstanceRequiredFieldsAbsentException(BAD_REQUEST_MESSAGE.formatted("urgencyCode"));

        if (false) throw new CorporateSettlementInstanceNotFoundException("TEST");
        return new ResponseEntity<>(new CorporateSettlementInstanceResponseDTO(
                new DataRecord(
                        "33",
                        List.of("33"),
                        List.of("33")
                )
        ), HttpStatus.OK);
    }
}
