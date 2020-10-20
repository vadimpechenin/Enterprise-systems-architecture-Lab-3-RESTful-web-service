package com.example.wokers_machines_l2.repository;

import com.example.wokers_machines_l2.entity.Worker;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WorkerRepository extends CrudRepository<Worker, Integer> {

    List<Worker> findWorkerByFullName(String fullName); //метод поиска в базе данных по имени
}