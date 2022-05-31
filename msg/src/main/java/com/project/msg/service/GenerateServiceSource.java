package com.project.msg.service;

import com.project.msg.dao.TableDao;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
@Slf4j
public class GenerateServiceSource {

    private  final TableDao tableDao;

    @Autowired
    public GenerateServiceSource(TableDao tableDao) {
        this.tableDao = tableDao;
    }
}
