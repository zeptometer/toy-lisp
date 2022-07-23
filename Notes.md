# ゆるいテック.fm
yuruitech.fm は大体都内のソフトウェアエンジニアがゆるく
ソフトウェアエンジニアリングについて語るチャンネルです。

## 目標
* Lisp処理系を作る
* fibonacci を動かせるようにする

```
(define add2 (lambda (x y) (+ x y)))

; f(0) = 0, f(1) = 1, f(2) = 1, f(3) = 2, f(4) = 3, f(5) = 5, ...
(define fib (lambda (x)
    (if (<= x 1) x
        (+ (fib (- x 1)) (fib (- x 2))))))
```

## 方針
* プログラミング言語：Java 18 + pattern match
* TDD (Test-Driven Development, テスト駆動開発)で進める

## 要求
* [ ] 抽象構文木
* [ ] Printer (抽象構文木 -> String)
* [ ] Parser (String -> 抽象構文木)
* [ ] Evaluator (抽象構文木を実行)
* [ ] REPL
