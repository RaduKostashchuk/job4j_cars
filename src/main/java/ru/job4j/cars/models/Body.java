package ru.job4j.cars.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Body {
    @Id
    @GeneratedValue
    private int id;

    private String type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Body body = (Body) o;
        return Objects.equals(id, body.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
