package com.isxcode.ispring.common;

import com.isxcode.ispring.exception.IsxcodeException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.id.UUIDGenerator;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.UUID;

/**
 * hibernate 自定义id生成策略
 *
 * @author ispong
 * @date 2019-11-27
 * @version v0.1.0
 */
@Slf4j
public class BaseUuidGenerator implements IdentifierGenerator {

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
            throw new IsxcodeException("必须使用uuid");
        }
    }

    @Override
    public boolean supportsJdbcBatchInserts() {
        return false;
    }

}
