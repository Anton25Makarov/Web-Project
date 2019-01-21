package com.epam.library.util;

public class StringUtil {
    private static final String NUMBER_PATTERN = "\\d+";

    public boolean areNotNullAndNotEmpty(String... strings) {
        if (strings == null || strings.length == 0) {
            return false;
        }
        for (String string : strings) {
            if (isNullOrEmpty(string)) {
                return false;
            }
        }
        return true;
    }

    public boolean areNotNull(String... strings) {
        if (strings == null || strings.length == 0) {
            return false;
        }
        for (String string : strings) {
            if (string == null) {
                return false;
            }
        }
        return true;
    }

    public boolean isNullOrEmpty(String string) {
        return string == null || string.isEmpty();
    }

    public boolean isNumber(String string) {
        if (string == null) {
            return false;
        }
        return string.matches(NUMBER_PATTERN);
    }

    public boolean areNumbers(String... strings) {
        if (strings == null || strings.length == 0) {
            return false;
        }
        for (String string : strings) {
            if (!isNumber(string)) {
                return false;
            }
        }
        return true;
    }

    public boolean isBoolean(String string) {
        return (string != null) && string.equalsIgnoreCase("true");
    }
}
