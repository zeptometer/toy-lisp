package tokyo.yabaitech.toylisp;

/**
 * パースしたときの結果。成功時は(何文字読んだか,結果)、失敗時は失敗したということを返す
 */
public sealed interface ParseResult {
    public record ParseSuccess(int charsRead, SExpr result) implements ParseResult {
        @Override
        public ParseResult addOffset(int offset) {
            return new ParseSuccess(charsRead + offset, result);
        }
    }

    public record ParseFailure(String message) implements ParseResult {
        @Override
        public ParseResult addOffset(int offset) {
            return this;
        }
    }

    public ParseResult addOffset(int offset);
}