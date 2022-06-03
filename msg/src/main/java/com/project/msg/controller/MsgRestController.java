package com.project.msg.controller;

import com.project.msg.dto.FileInfoDto;
import com.project.msg.service.GenerateControllerSource;
import com.project.msg.service.GenerateDaoSource;
import com.project.msg.service.GenerateServiceSource;
import com.project.msg.service.GenerateXmlSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.StringTokenizer;

@Slf4j
@RequiredArgsConstructor
@RestController
public class MsgRestController {

    private final GenerateControllerSource generateControllerSource;
    private final GenerateServiceSource generateServiceSource;
    private final GenerateXmlSource generateXmlSource;
    private final GenerateDaoSource generateDaoSource;

    @GetMapping("generate-all")
    public ResponseEntity<FileInfoDto> generateAll(FileInfoDto fileInfoDto) {

        String controller = generateControllerSource.generate(fileInfoDto);
        String service = generateServiceSource.generate(fileInfoDto);
        String xml = generateXmlSource.generate(fileInfoDto);
        String dao = generateDaoSource.generate(fileInfoDto);

        fileInfoDto.setController(controller);
        fileInfoDto.setService(service);
        fileInfoDto.setXml(xml);
        fileInfoDto.setDao(dao);

        return new ResponseEntity<FileInfoDto>(fileInfoDto, HttpStatus.OK);

    }

    @GetMapping("generate")
    public ResponseEntity<FileInfoDto> generateSource(FileInfoDto fileInfoDto) {

        log.info(fileInfoDto.toString());

        String fileList = fileInfoDto.getFiles();
        StringTokenizer stringTokenizer = new StringTokenizer(fileList);

        String controller="";
        String service="";
        String dao="";
        String xml="";
        String dto="";
        String config="";

        while(stringTokenizer.hasMoreTokens()){

            String file = stringTokenizer.nextToken();

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
//                dto = generateDtoSource.generate(fileInfoDto);
            }
            if(file.equals("config")){
//                config = generateConfigSource.generate(fileInfoDto);
            }

        }

        fileInfoDto.setController(controller);
        fileInfoDto.setService(service);
        fileInfoDto.setXml(xml);
        fileInfoDto.setDao(dao);
//        fileInfoDto.setDto(dto);
//        fileInfoDto.setConfig(config);


        return new ResponseEntity<FileInfoDto>(fileInfoDto, HttpStatus.OK);
    }
}
