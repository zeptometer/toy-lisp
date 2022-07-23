package tokyo.yabaitech.toylisp;

public class Printer {
    public static String print(SExpr sExpr) {
        return switch (sExpr) {
            case SExpr.Int i -> Integer.toString(i.value());
            case SExpr.Bool b -> b.value() ? "#t" : "#f";
            case SExpr.Symbol s -> s.name();
            case SExpr.Nil s -> "()";
            case SExpr.Cons cons -> "(" + printConsRaw(cons.car(), cons.cdr()) + ")";
        };
    }

    /*
     * cons cell を受け取り、両端の括弧がない文字列表現を返す。
     * 例: (1 . 2) ==> "1 . 2", (1 2 3) ==> "1 2 3"
     */
    static String printConsRaw(SExpr car, SExpr cdr) {
        String carString = print(car);
        return switch (cdr) {
            // car = 1, cdr = (2 3) ==> "1 2 3"
            // cdr の文字列表現は "2 3" なので、これは "1" + " " + "2 3"
            case SExpr.Cons sub ->
                carString + " " + printConsRaw(sub.car(), sub.cdr());
            case SExpr.Nil _nil ->
                // car = 1, cdr = nil のときは "1" (car の文字列表現と同じ)
                carString;
            default ->
                // それ以外の時は " . " でつなぐ。
                carString + " . " + print(cdr);
        };
    }
}
