package IO.Egen.respository;

import IO.Egen.entity.vehicleId;
import IO.Egen.entity.vehicleTirePressure;
import org.springframework.data.repository.CrudRepository;

public interface tireRepository extends CrudRepository<vehicleTirePressure, vehicleId> {
}
