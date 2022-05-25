package com.project.msg.service;

import com.project.msg.dto.FieldDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface MsgService {

    FieldDto getPrimaryFieldDto(String primaryKeyField);

    List<FieldDto> getFieldDtoList(String primaryFieldName, String field);
}
