package com.example.wokers_machines_l2.controllers;

import com.example.wokers_machines_l2.entity.Machine;
import com.example.wokers_machines_l2.entity.MachineList;
import com.example.wokers_machines_l2.entity.Worker;
import com.example.wokers_machines_l2.entity.WorkerList;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.http.ResponseEntity;

public abstract class XmlController {
    protected <T> ResponseEntity<String> getXmlResponse(T object) throws JsonProcessingException {
        XmlMapper xmlMapper = new XmlMapper();
        String xml = xmlMapper.writeValueAsString(object);
        xml = getXslTemplate(object) + xml;
        return ResponseEntity.ok().header("Content-type", "text/xml").body(xml);
    }

    protected <T> String getXslTemplate(T object) {
        String template = null;

        if (object.getClass() == Machine.class) {
            template = "Machine";
        }
        if (object.getClass() == MachineList.class) {
            template = "Machines";
        }
        if (object.getClass() == Worker.class) {
            template = "Worker";
        }
        if (object.getClass() == WorkerList.class) {
            template = "Workers";
        }

        if (template == null) {
            return "";
        } else {
            return "<?xml-stylesheet type=\"text/xsl\" href=\"/Xml2Html" + template + ".xsl\"?>";
        }

    }

}

