package tokyo.yabaitech.toylisp;

public class Printer {
    public static String print(SExpression exp) {
        return switch (exp) {
            case SExpression.Int x -> Integer.toString(x.value());
            case SExpression.Symbol s -> s.name();
            default -> throw new UnsupportedOperationException("doom!");
        };
    }
}
