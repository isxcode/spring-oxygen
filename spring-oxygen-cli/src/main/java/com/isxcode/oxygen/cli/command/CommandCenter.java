package com.isxcode.oxygen.cli.command;

import org.springframework.shell.component.StringInput;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

/**
 * all command
 *
 * @author ispong
 * @since 0.0.2
 */
@ShellComponent
public class CommandCenter {

//    @ShellMethod(key = "init", group = "Components")
//    public String stringInputCustom(boolean mask) {
//
//        StringInput component = new StringInput(getTerminal(), "Enter value", "myvalue",
//            new StringInputCustomRenderer());
//        component.setResourceLoader(getResourceLoader());
//        component.setTemplateExecutor(getTemplateExecutor());
//        if (mask) {
//            component.setMaskCharater('*');
//        }
//        StringInputContext context = component.run(StringInputContext.empty());
//        return "Got value " + context.getResultValue();
//    }
}
