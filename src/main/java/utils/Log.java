package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log {
    private static Logger logger = LogManager.getLogger(Log.class);

    private static String getClassName() {
        return Thread.currentThread().getStackTrace()[4].getClassName();
    }

    public static void info(String message) {
        logger.info(getClassName() + " - " + message +
        "\n--------------------------------------------------------------------------------------------------------");
    }

    public static void warn(String message) {
        logger.warn(getClassName() + " - " + message);
    }

    public static void error(String message) {
        logger.error(getClassName() + " - " + message);
    }

    public static void debug(String message) {
        logger.debug(getClassName() + " - " + message);
    }

    public static void error(String message, Throwable throwable) {
        logger.error(getClassName() + " - " + message, throwable);
    }
}