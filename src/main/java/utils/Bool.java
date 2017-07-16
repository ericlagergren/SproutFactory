package utils;

public class Bool {
    public static boolean parse(String s) {
        return     s.equalsIgnoreCase("true")
                || s.equalsIgnoreCase("t")
                || s.equalsIgnoreCase("1")
                || s.equalsIgnoreCase("yes")
                || s.equalsIgnoreCase("y")
                || s.equalsIgnoreCase("on")
                || s.equalsIgnoreCase("oui"); // French programmers, y'know?
    }
    public static boolean parse(int x) { return x == 0 ? false : true; }
    public static String format(boolean b) { return b ? "t" : "f"; }
}