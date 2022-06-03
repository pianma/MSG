package com.project.msg.util;

import com.project.msg.dto.TableDto;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class FieldUtil {


    public static String getPrimaryFieldParameter(List<TableDto> primaryFieldList) { //@PathVariable Integer userno, @PathVariable String another...

        StringBuffer result = new StringBuffer();

        for (int i = 0; i < primaryFieldList.size(); i++) {
            result.append("@PathVariable "+primaryFieldList.get(i).getJavaType()+" "+primaryFieldList.get(i).getField().toLowerCase());

            if(i != primaryFieldList.size() - 1 ){
                result.append(", ");
            }
        }

        log.info("getPrimaryFieldParameter: "+result);

        return result.toString();
    }

    public static String getTypeWithField(List<TableDto> tableDataList) { //@PathVariable Integer userno, @PathVariable String another...


        StringBuffer result = new StringBuffer();

        for (int i = 0; i < tableDataList.size(); i++) {
            result.append("private "+tableDataList.get(i).getJavaType()+" "+tableDataList.get(i).getField().toLowerCase());

            if(i != tableDataList.size() - 1 ){
                result.append(System.lineSeparator()).append(System.lineSeparator());

            }
        }

        log.info("getTypeWithField: "+result);

        return result.toString();
    }

    public static String getPrimaryFieldVariable(List<TableDto> primaryFieldList) {  //userno, another...

        StringBuffer result = new StringBuffer();

        for (int i = 0; i < primaryFieldList.size(); i++) {
            result.append(primaryFieldList.get(i).getField().toLowerCase());

            if(i != primaryFieldList.size() - 1 ){
                result.append(", ");
            }
        }
        log.info("getPrimaryFieldVariable: "+result);

        return result.toString();
    }

    public static String getPrimaryFieldVariableWithBraces(List<TableDto> primaryFieldList) { // {userno}/{another}/....

        StringBuffer result = new StringBuffer();

        for (int i = 0; i < primaryFieldList.size(); i++) {

            result.append("{");
            result.append(primaryFieldList.get(i).getField().toLowerCase());
            result.append("}");

            if(i != primaryFieldList.size() - 1 ){
                result.append("/");
            }
        }
        log.info("getPrimaryFieldVariableWithBraces: "+result);

        return result.toString();
    }
    public static String getWhereConditionOfPrimary(List<TableDto> primaryFieldList) { // USERNO = #{userNo} and anotherUnique = #{anotherUnique} ...

        StringBuffer result = new StringBuffer();

        for (int i = 0; i < primaryFieldList.size(); i++) {

            result.append(primaryFieldList.get(i).getField());
            result.append(" = ");
            result.append("#{");
            result.append(primaryFieldList.get(i).getField().toLowerCase());
            result.append("}");

            if(i != primaryFieldList.size() - 1 ) {
                result.append(" and ");
            }
        }

        log.info("getWhereConditionOfPrimary: "+result);

        return result.toString();
    }

    public static String getTargetFieldOfInsert(List<TableDto> tableDataList) { // null, #{userId},#{userNm},#{userPw},#{userRole},#{photo},#{deptNo},#{deleteFlag}

        StringBuffer result = new StringBuffer();

        for (int i = 0; i < tableDataList.size(); i++) {

            if(tableDataList.get(i).getExtra().equals("auto_increment")){
                // 기본키 필드가 auto_increment 일 경우 null 로 설정
                result.append("null");
                continue;
            }

            result.append("#{");
            result.append(tableDataList.get(i).getField().toLowerCase());
            result.append("}");

            if(i != tableDataList.size() - 1 ){
                result.append(", ");
            }
        }
        log.info("getTargetFieldOfInsert: " + result);

        return result.toString();
    }

    public static String getTargetFieldOfUpdate(List<TableDto> tableDataList) { // 기본키를 제외한 필드에 대해 USERNM = #{userNm}, USERNM = #{userNm}.....

        StringBuffer result = new StringBuffer();

        for (int i = 0; i < tableDataList.size(); i++) {

            if(tableDataList.get(i).getKey().equals("PRI")){
                // 기본키 필드는 업데이트 대상에서 제외
                continue;
            }

            result.append(tableDataList.get(i).getField());
            result.append(" = ");
            result.append("#{");
            result.append(tableDataList.get(i).getField().toLowerCase());
            result.append("}");

            if(i != tableDataList.size() - 1 ){
                result.append(", ");
            }
        }
        log.info("getTargetFieldOfUpdate: " + result);

        return result.toString();
    }


}
