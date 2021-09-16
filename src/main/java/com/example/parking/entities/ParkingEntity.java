package com.example.parking.entities;

import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "parking", schema = "parking")
public class ParkingEntity {
    private int idParking;
    private int lotItem;
    private LocalDateTime dateParking;
    private LocalDateTime dateDepart;
    private byte paid;
    private ClientEntity clientByIdClient;
    private AutoEntity autoByIdCar;
    private ParkingLotEntity parkingLotByIdLot;

    //TODO Поле расчета стоимости
    @Formula(value = "lot_item * 2")
    public int hours;

    @Id
    @Column(name = "id_parking", nullable = false)
    public int getIdParking() {
        return idParking;
    }

    public void setIdParking(int idParking) {
        this.idParking = idParking;
    }

    @Basic
    @Column(name = "lot_item", nullable = false)
    public int getLotItem() {
        return lotItem;
    }

    public void setLotItem(int lotItem) {
        this.lotItem = lotItem;
    }

    @Basic
    @Column(name = "date_parking", nullable = false)
    public LocalDateTime getDateParking() {
        return dateParking;
    }

    public void setDateParking(LocalDateTime dateParking) {
        this.dateParking = dateParking;
    }

    @Basic
    @Column(name = "date_depart", nullable = true)
    public LocalDateTime getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(LocalDateTime dateDepart) {
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
        return idParking == that.idParking &&
                lotItem == that.lotItem &&
                paid == that.paid &&
                Objects.equals(dateParking, that.dateParking) &&
                Objects.equals(dateDepart, that.dateDepart);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idParking, lotItem, dateParking, dateDepart, paid);
    }

    @ManyToOne
    @JoinColumn(name = "id_client", referencedColumnName = "id_client", nullable = false)
    public ClientEntity getClientByIdClient() {
        return clientByIdClient;
    }

    public void setClientByIdClient(ClientEntity clientByIdClient) {
        this.clientByIdClient = clientByIdClient;
    }

    @ManyToOne
    @JoinColumn(name = "id_car", referencedColumnName = "id_auto", nullable = false)
    public AutoEntity getAutoByIdCar() {
        return autoByIdCar;
    }

    public void setAutoByIdCar(AutoEntity autoByIdCar) {
        this.autoByIdCar = autoByIdCar;
    }

    @ManyToOne
    @JoinColumn(name = "id_lot", referencedColumnName = "id_lot", nullable = false)
    public ParkingLotEntity getParkingLotByIdLot() {
        return parkingLotByIdLot;
    }

    public void setParkingLotByIdLot(ParkingLotEntity parkingLotByIdLot) {
        this.parkingLotByIdLot = parkingLotByIdLot;
    }

}
