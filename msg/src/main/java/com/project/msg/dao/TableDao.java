package com.project.msg.dao;

import com.project.msg.dto.TableDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

@Slf4j
@Repository
public class TableDao {

    private final SqlSession sqlSession;

    @Autowired
    public TableDao(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public List<TableDto> selectTableData(TableDto tableDto){

        List<TableDto> tableDtoList = sqlSession.selectList("mapper.table.selectTableData", tableDto);

        //자바 타입
        tableDtoList.stream().forEach(column -> { column.setJavaType(column.getType()
                                                                        .replace("int","Integer")
                                                                        .replace("long","Long")
                                                                        .replace("varchar","String")
                                                                        .replace("char","String")
                                                                        .replace("mediumtext","String")
                                                                        .replace("mediumtext","String")
                                                                        .replace("datetime","java.sql.Timestamp"));
        });


        return tableDtoList;
    }


}
