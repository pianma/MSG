package com.project.msg.controller;


import com.project.msg.dto.UserDto;
import com.project.msg.service.IndexService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class IndexController {

    private final IndexService indexService;

    @GetMapping("/member/all")
    public List<UserDto> getMemberList(){
        return indexService.getMemberList();
    }

    @GetMapping("/member/{userNo}")
    public UserDto getMemberData(@PathVariable Integer userNo){
        return indexService.getMemberData(userNo);
    }

    @PostMapping("/member")
    public int addMemberData(UserDto userDto){
        System.out.println("userDto = " + userDto);
        return indexService.addMemberData(userDto);
    }

    @PutMapping("/member")
    public int modifyMemberData(UserDto userDto){
        return indexService.modifyMemberData(userDto);
    }

    @DeleteMapping("/member/{userNo}")
    public int deleteMemberData(@PathVariable Integer userNo){
        return indexService.deleteMemberData(userNo);
    }
}
