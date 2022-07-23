package tokyo.yabaitech.toylisp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import tokyo.yabaitech.toylisp.SExpr.Bool;
import tokyo.yabaitech.toylisp.SExpr.Cons;
import tokyo.yabaitech.toylisp.SExpr.Int;
import tokyo.yabaitech.toylisp.SExpr.Nil;
import tokyo.yabaitech.toylisp.SExpr.Symbol;
import tokyo.yabaitech.toylisp.ParseResult.ParseSuccess;
import tokyo.yabaitech.toylisp.ParseResult.ParseFailure;

public class ParserTests {
    @Test
    void parseInt() {
        assertEquals(new ParseSuccess(1, new Int(1)), Parser.parse("1"));
        assertEquals(new ParseSuccess(5, new Int(15223)), Parser.parse("15223"));
        assertEquals(new ParseSuccess(3, new Int(-32)), Parser.parse("-32"));
    }

    @Test
    void parseBool() {
        assertEquals(new ParseSuccess(2, new Bool(true)), Parser.parse("#t"));
        assertEquals(new ParseSuccess(2, new Bool(false)), Parser.parse("#f"));
    }

    @Test
    void parseNil() {
        assertEquals(new ParseSuccess(2, new Nil()), Parser.parse("()"));
        assertEquals(new ParseSuccess(5, new Nil()), Parser.parse("(   )"));
    }

    @Test
    void parseSymbol() {
        assertEquals(new ParseSuccess(4, new Symbol("asdf")), Parser.parse("asdf"));
        assertEquals(new ParseSuccess(11, new Symbol("hello-world")), Parser.parse("hello-world"));
        assertEquals(new ParseSuccess(1, new Symbol("-")), Parser.parse("-"));
        assertEquals(new ParseSuccess(1, new Symbol("+")), Parser.parse("+"));
        assertEquals(new ParseSuccess(6, new Symbol("-world")), Parser.parse("-world"));
        assertEquals(new ParseSuccess(5, new Symbol("-1and")), Parser.parse("-1and"));
        assertEquals(new ParseSuccess(2, new Symbol("1+")), Parser.parse("1+"));
    }

    @Test
    void ignoreHeadingSpace() {
        assertEquals(new ParseSuccess(7, new Int(1522)), Parser.parse("   1522"));
        assertEquals(new ParseSuccess(5, new Bool(false)), Parser.parse("   #f"));
    }

    @Test
    void dontReadRemainingString() {
        assertEquals(new ParseSuccess(4, new Int(1522)), Parser.parse("1522  "));
        assertEquals(new ParseSuccess(4, new Int(1522)), Parser.parse("1522  hello"));
    }

    @Test
    void invalidInput() {
        assertEquals(new ParseFailure("parse expected non-empty string."), Parser.parse(""));
    }

    @Test
    void parseConsCell() {
        assertEquals(new ParseSuccess(3, new Cons(new Int(1), new Nil())), Parser.parse("(1)"));
        assertEquals(new ParseSuccess(5, new Cons(new Int(1), new Cons(new Int(2), new Nil()))), Parser.parse("(1 2)"));
        assertEquals(new ParseSuccess(7, new Cons(new Int(1), new Int(2))), Parser.parse("(1 . 2)"));
        assertEquals(new ParseSuccess(9, new Cons(new Int(1), new Cons(new Int(2), new Int(3)))),
                Parser.parse("(1 2 . 3)"));
    }
}
