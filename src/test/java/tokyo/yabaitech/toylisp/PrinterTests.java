package tokyo.yabaitech.toylisp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import tokyo.yabaitech.toylisp.SExpr.Bool;
import tokyo.yabaitech.toylisp.SExpr.Cons;
import tokyo.yabaitech.toylisp.SExpr.Int;
import tokyo.yabaitech.toylisp.SExpr.Nil;
import tokyo.yabaitech.toylisp.SExpr.Symbol;

public class PrinterTests {
    @Test
    void printInt() {
        assertEquals("3", Printer.print(new Int(3)));
        assertEquals("0", Printer.print(new Int(0)));
        assertEquals("-1", Printer.print(new Int(-1)));
        assertEquals("110023", Printer.print(new Int(110023)));
    }

    @Test
    void printBool() {
        assertEquals("#t", Printer.print(new Bool(true)));
        assertEquals("#f", Printer.print(new Bool(false)));
    }

    @Test
    void printSymbol() {
        assertEquals("hello", Printer.print(new Symbol("hello")));
        assertEquals("pohe", Printer.print(new Symbol("pohe")));
    }

    @Test
    void printNil() {
        assertEquals("()", Printer.print(new Nil()));
    }

    @Test
    void printCons() {
        assertEquals("(1)", Printer.print(new Cons(new Int(1), new Nil())));
        assertEquals("(1 2)", Printer.print(new Cons(new Int(1), new Cons(new Int(2), new Nil()))));
        assertEquals("(1 . 2)", Printer.print(new Cons(new Int(1), new Int(2))));
        assertEquals("(1 2 . 3)", Printer.print(new Cons(new Int(1), new Cons(new Int(2), new Int(3)))));
    }
}
