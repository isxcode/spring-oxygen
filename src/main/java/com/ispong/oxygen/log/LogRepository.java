package com.ispong.oxygen.log;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import com.ispong.oxygen.flysql.Flysql;

import java.util.List;

/**
 * log repository
 *
 * @author ispong
 * @since 0.0.1
 */
@Slf4j
@Repository
public class LogRepository {

	/**
	 * query LogEntity
	 *
	 * @return List[LogEntity]
	 * @since 0.0.1
	 */
	public List<LogEntity> queryLog(){

		return Flysql.select(LogEntity.class).query();
	}

}