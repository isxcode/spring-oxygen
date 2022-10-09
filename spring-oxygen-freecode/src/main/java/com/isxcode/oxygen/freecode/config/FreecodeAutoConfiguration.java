package com.isxcode.oxygen.freecode.config;

import com.isxcode.oxygen.freecode.controller.FreecodeController;
import com.isxcode.oxygen.freecode.properties.FreecodeProperties;
import com.isxcode.oxygen.freecode.repository.FreecodeRepository;
import com.isxcode.oxygen.freecode.service.FreecodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * freecode auto configure
 *
 * @author ispong
 * @since 0.0.1
 */
@Slf4j
@EnableConfigurationProperties(FreecodeProperties.class)
public class FreecodeAutoConfiguration {

	/**
	 * init freecode repository
	 *
	 * @return FreecodeRepository
	 * @since 0.0.1
	 */
	@Bean
	@ConditionalOnClass(FreecodeAutoConfiguration.class)
	public FreecodeRepository initFreecodeRepository() {

		return new FreecodeRepository();
	}

	/**
	 * init freecode service
	 *
	 * @param freecodeProperties freecodeProperties
	 * @param freecodeRepository freecodeRepository
	 * @return FreecodeService
	 * @since 0.0.1
	 */
	@Bean
	@ConditionalOnClass(FreecodeRepository.class)
	public FreecodeService initFreecodeService(
			FreecodeRepository freecodeRepository, FreecodeProperties freecodeProperties) {

		return new FreecodeService(freecodeRepository, freecodeProperties);
	}

	/**
	 * init freecode controller
	 *
	 * @param freecodeService freecodeService
	 * @return FreecodeController
	 * @since 0.0.1
	 */
	@Bean
	@ConditionalOnBean(FreecodeService.class)
	public FreecodeController initFreecodeController(FreecodeService freecodeService) {

		return new FreecodeController(freecodeService);
	}
}
