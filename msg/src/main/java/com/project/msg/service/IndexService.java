package com.project.msg.service;

import com.project.msg.dto.MemberVO;

import java.util.List;

public interface IndexService {

    List<MemberVO> getMemberList();
    MemberVO getMemberData(Long id);
    Integer addMemberData(MemberVO memberVO);
    Integer modifyMemberData(MemberVO memberVO);
    Integer deleteMemberData(Long id);

}
