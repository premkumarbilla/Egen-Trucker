package IO.Egen.respository;

import IO.Egen.entity.vehicleInfo;
import org.springframework.data.repository.CrudRepository;

public interface vehicleRepository extends CrudRepository<vehicleInfo, String> {

}
