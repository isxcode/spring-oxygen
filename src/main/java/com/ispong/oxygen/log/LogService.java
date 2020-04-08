package com.ispong.oxygen.log;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * log service
 *
 * @author ispong
 * @since 0.0.1
 */
@Slf4j
@Service
public class LogService {

    private final LogRepository logRepository;

    public LogService(LogRepository logRepository){

        this.logRepository=logRepository;
    }

	/**
	 * query LogEntity
	 *
	 * @return List[LogEntity]
	 * @since 0.0.1
	 */
	public List<LogEntity> queryLog(){

		return logRepository.queryLog();
	}

}