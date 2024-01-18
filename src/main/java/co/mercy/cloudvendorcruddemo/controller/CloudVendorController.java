package co.mercy.cloudvendorcruddemo.controller;

import co.mercy.cloudvendorcruddemo.model.CloudVendor;
import co.mercy.cloudvendorcruddemo.response.ResponseHandler;
import co.mercy.cloudvendorcruddemo.service.CloudVendorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cloudvendor/v1")
public class CloudVendorController {
    CloudVendorService cloudVendorService;
    //CloudVendor cloudVendor;

    public CloudVendorController(CloudVendorService cloudVendorService) {
        this.cloudVendorService = cloudVendorService;
    }

    @GetMapping("/id/{vendorId}")
    public ResponseEntity<Object> getCloudVendorDetails(@PathVariable("vendorId") String vendorId) {
        // return new CloudVendor("CV1", "Vendor 1", "Waiyaki Way", "0722000000");
        // return cloudVendor;
        return ResponseHandler.responseBuilder("Requested vendor details are given here", HttpStatus.OK, cloudVendorService.getCloudVendor(vendorId));
    }

    @GetMapping("/all")
    public List<CloudVendor> getAllCloudVendorDetails() {
        return cloudVendorService.getAllCloudVendors();
    }

    @PostMapping
    public String createCloudVendor(@RequestBody CloudVendor cloudVendor){
        //this.cloudVendor = cloudVendor;
        cloudVendorService.createCloudVendor(cloudVendor);
        return "Cloud Vendor Created Successfully";
    }

    @PutMapping
    public String updateCloudVendor(@RequestBody CloudVendor cloudVendor){
        //this.cloudVendor = cloudVendor;
        cloudVendorService.updateCloudVendor(cloudVendor);
        return "Cloud Vendor Updated Successfully";
    }

    @DeleteMapping("{vendorId}")
    public String deleteVendor(@PathVariable("vendorId") String vendorId){
        //this.cloudVendor = null;
        cloudVendorService.deleteCloudVendor(vendorId);
        return "Cloud Vendor Deleted Successfully";
    }

    @GetMapping("/name/{vendorName}")
    public List<CloudVendor> getVendorByName(@PathVariable("vendorName") String vendorName){
        return cloudVendorService.getVendorByVendorName(vendorName);
    }

    @GetMapping("/all/address/{vendorAddress}")
    public List<CloudVendor> getVendorByAddress(@PathVariable("vendorAddress") String vendorAddress){
        return cloudVendorService.getVendorByAddress(vendorAddress);
    }
}
