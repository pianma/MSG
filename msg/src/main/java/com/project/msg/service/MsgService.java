package com.project.msg.service;

import com.project.msg.dto.FieldDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

@Service
public class MsgService{


    public FieldDto getPrimaryFieldDto(String primaryKeyField) {

        StringTokenizer primary = new StringTokenizer(primaryKeyField,"-");

        List<String> primaryWord = new ArrayList<String>();

        while(primary.hasMoreTokens()){

            String token = primary.nextToken();
            primaryWord.add(token);
        }

        FieldDto fieldDto = FieldDto.builder()
                .type(primaryWord.get(0))
                .name(primaryWord.get(1))
                .isPrimary(true).build();

        return fieldDto;
    }


    public List<FieldDto> getFieldDtoList(String primaryFieldName, String field) {

        StringTokenizer fields = new StringTokenizer(field, "-");

        List<String> fieldList = new ArrayList<String>();

        while (fields.hasMoreTokens()) {
            String token = fields.nextToken();
            fieldList.add(token);
        }

        List<FieldDto> fieldDtoList = new ArrayList<FieldDto>();  //기본키 앞으로 오게 정렬

        //fieldDTO type, name, isPrimary(Unique)
        for (int i = 0; i < fieldList.size(); i += 2) {

            FieldDto fieldDto;

            if(fieldList.get(i + 1).equals(primaryFieldName)) {

                fieldDto = FieldDto.builder()
                        .type(fieldList.get(i))
                        .name(fieldList.get(i + 1))
                        .isPrimary(true).build();

            } else {

                fieldDto = FieldDto.builder()
                        .type(fieldList.get(i))
                        .name(fieldList.get(i + 1))
                        .isPrimary(false).build();

            }

            fieldDtoList.add(fieldDto);
        }

            return fieldDtoList;
    }
}
