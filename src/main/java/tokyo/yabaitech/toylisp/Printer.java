package tokyo.yabaitech.toylisp;

public class Printer {
    public static String print(SExpression exp) {
        return switch (exp) {
            case SExpression.Int x -> Integer.toString(x.value());
            case SExpression.Symbol s -> s.name();
            case SExpression.ConsCell cell -> printConsCell(cell);
        };
    }

    private static String printConsCell(SExpression.ConsCell cell) {
        String left = print(cell.car());
        String right = print(cell.cdr());

        return "(" + left + " . " + right + ")";
    }
}
