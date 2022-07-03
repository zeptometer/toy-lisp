package tokyo.yabaitech.toylisp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static tokyo.yabaitech.toylisp.SExpression.ConsCell;
import static tokyo.yabaitech.toylisp.SExpression.Int;
import static tokyo.yabaitech.toylisp.SExpression.Symbol;

import org.junit.jupiter.api.Test;

public class PrinterTest {
    @Test
    public void printInteger() {
        assertEquals("0", Printer.print(new Int(0)));
        assertEquals("1", Printer.print(new Int(1)));
        assertEquals("123", Printer.print(new Int(123)));
    }

    @Test
    public void printSymbol() {
        assertEquals("()", Printer.print(new Symbol("nil")));
        assertEquals("hoge", Printer.print(new Symbol("hoge")));
        assertEquals("fib", Printer.print(new Symbol("fib")));
    }

    @Test
    public void printConsCell() {
        assertEquals("(1 . 1)", Printer.print(new ConsCell(new Int(1), new Int(1))));
        assertEquals(
                "(1 x . 2)",
                Printer.print(new ConsCell(new Int(1), new ConsCell(new Symbol("x"), new Int(2)))));
        assertEquals("()", Printer.print(new Symbol("nil")));
        assertEquals("(1)", Printer.print(new ConsCell(new Int(1), new Symbol("nil"))));
        assertEquals(
                "(1 2)",
                Printer.print(
                        new ConsCell(new Int(1), new ConsCell(new Int(2), new Symbol("nil")))));
    }
}
