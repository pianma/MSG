package com.project.msg.controller;

import com.project.msg.dto.MemberVO;
import com.project.msg.mapper.IndexMapper;
import com.project.msg.service.IndexService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class IndexController {

    private final IndexService indexService;

    @GetMapping("/member/all")
    public List<MemberVO> getMemberList(){
        List<MemberVO> list = indexService.getMemberList();
        return list;
    }

    @GetMapping("/member/{id}")
    public MemberVO getMemberData(@PathVariable Long id){
        return indexService.getMemberData(id);
    }

    @PostMapping("/member")
    public int addMemberData(MemberVO memberVO){
        return indexService.addMemberData(memberVO);
    }

    @PutMapping("/member")
    public int modifyMemberData(MemberVO memberVO){
        return indexService.modifyMemberData(memberVO);
    }

    @DeleteMapping("/member/{id}")
    public int deleteMemberData(@PathVariable Long id){
        return indexService.deleteMemberData(id);
    }
}
