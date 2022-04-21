package com.lyq.eca.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity
@Table(name = "const_project")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class ConstProject {
    @Id
    @Column(name = "pid")
    int pid;
    String state;
    @Column(name = "e_start_date")
    Date estart;
    @Column(name = "start_date")
    Date start;
    @Column(name = "e_end_date")
    Date eend;
    @Column(name = "end_date")
    Date end;
    @JsonFormat
    Double cost;
}
