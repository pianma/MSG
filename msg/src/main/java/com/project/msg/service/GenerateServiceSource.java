package com.project.msg.service;

import com.project.msg.dao.TableDao;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
public class GenerateServiceSource {

    private  final TableDao tableDao;

    @Autowired
    public GenerateServiceSource(TableDao tableDao) {
        this.tableDao = tableDao;
    }
}
