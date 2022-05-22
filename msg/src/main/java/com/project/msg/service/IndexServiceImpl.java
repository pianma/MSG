package com.project.msg.service;

import com.project.msg.dto.MemberVO;
import com.project.msg.mapper.IndexMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class IndexServiceImpl implements IndexService {

    private final IndexMapper indexMapper;


    @Override
    public List<MemberVO> getMemberList() {
        return indexMapper.selectMemberList();
    }

    @Override
    public MemberVO getMemberData(Long id) {
        return indexMapper.selectMemberData(id);
    }

    @Override
    public Integer addMemberData(MemberVO memberVO) {
        return indexMapper.insertMemberData(memberVO);
    }

    @Override
    public Integer modifyMemberData(MemberVO memberVO) {
        return indexMapper.updateMemberData(memberVO);
    }

    @Override
    public Integer deleteMemberData(Long id) {
        return indexMapper.deleteMemberData(id);
    }

}
