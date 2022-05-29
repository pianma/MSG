package com.project.msg.dao;

import com.project.msg.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
public class IndexDao {

    private static final String NAME_SPACE = "mapper.member";

    private final SqlSession sqlSession;

    @Autowired
    public IndexDao(SqlSession sqlSession) {
        log.info("sqlSession = " + sqlSession);
        this.sqlSession = sqlSession;
    }

    public List<UserDto> selectMemberList() {
        return sqlSession.selectList("mapper.member.selectMemberList");
    }

    public UserDto selectMemberData(Integer userNo) {
        UserDto userDto = sqlSession.selectOne("mapper.member.selectMemberData", userNo);
        return userDto;
    }

    public Integer insertMemberData(UserDto userDto) {
        return sqlSession.insert("mapper.member.insertMemberData", userDto);
    }

    public Integer updateMemberData(UserDto userDto) {
        return sqlSession.update("mapper.member.updateMemberData", userDto);
    }

    public Integer deleteMemberData(Integer userNo) {
        return sqlSession.delete("mapper.member.deleteMemberData", userNo);
    }
}
