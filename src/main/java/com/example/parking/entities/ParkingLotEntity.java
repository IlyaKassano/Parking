package com.example.parking.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "parking_lot", schema = "parking", catalog = "")
public class ParkingLotEntity {
    private int idLot;
    private String name;
    private String address;
    private int numLots;
    private BigDecimal price;
    private Collection<ParkingEntity> parkingsByIdLot;

    @Id
    @Column(name = "id_lot", nullable = false)
    public int getIdLot() {
        return idLot;
    }

    public void setIdLot(int idLot) {
        this.idLot = idLot;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 60)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "address", nullable = false, length = 130)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "num_lots", nullable = false)
    public int getNumLots() {
        return numLots;
    }

    public void setNumLots(int numLots) {
        this.numLots = numLots;
    }

    @Basic
    @Column(name = "price", nullable = false, precision = 2)
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParkingLotEntity that = (ParkingLotEntity) o;
        return idLot == that.idLot &&
                numLots == that.numLots &&
                Objects.equals(name, that.name) &&
                Objects.equals(address, that.address) &&
                Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idLot, name, address, numLots, price);
    }

    @OneToMany(mappedBy = "parkingLotByIdLot")
    public Collection<ParkingEntity> getParkingsByIdLot() {
        return parkingsByIdLot;
    }

    public void setParkingsByIdLot(Collection<ParkingEntity> parkingsByIdLot) {
        this.parkingsByIdLot = parkingsByIdLot;
    }
}
