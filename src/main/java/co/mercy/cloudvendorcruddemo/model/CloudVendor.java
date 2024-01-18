package co.mercy.cloudvendorcruddemo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity(name = "cloud_vendors_info")
@Table(name = "cloud_vendors_info")
public class CloudVendor {
    @Id
    String VendorId;
    String vendorName;
    String VendorAddress;
    String VendorPhone;

    public CloudVendor() {
    }

    public CloudVendor(String vendorId, String vendorName, String vendorAddress, String vendorPhone) {
        VendorId = vendorId;
        this.vendorName = vendorName;
        VendorAddress = vendorAddress;
        VendorPhone = vendorPhone;
    }

    public String getVendorId() {
        return VendorId;
    }

    public void setVendorId(String vendorId) {
        VendorId = vendorId;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getVendorAddress() {
        return VendorAddress;
    }

    public void setVendorAddress(String vendorAddress) {
        VendorAddress = vendorAddress;
    }

    public String getVendorPhone() {
        return VendorPhone;
    }

    public void setVendorPhone(String vendorPhone) {
        VendorPhone = vendorPhone;
    }
}
