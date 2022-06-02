package com.project.msg.service;

import com.project.msg.dao.TableDao;
import com.project.msg.dto.FileInfoDto;
import com.project.msg.dto.TableDto;
import com.project.msg.util.FieldUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.*;
import java.sql.Types;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class GenerateXmlSource {

    //xxx.xml 소스파일 생성 서비스

    private final TableDao tableDao;

    public String generate(FileInfoDto fileInfoDto) {

        //1. 테이블 정보 얻기
        List<TableDto> tableDataList = tableDao.selectTableData(fileInfoDto.getTableName());
        log.info(tableDataList.toString());

        //유니크 필드 타입, 변수명
        List<TableDto> primaryFieldList = tableDataList.stream().filter(column -> column.getKey().equals("PRI")).collect(Collectors.toList());
        log.info("primaryField="+primaryFieldList.toString());

        //2. 키워드 변환(소문자, 첫글자만 대문자), 패키지 경로 변환

        String keyword = fileInfoDto.getKeyword().trim().toLowerCase();
        String firstLetterUpperKeyword = keyword.substring(0, 1).toUpperCase() + keyword.substring(1);
        String upperKeyword = keyword.toUpperCase();

        String path = fileInfoDto.getPath().trim().toLowerCase().replace("-",".");
        String filePathWithSeparator = path.replace(".", File.separator);

        //테이블 이름
        String tableName = fileInfoDto.getTableName();

        //3. 컨트롤러 소스파일 생성

        String filePath = "src" + File.separator
                        + "main" + File.separator
                        + "java" + File.separator
                        + filePathWithSeparator + File.separator
                        + "controller" + File.separator;

        //리소스 경로
        String resourcePath = "src" + File.separator
                            + "main" + File.separator
                            + "resources" + File.separator
                            + "templates" + File.separator;

        //xml 파일 경로
        String xmlPath = "src" + File.separator
                        + "main" + File.separator
                        + "resources" + File.separator
                        + "mapper" + File.separator;

        File javaFile = new File(resourcePath);

        if (!javaFile.exists()) {
            javaFile.mkdirs();
            log.info("생성 경로: " + javaFile.getPath());
        } else {
            log.info("이미 경로 존재: "+ javaFile.getPath());
        }

        String line = "";
        StringBuilder stringBuilder = new StringBuilder();

        try (
                BufferedReader bufferedReader = new BufferedReader(new FileReader(resourcePath + "queryXml.mustache"));
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(xmlPath + "sql-"+ keyword +".xml"));
        ) {


            for (int i = 1; (line = bufferedReader.readLine()) != null; i++) {

                String temp = line
                        .replace("{{keyword}}", keyword)
                        .replace("{{upperKeyword}}", firstLetterUpperKeyword)
                        .replace("{{tableName}}", tableName)
//                        .replace("{{basicPath}}", path)
                        .replace("{{whereConditionOfPrimary}}", FieldUtil.getWhereConditionOfPrimary(primaryFieldList))
                        .replace("{{targetFieldOfInsert}}", FieldUtil.getTargetFieldOfInsert(tableDataList))
                        .replace("{{targetFieldOfUpdate}}", FieldUtil.getTargetFieldOfUpdate(tableDataList));

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
