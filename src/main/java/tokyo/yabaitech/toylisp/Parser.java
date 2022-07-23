package tokyo.yabaitech.toylisp;

import tokyo.yabaitech.toylisp.ParseResult.ParseSuccess;

import java.util.ArrayList;
import java.util.Collections;

import tokyo.yabaitech.toylisp.ParseResult.ParseFailure;
import tokyo.yabaitech.toylisp.SExpr.Bool;
import tokyo.yabaitech.toylisp.SExpr.Cons;
import tokyo.yabaitech.toylisp.SExpr.Int;
import tokyo.yabaitech.toylisp.SExpr.Nil;

/*

SExpression ->
  Atom (先頭がカッコじゃない)
  | Listish (先頭がカッコ)

Atom-ish ->
  Atom-ish
  // ただし () を除く

Listish ->
  ( -+-- )
     +-- SExpression+ ( . SExpression )? )

*/

public class Parser {
    public static ParseResult parse(String s) {
        var offset = skipSpace(s);
        if (offset >= s.length()) {
            return new ParseFailure("parse expected non-empty string.");
        }

        final var remaining = s.substring(offset);
        return (switch (s.charAt(offset)) {
            case '(' -> parseListish(remaining);
            default -> parseAtom(remaining);
        }).addOffset(offset);
    }

    static int skipSpace(String s) {
        int position = 0;
        while (position < s.length() && s.charAt(position) == ' ')
            position++;
        return position;
    }

    static int skipUntilDelimiter(String s) {
        int position = 0;
        while (position < s.length() && " ()".indexOf(s.charAt(position)) < 0)
            position++;
        return position;
    }

    public static ParseResult parseListish(String s) {
        var offset = 1; // skip '('
        offset += skipSpace(s.substring(offset));
        if (offset >= s.length()) {
            return new ParseFailure("expected nil or conscell");
        }

        return (switch (s.charAt(offset)) {
            case ')' -> new ParseSuccess(1, new SExpr.Nil());
            default -> parseConsCell(s.substring(offset));
        }).addOffset(offset);
    }

    // 先頭のカッコを除いたConsCellをパースする
    // x) -> new Cons(parse(x), new Nil())
    static ParseResult parseConsCell(String s) {
        var offset = 0;
        var parsedCells = new ArrayList<SExpr>();
        do {
            var parseResult = parse(s);
            switch (parseResult) {
                case ParseFailure failure:
                    return failure;
                case ParseSuccess success:
                    parsedCells.add(success.result());
                    s = s.substring(success.charsRead());
                    offset += success.charsRead();
            }
            var spaceOffset = skipSpace(s);
            s = s.substring(spaceOffset);
            offset += spaceOffset;
        } while (!s.isEmpty() && s.charAt(0) != ')');
        if (s.charAt(0) != ')') {
            return new ParseFailure("expected )");
        }
        offset += 1;
        Collections.reverse(parsedCells);
        return new ParseResult.ParseSuccess(offset,
                parsedCells.stream().reduce(new Nil(), (sExpr, cons) -> new Cons(cons, sExpr)));
    }

    /**
     * Atomの解析。異常なAtomに対し ParseFailure を返すのもここでやる。
     */
    public static ParseResult parseAtom(String s) {
        if (s.startsWith("#t")) {
            return new ParseSuccess(2, new SExpr.Bool(true));
        }
        if (s.startsWith("#f")) {
            return new ParseSuccess(2, new SExpr.Bool(false));
        }

        final var offsetToIntOrSymbol = skipUntilDelimiter(s);
        final var intOrSymbol = s.substring(0, offsetToIntOrSymbol);

        final int value;
        try {
            value = Integer.parseInt(intOrSymbol);
        } catch (NumberFormatException ex) {
            // symbol
            if (intOrSymbol.isEmpty()) {
                return new ParseFailure("expected atom, but found delimiter");
            }
            return new ParseSuccess(intOrSymbol.length(), new SExpr.Symbol(intOrSymbol));
        }

        return new ParseSuccess(intOrSymbol.length(), new SExpr.Int(value));
    }
}
