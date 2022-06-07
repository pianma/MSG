package com.project.msg.controller;

import com.project.msg.dto.UserDto;
import com.project.msg.service.MemberSerivceUsingDao;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RequestMapping("/dao")
@RestController
@RequiredArgsConstructor
@ApiIgnore
public class MemberControllerUsingDao {

    private final MemberSerivceUsingDao memberSerivceUsingDao;

    @GetMapping("/member/all")
    public List<UserDto> getMemberList(){
        return memberSerivceUsingDao.getMemberList();
    }

    @GetMapping("/member")
    public UserDto getMemberData(UserDto userDto){
        return memberSerivceUsingDao.getMemberData(userDto);
    }

    @PostMapping("/member")
    public int addMemberData(UserDto userDto){
        return memberSerivceUsingDao.addMemberData(userDto);
    }

    @PutMapping("/member")
    public int modifyMemberData(UserDto userDto){
        return memberSerivceUsingDao.modifyMemberData(userDto);
    }

    @DeleteMapping("/member")
    public int deleteMemberData(UserDto userDto){
        return memberSerivceUsingDao.deleteMemberData(userDto);
    }



}
