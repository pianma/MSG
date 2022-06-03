package com.project.msg.service;

import com.project.msg.dto.FileInfoDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.*;


@Service
@Slf4j
public class GenerateMybatisSource {

    public String generate(FileInfoDto fileInfoDto) {

        log.info("fileInfoDto.getPath():" + fileInfoDto.getPath());

        String path = fileInfoDto.getPath().trim().toLowerCase().replace("-",".");
        String filePathWithSeparator = path.replace(".", File.separator);

        String filePath = "src" + File.separator
                + "main" + File.separator
                + "java" + File.separator
                + filePathWithSeparator + File.separator;


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
                + "mybatisConfig.mustache";

        String line = "";
        StringBuilder stringBuilder = new StringBuilder();

        try (
                BufferedReader bufferedReader = new BufferedReader(new FileReader(resourcePath));
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath +"MybatisConfig.java"));
        ) {


            for (int i = 1; (line = bufferedReader.readLine()) != null; i++) {
                String temp = line.replace("{{basicPath}}", path);
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

