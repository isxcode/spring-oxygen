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
package com.ispong.oxygen.common;

import com.ispong.oxygen.flysql.FlysqlException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.UUID;

/**
 * hibernate 自定义id生成策略
 *
 * @author ispong
 * @version v0.1.0
 */
@Slf4j
public class BaseUuidGenerator implements IdentifierGenerator {

    /**
     * 自定义主键规则
     *
     * @since 2019-12-13
     */
    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {

        try {
            Method method = object.getClass().getMethod("getUuid");
            Object uuid = method.invoke(object);
            if (uuid == null) {
                return UUID.randomUUID().toString().replace("-", "");
            }
            return uuid.toString();
        } catch (Exception e) {
            throw new FlysqlException("必须使用uuid");
        }
    }

    @Override
    public boolean supportsJdbcBatchInserts() {
        return false;
    }

}
