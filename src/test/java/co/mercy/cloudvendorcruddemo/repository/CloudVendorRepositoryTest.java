package co.mercy.cloudvendorcruddemo.repository;

import co.mercy.cloudvendorcruddemo.model.CloudVendor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CloudVendorRepositoryTest {

    @Autowired
    private CloudVendorRepository cloudVendorRepository;
    CloudVendor cloudVendor;

    @BeforeEach
    void setUp() {
        cloudVendor = new CloudVendor("CV1", "Amazon", "CA", "12345");
        cloudVendorRepository.save(cloudVendor);
    }

    @AfterEach
    void tearDown() {
        cloudVendor = null;
        cloudVendorRepository.deleteAll();
    }

    @Test
    void testFindByVendorName_Found() {
        List<CloudVendor> cloudVendorList = cloudVendorRepository.findByVendorName("Amazon");
        assertThat(cloudVendorList.get(0).getVendorId()).isEqualTo(cloudVendor.getVendorId());
    }

    @Test
    void testFindByVendorName_NotFound() {
        List<CloudVendor> cloudVendorList = cloudVendorRepository.findByVendorName("Micros");
        assertThat(cloudVendorList.isEmpty()).isTrue();
    }

    @Test
    void testFindVendorByAddress_Found() {
        List<CloudVendor> cloudVendorList = cloudVendorRepository.findVendorByAddress("CA");
        assertThat(cloudVendorList.get(0).getVendorAddress()).isEqualTo(cloudVendor.getVendorAddress());
    }

    @Test
    void testFindVendorByAddress_NotFound() {
        List<CloudVendor> cloudVendorList = cloudVendorRepository.findVendorByAddress("WA");
        assertThat(cloudVendorList.isEmpty()).isTrue();
    }
}