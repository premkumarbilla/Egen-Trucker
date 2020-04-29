package IO.Egen.respository;

import IO.Egen.entity.alertId;
import IO.Egen.entity.alertInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface alertRepository extends CrudRepository<alertInfo, alertId> {
    @Query(value = "SELECT * FROM alert_info where Priority = 'HIGH' order by timestamp DESC ",nativeQuery = true)
    List<alertInfo> findHighAlerts();

    @Query(value = "SELECT * FROM alert_info where vin =:vin ",nativeQuery = true)
    List<alertInfo> findVehicleAlerts(@Param("vin") String vin);
}
