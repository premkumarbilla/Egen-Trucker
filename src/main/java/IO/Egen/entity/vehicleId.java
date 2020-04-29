package IO.Egen.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;


public class vehicleId implements Serializable {

    String vin;
    String timestamp;

    public vehicleId(){}
    public vehicleId(String vin, String timestamp) {
        this.vin = vin;
        this.timestamp = timestamp;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        vehicleId that = (vehicleId) o;
        return vin.equals(that.vin) &&
                timestamp.equals(that.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vin, timestamp);
    }
}
