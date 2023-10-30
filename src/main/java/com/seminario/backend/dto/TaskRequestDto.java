package com.seminario.backend.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class TaskRequestDto {
    private Integer userId;

    private String name;

    private String description;

    private Date startDate;

    private Date endDate;

    private Integer contactId;
}
