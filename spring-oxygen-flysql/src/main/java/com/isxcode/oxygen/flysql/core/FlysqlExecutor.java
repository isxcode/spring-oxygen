package com.isxcode.oxygen.flysql.core;

import java.util.List;

/**
 * sql executor
 *
 * @author ispong
 * @version v0.1.0
 */
public interface FlysqlExecutor<A> {

    /**
     * select
     *
     * @return list[data]
     * @since 2019-12-26
     */
    List<A> query();

    /**
     * page select
     *
     * @param page page
     * @param size size
     * @return list[data]
     * @since 2019-12-26
     */
    List<A> query(Integer page, Integer size);

    /**
     * select one
     *
     * @return data
     * @since 2019-12-26
     */
    A getOne();

    /**
     * update data
     *
     * @since 2019-12-26
     */
    void doUpdate();

    /**
     * save data
     *
     * @param entity model object
     * @since 2019-12-26
     */
    void save(A entity);

    /**
     * batch save data
     *
     * @param entity model object
     * @since 2019-12-26
     */
    void batchSave(List<A> entity);

    /**
     * delete data
     *
     * @since 2019-12-26
     */
    void doDelete();

    /**
     * count data
     *
     * @return countNum
     * @since 0.0.1
     */
    Integer count();
}
