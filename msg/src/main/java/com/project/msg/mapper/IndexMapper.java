package com.project.msg.mapper;

import com.project.msg.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IndexMapper {

    public List<UserDto> selectMemberList();
    public UserDto selectMemberData(Integer userNo);
    public Integer insertMemberData(UserDto userDto);
    public Integer updateMemberData(UserDto userDto);
    public Integer deleteMemberData(Integer userNo);


}
