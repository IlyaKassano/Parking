package com.example.parking.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "auto", schema = "parking", catalog = "")
public class AutoEntity {
    private int idAuto;
    private String brand;
    private String model;
    private Collection<ParkingEntity> parkingsByIdAuto;

    @Id
    @Column(name = "id_auto", nullable = false)
    public int getIdAuto() {
        return idAuto;
    }

    public void setIdAuto(int idAuto) {
        this.idAuto = idAuto;
    }

    @Basic
    @Column(name = "brand", nullable = false, length = 50)
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Basic
    @Column(name = "model", nullable = false, length = 70)
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AutoEntity that = (AutoEntity) o;
        return idAuto == that.idAuto &&
                Objects.equals(brand, that.brand) &&
                Objects.equals(model, that.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAuto, brand, model);
    }

    @OneToMany(mappedBy = "autoByIdCar")
    public Collection<ParkingEntity> getParkingsByIdAuto() {
        return parkingsByIdAuto;
    }

    public void setParkingsByIdAuto(Collection<ParkingEntity> parkingsByIdAuto) {
        this.parkingsByIdAuto = parkingsByIdAuto;
    }
}
