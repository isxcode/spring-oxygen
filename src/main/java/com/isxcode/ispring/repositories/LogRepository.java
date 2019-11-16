package com.isxcode.ispring.repositories;

import com.isxcode.ispring.model.entity.LogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 日志repository
 *
 * @author ispong
 * @version v0.1.0
 * @date 2019-11-15
 */
public interface LogRepository extends JpaRepository<LogEntity, String>, JpaSpecificationExecutor {


}
