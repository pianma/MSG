package com.project.msg.controller;

import com.project.msg.dto.UserDto;
import com.project.msg.service.IndexService;
import com.project.msg.service.MemberSerivceUsingDao;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/dao")
@RestController()
@RequiredArgsConstructor
public class MemberControllerUsingDao {

    private final MemberSerivceUsingDao memberSerivceUsingDao;

    @GetMapping("/member/all")
    public List<UserDto> getMemberList(){
        return memberSerivceUsingDao.getMemberList();
    }

    @GetMapping("/member/{userNo}")
    public UserDto getMemberData(@PathVariable Integer userNo){
        return memberSerivceUsingDao.getMemberData(userNo);
    }

    @PostMapping("/member")
    public int addMemberData(UserDto userDto){
        return memberSerivceUsingDao.addMemberData(userDto);
    }

    @PutMapping("/member")
    public int modifyMemberData(UserDto userDto){
        return memberSerivceUsingDao.modifyMemberData(userDto);
    }

    @DeleteMapping("/member/{userNo}")
    public int deleteMemberData(@PathVariable Integer userNo){
        return memberSerivceUsingDao.deleteMemberData(userNo);
    }

}
