package com.project.msg.util;

import com.project.msg.dto.TableDto;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
public class FieldUtil {


    public static String getPrimaryFieldParameterWithPathVariable(List<TableDto> primaryFieldList) { //@PathVariable Integer userno, @PathVariable String another...

        StringBuffer result = new StringBuffer();

        for (int i = 0; i < primaryFieldList.size(); i++) {
            result.append("@PathVariable "+primaryFieldList.get(i).getJavaType()+" "+primaryFieldList.get(i).getField().toLowerCase());

            if(i != primaryFieldList.size() - 1 ){
                result.append(", ");
            }
        }

        log.info("getPrimaryFieldParameterWithPathVariable: "+result);

        return result.toString();
    }
    public static String getApiImplicitParamsOfPrimaryField(List<TableDto> primaryFieldList) { //@ApiImplicitParam(name = "keyword", paramType = "query", required = true),...

        StringBuffer result = new StringBuffer();

        for (int i = 0; i < primaryFieldList.size(); i++) {

            if(i > 0){
                result.append("\t").append("\t");
            }

            result.append("@ApiImplicitParam(name = \""+primaryFieldList.get(i).getField().toLowerCase()+"\", paramType = \"query\", required = true)");

            if(i != primaryFieldList.size() - 1 ){
                result.append(",").append(System.lineSeparator());
            }
        }

        log.info("getApiImplicitParamsOfPrimaryField: "+result);

        return result.toString();
    }
    public static String getApiImplicitParamsForRegistration(List<TableDto> tableDataList) { //@ApiImplicitParam(name = "keyword", paramType = "query", required = true),...

        StringBuffer result = new StringBuffer();

        // 등록: 기본키 필드가 아니고 동시에 auto_increment 가 아닌 필드는 모두 등록 대상 필드이다.
        List<TableDto> registrationFieldList = tableDataList.stream().filter(column -> !column.getKey().equals("PRI") && !column.getExtra().equals("auto_increment")).collect(Collectors.toList());

        for (int i = 0; i < registrationFieldList.size(); i++) {

            if(i > 0){
                result.append("\t").append("\t");
            }

            result.append("@ApiImplicitParam(name = \"");
            result.append(registrationFieldList.get(i).getField().toLowerCase());
            result.append("\"");
            result.append(",");
            result.append(" paramType = \"query\"");

            if(registrationFieldList.get(i).getKey().equals("PRI")){
                result.append(", required = true");
            }

            result.append(")");

            if(i != registrationFieldList.size() - 1 ){
                result.append(",").append(System.lineSeparator());
            }
        }

        log.info("getApiImplicitParamsForRegistration: "+result);

        return result.toString();
    }
    public static String getApiImplicitParamsForUpdate(List<TableDto> tableDataList) { //@ApiImplicitParam(name = "keyword", paramType = "query", required = true),...

        StringBuffer result = new StringBuffer();

        // 수정: 기본키 + 수정대상필드

        for (int i = 0; i < tableDataList.size(); i++) {

            if(i > 0){
                result.append("\t").append("\t");
            }

            result.append("@ApiImplicitParam(name = \"");
            result.append(tableDataList.get(i).getField().toLowerCase());
            result.append("\"");
            result.append(",");
            result.append(" paramType = \"query\"");

            if(tableDataList.get(i).getKey().equals("PRI")){
                result.append(", required = true");
            }

            result.append(")");

            if(i != tableDataList.size() - 1 ){
                result.append(",").append(System.lineSeparator());
            }
        }

        log.info("getApiImplicitParamsForUpdate: "+result);

        return result.toString();
    }

    public static String getTypeWithField(List<TableDto> tableDataList) { //Integer userno, String another...


        StringBuffer result = new StringBuffer();

        for (int i = 0; i < tableDataList.size(); i++) {
            String javaType = tableDataList.get(i).getJavaType();

            if(javaType.indexOf("java.lang") != -1){
                javaType = javaType.replace("java.lang.","");
            }else {
                javaType = javaType.substring(javaType.lastIndexOf(".") + 1);
            }

            log.info("getTypeWithField javaType="+javaType);

            result.append("\t ");
            result.append("private "+javaType+" "+tableDataList.get(i).getField().toLowerCase()+";");

            if(i != tableDataList.size() - 1 ){
                result.append(System.lineSeparator()).append(System.lineSeparator());

            }
        }

        log.info(""+result);

        return result.toString();
    }

    public static String getImportClassList(List<TableDto> tableDataList) {

        StringBuffer result = new StringBuffer();
        Set<String> importSet = new HashSet<String>();

        for (int i = 0; i < tableDataList.size(); i++) {
            importSet.add(tableDataList.get(i).getJavaType());
        }
        log.info("importSet:"+importSet.toString());

        Iterator<String> iterator = importSet.iterator();

        while(iterator.hasNext()){

            String javaType = iterator.next();

            if(javaType.indexOf("java.lang") != -1){
                continue;
            }
//            log.info("getImportClassList: "+javaType);

            result.append("import "+javaType+";");
            result.append(System.lineSeparator());
        }

        log.info(""+result);

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
                result.append("null, ");
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

    public static String getPrimaryFieldParameterDto(List<TableDto> primaryFieldList) { //Integer userno, ...

        StringBuffer result = new StringBuffer();

        for (int i = 0; i < primaryFieldList.size(); i++) {
            result.append(primaryFieldList.get(i).getJavaType()+" "+primaryFieldList.get(i).getField().toLowerCase());

            if(i != primaryFieldList.size()-1){
                result.append(", ");
            }
        }

        log.info("getPrimaryFieldParameterDto: "+result);

        return result.toString();
    }

    public static String getParameterOfPrimaryField(List<TableDto> primaryFieldList) { //Integer userno, String anotherUniqueField...

        StringBuffer result = new StringBuffer();

        for (int i = 0; i < primaryFieldList.size(); i++) {
            result.append(primaryFieldList.get(i).getJavaType()+" "+primaryFieldList.get(i).getField().toLowerCase());

            if(i != primaryFieldList.size() - 1 ){
                result.append(", ");
            }
        }

        log.info("getParameterOfPrimaryField: "+result);

        return result.toString();
    }

}
