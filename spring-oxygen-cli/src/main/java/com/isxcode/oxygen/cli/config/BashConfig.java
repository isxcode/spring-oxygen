package com.isxcode.oxygen.cli.config;

import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class BashConfig implements PromptProvider {

    @Override
    public AttributedString getPrompt() {

        return new AttributedString("oxygen-cli:>", new AttributedStyle(AttributedStyle.RED, AttributedStyle.RED))
            .styleMatches(Pattern.compile("oxygen-cli"), AttributedStyle.BOLD);
    }
}
