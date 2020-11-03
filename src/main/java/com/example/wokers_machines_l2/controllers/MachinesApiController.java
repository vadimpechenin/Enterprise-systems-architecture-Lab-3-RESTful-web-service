package com.example.wokers_machines_l2.controllers;

import com.example.wokers_machines_l2.entity.Machine;
import com.example.wokers_machines_l2.entity.MachineList;
import com.example.wokers_machines_l2.repository.MachineRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.wokers_machines_l2.exception.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/RESTapi/machines/")
public class MachinesApiController extends XmlController {
    @Autowired // его средства для получения bean-компонента под названием user Repository, который автоматически
    // создается Spring, мы будем использовать его для обработки данных
    MachineRepository machineRepository;

    // В формате xml
    // Получить все записи
    @GetMapping("xml")
    private ResponseEntity<String> getMachinesXml() throws JsonProcessingException {
        List<Machine> machines = new ArrayList<Machine>();
        machines = this.machineRepository.findAll();
        return getXmlResponse(new MachineList(machines));
    }

    // Получить запись по id
    @GetMapping("xml/{id}")
    public ResponseEntity<String> getMachineByIdXml(@PathVariable(value = "id") Long machineId)
            throws NotFoundException, JsonProcessingException {
        Machine machine = machineRepository.findById(machineId)
                .orElseThrow(() -> new NotFoundException("Станок не находится по этому id :: " + machineId));
        return getXmlResponse(machine);
    }

    // Создать запись
    @PostMapping("xml")
    @ResponseBody
    public Machine addMachineXml(@RequestBody Machine machine) {

        return machineRepository.save(machine);
    }

    // Обновить запись
    @PutMapping("xml/{id}")
    @ResponseBody
    public ResponseEntity<Machine> updateMachineXml(@PathVariable Long id, @Validated @RequestBody Machine machineDetails)
            throws NotFoundException {
        Machine machine = machineRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Станок не находится по этому id :: " + id));
        machine.setType(machineDetails.getType());

        return ResponseEntity.ok(machineRepository.save(machine));
    }

    //удалить запись
    @DeleteMapping("xml/{id}")
    @ResponseBody
    public Map<String, Boolean> deleteMachineXml(@PathVariable Long id)
            throws NotFoundException {
        Machine machine = machineRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Станок не находится по этому idd :: " + id));

        machineRepository.delete(machine);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return response;
    }

    // Формат json
    // Получить все записи
    //@GetMapping
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    private List<Machine> getMachines() {

        return this.machineRepository.findAll();
    }

    // Получить запись по id
    //@GetMapping("{id}")
    @RequestMapping(value="{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Machine> getMachineById(@PathVariable(value = "id") Long machineId)
            throws NotFoundException {
        Machine machine = machineRepository.findById(machineId)
                .orElseThrow(() -> new NotFoundException("Станок не находится по этому id :: " + machineId));
        return ResponseEntity.ok().body(machine);
    }

    // Создать запись
    //@PostMapping
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Machine addMachine(@RequestBody Machine machine) {
        return machineRepository.save(machine);
    }

    // Обновить запись
    //@PutMapping("{id}")
    @RequestMapping(value="{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Machine> updateMachine(@PathVariable Long id, @Validated @RequestBody Machine machineDetails)
            throws NotFoundException {
        Machine machine = machineRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Станок не находится по этому id :: " + id));
        machine.setType(machineDetails.getType());

        return ResponseEntity.ok(machineRepository.save(machine));
    }

    //удалить запись
    //@DeleteMapping("{id}")
    @RequestMapping(value="{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
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
