package com.project.msg.mapper;

import com.project.msg.dto.MemberVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface IndexMapper {

    public List<MemberVO> selectMemberList();
    public MemberVO selectMemberData(Long id);
    public Integer insertMemberData(MemberVO memberVO);
    public Integer updateMemberData(MemberVO memberVO);
    public Integer deleteMemberData(Long id);


}
