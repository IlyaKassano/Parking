package com.example.parking.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class ParkingEntityPK implements Serializable {
    private int idParking;
    private int lotItem;

    @Column(name = "id_parking", nullable = false)
    @Id
    public int getIdParking() {
        return idParking;
    }

    public void setIdParking(int idParking) {
        this.idParking = idParking;
    }

    @Column(name = "lot_item", nullable = false)
    @Id
    public int getLotItem() {
        return lotItem;
    }

    public void setLotItem(int lotItem) {
        this.lotItem = lotItem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParkingEntityPK that = (ParkingEntityPK) o;
        return idParking == that.idParking &&
                lotItem == that.lotItem;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idParking, lotItem);
    }
}
