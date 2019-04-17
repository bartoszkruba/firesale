package com.company.firesale.config.Controllers;

import com.company.firesale.config.Servis.ActionEntityServis;
import com.company.firesale.config.Tabeller.ActionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/action")
public class ActionEntityController {

    @Autowired
    ActionEntityServis actionEntityServis;

    @PostMapping
    ActionEntity createActionEntity() {
        return actionEntityServis.createActionEntity();
    }

    @GetMapping("/{page}")
    List<ActionEntity> getTenActionEntityOrderById(@PathVariable int page) {
        return actionEntityServis.findTenById(page).getContent();
    }

    @GetMapping("/date/{page}")
    List<ActionEntity> getTenActionEntityOrderByDate(@PathVariable int page) {
        return actionEntityServis.findTenByDate(page).getContent();
    }

}
