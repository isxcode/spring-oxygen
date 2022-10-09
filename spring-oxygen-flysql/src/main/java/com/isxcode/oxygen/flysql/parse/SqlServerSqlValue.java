package com.isxcode.oxygen.flysql.parse;

import com.isxcode.oxygen.flysql.core.FlysqlExecute;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class SqlServerSqlValue implements SqlValue {

	@Override
	public String getBooleanValue(String val) {
		return Boolean.getBoolean(val) ? "1" : "0";
	}

	@Override
	public String othersVarchar(String val) {
		return "N" + FlysqlExecute.addSingleQuote(val);
	}

	@Override
	public String getDateValue(String val) {
		SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return "N'" + sdf2.format(sdf.parse(String.valueOf(val))) + "'";
		} catch (ParseException e) {
			return null;
		}
	}

	@Override
	public String getLocalDateTimeValue(String val) {
		val = val.replace("T", " ");
		val = val.substring(0, val.indexOf("."));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		try {
			return "N'" + sdf2.format(sdf.parse(String.valueOf(val))) + "'";
		} catch (ParseException e) {
			return null;
		}
	}

	@Override
	public String getLocalDateValue(String val) {
		return "N'" + val + "'";
	}
}
