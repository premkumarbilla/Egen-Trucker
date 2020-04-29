package IO.Egen.service;

import IO.Egen.entity.*;
import IO.Egen.exception.ResourceNotFoundException;
import IO.Egen.respository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleServiceImplementation implements VehicleService {

    @Autowired
    VehicleRepository vehicles;

    @Autowired
    updateRepository readings;

    @Autowired
    tireRepository tires;

    @Autowired
    alertRepository alerts;

    @Transactional(readOnly = true)
    public List<vehicleInfo> findAll() {
        return (List<vehicleInfo>) vehicles.findAll();
    }

    @Transactional(readOnly = true)
    public vehicleInfo findById(String ID) {
        Optional<vehicleInfo> existing = vehicles.findById(ID);
        if (!existing.isPresent()) {
            throw new ResourceNotFoundException("Employee with id " + ID + " doesn't exist.");
        }
        return existing.get();
    }

    @Transactional
    public List<vehicleUpdate> findGeoLocation(String vin) {
        Optional<List<vehicleUpdate>> existing = readings.findGeoLocation(vin);
        if (!existing.isPresent()){
            throw new ResourceNotFoundException("Vehicle with vin " + vin + " doesn't exist.");
        }
        return existing.get();
    }

    @Transactional(readOnly = true)
    public List<alertInfo> findHighAlerts(){
            return alerts.findHighAlerts();
    }

    @Transactional(readOnly = true)
    public List<alertInfo> findVehicleAlerts(String vin){
        return alerts.findVehicleAlerts(vin);
    }

    @Transactional
    public vehicleInfo update(vehicleInfo vehicleInfo) {
        Optional<IO.Egen.entity.vehicleInfo> existing = vehicles.findById(vehicleInfo.getVin());
        if(existing.isPresent()) {
            return null;
        }
        return vehicles.save(vehicleInfo);
    }


    @Transactional
    public vehicleUpdate create(vehicleUpdate vehicleUpdate) {
        Optional<vehicleInfo> existing = vehicles.findById(vehicleUpdate.getVin());
        if(!existing.isPresent()){
            throw new ResourceNotFoundException(" Vehicle with vin "+ vehicleUpdate.getVin() + " is not in our records");
        }
        return readings.save(vehicleUpdate);
    }

    @Transactional
    public vehicleTirePressure create(vehicleTirePressure pressure) {
        Optional<vehicleInfo> existing = vehicles.findById(pressure.getVin());
        if(!existing.isPresent()){
            throw new ResourceNotFoundException(" Vehicle with vin "+ pressure.getVin() + " is not in our records");
        }
        return tires.save(pressure);
    }


    public void throwAlerts(vehicleUpdate vehicle){
        Optional<vehicleInfo> vehicleInfo = vehicles.findById(vehicle.getVin());
        Optional<vehicleTirePressure> tirePressure = tires.findById(new vehicleId(vehicle.getVin(),vehicle.getTimestamp()));

        if(vehicleInfo.isPresent()){
            if(vehicleInfo.get().getRedlineRpm() < vehicle.getEngineRpm())
            {
                alertInfo alertInfo = new alertInfo(vehicle.getVin(),vehicle.getTimestamp());
                alertInfo.setPriority("HIGH");
                alertInfo.setAlertType("RPM");
                alertInfo.setMessage(" Vehicle moving at high RPM");
                alerts.save(alertInfo);
            }
            if(vehicleInfo.get().getMaxFuelVolume()*0.1 > vehicle.getFuelVolume()){
                alertInfo alertInfo = new alertInfo(vehicle.getVin(),vehicle.getTimestamp());
                alertInfo.setAlertType("FUEL");
                alertInfo.setPriority("MEDIUM");
                alertInfo.setMessage(" LOW VOLUME ALERT");
                alerts.save(alertInfo);
            }

            if(tirePressure.get().getRearLeft() < 32 || tirePressure.get().getRearLeft() > 36 || tirePressure.get().getRearRight() < 32 || tirePressure.get().getRearRight() > 36  ||
                    tirePressure.get().getFrontLeft() < 32 || tirePressure.get().getFrontLeft() > 36 || tirePressure.get().getFrontRight() < 32 || tirePressure.get().getFrontRight() > 36 )
            {
                alertInfo alertInfo = new alertInfo(vehicle.getVin(),vehicle.getTimestamp());
                alertInfo.setPriority("LOW");
                alertInfo.setAlertType("TIRE");
                alertInfo.setMessage(" PLEASE CHECK TIRE PRESSURE");
                alerts.save(alertInfo);
            }

            if(vehicle.isEngineCoolantLow() || vehicle.isCheckEngineLightOn()) {
                alertInfo alertInfo = new alertInfo(vehicle.getVin(),vehicle.getTimestamp());
                alertInfo.setPriority("LOW");
                alertInfo.setAlertType("ENGINE");
                alertInfo.setMessage(" PLEASE CHECK ENGINE-COOLANT and ENGINE CONDITION");
                alerts.save(alertInfo);
            }
        }

    }



}
