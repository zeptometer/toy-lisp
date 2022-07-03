package tokyo.yabaitech.toylisp;

public class Printer {
    public static String print(SExpression exp) {
        return switch (exp) {
            case SExpression.Int x -> Integer.toString(x.value());
            case SExpression.Symbol s -> s.name().equals("nil") ? "()" : s.name();
            case SExpression.ConsCell cell -> printConsCell(cell);
        };
    }

    private static String printConsCell(SExpression.ConsCell cell) {
        return "(" + printConsCellInner(cell) + ")";
    }

    // (1 2 3) ==> "1 2 3"
    // (1 2 3 . 4) ==> "1 2 3 . 4"
    // ((1 . 2) 3) ==> "(1 . 2) 3"
    private static String printConsCellInner(SExpression.ConsCell cell) {
        String left = print(cell.car());
        // nil と cons cell だけ特別扱いしたい

        switch (cell.cdr()) {
            case SExpression.Symbol s:
                if (s.name().equals("nil")) {
                    // 例: (3 . nil) ==> "3"
                    return left;
                }
                break;
            case SExpression.ConsCell cdr:
                // 例: (1 . (x . y)) ==> "1 x . y" = "1" + " " + "x . y"
                String cdrString = printConsCellInner(cdr);
                return left + " " + cdrString;
            default:
        }
        // 例: (1 . x) ==> "1 . x"
        String right = print(cell.cdr());

        return left + " . " + right;
    }
}
