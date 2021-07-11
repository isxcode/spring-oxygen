package com.isxcode.oxygen.flysql.parse;

import com.isxcode.oxygen.flysql.core.FlysqlExecute;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public interface SqlValue {

    default String othersValue(String val) {
        return FlysqlExecute.addSingleQuote(val);
    }

    default String othersVarchar(String val) {
        return FlysqlExecute.addSingleQuote(val);
    }

    default String getBooleanValue(String val) {
        return val.toString();
    }

    default String getDateValue(String val) {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return FlysqlExecute.addSingleQuote(sdf2.format(sdf.parse(String.valueOf(val))));
        }catch (ParseException e) {
            return null;
        }
    }

    default String getLocalDateTimeValue(String val) {
        return FlysqlExecute.addSingleQuote(val);
    }

    default String getLocalDateValue(String val) {
        return FlysqlExecute.addSingleQuote(val);
    }
}
