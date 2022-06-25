# ゆるいテック.fm

yuruitech.fm は大体都内のソフトウェアエンジニアがゆるく
ソフトウェアエンジニアリングについて語るチャンネルです。


# 今回の収録について
Toy Lisp

## 目標
* Lisp処理系を作る
* fibonacci を動かせるようにする

```
; f(0) = 0, f(1) = 1, f(2) = 1, ...
(define fib (lambda (x)
    (if (<= x 1) x
        (+ (fib (- x 1)) (fib (- x 2)))))
```


## 要求
* `define` (変数定義)
* `lambda` (無名関数)
* 変数
* `if`
* 数値計算 (整数) ← fixed int
* 再帰
* REPL (Read-Evaluate-Print Loop)
> # REPLとは、プログラミング言語の実行環境の一つで、利用者が入力欄にキーボードなどから式や文を一行入力すると、即座に解釈・実行して結果を返し、再び入力可能になるもの。

## TODO
コード(String) → パース(S-expression) → 評価(何らかの値)

* [x] S式を定義する
* [] Printer
* [] Parser
  * [] 1行入力
  * [] n行入力
* [] Evaluator

```
> (+ 1 2)
> (+ 2 3)
```

```
(begin
	(+ 1 2)
	(+ 2 3)
)
```

```
(define hoge (lambda ()
    (print "hello!")
    (+ 1 2)
))
```

