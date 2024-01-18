package co.mercy.cloudvendorcruddemo.repository;

import co.mercy.cloudvendorcruddemo.model.CloudVendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CloudVendorRepository extends JpaRepository<CloudVendor, String> {
    // custom methods go here
    List<CloudVendor> findByVendorName(String vendorName);

    // JPQL
     @Query("select vi from cloud_vendors_info vi where vi.VendorAddress=?1")
     List<CloudVendor> findVendorByAddress(String vendorAddress);

    // Native Query
    // @Query(value="select * from cloud_vendors_info vi where vi.VendorAddress=?1", nativeQuery = true)
    // List<CloudVendor> findVendorByAddress(String vendorAddress);
}
