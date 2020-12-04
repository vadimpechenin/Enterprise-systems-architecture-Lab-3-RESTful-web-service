package com.example.wokers_machines_l2.repository;

import com.example.wokers_machines_l2.entity.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

//import java.util.List;
import java.util.Optional;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, Integer> {

    //List<Worker> findWorkerByFullName(String fullName); //метод поиска в базе данных по имени
    @Query("select t from Worker t where t.personnelNumber = :number")
    Optional<Worker> findByPersonnelNumber(@Param("number") int number);

}