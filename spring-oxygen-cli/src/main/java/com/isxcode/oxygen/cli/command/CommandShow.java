package com.isxcode.oxygen.cli.command;

import org.jline.utils.AttributedString;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.stereotype.Component;

@Component
public class CommandShow implements PromptProvider {

    @Override
    public AttributedString getPrompt() {

        switch (LocalStorage.commandShow) {

            default:
                return new AttributedString("spring-oxygen:>");
        }

    }
}
