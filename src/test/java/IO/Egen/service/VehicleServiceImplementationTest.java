package IO.Egen.service;

import IO.Egen.entity.vehicleInfo;
import IO.Egen.respository.alertRepository;
import IO.Egen.respository.tireRepository;
import IO.Egen.respository.updateRepository;
import IO.Egen.respository.vehicleRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
public class VehicleServiceImplementationTest {

    @TestConfiguration
    static class VehicleServiceTestConfiguration{
        @Bean
        @Qualifier("test")
        public vehicleService getVehicle(){
            return new VehicleServiceImplementation();
        }
    }

    public List<vehicleInfo> vehicleRecord;
    @Before
    public void setup(){
        System.out.println("before");
        vehicleInfo vehicle = new vehicleInfo();
        vehicle.setLastServiceDate("2017-05-25T17:31:25.268Z");
        vehicle.setVin("1HGCR2F3XFA027534");
        vehicle.setMake( "HONDA");
        vehicle.setModel("ACCORD");
        vehicle.setYear(2015);
        vehicle.setRedlineRpm(5500);
        vehicle.setMaxFuelVolume(15);

        vehicleRecord = Collections.singletonList(vehicle);

        Mockito.when(vehicleService.findAll())
                .thenReturn(vehicleRecord);


    }

    @After
    public void clean(){
        System.out.println("after");
    }

    @Autowired
    private vehicleService vehicleService;

    @MockBean
    private alertRepository alerts;
    @MockBean
    private tireRepository tires;
    @MockBean
    private updateRepository updates;
    @MockBean
    private vehicleRepository vehicles;

    @Test
    public void findAll() {
        List<vehicleInfo> result = vehicleService.findAll();
        Assert.assertEquals("Vehicle records shoud match",vehicleRecord,result);
    }

    @Test
    public void findById() {

    }

    @Test
    public void findGeoLocation() {
    }

    @Test
    public void findHighAlerts() {
    }

    @Test
    public void findVehicleAlerts() {
    }

    @Test
    public void update() {
    }

    @Test
    public void create() {
    }

    @Test
    public void testCreate() {
    }

    @Test
    public void throwAlerts() {
    }
}