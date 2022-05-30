package com.project.msg.service;

import com.project.msg.dao.TableDao;
import com.project.msg.dto.FileInfoDto;
import com.project.msg.dto.TableDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.*;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class GenerateControllerSource {

    private final TableDao tableDao;
//    private final

    //컨트롤러 소스파일을 생성하는 서비스 모듈

    /**
     * 키워드
     * 테이블명
     * 패키지 경로
     * 생성해야 될 소스파일 종류
     */

    public String generate(FileInfoDto fileInfoDto) {

        log.info("fileInfoDto.getPath():" + fileInfoDto.getPath());

        //1. 테이블 정보 얻기
        List<TableDto> tableDataList = tableDao.selectTableData(fileInfoDto.getTableName());

        //유니크 키 타입, 변수명
        log.info(tableDataList.toString());

        List<TableDto> primaryFieldList = tableDataList.stream().filter(column -> column.getKey().equals("PRI")).collect(Collectors.toList());
        log.info("primaryField.toString()="+primaryFieldList.toString());


        //추후 추가로 기본키가 2개인 테이블을 고려해서 수정해야함. TODO
        TableDto uniqueFieldDto = primaryFieldList.get(0);


        //2. 키워드 변환(소문자, 첫글자만 대문자), 패키지 경로 변환

        String keyword = fileInfoDto.getKeyword().trim().toLowerCase();
        String firstLetterUpperKeyword = keyword.substring(0, 1).toUpperCase() + keyword.substring(1);
        String upperKeyword = keyword.toUpperCase();

        String path = fileInfoDto.getPath().trim().toLowerCase().replace("-",".");
        String filePathWithSeparator = path.replace(".", File.separator);



        //3. 컨트롤러 소스파일 생성

//        String filePath = "src" + File.separator
//                + "main" + File.separator
//                + "java" + File.separator
//                + "com" + File.separator          //패키지 경로 ~
//                + "project" + File.separator
//                + "target" + File.separator
//                + "controller" + File.separator; //역할 경로

        String filePath = "src" + File.separator
                + "main" + File.separator
                + "java" + File.separator
                + filePathWithSeparator + File.separator
                + "controller" + File.separator; //'컨트롤러'


        File javaFile = new File(filePath);

        if (!javaFile.exists()) {

            javaFile.mkdirs();
            log.info("생성 경로: " + javaFile.getPath());

        } else {
            log.info("이미 경로 존재: "+javaFile.getPath());
        }

        //리소스 경로
        String resourcePath = "src" + File.separator
                + "main" + File.separator
                + "resources" + File.separator
                + "templates" + File.separator
                + "controller.mustache";

        String line = "";
        StringBuilder stringBuilder = new StringBuilder();

        try (
                BufferedReader bufferedReader = new BufferedReader(new FileReader(resourcePath));
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath + firstLetterUpperKeyword +"Controller.java"));
        ) {


            for (int i = 1; (line = bufferedReader.readLine()) != null; i++) {

                String temp = line
                        .replace("{{basicPath}}", path)
                        .replace("{{upperKeyword}}", firstLetterUpperKeyword)
                        .replace("{{keyword}}", keyword)
                        .replace("{{primaryFieldDto.type}}", uniqueFieldDto.getType())
                        .replace("{{primaryFieldDto.name}}", uniqueFieldDto.getField().toLowerCase())
                        .replace("{{primaryFieldDtoNameWithBraces}}", "{"+uniqueFieldDto.getField().toLowerCase()+"}");

//                log.info(i + ":" + temp);
                stringBuilder.append(temp).append(System.getProperty("line.separator"));

            }

            log.info("===================================");
            log.info(""+stringBuilder);
            log.info("===================================");

            bufferedWriter.write(stringBuilder.toString());
            bufferedWriter.flush();

        }catch (IOException e) {
            throw new RuntimeException(e);
        }



        return stringBuilder.toString();
    }
}

