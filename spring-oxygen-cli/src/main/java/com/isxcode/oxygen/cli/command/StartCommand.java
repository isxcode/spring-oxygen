package com.isxcode.oxygen.cli.command;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.shell.component.flow.ComponentFlow;
import org.springframework.shell.component.flow.ResultMode;
import org.springframework.shell.component.flow.SelectItem;
import org.springframework.shell.standard.AbstractShellComponent;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Configuration
@ShellComponent
@RequiredArgsConstructor
public class StartCommand extends AbstractShellComponent {

    private final ComponentFlow.Builder componentFlowBuilder;

    /*
     * init command
     *
     * @ispong
     */
    @ShellMethod(key = "init", value = "init spring project from oxygen", group = "oxygen cli")
    public void init() {

        // project
        Map<String, String> projectSingleSelectItems = new HashMap<>();
        projectSingleSelectItems.put("Gradle Project", "gradle");
        projectSingleSelectItems.put("Maven Project", "maven");

        // language
        Map<String, String> languageSingleSelectItems = new HashMap<>();
        languageSingleSelectItems.put("Java", "java");
        languageSingleSelectItems.put("Kotlin", "kotlin");
        languageSingleSelectItems.put("Groovy", "groovy");

        // release version
        Map<String, String> springBootSingleSelectItems = new HashMap<>();
        springBootSingleSelectItems.put("3.0.0(SNAPSHOT)", "3.0.0-snapshot");
        springBootSingleSelectItems.put("3.0.0(RC2)", "3.0.0-rc2");
        springBootSingleSelectItems.put("2.7.6(SNAPSHOT)", "2.7.6-snapshot");
        springBootSingleSelectItems.put("2.6.14(SNAPSHOT)", "2.6.14-snapshot");
        springBootSingleSelectItems.put("2.6.13", "2.6.13");

        // Packaging
        Map<String, String> packageSingleSelectItems = new HashMap<>();
        packageSingleSelectItems.put("Jar", "jar");
        packageSingleSelectItems.put("War", "war");

        // Java
        Map<String, String> javaSingleSelectItems = new HashMap<>();
        javaSingleSelectItems.put("19", "19");
        javaSingleSelectItems.put("17", "17");
        javaSingleSelectItems.put("11", "11");
        javaSingleSelectItems.put("8", "8");

        // dependencies
        List<SelectItem> dependMultiSelectItems = Arrays.asList(
            SelectItem.of("Spring Web: Build web, including RESTful, applications using Spring MVC. Uses Apache Tomcat as the default embedded container.", "spring web", true, true),
            SelectItem.of("Lombok: Java annotation library which helps to reduce boilerplate code.", "lombok")
        );

        // flow command
        ComponentFlow initFlow = componentFlowBuilder.clone().reset()
            .withSingleItemSelector("project")
            .name("Project Build Type")
            .defaultSelect("maven")
            .selectItems(projectSingleSelectItems)
            .and()
            .withSingleItemSelector("language")
            .name("Language")
            .defaultSelect("java")
            .selectItems(languageSingleSelectItems)
            .and()
            .withSingleItemSelector("spring boot")
            .name("Spring Boot")
            .defaultSelect("2.6.13")
            .selectItems(springBootSingleSelectItems)
            .and()
            .withStringInput("group")
            .name("Project Metadata: \n Group:")
            .defaultValue("com.example")
            .and()
            .withStringInput("artifact")
            .name("Artifact:")
            .defaultValue("demo")
            .and()
            .withStringInput("name")
            .name("Name:")
            .defaultValue("demo")
            .and()
            .withStringInput("description")
            .name("Description:")
            .defaultValue("Demo project for Spring Boot")
            .and()
            .withStringInput("Package name")
            .name("Package name:")
            .defaultValue("com.example.demo")
            .and()
            .withSingleItemSelector("package")
            .name("Packaging")
            .defaultSelect("jar")
            .selectItems(packageSingleSelectItems)
            .and()
            .withSingleItemSelector("java")
            .name("Java")
            .defaultSelect("8")
            .selectItems(javaSingleSelectItems)
            .and()
            .withMultiItemSelector("多选")
            .name("Multi1")
            .selectItems(dependMultiSelectItems)
            .and()
            .withPathInput("path")
            .name("Path")
            .and()
            .withConfirmationInput("confirmation")
            .name("Init Now?")
            .and()
            .build();

        initFlow.run();
    }

    @ShellMethod(key = "flow showcase2", value = "Showcase with options", group = "Flow")
    public String showcase2(
        @ShellOption(help = "Field1 value", defaultValue = ShellOption.NULL) String field1,
        @ShellOption(help = "Field2 value", defaultValue = ShellOption.NULL) String field2,
        @ShellOption(help = "Confirmation1 value", defaultValue = ShellOption.NULL) Boolean confirmation1,
        @ShellOption(help = "Path1 value", defaultValue = ShellOption.NULL) String path1,
        @ShellOption(help = "Single1 value", defaultValue = ShellOption.NULL) String single1,
        @ShellOption(help = "Multi1 value", defaultValue = ShellOption.NULL) List<String> multi1
    ) {
        Map<String, String> single1SelectItems = new HashMap<>();
        single1SelectItems.put("key1", "value1");
        single1SelectItems.put("key2", "value2");

        List<SelectItem> multi1SelectItems = Arrays.asList(
            SelectItem.of("key1", "value1"),
            SelectItem.of("key2", "value2"),
            SelectItem.of("key3", "value3")
        );

        List<String> multi1ResultValues = multi1 != null ? multi1 : new ArrayList<>();

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
            .resultValues(multi1ResultValues)
            .resultMode(ResultMode.ACCEPT)
            .and()
            .build();
        ComponentFlow.ComponentFlowResult result = flow.run();
        StringBuilder buf = new StringBuilder();
        result.getContext().stream().forEach(e -> {
            buf.append(e.getKey());
            buf.append(" = ");
            buf.append(e.getValue());
            buf.append("\n");
        });
        return buf.toString();
    }

    @ShellMethod(key = "flow conditional", value = "Second component based on first", group = "Flow")
    public void conditional() {
        Map<String, String> single1SelectItems = new HashMap<>();
        single1SelectItems.put("Field1", "field1");
        single1SelectItems.put("Field2", "field2");
        ComponentFlow flow = componentFlowBuilder.clone().reset()
            .withSingleItemSelector("single1")
            .name("Single1")
            .selectItems(single1SelectItems)
            .next(ctx -> ctx.getResultItem().get().getItem())
            .and()
            .withStringInput("field1")
            .name("Field1")
            .defaultValue("defaultField1Value")
            .next(ctx -> null)
            .and()
            .withStringInput("field2")
            .name("Field2")
            .defaultValue("defaultField2Value")
            .next(ctx -> null)
            .and()
            .build();
        flow.run();
    }

    @ShellMethod(key = "flow autoselect", value = "Autoselect item", group = "Flow")
    public void autoselect(
        @ShellOption(defaultValue = "Field3") String defaultValue
    ) {
        Map<String, String> single1SelectItems = IntStream.range(1, 10)
            .boxed()
            .collect(Collectors.toMap(i -> "Field" + i, i -> "field" + i));

        ComponentFlow flow = componentFlowBuilder.clone().reset()
            .withSingleItemSelector("single1")
            .name("Single1")
            .selectItems(single1SelectItems)
            .defaultSelect(defaultValue)
            .sort((o1, o2) -> o1.getName().compareTo(o2.getName()))
            .and()
            .build();
        flow.run();
    }
}
