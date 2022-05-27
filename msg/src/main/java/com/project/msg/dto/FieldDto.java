package com.project.msg.dto;

import lombok.*;



//@NoArgsConstructor
@Builder
@Data
public class FieldDto {

    private final String type;

    private final String name;

    private final boolean isPrimary;
}
