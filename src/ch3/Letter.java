package ch3;

/**
 * Created by SungHyun Park on 2016-01-15.
 */
public class Letter {
    public static String addHeader(String text) {
        return "From PSH" + text;
    }

    public static String addFooter(String text) {
        return text + "Best Regards";
    }
}
