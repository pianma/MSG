package com.project.msg.service;

import com.project.msg.dao.TableDao;
import com.project.msg.dto.FileInfoDto;
import com.project.msg.dto.TableDto;
import com.project.msg.util.FieldUtil;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Service
@Slf4j
@RequiredArgsConstructor
public class GenerateServiceSource {

    private  final TableDao tableDao;


    public String generate(FileInfoDto fileInfoDto) {

        //1. 테이블 정보 얻기
        TableDto tableDto = new TableDto();
        tableDto.setSchema(fileInfoDto.getSchema());
        tableDto.setTableName(fileInfoDto.getTableName());

        List<TableDto> tableDataList = tableDao.selectTableData(tableDto);
        log.info(tableDataList.toString());

        //유니크 키 타입, 변수명
        List<TableDto> primaryFieldList = tableDataList.stream().filter(column -> column.getKey().equals("PRI")).collect(Collectors.toList());
        log.info("primaryField.toString()="+primaryFieldList.toString());

        //2. 키워드 변환(소문자, 첫글자만 대문자), 패키지 경로 변환

        String keyword = fileInfoDto.getKeyword().trim().toLowerCase();
        String firstLetterUpperKeyword = keyword.substring(0, 1).toUpperCase() + keyword.substring(1);


        String path = fileInfoDto.getPath().trim().toLowerCase().replace("-",".");
        String filePathWithSeparator = path.replace(".", File.separator);
        String directory = fileInfoDto.getDirectory().replace("-", File.separator);

        //3. 컨트롤러 소스파일 생성

//        String filePath = "src" + File.separator
//                + "main" + File.separator
//                + "java" + File.separator
//                + "com" + File.separator          //패키지 경로 ~
//                + "project" + File.separator
//                + "target" + File.separator
//                + "service" + File.separator; //역할 경로

        String filePath =  "C:"+ File.separator
                           + directory + File.separator
                           + "src" + File.separator
                           + "main" + File.separator
                           + "java" + File.separator
                           + filePathWithSeparator + File.separator
                           + "service" + File.separator;


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
                + "service.mustache";

        String line = "";
        StringBuilder stringBuilder = new StringBuilder();

        try (
                BufferedReader bufferedReader = new BufferedReader(new FileReader(resourcePath));
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath + firstLetterUpperKeyword +"Service.java"));
        ) {


            for (int i = 1; (line = bufferedReader.readLine()) != null; i++) {

                String temp = line
                        .replace("{{basicPath}}", path)
                        .replace("{{upperKeyword}}", firstLetterUpperKeyword)
                        .replace("{{keyword}}", keyword)
                        .replace("{{primaryFieldParameter}}", FieldUtil.getParameterOfPrimaryField(primaryFieldList))
                        .replace("{{primaryFieldVariable}}", FieldUtil.getPrimaryFieldVariable(primaryFieldList))
                        .replace("{{primaryFieldVariableWithBraces}}", FieldUtil.getPrimaryFieldVariableWithBraces(primaryFieldList));

                stringBuilder.append(temp).append(System.lineSeparator());


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