package com.project.msg.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
import java.util.List;

@Data
public class FileInfoDto {

    /**
     * 만들어야될 파일에 대한 정보, 만들어진 파일 정보
     *
     * 1. 만들어야 될 파일에 대한 정보
     *
     * keyword      키워드
     * schema       스키마 이름
     * tableName    테이블명 (대소문자 구분)
     * path         패키지 경로 (com-project-target)
     * files        생성해야 될 소스파일 종류 (controller-service-dao-xml-dto-config)
     *
     * 2. 만들어진 파일 정보
     *
     * fieldDataList 테이블 필드 정보 리스트
     * 각각의 파일 내용
     * */

    @ApiModelProperty(example = "키워드")
    private String keyword;

    @ApiModelProperty(example = "스키마명" )
    private String schema;

    @ApiModelProperty(example = "테이블명")
    private String tableName;

    @ApiModelProperty(example = "패키지 경로(com-project-target)")
    private String path;

    @ApiModelProperty(example = "생성할 소스파일(controller-service-dao-dto-xml-config)")
    private String files;

    private String controller;
    private String service;
    private String dao;
    private String xml;
    private String dto;
    private String config;


}
