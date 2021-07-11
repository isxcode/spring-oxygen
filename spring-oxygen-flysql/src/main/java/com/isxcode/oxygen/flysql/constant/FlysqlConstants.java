package com.isxcode.oxygen.flysql.constant;

/**
 * flysql constants
 *
 * @author ispong
 * @since 0.0.1
 */
public interface FlysqlConstants {

    /**
     * 默认数据库名称
     */
    String PRIMARY_DATASOURCE_NAME = "oxygen_primary_database_name";

    /**
     * default view name
     */
    String PRIMARY_VIEW_NAME = "primary";

    /**
     * replace key
     */
    String SELECT_REPLACE_CONTENT = "##flysql_columns_info##";

    /**
     * H2
     */
    String H2_DB = "H2";

    /**
     * MYSQL
     */
    String MYSQL_DB = "MySQL";

    /**
     * ORACLE
     */
    String ORACLE_DB = "Oracle";

    /**
     * Microsoft SQL Server
     */
    String SQL_SERVER_DB = "Microsoft SQL Server";
}

