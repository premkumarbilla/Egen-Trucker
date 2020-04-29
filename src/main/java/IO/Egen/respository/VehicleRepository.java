package IO.Egen.respository;

import IO.Egen.entity.vehicleInfo;
import org.springframework.data.repository.CrudRepository;

public interface VehicleRepository extends CrudRepository<vehicleInfo, String> {

}
