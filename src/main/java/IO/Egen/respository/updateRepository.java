package IO.Egen.respository;

import IO.Egen.entity.alertInfo;
import IO.Egen.entity.vehicleId;
import IO.Egen.entity.vehicleUpdate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface updateRepository extends CrudRepository<vehicleUpdate, vehicleId> {
    @Query(value = " select * from vehicle_update v where v.vin = :vin and TIMESTAMPDIFF(minute, current_timestamp, v.timestamp) < 30 ", nativeQuery = true)
    Optional<List<vehicleUpdate>> findGeoLocation(@Param("vin") String vin);
}
