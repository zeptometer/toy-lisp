package tokyo.yabaitech.toylisp;

public sealed interface SExp {
    public record Number(int value) implements SExp {}

    public record Symbol(String name) implements SExp {}

    public record ConsCell(SExp car, SExp cdr) implements SExp {}
}
