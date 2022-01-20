package ru.job4j.cars.models;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
public class Advertisement {
    @Id
    @GeneratedValue
    private int id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date created = new Date(System.currentTimeMillis());

    private String description;

    private int price;

    private boolean sold;

    @ManyToOne(fetch = FetchType.LAZY)
    private Driver driver;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Car car;

    public static Advertisement of(String description, Driver driver, Car car, int price) {
        Advertisement advertisement = new Advertisement();
        advertisement.description = description;
        advertisement.driver = driver;
        advertisement.car = car;
        advertisement.price = price;
        return advertisement;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Advertisement that = (Advertisement) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Advertisement{"
                + "id=" + id
                + ", created=" + created
                + ", description='" + description + '\''
                + ", price=" + price
                + ", sold=" + sold
                + ", driver=" + driver
                + ", car=" + car
                + '}';
    }
}
