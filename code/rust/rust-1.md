# 1、基本介绍

《Rust实战》《Rust In Action》2022

系统编程

编写更安全软件



核心概念：

所有权系统

trait



包管理

错误处理

条件编译

内存模型

文件操作

多线程

网络编程



## 安装

todo

## hello world

```shell
cd ~/rustdemo
# cargo new会遵照标准模板创建一个项目
cargo new hello
cd hello
tree
hello
|--src
	|--main.rs
|--Cargo.toml

cargo run
tree
hello
|--Cargo.toml
|--src
	|--main.rs
|--target
	|--debug
		|--build
		|--deps
		|--examples
		|--native
		|--hello
|--Cargo.lock
|--Cargo.toml
```

例子2：

```rust
cargo new hello2
cd hello2
vim src/main.rs

// main.rs
fn greet_world() {
    println!("hello world");
    // 赋值语句，更恰当的说法叫变量绑定，使用let关键字
    let a = "aaa";
    let b = "bbb";
    let c = "ccc";
    let arr = [a, b, c]; // 数组字面量使用[]
    for item in arr.iter() { // 很多类型都有iter()方法，返回一个迭代器
        println!("{}", &item); // &表示借用item的值，用于只读的访问
    }
}

fn main() {
    greet_world(); // 调用函数
}
```

例子3：

对csv数据的一些基本处理

```rust
// 在可执行的项日中main()函数是必须的
fn main() {
    // "\ -> 忽略掉末尾的换行符
    let p_data = "\
    a,1
    b,2
    c,3
    ";
    let rows = p_data.lines();
    for (i, item) in rows.enumerate() {
        // 跳过表头行和只含有空白符的行
        if i == 0 || item.trim().len() == 0 {
            continue;
        }
        // 将每行文本进行分割
        let names: Vec<_> = item
        	.split(',')
        	.map(|name| name.trim()) // 修剪掉每个字段中两端的空白符
        	.collect(); // 构建具有多个字段的集合
        // cfg!用于在编译时配置
        if cfg!(debug_assertions) {
            // eprintln!用于输出到标准错误stderr
            eprintln!("debug: {:?} -> {:?}", item, names);
        }
        
        let name = names[0];
        // 试图把字段解析为一个浮点数
        if let OK(length) = names[1].parse::<f32>() {
            // println!用于输出到标准输出stdout
            println!("{}, {}", name, length);
        }
    }
}
```





项目说明：

`Cargo.toml` 文件描述了项目的元数据，例如项目的名称、项目的版本号及其依赖项。

源代码保存在src 目录中。

Rust源文件使用`.rs`作为它的文件扩展名。



`cargo run`说明：

默认使用调试模式（debug mode）来编译代码，这样可以提供最大化的错误信息（error information）



`Cargo.lock`文件指定了所有依赖项的具体版本号。这样项目发布后，总是可以相同的依赖进行构建。



## 宏

宏类似于函数，但它返回的是代码而不是值。

通常宏用于简化常见的代码模式。

可以把宏看作—类奇特的函数，提供了避免“样板代码” （bollcrplateCode）的能力。

对于`println!`宏来说，实际上在底层进行了大量的类型检测工作，所以才能把任意的数据类型输出至屏幕上。



占位符`{}`表示Rust应该使用程序员定义的方法将该值表示为一个字符串。

而`{:?}`则表示要求使用该值的默认表示形式。







## 流程控制



`if let`是一种有条件地处理数据的简明方法。

如果成功解析，`parse()`方法会返回`Ok(T)`（这里的T代表任何类型）；反之如果解析失败，它会返回`Err(E)`（这里的E代表一个错误类型）。`if let OK(T)`的效果就是忽略任何错误的情况。

`parse::<f32>()`就有一个内嵌的类型注解。





## 方法语义

Rust不是面向对象的，不支持继承，但是Rust用到了面向对象语言里的方法语法。



## 函数

**函数可以接收和返回函数**

**隐式返回**

> Rust提供了return关键字，但通常情况下会将其省略。
>
> Rust是一门基于**表达式**的语言





## 闭包

闭包（closure）也叫作匿名函数或lambda函数。



## 类型注解

**类型注解作为给编译器的提示信息**



## 条件编译

条件编译不会被包含到该程序的发布构建（release build）当中。



## Vec动态数组

Vec类型是动态数组，是vector的缩写。

它是一个可以动态扩展的集合类型。

`Vec<_>`表示要求Rust推断出此动态数组的元素类型。





# Cargo

构建工具

> 将Rust代码转换成可执行的二进制文件

包管理器

> 管理项目依赖包的下载和编译的过程





# 例子

## 芒德布罗集（Mandelbrot set）渲染器



## 一个grep的克隆



## CPU模拟器



## 自动生成艺术项目

## 一个数据库

## BTTP、NTP以及hexdump客户端

## LOGO语官解释器

## 操作系统内核



