package co.mercy.cloudvendorcruddemo.service;

import co.mercy.cloudvendorcruddemo.CloudVendorApplication;
import co.mercy.cloudvendorcruddemo.model.CloudVendor;

import java.util.List;

public interface CloudVendorService {
    public String createCloudVendor(CloudVendor cloudVendor);
    public String updateCloudVendor(CloudVendor cloudVendor);
    public String deleteCloudVendor(String cloudVendorId);
    public CloudVendor getCloudVendor(String cloudVendorId);
    public List<CloudVendor> getAllCloudVendors();
    public List<CloudVendor> getVendorByVendorName(String vendorName);
    public List<CloudVendor> getVendorByAddress(String vendorAddress);
}
