package org.acme.entitites;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.acme.utils.Status;

import javax.persistence.Entity;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Person extends PanacheEntity {

    private String name;
    private Status status;

    public static Person findByName(String name){
        return find("name", name).firstResult();
    }

    public static List<Person> findActive() {
        return list("status", Status.ACTIVE);
    }


}
