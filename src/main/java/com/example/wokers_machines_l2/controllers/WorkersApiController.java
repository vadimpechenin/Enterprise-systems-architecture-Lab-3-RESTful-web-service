package com.example.wokers_machines_l2.controllers;


import com.example.wokers_machines_l2.entity.Machine;
import com.example.wokers_machines_l2.entity.Worker;
import com.example.wokers_machines_l2.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.wokers_machines_l2.exception.NotFoundException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/RESTapi/workers/")
public class WorkersApiController {

    @Autowired
    WorkerRepository workerRepository;
    //Для xml ответа (по умолчанию)
    // Получить все записи
    @GetMapping("xml")
    private List<Worker> getWorkersXml() {
        return this.workerRepository.findAll();
    }

    // Получить запись по id
    @GetMapping("xml/{id}")
    public ResponseEntity<Worker> getWorkerByIdXml(@PathVariable(value = "personnelNumber") int workerId)
            throws NotFoundException {
        Worker worker = workerRepository.findById(workerId)
                .orElseThrow(() -> new NotFoundException("Рабочий не находится по этому id :: " + workerId));
        return ResponseEntity.ok().body(worker);

    }

    // Создать запись
    @PostMapping("xml")
    public Worker addWorkerXml(@RequestBody Worker worker) {
        return workerRepository.save(worker);
    }

    // Обновить запись
    @PutMapping("xml/{id}")
    public ResponseEntity<Worker> updateWorkerXml(@PathVariable int personnelNumber, @Validated @RequestBody Worker workerDetails)
            throws NotFoundException {
        Worker worker = workerRepository.findById(personnelNumber)
                .orElseThrow(() -> new NotFoundException("Рабочий не находится по этому id :: " + personnelNumber));
        worker.setFullName(workerDetails.getFullName());
        worker.setCategory(workerDetails.getCategory());
        worker.setMachine(workerDetails.getMachine());

        return ResponseEntity.ok(workerRepository.save(worker));
    }

    //удалить запись
    @DeleteMapping("xml/{id}")
    public Map<String, Boolean> deleteMachineXml(@PathVariable int personnelNumber)
            throws NotFoundException {
        Worker worker = workerRepository.findById(personnelNumber)
                .orElseThrow(() -> new NotFoundException("Рабочий не находится по этому idd :: " + personnelNumber));

        workerRepository.delete(worker);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return response;
    }
    //Для json ответа
    // Получить все записи
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    private List<Worker> getWorkers() {
        return this.workerRepository.findAll();
    }

    // Получить запись по id
    @RequestMapping(value="{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Worker> getWorkerById(@PathVariable(value = "personnelNumber") int workerId)
            throws NotFoundException {
        Worker worker = workerRepository.findById(workerId)
                .orElseThrow(() -> new NotFoundException("Рабочий не находится по этому id :: " + workerId));
        return ResponseEntity.ok().body(worker);

    }

    // Создать запись
    @PostMapping
    public Worker addWorker(@RequestBody Worker worker) {
        return workerRepository.save(worker);
    }

    // Обновить запись
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
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
    @RequestMapping(value="{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
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
