public final class Logger {
    public enum Level {
        ERROR, WARNING, INFO, DEBUG
    }

    private Logger() {
        // No implementation needed
    }

    public static Level level = Level.INFO;

    public static boolean isError() {
        return levelHigherThan(Level.ERROR);
    }

    public static boolean isWarning() {
        return levelHigherThan(Level.WARNING);
    }

    public static boolean isInfo() {
        return levelHigherThan(Level.INFO);
    }

    public static boolean isDebug() {
        return levelHigherThan(Level.DEBUG);
    }

    public static void error(final String message, final Object... args) {
        if (isError()) {
            log(message, args);
        }
    }

    public static void warning(final String message, final Object... args) {
        if (isWarning()) {
            log(message, args);
        }
    }

    public static void info(final String message, final Object... args) {
        if (isInfo()) {
            log(message, args);
        }
    }

    public static void debug(final String message, final Object... args) {
        if (isDebug()) {
            log(message, args);
        }
    }

    private static boolean levelHigherThan(final Level levelx) {
        return level.ordinal() >= levelx.ordinal();
    }

    private static void log(final String message, final Object... args) {
        System.out.printf(message + "\n", args);
    }
}
