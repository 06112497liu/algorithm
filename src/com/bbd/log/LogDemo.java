package com.bbd.log;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.PatternLayout;
import ch.qos.logback.core.Appender;
import ch.qos.logback.core.ConsoleAppender;
import ch.qos.logback.core.Layout;
import ch.qos.logback.core.LayoutBase;
import ch.qos.logback.core.pattern.PatternLayoutBase;
import ch.qos.logback.core.util.StatusPrinter;
import org.slf4j.LoggerFactory;

/**
 * @author Liuweibo
 * @version Id: LogDemo.java, v0.1 2018/1/16 Liuweibo Exp $$
 */
public class LogDemo {
    public static void main(String[] args) {
        Logger logger = (Logger) LoggerFactory.getLogger("com.bbd");
//        // build appender
//        Appender appender = new ConsoleAppender();
//        appender.setName("CONSOLE");
//
//        // build layout
//        PatternLayoutBase layout = new PatternLayout();
//        layout.setPattern("%date{yyyy-MM-dd HH:mm:ss}||%thread||%level||%logger:%line||%msg %ex||%X{traceId}||%n");
//
//        //appender.doAppend(layout);
//        logger.addAppender(appender);
//        LoggerContext lc = logger.getLoggerContext();
//        StatusPrinter.print(lc);
        // setting log level
        LoggerContext lc = logger.getLoggerContext();
        System.out.println(lc.getName());
        logger.setLevel(Level.WARN);
        // Level.INFO < Levle.WARN
        logger.info("你好"); // no print
        logger.error("错了"); // print

    }
}
    
    