package com.taylor.hellokafka.pojo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserLog {
    private String username;
    private String userid;
    private String state;

}