package com.lyq.eca.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "bid_project")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class BidProject {
    @Id
    @Column(name = "pid")
    int pid;
    String state;
}
