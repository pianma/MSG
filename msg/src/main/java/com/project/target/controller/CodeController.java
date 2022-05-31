package com.project.target.controller;

import com.project.target.dto.CodeDto;
import com.project.target.service.CodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequiredArgsConstructor
public class CodeController {

    private final CodeService codeService;

    @GetMapping("/code-all")
    public List<CodeDto> getCodeList(){
        return codeService.getCodeList();
    }

    @GetMapping("/code/{classno}/{codecd}")
    public CodeDto getCodeData(@PathVariable Integer classno, @PathVariable String codecd){
        return codeService.getCodeData(classno, codecd);
    }

    @PostMapping("/code")
    public int addCodeData(CodeDto codeDto){
        return codeService.addCodeData(codeDto);
    }

    @PutMapping("/code")
    public int modifyCodeData(CodeDto codeDto){
        return codeService.modifyCodeData(codeDto);
    }

    @DeleteMapping("/code/{classno}/{codecd}")
    public int deleteCodeData(@PathVariable Integer classno, @PathVariable String codecd){
        return codeService.deleteCodeData(classno, codecd);
    }
}
