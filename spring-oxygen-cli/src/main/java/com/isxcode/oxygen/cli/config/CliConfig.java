package com.isxcode.oxygen.cli.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.shell.command.CommandRegistration;

@Configuration
public class CliConfig {

	@Bean
	CommandRegistration commandRegistration() {
		return CommandRegistration.builder().command("C").build();
	}
}
