package co.mercy.cloudvendorcruddemo.service.impl;

import co.mercy.cloudvendorcruddemo.exception.CloudVendorNotFoundException;
import co.mercy.cloudvendorcruddemo.model.CloudVendor;
import co.mercy.cloudvendorcruddemo.repository.CloudVendorRepository;
import co.mercy.cloudvendorcruddemo.service.CloudVendorService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class CloudVendorServiceImplTest {

    @Mock
    private CloudVendorRepository cloudVendorRepository;
    private CloudVendorService cloudVendorService;
    AutoCloseable autoCloseable; // release resources when exec is over
    CloudVendor cloudVendor;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        cloudVendorService = new CloudVendorServiceImpl(cloudVendorRepository);
        cloudVendor = new CloudVendor("1", "Amazon", "CA", "12345");
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void testCreateCloudVendor() {
        mock(CloudVendor.class);
        mock(CloudVendorRepository.class);
        when(cloudVendorRepository.save(cloudVendor)).thenReturn(cloudVendor);
        assertThat(cloudVendorService.createCloudVendor(cloudVendor)).isEqualTo("Success");
    }

    @Test
    void testUpdateCloudVendor() {
        mock(CloudVendor.class);
        mock(CloudVendorRepository.class);
        when(cloudVendorRepository.save(cloudVendor)).thenReturn(cloudVendor);
        assertThat(cloudVendorService.updateCloudVendor(cloudVendor)).isEqualTo("Success");
    }

    @Test
    void testGetCloudVendor() {
        mock(CloudVendor.class);
        mock(CloudVendorRepository.class);
        when(cloudVendorRepository.findById("1")).thenReturn(Optional.ofNullable(cloudVendor));
        assertThat(cloudVendorService.getCloudVendor("1").getVendorName()).isEqualTo(cloudVendor.getVendorName());
        CloudVendorNotFoundException thrown = assertThrows(CloudVendorNotFoundException.class, () -> {
            cloudVendorService.getCloudVendor("2");
        }, "Requested vendor not found");

        assertEquals("Requested vendor not found", thrown.getMessage());
    }

    @Test
    void testGetAllCloudVendors() {
        mock(CloudVendor.class);
        mock(CloudVendorRepository.class);
        when(cloudVendorRepository.findAll()).thenReturn(
                new ArrayList<CloudVendor>(Collections.singletonList(cloudVendor))
        );
        assertThat(cloudVendorService.getAllCloudVendors().get(0).getVendorId()).isEqualTo(cloudVendor.getVendorId());

        when(cloudVendorRepository.findAll()).thenReturn(Collections.emptyList());

        CloudVendorNotFoundException thrown = assertThrows(CloudVendorNotFoundException.class, () -> {
            cloudVendorService.getAllCloudVendors();
        }, "No vendors found");

        assertEquals("No vendors found", thrown.getMessage());
    }

    @Test
    void testGetByVendorName() {
        mock(CloudVendor.class);
        mock(CloudVendorRepository.class);
        when(cloudVendorRepository.findByVendorName("Amazon")).thenReturn(
                new ArrayList<CloudVendor>(Collections.singletonList(cloudVendor))
        );
        assertThat(cloudVendorService.getVendorByVendorName("Amazon").get(0).getVendorId())
                .isEqualTo(cloudVendor.getVendorId());
    }

    @Test
    void testDeleteCloudVendor() {
        mock(CloudVendor.class);
        mock(CloudVendorRepository.class, Mockito.CALLS_REAL_METHODS);
        doAnswer(Answers.CALLS_REAL_METHODS).when(cloudVendorRepository).deleteById(any());
        assertThat(cloudVendorService.deleteCloudVendor("1")).isEqualTo("Success");
    }

    @Test
    void testGetVendorByAddress() {
        mock(CloudVendor.class);
        mock(CloudVendorRepository.class);
        when(cloudVendorRepository.findVendorByAddress("CA")).thenReturn(
                new ArrayList<CloudVendor>(Collections.singletonList(cloudVendor))
        );
        assertThat(cloudVendorService.getVendorByAddress("CA").get(0).getVendorAddress())
                .isEqualTo(cloudVendor.getVendorAddress());
    }
}