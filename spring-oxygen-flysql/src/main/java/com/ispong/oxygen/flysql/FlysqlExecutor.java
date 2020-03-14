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
 * 定义sql最终执行方式
 *
 * @author ispong
 * @version v0.1.0
 */
public interface FlysqlExecutor<A> {

    /**
     * select 正常查询
     *
     * @return list[data]
     * @since 2019-12-26
     */
    List<A> query();

    /**
     * select 分页查询
     *
     * @param page page
     * @param size size
     * @return list[data]
     * @since 2019-12-26
     */
    List<A> query(Integer page, Integer size);

    /**
     * select 查询一个对象
     *
     * @return data
     * @since 2019-12-26
     */
    A getOne();

    /**
     * update 数据更新
     *
     * @since 2019-12-26
     */
    void doUpdate();

    /**
     * save 保存一条数据
     *
     * @param obj model object
     * @since 2019-12-26
     */
    void save(Object obj);

    /**
     * delete 删除数据
     *
     * @since 2019-12-26
     */
    void doDelete();

    /**
     * count 查询数据条数
     *
     * @return countNum
     * @since 0.0.1
     */
    Integer count();
}
