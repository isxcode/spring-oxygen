package com.isxcode.oxygen.cli.repository;

import com.isxcode.oxygen.cli.constant.FreecodeConstants;
import com.isxcode.oxygen.cli.entity.TableColumnInfo;
import com.isxcode.oxygen.cli.exception.FreecodeException;
import com.isxcode.oxygen.cli.utils.FreecodeUtils;
import com.isxcode.oxygen.core.reflect.ReflectUtils;
import com.isxcode.oxygen.flysql.enums.DataBaseType;
import java.util.ArrayList;
import java.util.List;

/**
 * freecode repository
 *
 * @author ispong
 * @since 0.0.1
 */
public class FreecodeRepository {

	/**
	 * get tables columns
	 *
	 * @param tableName tableName
	 * @param ignoreFields ignoreFields
	 * @return List[TableColumnInfo]
	 * @since 2020-01-09
	 */
	public List<TableColumnInfo> getTableColumns(String tableName, List<String> ignoreFields) {

		// get sql string
		String sqlStr;
		if (DataBaseType.MYSQL.name().equals(FreecodeUtils.getDataBaseType())) {
			sqlStr = "show full columns from " + tableName;
		} else if (DataBaseType.H2.name().equals(FreecodeUtils.getDataBaseType())) {
			sqlStr = "show columns from " + tableName;
		} else {
			throw new FreecodeException("dataSource type not support");
		}

		// get table columns
		List<TableColumnInfo> columnList = new ArrayList<>();
		//        todo 完善freecode
		//        List<TableColumnInfo> tableColumnInfos =
		// Flysql.select(TableColumnInfo.class).sql(sqlStr).query();
		List<TableColumnInfo> tableColumnInfos = new ArrayList<>();
		// remove ignore columns
		ignoreFields.addAll(FreecodeConstants.sysColumns);
		tableColumnInfos.forEach(
				e -> {
					if (!ignoreFields.contains(ReflectUtils.humpToLine(e.getField()))) {
						columnList.add(e);
					}
				});

		return columnList;
	}

	/**
	 * get table info
	 *
	 * @param tableName tableName
	 * @return table info
	 * @since 2020-01-09
	 */
	public String getTableInfo(String tableName) {

		//        todo 完善freecode
		//        if (DataBaseType.MYSQL.name().equals(FreecodeUtils.getDataBaseType())) {
		//
		//            String sqlStr = "SELECT TABLE_COMMENT FROM information_schema.TABLES where
		// TABLE_NAME = '" + tableName + "'";
		//            TableInfo tableInfo = Flysql.select(TableInfo.class).sql(sqlStr).getOne();
		//            if (tableInfo == null || tableInfo.getTableComment() == null) {
		//                return "";
		//            } else {
		//                return tableInfo.getTableComment();
		//            }
		//        }else{
		return "";
		//        }
	}
}
