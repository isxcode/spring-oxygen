package com.isxcode.oxygen.core.command;

import com.isxcode.oxygen.core.exception.OxygenException;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteWatchdog;
import org.apache.commons.exec.PumpStreamHandler;

/*
 * https://commons.apache.org/proper/commons-exec/tutorial.html
 *
 * @ispong
 */
@Slf4j
public class CommandUtils {

    public static String LINUX_BASH = "/bin/sh";
    public static String WINDOWS_CMD = "C:\\Windows\\System32\\cmd.exe";
    public static long DEFAULT_WAITING_TIME = 60000;

    /**
     * execute command to log file
     *
     * @param command command
     * @param logPath log file path
     * @return exit code
     */
    public static int execute(String command, String logPath) {

        return execute(command, logPath, DEFAULT_WAITING_TIME);
    }

    /**
     * execute command, return log content
     *
     * @param command command
     * @return command result str
     */
    public static String executeBack(String command) {

        return executeBack(command, DEFAULT_WAITING_TIME);
    }

    /**
     * execute command to log file
     *
     * @param command command
     * @return exit code
     */
    public static int execute(String command) {

        return execute(command, DEFAULT_WAITING_TIME);
    }

    /**
     * execute command, return log content
     *
     * @param command     command
     * @param waitingTime waiting timeMillis
     * @return exit code
     */
    public static int execute(String command, long waitingTime) {

        DefaultExecutor executor = new DefaultExecutor();
        CommandLine cmdLine = generateCommandLine(command);

        try {
            // set watchdog for waiting
            ExecuteWatchdog watchdog = new ExecuteWatchdog(waitingTime);
            executor.setWatchdog(watchdog);
            return executor.execute(cmdLine);

        } catch (IOException e) {
            log.debug(e.getMessage());
            throw new OxygenException("execute command error");
        }
    }

    /**
     * execute command to log file
     *
     * @param command     command
     * @param logPath     log file path
     * @param waitingTime waiting timeMillis
     * @return exit code
     */
    public static int execute(String command, String logPath, long waitingTime) {

        DefaultExecutor executor = new DefaultExecutor();
        CommandLine cmdLine = generateCommandLine(command);

        try (FileOutputStream fileOutputStream = new FileOutputStream(logPath, true)) {

            // set log file path
            PumpStreamHandler streamHandler =
                new PumpStreamHandler(fileOutputStream, fileOutputStream, null);
            executor.setStreamHandler(streamHandler);

            // set watchdog for waiting
            ExecuteWatchdog watchdog = new ExecuteWatchdog(waitingTime);
            executor.setWatchdog(watchdog);

            // execute command
            return executor.execute(cmdLine);
        } catch (IOException e) {
            log.debug(e.getMessage());
            throw new OxygenException("execute command error");
        }
    }

    /**
     * execute command, return log content
     *
     * @param command     command
     * @param waitingTime waiting timeMillis
     * @return result string
     */
    public static String executeBack(String command, long waitingTime) {

        DefaultExecutor executor = new DefaultExecutor();
        CommandLine cmdLine = generateCommandLine(command);

        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

            PumpStreamHandler streamHandler =
                new PumpStreamHandler(byteArrayOutputStream, byteArrayOutputStream, null);
            executor.setStreamHandler(streamHandler);

            // set watchdog for waiting
            ExecuteWatchdog watchdog = new ExecuteWatchdog(waitingTime);
            executor.setWatchdog(watchdog);
            executor.execute(cmdLine);

            byte[] bytes = byteArrayOutputStream.toByteArray();
            return new String(bytes, 0, bytes.length);

        } catch (IOException e) {
            log.debug(e.getMessage());
            throw new OxygenException("execute command error");
        }
    }

    /**
     * generate CommandLine
     *
     * @param command command
     * @return CommandLine
     */
    private static CommandLine generateCommandLine(String command) {

        CommandLine cmdLine;
        String[] cmd;
        if (System.getProperty("os.name").toLowerCase().contains("windows")) {
            cmd = new String[]{"/c", command};
            cmdLine = CommandLine.parse(WINDOWS_CMD);
        } else {
            cmd = new String[]{"-c", command};
            cmdLine = CommandLine.parse(LINUX_BASH);
        }
        cmdLine.addArguments(cmd, false);
        return cmdLine;
    }
}
