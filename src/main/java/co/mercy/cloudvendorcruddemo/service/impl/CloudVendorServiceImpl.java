package co.mercy.cloudvendorcruddemo.service.impl;

import co.mercy.cloudvendorcruddemo.exception.CloudVendorNotFoundException;
import co.mercy.cloudvendorcruddemo.model.CloudVendor;
import co.mercy.cloudvendorcruddemo.repository.CloudVendorRepository;
import co.mercy.cloudvendorcruddemo.service.CloudVendorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CloudVendorServiceImpl implements CloudVendorService {
    CloudVendorRepository cloudVendorRepository;
    public CloudVendorServiceImpl(CloudVendorRepository cloudVendorRepository) {
        this.cloudVendorRepository = cloudVendorRepository;
    }

    @Override
    public String createCloudVendor(CloudVendor cloudVendor) {
        cloudVendorRepository.save(cloudVendor);
        return "Success";
    }

    @Override
    public String updateCloudVendor(CloudVendor cloudVendor) {
        cloudVendorRepository.save(cloudVendor);
        return "Success";
    }

    @Override
    public String deleteCloudVendor(String cloudVendorId) {
        cloudVendorRepository.deleteById(cloudVendorId);
        return "Success";
    }

    @Override
    public CloudVendor getCloudVendor(String cloudVendorId) {
        Optional<CloudVendor> vendor = cloudVendorRepository.findById(cloudVendorId);
        if(cloudVendorRepository.findById(cloudVendorId).isEmpty())
            throw new CloudVendorNotFoundException("Requested vendor not found");
        return vendor.get();
    }

    @Override
    public List<CloudVendor> getAllCloudVendors() {
        List<CloudVendor> vendors = cloudVendorRepository.findAll();
        if(vendors.isEmpty())
            throw new CloudVendorNotFoundException("No vendors found");
        return vendors;

    }

    @Override
    public List<CloudVendor> getVendorByVendorName(String vendorName) {
        return cloudVendorRepository.findByVendorName(vendorName);
    }

    @Override
    public List<CloudVendor> getVendorByAddress(String vendorAddress) {
        return cloudVendorRepository.findVendorByAddress(vendorAddress);
    }
}
