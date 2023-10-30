package com.seminario.backend.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class TaskResponseDto {
    private Integer id;

    private String name;

    private String description;

    private Date startDate;

    private Date endDate;

    private String contactName;

    private Integer contactId;

    public TaskResponseDto(Integer id, String name, String description, Date startDate, Date endDate, String contactName, Integer contactId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.contactName = contactName;
        this.contactId = contactId;
    }
}
