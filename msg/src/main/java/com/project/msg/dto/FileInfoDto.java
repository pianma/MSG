package com.project.msg.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class FileInfoDto {

    /**
     * 만들어야될 파일에 대한 정보, 만들어진 파일 정보
     *
     * keyword 키워드
     * tableName 테이블명 (대소문자 구분)
     * path 패키지 경로 (com-project-target)
     * files 생성해야 될 소스파일 종류 (controller-service-dao-xml-dto-config)
     *
     * fieldDataList 테이블 필드 정보 리스트
     * */

    private String keyword;
    private String tableName;
    private String path;
    private String files;
    private List<TableDto> fieldDataList = new ArrayList<>();

    private String controller;
    private String service;
    private String dao;
    private String xml;
    private String dto;
    private String config;


}
