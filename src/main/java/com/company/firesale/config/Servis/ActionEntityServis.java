package com.company.firesale.config.Servis;

import com.company.firesale.config.Interface.ActionEntityRepository;
import com.company.firesale.config.Tabeller.ActionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActionEntityServis {

    @Autowired
    ActionEntityRepository actionEntityRepository;


    public ActionEntity createActionEntity(){
        ActionEntity actionEntity = new ActionEntity();
        actionEntityRepository.save(actionEntity);
        return actionEntity;
    }
}
