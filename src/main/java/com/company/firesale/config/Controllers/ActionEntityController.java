package com.company.firesale.config.Controllers;

import com.company.firesale.config.Servis.ActionEntityServis;
import com.company.firesale.config.Tabeller.ActionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/action")
public class ActionEntityController {

    @Autowired
    ActionEntityServis actionEntityServis;

    @PostMapping
    ActionEntity createActionEntity(){
        return actionEntityServis.createActionEntity();
    }

}
