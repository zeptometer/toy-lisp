package tokyo.yabaitech.toylisp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class PrinterTests {
    @Test
    void printInt() {
        assertEquals("0", Printer.print(new SExp.Number(0)));
        assertEquals("1", Printer.print(new SExp.Number(1)));
        assertEquals("10", Printer.print(new SExp.Number(10)));
        assertEquals("-182372", Printer.print(new SExp.Number(-182372)));
    }

    @Test
    void printSymbol() {
        assertEquals("hello", Printer.print(new SExp.Symbol("hello")));
    }
}
