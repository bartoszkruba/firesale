package com.company.firesale.config.Servis;

import com.company.firesale.config.Interface.ActionEntityRepository;
import com.company.firesale.config.Tabeller.ActionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ActionEntityServis {

    @Autowired
    ActionEntityRepository actionEntityRepository;

    public ActionEntityServis() {
    }

    public Page<ActionEntity> findTenByDate(int page) {
        Pageable PageWithTen = PageRequest.of(page, 10, Sort.by("closeAt"));
        return actionEntityRepository.findAll(PageWithTen);
    }

    public Page<ActionEntity> findTenById(int page) {
        Pageable PageWithTen = PageRequest.of(page, 10);
        return actionEntityRepository.findAll(PageWithTen);
    }

    public ActionEntity createActionEntity() {
        ActionEntity actionEntity = new ActionEntity();
        actionEntityRepository.save(actionEntity);
        return actionEntity;
    }
}
