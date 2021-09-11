package com.example.parking.domain;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "parking_lot", schema = "parking")
public class ParkingLotEntity {
    private int idLot;
    private String address;
    private int numLots;
    private BigDecimal price;

    @Id
    @Column(name = "id_lot", nullable = false)
    public int getIdLot() {
        return idLot;
    }

    public void setIdLot(int idLot) {
        this.idLot = idLot;
    }

    @Basic
    @Column(name = "address", nullable = false, length = 45)
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

        if (idLot != that.idLot) return false;
        if (numLots != that.numLots) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idLot;
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + numLots;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }
}
