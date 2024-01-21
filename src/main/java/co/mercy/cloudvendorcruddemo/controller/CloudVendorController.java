package co.mercy.cloudvendorcruddemo.controller;

import co.mercy.cloudvendorcruddemo.model.CloudVendor;
import co.mercy.cloudvendorcruddemo.response.ResponseHandler;
import co.mercy.cloudvendorcruddemo.service.CloudVendorService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cloudvendor/v1")
public class CloudVendorController {
    private static final Logger logInfo = LoggerFactory.getLogger(CloudVendorController.class);

    CloudVendorService cloudVendorService;

    public CloudVendorController(CloudVendorService cloudVendorService) {
        this.cloudVendorService = cloudVendorService;
    }

    @GetMapping("/id/{vendorId}")
    @ApiOperation(value = "cloud vendor id", notes = "Gets cloud vendor information",
    response = ResponseEntity.class)
    public ResponseEntity<Object> getCloudVendorDetails(@PathVariable("vendorId") String vendorId) {
        logInfo.info("Fetching Cloud Vendor Details with ID " + vendorId + " ...");
        logInfo.debug("Entering DEBUG log");
        logInfo.trace("Entering TRACE log");
        return ResponseHandler.responseBuilder(HttpStatus.OK, cloudVendorService.getCloudVendor(vendorId));
    }

    @GetMapping("/all")
    public ResponseEntity<Object> getAllCloudVendorDetails() {
        return ResponseHandler.responseBuilder(HttpStatus.OK, cloudVendorService.getAllCloudVendors());
    }

    @PostMapping
    public String createCloudVendor(@RequestBody CloudVendor cloudVendor){
        cloudVendorService.createCloudVendor(cloudVendor);
        return "Cloud Vendor Created Successfully";
    }

    @PutMapping
    public String updateCloudVendor(@RequestBody CloudVendor cloudVendor){
        cloudVendorService.updateCloudVendor(cloudVendor);
        return "Cloud Vendor Updated Successfully";
    }

    @DeleteMapping("{vendorId}")
    public String deleteVendor(@PathVariable("vendorId") String vendorId){
        cloudVendorService.deleteCloudVendor(vendorId);
        return "Cloud Vendor Deleted Successfully";
    }

    @GetMapping("/name/{vendorName}")
    public ResponseEntity<Object> getVendorByName(@PathVariable("vendorName") String vendorName){
        return ResponseHandler.responseBuilder(HttpStatus.OK, cloudVendorService.getVendorByVendorName(vendorName));
    }

    @GetMapping("/all/address/{vendorAddress}")
    public ResponseEntity<Object> getVendorByAddress(@PathVariable("vendorAddress") String vendorAddress){
        return ResponseHandler.responseBuilder(HttpStatus.OK, cloudVendorService.getVendorByAddress(vendorAddress));
    }
}
