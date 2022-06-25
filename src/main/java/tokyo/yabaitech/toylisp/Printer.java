package tokyo.yabaitech.toylisp;

public class Printer {
    public static String print(SExp sexp) {
        return switch (sexp) {
            case SExp.Number num -> Integer.toString(num.value());
            case SExp.Symbol symbol -> null;
            case SExp.ConsCell cell -> null;
        };
    }
}
