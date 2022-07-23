package tokyo.yabaitech.toylisp;

/**
 * SExprはLispの抽象構文木を表すデータ構造です。
 * * 整数: -1, 0, 1 ...
 * * 真偽値: #t, #f
 * * シンボル(変数名, 構文キーワード): x, y, hoge, fuga, fib, define, ...
 * * nil: null的な特別な値 (空リスト)
 * * コンスセル: (0 . 1), (fib . hoge), (fib . 0), ...
 * ** リスト記法: (0 1 2) = (0 . (1 . (2 . nil))), (0 1 2 . 3) = (0 . (1 . (2 . 3)))
 */
public sealed interface SExpr {
    public record Int(int value) implements SExpr {
    }

    public record Bool(boolean value) implements SExpr {
    }

    public record Symbol(String name) implements SExpr {
    }

    public record Nil() implements SExpr {
    }

    public record Cons(SExpr car, SExpr cdr) implements SExpr {
    }
}
