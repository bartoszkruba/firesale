package com.company.firesale.config.Tabeller;

import net.bytebuddy.dynamic.loading.InjectionClassLoader;
import org.aspectj.apache.bcel.classfile.Module;

import javax.persistence.*;
import java.util.Date;

@Entity
public class ActionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    private final String title = null;
    private final String description = null;
    private final Date opendAt = null;
    private final Date closeAt = null;
    private final double startUpPrice = 0;
    private final double buyOutPrice = 0;

    private enum ActionStatus{ Open, Close}
    @Enumerated(value = EnumType.STRING)
    private ActionStatus status;


    public ActionEntity(){
    }

    public long getId() {
        return Id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Date getOpendAt() {
        return opendAt;
    }

    public Date getCloseAt() {
        return closeAt;
    }

    public double getStartUpPrice() {
        return startUpPrice;
    }

    public double getBuyOutPrice() {
        return buyOutPrice;
    }

    public String getStatus() {
        return this.status.name();
    }


}
