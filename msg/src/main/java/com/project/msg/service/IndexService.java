package com.project.msg.service;

import com.project.msg.dto.UserDto;
import com.project.msg.mapper.IndexMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class IndexService  {

    private final IndexMapper indexMapper;

    public List<UserDto> getMemberList() {
        return indexMapper.selectMemberList();
    }

    public UserDto getMemberData(Integer userNo) {
        return indexMapper.selectMemberData(userNo);
    }

    public Integer addMemberData(UserDto userDto) {
        return indexMapper.insertMemberData(userDto);
    }

    public Integer modifyMemberData(UserDto userDto) {
        return indexMapper.updateMemberData(userDto);
    }

    public Integer deleteMemberData(Integer userNo) {
        return indexMapper.deleteMemberData(userNo);
    }

}
