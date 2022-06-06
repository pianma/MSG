package com.project.msg.dao;

import com.project.msg.dto.TableDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class TableDao {

    private final SqlSessionFactory sqlSessionFactory;
    private final SqlSession sqlSession;




    private List<String> getColumnClassName(TableDto tableDto){

        List<String> javaTypeList = new ArrayList<String>();

        try (
                Statement statement = sqlSessionFactory.openSession().getConnection().createStatement();
        ){
            ResultSet resultSet = statement.executeQuery("SELECT * FROM "+tableDto.getTableName()+" WHERE 0=1");

            for (int i = 0; i < resultSet.getMetaData().getColumnCount(); i++) {
                String javaType = resultSet.getMetaData().getColumnClassName(i+1);
//                javaType.replace("java.lang.","");

                log.info(javaType);
                javaTypeList.add(javaType);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return javaTypeList;
    }



    public List<TableDto> selectTableData(TableDto tableDto){

        List<TableDto> tableDtoList = sqlSession.selectList("mapper.table.selectTableData", tableDto);
        log.info(tableDtoList.toString());

        List<String> javaTypeList = getColumnClassName(tableDto);
        log.info("javaTypeList"+javaTypeList.toString());

        //자바 타입
        for (int i = 0; i < tableDtoList.size(); i++) {
            tableDtoList.get(i).setJavaType(javaTypeList.get(i));
        }
        log.info(tableDtoList.toString());


        return tableDtoList;
    }


}
