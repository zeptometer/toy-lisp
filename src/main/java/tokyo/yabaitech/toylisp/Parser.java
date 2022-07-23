package tokyo.yabaitech.toylisp;

import tokyo.yabaitech.toylisp.ParseResult.ParseSuccess;

import tokyo.yabaitech.toylisp.ParseResult.ParseFailure;

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
    static ParseResult parseConsCell(String token) {
        return new ParseFailure("unimplemented");
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
