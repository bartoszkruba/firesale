package com.company.firesale.config.Tabeller;

import net.bytebuddy.dynamic.loading.InjectionClassLoader;
import org.aspectj.apache.bcel.classfile.Module;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class ActionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    private final String title = null;
    private final Date opendAt = null;
    private final Date closeAt = null;
    private final double startUpPrice = 0;
    private final double buyOutPrice = 0;
    private enum MyStatus { Open, Close }
    private MyStatus status = null;

    public ActionEntity(){
    }

    public long getId() {
        return Id;
    }

    public String getTitle() {
        return title;
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
