package com.project.msg.controller;

import com.project.msg.service.MsgService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class MsgRestController {

    private final MsgService msgService;

    //generateAll() 함수 추가

    @GetMapping("gcr")
    public ResponseEntity<List<String>> generateSource(
        @RequestParam String keyword,
        @RequestParam String files,                                                       //controller-service-serviceImpl-mapper-xml-dto-config
        @RequestParam(required = false) String tableName,                                   //소문자 ex) tbl_board
        @RequestParam(required = false, defaultValue = "com-project-target") String path,   //패키지경로 -로 구분
        @RequestParam(required = false) String primaryKeyField,                             // Long-id, - 로 구분
        @RequestParam(required = false) String field                                        //Long-id-String-name, - 로 구분
    ) {

        List<String> fileList = new ArrayList<>();
        //템플릿 파일을 읽어와서 => 결합후 => 소스파일만들고 데이터로 전송? ... 아직 진행중  줄바꿈, 들여쓰기 적용
        //추후 복잡한 로직 모두 서비스 계층으로 이동!

        //어떤 소스파일을 생성할 것인지에대한 파라미터 값도 받아야함. - 구분 값 확인

        //ResponseEntity 정보 : 소스파일 + 메타정보 ?

        //기본 패키지 경로 반환하는 함수로 정리
//        String basicPath = msgService.getPackagePath(path);  //com+File.separator+project+File.separator+target+File.separator

        String filePath = "src"+File.separator
                            +"main"+File.separator
                            + "java"+File.separator
                            + "com"+File.separator          //패키지 경로 ~
                            + "project"+File.separator
                            + "target"+File.separator
                            + "controller"+ File.separator; //역할 경로

        File javaFile = new File(filePath);

        if (!javaFile.exists()) {

            javaFile.mkdirs();
            System.out.println("생성 경로= "+ javaFile.getPath());

        }else {
            System.out.println("이미 경로 존재");
        }

        //리소스 경로
        String resourcePath = "src"+ File.separator
                +"main"+File.separator
                +"resources"+File.separator
                + "templates"+File.separator
                + "controller.mustache";

        try (
                BufferedReader bufferedReader = new BufferedReader(new FileReader(resourcePath));
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath + "MemberController.java"));
                ) {

            String line = "";
            StringBuilder stringBuilder = new StringBuilder();

            for(int i = 1; (line = bufferedReader.readLine()) !=null; i++){

                String temp = line
                                .replace("{{basicPath}}","com.project.target")
                                .replace("{{upperKeyword}}","Member")
                                .replace("{{keyword}}","member")
                                .replace("{{primaryFieldDto.type}}","Long")
                                .replace("{{primaryFieldDto.name}}","id")
                                .replace("{{primaryFieldDtoNameWithBraces}}","{id}");

                System.out.println(i+":"+temp);
                stringBuilder.append(temp).append(System.getProperty("line.separator"));





//                if(line.indexOf(";") != -1){
//                    System.out.println(i+":"+line);
//                }

            }

            System.out.println("stringBuilder = " + stringBuilder);

            bufferedWriter.write(stringBuilder.toString());
            bufferedWriter.flush();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return new ResponseEntity<List<String>>(fileList, HttpStatus.OK);
    }
}
