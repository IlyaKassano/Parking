package com.example.parking.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class ParkingEntityPK implements Serializable {
    private int idClient;
    private int idCar;
    private int idLot;
    private int lotItem;

    @Column(name = "id_client", nullable = false)
    @Id
    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    @Column(name = "id_car", nullable = false)
    @Id
    public int getIdCar() {
        return idCar;
    }

    public void setIdCar(int idCar) {
        this.idCar = idCar;
    }

    @Column(name = "id_lot", nullable = false)
    @Id
    public int getIdLot() {
        return idLot;
    }

    public void setIdLot(int idLot) {
        this.idLot = idLot;
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

        if (idClient != that.idClient) return false;
        if (idCar != that.idCar) return false;
        if (idLot != that.idLot) return false;
        if (lotItem != that.lotItem) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idClient;
        result = 31 * result + idCar;
        result = 31 * result + idLot;
        result = 31 * result + lotItem;
        return result;
    }
}
