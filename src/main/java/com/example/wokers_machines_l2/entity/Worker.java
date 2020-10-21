package com.example.wokers_machines_l2.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(schema = "public", name = "worker")
public class Worker {
    private int personnelNumber;
    private String fullName;
    private int category;
    private Machine machine;
    public Worker() {
    }

    @Id
    @Column(name = "personnel_number", nullable = false)
    public int getPersonnelNumber() {
        return personnelNumber;
    }

    public void setPersonnelNumber(int personnelNumber) {
        this.personnelNumber = personnelNumber;
    }

    @Column(name = "full_name", nullable = false, length = 40)
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Column(name = "category", nullable = false)
    public int getCategory() { return category; }

    public void setCategory(int category) { this.category = category; }

    @ManyToOne(fetch = FetchType.EAGER, optional = false, cascade = {CascadeType.ALL})
    @JoinColumn(name = "mashine_id")

    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Worker worker = (Worker) o;
        return personnelNumber == worker.personnelNumber &&
                Objects.equals(fullName, worker.fullName) &&
                category == worker.category;
    }

}
