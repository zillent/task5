package ru.zillent.study.task5.corporateSettlement.instance;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class CorporateSettlementInstanceControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    CorporateSettlementInstanceService corporateSettlementInstanceService;

    Integer instanceId;
    private String registryTypeCode = "";
    private String accountType = "";
    private String currencyCode = "";
    private String branchCode = "";
    private String priorityCode = "";
    private String mdmCode = "";
    private String clientCode = "";
    private String trainRegion = "";
    private String counter = "";
    private String salesCode = "";

    @Test
    public void postCreateSuccessTest() throws Exception, CorporateSettlementInstanceBadRequestException, CorporateSettlementInstanceNotFoundException {
        CorporateSettlementInstanceDTO requestBodyDTO = new CorporateSettlementInstanceDTO();
        CorporateSettlementInstanceResponseDTO responseDTO = new CorporateSettlementInstanceResponseDTO(new DataRecord("34", List.of(), List.of()));
        ObjectMapper objectMapper = new ObjectMapper();
        doReturn(new ResponseEntity<>(responseDTO, HttpStatus.OK)).when(corporateSettlementInstanceService).createInstance(requestBodyDTO);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/corporate-settlement-instance/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(requestBodyDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.instanceId").exists())
                .andReturn();
        Assertions.assertEquals("{\"data\":{\"instanceId\":\"34\",\"registerId\":[],\"supplementaryAgreementId\":[]}}",mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void postCreateRequiredFieldsAbsentExceptionTest() throws Exception, CorporateSettlementInstanceBadRequestException, CorporateSettlementInstanceNotFoundException {
        CorporateSettlementInstanceDTO requestBodyDTO = new CorporateSettlementInstanceDTO();
        CorporateSettlementInstanceResponseDTO responseDTO = new CorporateSettlementInstanceResponseDTO(new DataRecord("34", List.of(), List.of()));
        ObjectMapper objectMapper = new ObjectMapper();
        when(corporateSettlementInstanceService.createInstance(any())).thenThrow(
                new CorporateSettlementInstanceBadRequestException("TEST REQUIRED FIELDS EXCEPTION MSG")
        );
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/corporate-settlement-instance/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(requestBodyDTO)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();
        Assertions.assertEquals("TEST REQUIRED FIELDS EXCEPTION MSG", mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void postCreateNotFoundExceptionTest() throws Exception, CorporateSettlementInstanceBadRequestException, CorporateSettlementInstanceNotFoundException {
        CorporateSettlementInstanceDTO requestBodyDTO = new CorporateSettlementInstanceDTO();
        CorporateSettlementInstanceResponseDTO responseDTO = new CorporateSettlementInstanceResponseDTO(new DataRecord("34", List.of(), List.of()));
        ObjectMapper objectMapper = new ObjectMapper();
        when(corporateSettlementInstanceService.createInstance(any())).thenThrow(
                new CorporateSettlementInstanceNotFoundException("TEST NOT FOUND EXCEPTION MSG")
        );
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/corporate-settlement-instance/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(requestBodyDTO)))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn();
        Assertions.assertEquals("TEST NOT FOUND EXCEPTION MSG", mvcResult.getResponse().getContentAsString());
    }
}
