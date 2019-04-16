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
    private String title = null;
    private String description = null;
    private Date openAt = null;
    private Date closeAt = null;
    private double startUpPrice = 0;
    private double buyOutPrice = 0;

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

    public Date getOpenAt() {
        return openAt;
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

    public ActionStatus getStatus() {
        return status;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setOpenAt(Date openAt) {
        this.openAt = openAt;
    }

    public void setCloseAt(Date closeAt) {
        this.closeAt = closeAt;
    }

    public void setStartUpPrice(double startUpPrice) {
        this.startUpPrice = startUpPrice;
    }

    public void setBuyOutPrice(double buyOutPrice) {
        this.buyOutPrice = buyOutPrice;
    }

    public void setStatus(ActionStatus status) {
        this.status = status;
    }
}
