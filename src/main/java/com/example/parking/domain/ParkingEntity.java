package com.example.parking.domain;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "parking", schema = "parking")
@IdClass(ParkingEntityPK.class)
public class ParkingEntity {
    private int idClient;
    private int idCar;
    private int idLot;
    private int lotItem;
    private Timestamp dateParking;
    private Timestamp dateDepart;
    private byte paid;

    @Id
    @Column(name = "id_client", nullable = false)
    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    @Id
    @Column(name = "id_car", nullable = false)
    public int getIdCar() {
        return idCar;
    }

    public void setIdCar(int idCar) {
        this.idCar = idCar;
    }

    @Id
    @Column(name = "id_lot", nullable = false)
    public int getIdLot() {
        return idLot;
    }

    public void setIdLot(int idLot) {
        this.idLot = idLot;
    }

    @Id
    @Column(name = "lot_item", nullable = false)
    public int getLotItem() {
        return lotItem;
    }

    public void setLotItem(int lotItem) {
        this.lotItem = lotItem;
    }

    @Basic
    @Column(name = "date_parking", nullable = false)
    public Timestamp getDateParking() {
        return dateParking;
    }

    public void setDateParking(Timestamp dateParking) {
        this.dateParking = dateParking;
    }

    @Basic
    @Column(name = "date_depart", nullable = true)
    public Timestamp getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(Timestamp dateDepart) {
        this.dateDepart = dateDepart;
    }

    @Basic
    @Column(name = "paid", nullable = false)
    public byte getPaid() {
        return paid;
    }

    public void setPaid(byte paid) {
        this.paid = paid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ParkingEntity that = (ParkingEntity) o;

        if (idClient != that.idClient) return false;
        if (idCar != that.idCar) return false;
        if (idLot != that.idLot) return false;
        if (lotItem != that.lotItem) return false;
        if (paid != that.paid) return false;
        if (dateParking != null ? !dateParking.equals(that.dateParking) : that.dateParking != null) return false;
        if (dateDepart != null ? !dateDepart.equals(that.dateDepart) : that.dateDepart != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idClient;
        result = 31 * result + idCar;
        result = 31 * result + idLot;
        result = 31 * result + lotItem;
        result = 31 * result + (dateParking != null ? dateParking.hashCode() : 0);
        result = 31 * result + (dateDepart != null ? dateDepart.hashCode() : 0);
        result = 31 * result + (int) paid;
        return result;
    }
}
