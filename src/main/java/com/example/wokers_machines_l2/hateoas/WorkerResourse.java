package com.example.wokers_machines_l2.hateoas;

import com.example.wokers_machines_l2.entity.Machine;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.example.wokers_machines_l2.entity.Worker;
import com.example.wokers_machines_l2.repository.WorkerRepository;

import org.springframework.hateoas.RepresentationModel;

public class WorkerResourse extends RepresentationModel {

    @JsonProperty
    public int personnelNumber;
    public String fullName;
    public int category;
    public Object machine;

    public WorkerResourse(Worker model){
        personnelNumber = model.getPersonnelNumber();
        fullName = model.getFullName();
        category = model.getCategory();
        machine = model.getMachine();
    }
}
