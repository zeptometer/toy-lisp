# ゆるいテック.fm

yuruitech.fm は大体都内のソフトウェアエンジニアがゆるく
ソフトウェアエンジニアリングについて語るチャンネルです。

## 目標
* Lisp処理系を作る
* fibonacci を動かせるようにする

```
; f(0) = 0, f(1) = 1, f(2) = 1, f(3) = 2, f(4) = 3, f(5) = 5, ...
(define fib (lambda (x)
	(if (<= x 1) x
		(+ (fib (- x 1)) (fib (- x 2))))))
```

## 要求
* 整数リテラル
* define
* lambda (anonymous function, 匿名関数)
* if
* 算術 (<=, +, -)
* 再帰的定義
* 関数呼び出し
* REPL (Read-Eval-Print Loop)
- インタープリタ

## TODO
String -(Parser)-> AST (抽象構文木) -(Evaluator)-> (何らかの値)

* [x] 抽象構文木
* [x] 整数リテラル 1, 10
* [x] シンボル x, define, if
	* [ ] nil
* [x] コンスセル (1 . 10) (fib . (3 . nil))
	* [ ] リスト (+ 1 2 3) -> (+ . (1 . (2 . (3 . nil))))
* [ ] Printer
* [x] 整数
* [x] シンボル
* [x] コンスセル
	* [ ] リスト記法
* [ ] Int
* [ ] Symbol
* [ ] Cons cell
* [ ] Parser
* [ ] Evaluator
* [ ] REPL

## 方針
* Java 17 + pattern match
* TDD (Test-Driven Development, テスト駆動開発)で進める
