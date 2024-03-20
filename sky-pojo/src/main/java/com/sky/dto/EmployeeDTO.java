package com.sky.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 前端新增员工的提交的数据模型
 */
@Data
public class EmployeeDTO implements Serializable {

    private Long id;

    private String username;

    private String name;

    private String phone;

    private String sex;

    private String idNumber;

}
