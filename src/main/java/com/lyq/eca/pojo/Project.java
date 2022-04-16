package com.lyq.eca.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "project")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pid")
    int pid;
    String pname;//项目名称
    String bnumber;//招标编号
    String state;
    /*
     * 部署时间
     * */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd",timezone = "GMT+08")
    Date start_date;//开工日期
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd",timezone = "GMT+08")
    Date end_date;//完工日期
    String address;//地址
    String type;//项目类型 =>交通运输，住宅建筑，商业办公，医疗卫生，环保节能，文化体育，服务领域，农业水利，其他领域
    @JsonFormat
    double cost_all;//花费总成本（已完工）
    @JsonFormat
    double budget;//预算

    public Project(int pid, String pname, String bnumber, String state, Date start_date, Date end_date, String address, String type, double cost_all, double budget) {
        this.pid = pid;
        this.pname = pname;
        this.bnumber = bnumber;
        this.state = state;
        this.start_date = start_date;
        this.end_date = end_date;
        this.address = address;
        this.type = type;
        this.cost_all = cost_all;
        this.budget = budget;
    }

    public Project() {

    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getBnumber() {
        return bnumber;
    }

    public void setBnumber(String bnumber) {
        this.bnumber = bnumber;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getCost_all() {
        return cost_all;
    }

    public void setCost_all(double cost_all) {
        this.cost_all = cost_all;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    @Override
    public String toString() {
        return "Project{" +
                "pid=" + pid +
                ", pname='" + pname + '\'' +
                ", bnumber='" + bnumber + '\'' +
                ", state='" + state + '\'' +
                ", start_date=" + start_date +
                ", end_date=" + end_date +
                ", address='" + address + '\'' +
                ", type='" + type + '\'' +
                ", cost_all=" + cost_all +
                ", budget=" + budget +
                '}';
    }
}
