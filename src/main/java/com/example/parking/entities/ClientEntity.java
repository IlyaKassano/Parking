package com.example.parking.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "client", schema = "parking", catalog = "")
public class ClientEntity {
    private int idClient;
    private String firstName;
    private String lastName;
    private String secondName;
    private String telephone;
    private Collection<ParkingEntity> parkingsByIdClient;

    @Id
    @Column(name = "id_client", nullable = false)
    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    @Basic
    @Column(name = "first_name", nullable = false, length = 45)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "last_name", nullable = false, length = 45)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "second_name", nullable = true, length = 45)
    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    @Basic
    @Column(name = "telephone", nullable = true, length = 45)
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientEntity that = (ClientEntity) o;
        return idClient == that.idClient &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(secondName, that.secondName) &&
                Objects.equals(telephone, that.telephone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idClient, firstName, lastName, secondName, telephone);
    }

    @OneToMany(mappedBy = "clientByIdClient")
    public Collection<ParkingEntity> getParkingsByIdClient() {
        return parkingsByIdClient;
    }

    public void setParkingsByIdClient(Collection<ParkingEntity> parkingsByIdClient) {
        this.parkingsByIdClient = parkingsByIdClient;
    }
}
