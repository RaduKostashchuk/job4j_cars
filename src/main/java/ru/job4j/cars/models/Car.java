package ru.job4j.cars.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Car {
    @Id
    @GeneratedValue
    private int id;

    @OneToOne(fetch = FetchType.LAZY)
    private Engine engine;

    @OneToOne(fetch = FetchType.LAZY)
    private Body body;

    @OneToOne(fetch = FetchType.LAZY)
    private Brand brand;

    @OneToOne(fetch = FetchType.LAZY)
    private Model model;

    @ManyToMany
    @JoinTable(name = "history_owner",
            joinColumns = {@JoinColumn(name = "car_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "driver_id", nullable = false, updatable = false)})
    private Set<Driver> drivers = new HashSet<>();

    public static Car of(Brand brand, Model model, Body body, Engine engine) {
        Car car = new Car();
        car.brand = brand;
        car.model = model;
        car.body = body;
        car.engine = engine;
        return car;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Set<Driver> getDrivers() {
        return drivers;
    }

    public void setDrivers(Set<Driver> drivers) {
        this.drivers = drivers;
    }

    public void addDriver(Driver driver) {
        drivers.add(driver);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Car car = (Car) o;
        return Objects.equals(id, car.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Car{"
                + "id=" + id
                + ", engine=" + engine
                + ", body=" + body
                + ", brand=" + brand
                + ", model=" + model
                + ", drivers=" + drivers
                + '}';
    }
}
