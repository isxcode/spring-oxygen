package com.isxcode.ispring.utils.sql;

import java.util.List;

/**
 * sql 执行的行为
 *
 * @author ispong
 * @version v0.1.0
 * @date 2019-12-26
 */
public interface SqlOperations {

    /**
     * 查询列表
     *
     * @return 返回List类型数据对象
     * @since 2019-12-26
     */
    <A> List<A> query();

    /**
     * 查询某一个值
     *
     * @return 返回映射对象
     * @since 2019-12-26
     */
    <A> A getOne();

    /**
     * 执行更新
     *
     * @since 2019-12-26
     */
    void doUpdate();

    /**
     * 执行保存
     *
     * @since 2019-12-26
     */
    void save();
}
