package co.mercy.cloudvendorcruddemo.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity(name = "cloud_vendors_info")
@Table(name = "cloud_vendors_info")
@ApiModel(description = "This table holds cloud vendors and their information")
public class CloudVendor {
    @Id
    @ApiModelProperty(notes = "This is a unique identifier for a cloud vendor")
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
