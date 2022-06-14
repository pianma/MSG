package com.project.msg.controller;

import com.project.msg.dto.FileInfoDto;
import com.project.msg.service.*;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.StringTokenizer;

@Slf4j
@RequiredArgsConstructor
@RestController
public class MsgRestController {

    private final GenerateControllerSource generateControllerSource;
    private final GenerateServiceSource generateServiceSource;
    private final GenerateXmlSource generateXmlSource;
    private final GenerateDaoSource generateDaoSource;
    private final GenerateDtoSource generateDtoSource;
    private final GenerateMybatisSource generateMybatisSource;


    @GetMapping("generate-all")
    @ApiOperation(value = "전체 소스 파일 생성", notes = "전체 소스파일을 생성한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "keyword", value = "키워드", paramType = "query", required = true),
            @ApiImplicitParam(name = "schema", value = "스키마명", paramType = "query", required = true),
            @ApiImplicitParam(name = "tableName", value = "테이블명", paramType = "query", required = true),
            @ApiImplicitParam(name = "directory", value = "프로젝트 폴더 위치", paramType = "query", required = true),
            @ApiImplicitParam(name = "path", value = "패키지 경로(com-project-target)", paramType = "query", required = true),

    })
    public ResponseEntity<FileInfoDto> generateAll(@ApiIgnore FileInfoDto fileInfoDto) {

        String controller = generateControllerSource.generate(fileInfoDto);
        String service = generateServiceSource.generate(fileInfoDto);
        String xml = generateXmlSource.generate(fileInfoDto);
        String dao = generateDaoSource.generate(fileInfoDto);
        String dto = generateDtoSource.generate(fileInfoDto);
//        String config = generateMybatisSource.generate(fileInfoDto);

        fileInfoDto.setController(controller);
        fileInfoDto.setService(service);
        fileInfoDto.setXml(xml);
        fileInfoDto.setDao(dao);
        fileInfoDto.setDto(dto);
//        fileInfoDto.setConfig(config);

        return new ResponseEntity<FileInfoDto>(fileInfoDto, HttpStatus.OK);

    }


    @GetMapping("generate")
    @ApiOperation(value = "원하는 소스 파일만 생성", notes = "소스 파일 종류를 입력하여 원하는 소스 파일을 생성한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "keyword", value = "키워드", paramType = "query", required = true),
            @ApiImplicitParam(name = "schema", value = "스키마명", paramType = "query", required = true),
            @ApiImplicitParam(name = "tableName", value = "테이블명", paramType = "query", required = true),
            @ApiImplicitParam(name = "directory", value = "프로젝트 폴더 위치(C드라이브 하위 경로 작성, Sample-...-...)", paramType = "query", required = true),
            @ApiImplicitParam(name = "path", value = "패키지 경로(com-project-target)", paramType = "query", required = true),
            @ApiImplicitParam(name = "files", value = "생성할 소스 파일종류(controller-service-dao-dto-xml)", paramType = "query", required = true)
    })
    public ResponseEntity<FileInfoDto> generateSource(@ApiIgnore FileInfoDto fileInfoDto) {

        log.info(fileInfoDto.toString());

        String fileList = fileInfoDto.getFiles();
        StringTokenizer stringTokenizer = new StringTokenizer(fileList,"-");

        String controller="";
        String service="";
        String dao="";
        String xml="";
        String dto="";
//        String config="";

        while(stringTokenizer.hasMoreTokens()){

            String file = stringTokenizer.nextToken();
            log.info(file);

            if(file.equals("controller")){
                controller = generateControllerSource.generate(fileInfoDto);
            }
            if(file.equals("service")){
                service = generateServiceSource.generate(fileInfoDto);
            }
            if(file.equals("dao")){
                dao = generateDaoSource.generate(fileInfoDto);
            }
            if(file.equals("xml")){
                xml = generateXmlSource.generate(fileInfoDto);
            }
            if(file.equals("dto")){
                dto = generateDtoSource.generate(fileInfoDto);
            }
//            if(file.equals("config")){
//                config = generateMybatisSource.generate(fileInfoDto);
//            }

        }

        fileInfoDto.setController(controller);
        fileInfoDto.setService(service);
        fileInfoDto.setXml(xml);
        fileInfoDto.setDao(dao);
        fileInfoDto.setDto(dto);
//        fileInfoDto.setConfig(config);


        return new ResponseEntity<FileInfoDto>(fileInfoDto, HttpStatus.OK);
    }
}
