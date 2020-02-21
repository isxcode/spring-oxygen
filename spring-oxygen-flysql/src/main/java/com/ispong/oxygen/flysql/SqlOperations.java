/*
 * Copyright [2020] [ispong]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ispong.oxygen.flysql;

import java.util.List;

/**
 * sql operations
 *
 * @author ispong
 * @version v0.1.0
 */
public interface SqlOperations {

    /**
     * query
     *
     * @param <A> model class
     * @return data
     * @since 2019-12-26
     */
    <A> List<A> query();

    /**
     * query page size
     *
     * @param page page
     * @param size size
     * @param <A>  model class
     * @return data
     * @since 2019-12-26
     */
    <A> List<A> query(Integer page, Integer size);

    /**
     * get one
     *
     * @param <A> model class
     * @return data
     * @since 2019-12-26
     */
    <A> A getOne();

    /**
     * update
     *
     * @since 2019-12-26
     */
    void doUpdate();

    /**
     * save
     *
     * @param obj model object
     * @since 2019-12-26
     */
    void save(Object obj);

    /**
     * delete
     *
     * @since 2019-12-26
     */
    void doDelete();

    /**
     * count
     *
     * @return countNum
     * @since 0.0.1
     */
    Integer count();
}
