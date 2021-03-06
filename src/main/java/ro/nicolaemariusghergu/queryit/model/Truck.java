package ro.nicolaemariusghergu.queryit.model;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "trucks")
public class Truck implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotFound(action = NotFoundAction.IGNORE)
    @Column(name = "truck_id", nullable = false)
    private Long id;

    @Column(name = "serial_number", nullable = false)
    private String serialNumber;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Truck truck = (Truck) o;
        return id != null && Objects.equals(id, truck.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
