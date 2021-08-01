package com.isxcode.oxygen.flysql.parse;

import com.isxcode.oxygen.flysql.core.FlysqlExecute;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class H2SqlValue implements SqlValue {

    @Override
    public String getDateValue(String val) {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return FlysqlExecute.addSingleQuote(sdf2.format(sdf.parse(String.valueOf(val))));
        } catch (ParseException ignored) {
            return null;
        }
    }
}
