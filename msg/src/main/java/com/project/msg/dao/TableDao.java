package com.project.msg.dao;

import com.project.msg.dto.TableDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
public class TableDao {

    private final SqlSession sqlSession;

    @Autowired
    public TableDao(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public List<TableDto> selectTableData(String tableName){
        return sqlSession.selectList("mapper.table.selectTableData", tableName);

    }


}
