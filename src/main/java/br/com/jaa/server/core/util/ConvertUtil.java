package br.com.jaa.server.core.util;

public class ConvertUtil {

    private ConvertUtil() {}

    public static Long toLong(String s) {
        if (s == null) return 0L;
//        if (!StringUtils.isNumeric(s)) return 0L;
        return Long.parseLong(s);
    }

}
