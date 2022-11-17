package com.isxcode.oxygen.cli.config;

import lombok.RequiredArgsConstructor;
import org.jline.terminal.impl.DumbTerminal;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.shell.command.CommandExecution;
import org.springframework.shell.command.CommandParser;
import org.springframework.shell.command.CommandRegistration;
import org.springframework.shell.component.flow.ComponentFlow;
import org.springframework.shell.component.flow.ResultMode;
import org.springframework.shell.component.flow.SelectItem;
import org.springframework.util.StringUtils;

import java.util.*;

@Configuration
@RequiredArgsConstructor
public class CliConfig {

    private final ComponentFlow.Builder componentFlowBuilder;

    @Bean
    public CommandRegistration showcaseRegistration() {

        return CommandRegistration.builder()
            .command("flow", "showcase3")
            .description("Showcase")
            .withOption()
            .longNames("field1")
            .and()
            .withOption()
            .longNames("field2")
            .and()
            .withOption()
            .longNames("confirmation1")
            .type(Boolean.class)
            .and()
            .withOption()
            .longNames("path1")
            .and()
            .withOption()
            .longNames("single1")
            .and()
            .withOption()
            .longNames("multi1")
            .and()
            .withTarget()
            .consumer(ctx -> {

                String field1 = ctx.getOptionValue("field1");
                String field2 = ctx.getOptionValue("field2");
                Boolean confirmation1 = ctx.getOptionValue("confirmation1");
                String path1 = ctx.getOptionValue("path1");
                String single1 = ctx.getOptionValue("single1");
                String asdf = ctx.getOptionValue("multi1");
                List<String> multi1 = new ArrayList<>();
                if (StringUtils.hasText(asdf)) {
                    multi1.add(asdf);
                }

                Map<String, String> single1SelectItems = new HashMap<>();
                single1SelectItems.put("key1", "value1");
                single1SelectItems.put("key2", "value2");
                List<SelectItem> multi1SelectItems = Arrays.asList(SelectItem.of("key1", "value1"),
                    SelectItem.of("key2", "value2"), SelectItem.of("key3", "value3"));
                ComponentFlow flow = componentFlowBuilder.clone().reset()
                    .withStringInput("field1")
                    .name("Field1")
                    .defaultValue("defaultField1Value")
                    .resultValue(field1)
                    .resultMode(ResultMode.ACCEPT)
                    .and()
                    .withStringInput("field2")
                    .name("Field2")
                    .resultValue(field2)
                    .resultMode(ResultMode.ACCEPT)
                    .and()
                    .withConfirmationInput("confirmation1")
                    .name("Confirmation1")
                    .resultValue(confirmation1)
                    .resultMode(ResultMode.ACCEPT)
                    .and()
                    .withPathInput("path1")
                    .name("Path1")
                    .resultValue(path1)
                    .resultMode(ResultMode.ACCEPT)
                    .and()
                    .withSingleItemSelector("single1")
                    .name("Single1")
                    .selectItems(single1SelectItems)
                    .resultValue(single1)
                    .resultMode(ResultMode.ACCEPT)
                    .and()
                    .withMultiItemSelector("multi1")
                    .name("Multi1")
                    .selectItems(multi1SelectItems)
                    .resultValues(multi1)
                    .resultMode(ResultMode.ACCEPT)
                    .and()
                    .build();
                ComponentFlow.ComponentFlowResult result = flow.run();

                boolean hasTty = !((ctx.getTerminal() instanceof DumbTerminal) && ctx.getTerminal().getSize().getRows() == 0);
                if (hasTty) {
                    StringBuilder buf = new StringBuilder();
                    result.getContext().stream().forEach(e -> {
                        buf.append(e.getKey());
                        buf.append(" = ");
                        buf.append(e.getValue());
                        buf.append("\n");
                    });
                    ctx.getTerminal().writer().print(buf.toString());
                    ctx.getTerminal().writer().flush();
                } else {
                    List<CommandParser.CommandParserException> errors = new ArrayList<>();
                    result.getContext().stream().forEach(e -> {
                        if (e.getValue() == null) {
                            errors.add(CommandParser.CommandParserException.of(String.format("Missing option, longnames='%s'", e.getKey())));
                        }
                    });
                    if (!result.getContext().containsKey("single1")) {
                        errors.add(CommandParser.CommandParserException.of("Missing option, longnames='single'"));
                    }
                    if (!errors.isEmpty()) {
                        throw CommandExecution.CommandParserExceptionsException.of("Missing options", errors);
                    }
                }
            })
            .and()
            .build();
    }

}
