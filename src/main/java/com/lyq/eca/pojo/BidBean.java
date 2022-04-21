package com.lyq.eca.pojo;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BidBean {
    int pid;
    String pname;
    Project project;
    String state;
}
