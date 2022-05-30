package com.project.msg.service;

import com.project.msg.dao.TableDao;
import com.project.msg.dto.TableDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TableService {

    private final TableDao tableDao;

    @Autowired
    public TableService(TableDao tableDao) {
        this.tableDao = tableDao;
    }

    public List<TableDto> getTableDataList(String tableName){
        return tableDao.selectTableData(tableName);
    }
}
