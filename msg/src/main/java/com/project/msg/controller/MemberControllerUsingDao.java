package com.project.msg.controller;

import com.project.msg.dto.TableDto;
import com.project.msg.dto.UserDto;
import com.project.msg.service.IndexService;
import com.project.msg.service.MemberSerivceUsingDao;
import com.project.msg.service.TableService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/dao")
@RestController()
@RequiredArgsConstructor
public class MemberControllerUsingDao {

    private final MemberSerivceUsingDao memberSerivceUsingDao;
    private final TableService tableService;

    @GetMapping("/member/all")
    public List<UserDto> getMemberList(){
        return memberSerivceUsingDao.getMemberList();
    }

    @GetMapping("/member/{userNo}")
    public UserDto getMemberData(@PathVariable Integer userNo){
        return memberSerivceUsingDao.getMemberData(userNo);
    }

    @PostMapping("/member")
    public int addMemberData(UserDto userDto){
        return memberSerivceUsingDao.addMemberData(userDto);
    }

    @PutMapping("/member")
    public int modifyMemberData(UserDto userDto){
        return memberSerivceUsingDao.modifyMemberData(userDto);
    }

    @DeleteMapping("/member/{userNo}")
    public int deleteMemberData(@PathVariable Integer userNo){
        return memberSerivceUsingDao.deleteMemberData(userNo);
    }

    @GetMapping("/table/{tableName}")
    public List<TableDto> getTableDataList(@PathVariable String tableName){
        return tableService.getTableDataList(tableName);
    }


}
