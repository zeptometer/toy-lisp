package tokyo.yabaitech.toylisp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class PrinterTest {
    @Test
    public void printInteger() {
        assertEquals("0", Printer.print(new SExpression.Int(0)));
        assertEquals("1", Printer.print(new SExpression.Int(1)));
        assertEquals("123", Printer.print(new SExpression.Int(123)));
    }

    @Test
    public void printSymbol() {
        assertEquals("nil", Printer.print(new SExpression.Symbol("nil")));
        assertEquals("hoge", Printer.print(new SExpression.Symbol("hoge")));
        assertEquals("fib", Printer.print(new SExpression.Symbol("fib")));
    }
}
