package com.project.msg.dto;

import lombok.*;


@ToString
@Getter
@RequiredArgsConstructor
//@NoArgsConstructor
@Builder
public class FieldDto {

    private final String type;

    private final String name;

    private final boolean isPrimary;
}
