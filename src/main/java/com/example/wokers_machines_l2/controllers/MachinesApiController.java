package com.example.wokers_machines_l2.controllers;

import com.example.wokers_machines_l2.entity.Machine;
import com.example.wokers_machines_l2.repository.MachineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.wokers_machines_l2.exception.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/RESTapi/mac/")
public class MachinesApiController {
    @Autowired // его средства для получения bean-компонента под названием user Repository, который автоматически
    // создается Spring, мы будем использовать его для обработки данных
    MachineRepository machineRepository;
    // Получить все записи
    @GetMapping("machines")
    private List<Machine> getMachines() {
        return this.machineRepository.findAll();
    }

    // Получить запись по id
    @GetMapping("machines/{id}")
    public ResponseEntity<Machine> getEmployeeById(@PathVariable(value = "id") Long machineId)
            throws NotFoundException {
        Machine machine = machineRepository.findById(machineId)
                .orElseThrow(() -> new NotFoundException("Станок не находится по этому id :: " + machineId));
        return ResponseEntity.ok().body(machine);
    }

    // Создать запись
    @PostMapping("machines")
    public Machine addMachine(@RequestBody Machine machine) {
        return machineRepository.save(machine);
    }

    // Обновить запись
    @PutMapping("machines/{id}")
    public ResponseEntity<Machine> updateMachine(@PathVariable Long id, @Validated @RequestBody Machine machineDetails)
            throws NotFoundException {
        Machine machine = machineRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Станок не находится по этому id :: " + id));
        machine.setType(machineDetails.getType());

        return ResponseEntity.ok(machineRepository.save(machine));
    }

    //удалить запись
    @DeleteMapping("employees/{id}")
    public Map<String, Boolean> deleteMachine(@PathVariable Long id)
            throws NotFoundException {
        Machine machine = machineRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Станок не находится по этому idd :: " + id));

        machineRepository.delete(machine);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return response;
    }
}
