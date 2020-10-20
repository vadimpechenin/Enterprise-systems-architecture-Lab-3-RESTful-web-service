package com.example.wokers_machines_l2.controllers;


import com.example.wokers_machines_l2.entity.Machine;
import com.example.wokers_machines_l2.entity.Worker;
import com.example.wokers_machines_l2.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.wokers_machines_l2.exception.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/RESTapi/work/")
public class WorkersApiController {

    @Autowired
    WorkerRepository workerRepository;
    // Получить все записи
    @GetMapping("workers")
    private List<Worker> getWorkers() {
        return this.workerRepository.findAll();
    }

    // Получить запись по id
    @GetMapping("workers/{id}")
    public ResponseEntity<Worker> getWorkerById(@PathVariable(value = "personnelNumber") int workerId)
            throws NotFoundException {
        Worker worker = workerRepository.findById(workerId)
                .orElseThrow(() -> new NotFoundException("Рабочий не находится по этому id :: " + workerId));
        return ResponseEntity.ok().body(worker);
    }

    // Создать запись
    @PostMapping("workers")
    public Worker addWorker(@RequestBody Worker worker) {
        return workerRepository.save(worker);
    }

    // Обновить запись
    @PutMapping("workers/{id}")
    public ResponseEntity<Worker> updateWorker(@PathVariable int personnelNumber, @Validated @RequestBody Worker workerDetails)
            throws NotFoundException {
        Worker worker = workerRepository.findById(personnelNumber)
                .orElseThrow(() -> new NotFoundException("Рабочий не находится по этому id :: " + personnelNumber));
        worker.setFullName(workerDetails.getFullName());
        worker.setCategory(workerDetails.getCategory());
        worker.setMachine(workerDetails.getMachine());

        return ResponseEntity.ok(workerRepository.save(worker));
    }

    //удалить запись
    @DeleteMapping("workers/{id}")
    public Map<String, Boolean> deleteMachine(@PathVariable int personnelNumber)
            throws NotFoundException {
        Worker worker = workerRepository.findById(personnelNumber)
                .orElseThrow(() -> new NotFoundException("Рабочий не находится по этому idd :: " + personnelNumber));

        workerRepository.delete(worker);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return response;
    }
}
