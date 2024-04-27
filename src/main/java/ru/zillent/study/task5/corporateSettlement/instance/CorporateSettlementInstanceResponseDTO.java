package ru.zillent.study.task5.corporateSettlement.instance;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CorporateSettlementInstanceResponseDTO {
    DataRecord data;

//    Response.Body
//    {
//        "data": {
//        "instanceId": "string", 	// Идентификатор экземпляра продукта, при Status <> 200 OK может быть NULL
//                "registerId": [		// Идентификатор продуктового регистра, массив, при Status <> 200 OK может быть пуст
//        "registerId1", … " registerIdN"
//],
//        "supplementaryAgreementId": [ //ID доп.соглашения, при Status <> 200 OK может быть пуст
//        " supplementaryAgreementId1 ", … " supplementaryAgreementIdN"
//]
//    }
//    }
}

record DataRecord(String instanceId, List<String> registerId, List<String> supplementaryAgreementId) {};