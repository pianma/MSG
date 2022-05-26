package com.project.msg.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@RestController
public class MsgRESTController {

    @GetMapping("gcr")
    public void genRestController() {
        //템플릿 파일을 읽어와서 => 결합후 => 소스파일만들고 데이터로 전송? ... 아직 진행중
        //추후 복잡한 로직 모두 서비스 계층으로 이동!

        String path = "src"+ File.separator
                +"main"+File.separator
                +"resources"+File.separator
                + "templates"+File.separator
                + "controller.mustache";

        try {

            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));

            String line = "";
            StringBuilder stringBuilder = new StringBuilder();

            for(int i = 1; (line = bufferedReader.readLine()) !=null; i++){

                String temp = line;

                System.out.println(i+":"+temp);
                stringBuilder.append(temp).append(System.getProperty("line.separator"));

                //해당 라인 문자열중 치환해야하는 부분 replace 해야함



//                if(line.indexOf(";") != -1){
//                    System.out.println(i+":"+line);
//                }

            }

            System.out.println("stringBuilder = " + stringBuilder);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
