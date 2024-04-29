package ru.zillent.study.task5.corporateSettlement.account;

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

import static org.mockito.Mockito.doReturn;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class CorporateSettlementAccountControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    CorporateSettlementAccountService corporateSettlementAccountService;

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
    public void postCreateTest() throws Exception {
        CorporateSettlementAccountDTO account = new CorporateSettlementAccountDTO(
                instanceId,
                registryTypeCode,
                accountType,
                currencyCode,
                branchCode,
                priorityCode,
                mdmCode,
                clientCode,
                trainRegion,
                counter,
                salesCode
        );
        CorporateSettlementAccountResponseDTO responseDTO = new CorporateSettlementAccountResponseDTO(new AccountDataRecord("34"));
        ObjectMapper objectMapper = new ObjectMapper();
        doReturn(new ResponseEntity<>(objectMapper.writeValueAsString(responseDTO), HttpStatus.OK)).when(corporateSettlementAccountService).createAccount(account);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/corporate-settlement-account/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(account)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.accountId").exists())
                .andReturn();
        Assertions.assertEquals("{\"data\":{\"accountId\":\"34\"}}",mvcResult.getResponse().getContentAsString());
    }
}
