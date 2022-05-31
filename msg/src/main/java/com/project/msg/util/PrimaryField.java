package com.project.msg.util;

import com.project.msg.dto.TableDto;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class PrimaryField {


    public static String getPrimaryFieldParameter(List<TableDto> primaryFieldList) { //@PathVariable Integer userno, ...

        StringBuffer result = new StringBuffer();

        for (int i = 0; i < primaryFieldList.size(); i++) {
            result.append("@PathVariable "+primaryFieldList.get(i).getJavaType()+" "+primaryFieldList.get(i).getField().toLowerCase());

            if(i != primaryFieldList.size()-1){
                result.append(", ");
            }
        }

        log.info("getPrimaryFieldParameter: "+result);

        return result.toString();
    }

    public static String getPrimaryFieldVariable(List<TableDto> primaryFieldList) {  //userno, another...

        StringBuffer result = new StringBuffer();

        for (int i = 0; i < primaryFieldList.size(); i++) {
            result.append(primaryFieldList.get(i).getField().toLowerCase());

            if(i != primaryFieldList.size()-1){
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

            if(i != primaryFieldList.size()-1){
                result.append("/");
            }
        }
        log.info("getPrimaryFieldVariableWithBraces: "+result);

        return result.toString();
    }
}
