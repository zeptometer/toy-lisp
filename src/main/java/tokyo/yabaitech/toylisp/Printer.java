package tokyo.yabaitech.toylisp;

public class Printer {
    public static String print(SExpression exp) {
        return switch (exp) {
            case SExpression.Int x -> Integer.toString(x.value());
            default -> throw new UnsupportedOperationException("doom!");
        };
    }
}
