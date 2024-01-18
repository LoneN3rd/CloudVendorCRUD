package co.mercy.cloudvendorcruddemo.controller;

import co.mercy.cloudvendorcruddemo.CloudVendorApplication;
import co.mercy.cloudvendorcruddemo.model.CloudVendor;
import co.mercy.cloudvendorcruddemo.service.CloudVendorService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CloudVendorController.class)
class CloudVendorControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CloudVendorService cloudVendorService;
    CloudVendor cloudVendorOne;
    CloudVendor cloudVendorTwo;
    CloudVendor cloudVendorThree;
    List<CloudVendor> cloudVendorList = new ArrayList<>();
    List<CloudVendor> cloudVendorListName = new ArrayList<>();
    List<CloudVendor> cloudVendorListAddress = new ArrayList<>();

    @BeforeEach
    void setUp() {
        cloudVendorOne = new CloudVendor("1", "Amazon", "CA", "12345");
        cloudVendorTwo = new CloudVendor("2", "GCP", "WA", "67890");
        cloudVendorThree = new CloudVendor("3", "Microsoft", "WA", "3456");
        cloudVendorList.add(cloudVendorOne);
        cloudVendorList.add(cloudVendorTwo);
        cloudVendorList.add(cloudVendorThree);
        cloudVendorListName.add(cloudVendorTwo);
        cloudVendorListAddress.add(cloudVendorTwo);
        cloudVendorListAddress.add(cloudVendorThree);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testGetCloudVendorDetails() throws Exception {
        when(cloudVendorService.getCloudVendor("1")).thenReturn(cloudVendorOne);
        this.mockMvc.perform(get("/cloudvendor/v1/id/" + "1")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void testGetAllCloudVendorDetails() throws Exception {
        when(cloudVendorService.getAllCloudVendors()).thenReturn(cloudVendorList);
        this.mockMvc.perform(get("/cloudvendor/v1/all"))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void testDeleteVendor() throws Exception {
        when(cloudVendorService.deleteCloudVendor("1"))
                .thenReturn("Success");
        this.mockMvc.perform(delete("/cloudvendor/v1/" + "1"))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void testCreateCloudVendor() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(cloudVendorOne);

        when(cloudVendorService.createCloudVendor(cloudVendorOne))
                .thenReturn("Success");
        this.mockMvc.perform(post("/cloudvendor/v1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void testUpdateCloudVendor() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(cloudVendorOne);

        when(cloudVendorService.updateCloudVendor(cloudVendorOne))
                .thenReturn("Success");
        this.mockMvc.perform(put("/cloudvendor/v1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void testGetVendorByName() throws Exception {
        when(cloudVendorService.getVendorByVendorName("GCP")).thenReturn(cloudVendorListName);
        this.mockMvc.perform(get("/cloudvendor/v1/name/gcp"))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void testGetVendorByAddress() throws Exception {
        when(cloudVendorService.getVendorByAddress("CA")).thenReturn(cloudVendorListAddress);
        this.mockMvc.perform(get("/cloudvendor/v1/all/address/CA"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}