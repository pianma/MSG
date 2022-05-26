package com.project.msg.controller;

import com.project.msg.dto.FieldDto;
import com.project.msg.service.MsgService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Controller
public class MsgController {

    private final MsgService msgService;

    @GetMapping("gcon")
    public String genController(Model model,
                                @RequestParam String keyword,
                                @RequestParam(required = false, defaultValue = "com.project.target") String path,
                                @RequestParam(required = false) String primaryKeyField,
                                @RequestParam(required = false) String field
                                                            ) {

        // 생성할 소스파일 ( controller, service, dto, mapper, xml) - 컨트롤러만 생성해서 전송하는 모듈


        log.info("field = " + field);
        log.info("primaryKeyField = " + primaryKeyField);

        //기본 패키지 경로(com.project.target) 및 원하는 패키지 경로

        //키워드 소문자, 앞글자만 대문자
        String keywordName = keyword.toLowerCase().trim();
        String upperKeyword = keywordName.toUpperCase();
        String firstLetterToUpper = keywordName.substring(0, 1).toUpperCase() + keywordName.substring(1);

        // 테이블 명 - 키워드
        String tableName = keywordName.toUpperCase();

        //필드정보 DTO : 필드 타입 및 변수명  ("-" 필드 구분)

        List<FieldDto> fieldDtoList = new ArrayList<FieldDto>();
        FieldDto primaryFieldDto = null;

        if (field != null && primaryKeyField != null) {

            //기본키 또는 유니크 키 필드 이름
            primaryFieldDto = msgService.getPrimaryFieldDto(primaryKeyField);
            log.info("primaryFieldDto = " + primaryFieldDto);

            //필드 리스트 생성(유니크키 변수이름, 필드이름)
            fieldDtoList.addAll(msgService.getFieldDtoList(primaryFieldDto.getName(),field));

            log.info("fieldDtoList = " + fieldDtoList);

        }


        model.addAttribute("basicPath", path);
        model.addAttribute("upperKeyword", firstLetterToUpper);
        model.addAttribute("keyword", keywordName);
        model.addAttribute("primaryFieldDto", primaryFieldDto);
        model.addAttribute("primaryFieldDtoNameWithBraces", "{"+primaryFieldDto.getName()+"}");

        return "controller"; //나중에 파일명앞에 키워드 붙여서 파일이름이 정해져야함.
    }



    @GetMapping("scon")
    public String ServiceController(Model model,
                                    @RequestParam String keyword,
                                    @RequestParam(required = false) String path,
                                    @RequestParam(required = false) String primaryKeyField,
                                    @RequestParam(required = false) String field) {


        String keywordName = keyword.toLowerCase().trim();
        String upperKeyword = keywordName.toUpperCase();
        String firstLetterToUpper = keywordName.substring(0, 1).toUpperCase() + keywordName.substring(1);

        // 테이블 명 - 키워드
        String tableName = keywordName.toUpperCase();

        //필드정보 DTO : 필드 타입 및 변수명  ("-" 필드 구분)

        List<FieldDto> fieldDtoList = new ArrayList<FieldDto>();
        FieldDto primaryFieldDto = null;

        if (field != null && primaryKeyField != null) {

            //기본키 또는 유니크 키 필드 이름
            primaryFieldDto = msgService.getPrimaryFieldDto(primaryKeyField);
            log.info("primaryFieldDto = " + primaryFieldDto);

            //필드 리스트 생성(유니크키 변수이름, 필드이름)
            fieldDtoList.addAll(msgService.getFieldDtoList(primaryFieldDto.getName(), field));

            log.info("fieldDtoList = " + fieldDtoList);

        }

        model.addAttribute("basicPath", path);
        model.addAttribute("upperKeyword", firstLetterToUpper);
        model.addAttribute("keyword", keywordName);
        model.addAttribute("primaryFieldDto", primaryFieldDto);
        model.addAttribute("primaryFieldDtoNameWithBraces", "{" + primaryFieldDto.getName() + "}");


        return "service";
    }
}
