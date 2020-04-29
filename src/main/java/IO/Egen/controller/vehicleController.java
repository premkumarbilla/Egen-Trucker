package IO.Egen.controller;

import IO.Egen.entity.*;
import IO.Egen.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLOutput;
import java.util.List;
import java.util.Optional;
@CrossOrigin
@RestController
public class vehicleController {

    @Autowired
    VehicleService service;

    @GetMapping("/vehicles")
    //@RequestMapping(method = RequestMethod.GET, value = )
    public List<vehicleInfo> findAll(){
        return service.findAll();
    }
    @GetMapping("/vehicles/{id}")
    //@RequestMapping(method = RequestMethod.GET, value = )
    public vehicleInfo findByID(@PathVariable("id") String vin ){
        return service.findById(vin);
    }

    //@RequestMapping(method = RequestMethod.GET, value = "/alerts/high")
    @GetMapping("alerts/high")
    public List<alertInfo> findHighAlerts(){
        return service.findHighAlerts();
    }

    //@RequestMapping(method = RequestMethod.GET, value = "/alerts/{vin}")
    @GetMapping("alerts/{vin}")
    public List<alertInfo> findVehicleAlerts(@PathVariable("vin") String vin){
        return service.findVehicleAlerts(vin);
    }

    //@RequestMapping(method = RequestMethod.GET, value = "/geolocation/{vin}")
    @GetMapping("/geolocation/{vin}")
    public List<vehicleUpdate> findGeoLocation(@PathVariable("vin") String vin){
        return service.findGeoLocation(vin);
    }

//    @RequestMapping(method = RequestMethod.POST, value = "/readings",
//            consumes = MediaType.APPLICATION_JSON_VALUE,
//            produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value = "/readings", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public vehicleUpdate create(@RequestBody vehicleUpdate vehicle) {

            vehicleTirePressure pressure = new vehicleTirePressure();
            pressure.vin = vehicle.getVin();
            pressure.timestamp = vehicle.getTimestamp();
            pressure.rearRight = vehicle.tires.rearRight;
            pressure.frontRight = vehicle.tires.frontRight;
            pressure.rearLeft = vehicle.tires.rearLeft;
            pressure.frontLeft = vehicle.tires.frontLeft;
            service.create(pressure);
            service.throwAlerts(vehicle);
            return service.create(vehicle);

    }
//    @RequestMapping(method = RequestMethod.PUT, value = "/vehicles",
//            consumes = MediaType.APPLICATION_JSON_VALUE,
//            produces = MediaType.APPLICATION_JSON_VALUE)
    @PutMapping(value = "/vehicles",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<vehicleInfo> update(@RequestBody List<vehicleInfo> vehicles) {
        for(vehicleInfo vehicle : vehicles) {
            service.update(vehicle);
        }
        return vehicles;
    }

}
