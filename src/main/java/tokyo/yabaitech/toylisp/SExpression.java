package tokyo.yabaitech.toylisp;

public sealed interface SExpression {
    public record Int(int value) implements SExpression {}

    public record Symbol(String name) implements SExpression {}

    public record ConsCell(SExpression car, SExpression cdr) implements SExpression {}
}
