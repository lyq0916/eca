package com.lyq.eca.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="cost_maintain")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class CostMaintain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mid")
    int mid;
    String name;
    String type;
    @JsonFormat
    Double money;
    int pid;
    String note;

    @Column(name = "update_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd",timezone = "GMT+08")
    Date updatedate;


    public CostMaintain() {
    }

    public CostMaintain(int mid, String name, String type, Double money, int pid, String note, Date update_date) {
        this.mid = mid;
        this.name = name;
        this.type = type;
        this.money = money;
        this.pid = pid;
        this.note = note;
        this.updatedate = update_date;
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(Date updatedate) {
        this.updatedate = updatedate;
    }

    @Override
    public String toString() {
        return "CostMaintain{" +
                "mid=" + mid +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", money=" + money +
                ", pid=" + pid +
                ", note='" + note + '\'' +
                ", update_date=" + updatedate +
                '}';
    }
}
