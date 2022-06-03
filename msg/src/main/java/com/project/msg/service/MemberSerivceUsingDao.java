package com.project.msg.service;

import com.project.msg.dao.IndexDao;
import com.project.msg.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberSerivceUsingDao {

    private final IndexDao indexDao;

    public List<UserDto> getMemberList() {
        return indexDao.selectMemberList();
    }

    public UserDto getMemberData(UserDto userDto) {
        return indexDao.selectMemberData(userDto);
    }

    public Integer addMemberData(UserDto userDto) {
        return indexDao.insertMemberData(userDto);
    }

    public Integer modifyMemberData(UserDto userDto) {
        return indexDao.updateMemberData(userDto);
    }

    public Integer deleteMemberData(UserDto userDto) {
        return indexDao.deleteMemberData(userDto);
    }

}
