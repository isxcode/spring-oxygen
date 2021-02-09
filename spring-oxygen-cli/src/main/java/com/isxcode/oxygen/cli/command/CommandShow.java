package com.isxcode.oxygen.cli.command;

import com.isxcode.oxygen.cli.store.LocalStorage;
import org.jline.utils.AttributedString;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.stereotype.Component;

@Component
public class CommandShow implements PromptProvider {

    @Override
    public AttributedString getPrompt() {

        String commandShowStr = "";

        switch (LocalStorage.nowCommandCode) {

            case "Select Build Tool":
                commandShowStr = "select build tool:>";
            default:
                commandShowStr = "spring-oxygen:>";
        }

        return new AttributedString(commandShowStr);
    }
}
