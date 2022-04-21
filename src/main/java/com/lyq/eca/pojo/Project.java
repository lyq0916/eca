package com.lyq.eca.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity
@Table(name = "project")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pid")
    @JsonFormat
    int pid;
    String pname;//项目名称
    String bnumber;//招标编号
    String state;
    String type;//项目类型 =>交通运输，住宅建筑，商业办公，医疗卫生，环保节能，文化体育，服务领域，农业水利，其他领域

    @JsonFormat
    double budget;//预算
    @Column(name = "m_budget")
    @JsonFormat
    double mbudget;//材料预算
    @Column(name = "e_budget")
    @JsonFormat
    double ebudget;//机械设备预算
    @Column(name = "l_budget")
    @JsonFormat
    double lbudget;//人工预算
    @Column(name = "o_budget")
    @JsonFormat
    double obudget;//其他
    @Column(name = "i_budget")
    @JsonFormat
    double ibudget;//间接支出预算

    String address;//地址
    Date date;
}
