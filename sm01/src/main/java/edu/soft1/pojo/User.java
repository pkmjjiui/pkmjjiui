package edu.soft1.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class User {
    String username;
    String pwd;
    int age;
    @DateTimeFormat(pattern = "yyyy-MM-dd")//类型转换
    Date birthday;//日期类型
    Address address;//自定义对象类型
}
