# 1 概述
学习计算机语言和学习人类语言不同，不是人与人之间的语言，像人和“动物”之间的语言，要理解计算机运行的思维来学习一门语言。

学习路径：
<极客时间>
- java核心语法
- 环境搭建和使用
- 语言基础
- 语言难点解析
- 常用库（工具箱）
- 面向对象思想
- 代码练兵（例子）
- 做出一个小游戏

常用概念：
```text
JDK：Java Development Kit

关键字：语言的保留字，不能拿来做标识符用
语言关键字：
class
enum
public
private
static
int、long、short、float、double、boolean、String
void

标识符：代码中的各种命名，比如类型，变量名，方法名
由大小写英文字母、数字和_组成。
区分大小写。
不以数字开头

表达式（expression）：语言最基本的一个运算，比如1+1
语句（statement）: 由表达式组成，;结尾的一个句子，类似平常说的一句话
代码块（block）: {}括起来的部分

进制：
十六进制：0-9,A-F这16个值，10=A

bit：一个二进制位叫一个bit，网络带宽的单位都是bit。
byte：8个二进制位组成一个byte，硬盘的存储单位都是byte。
byte是计算机衡量存储的基本单位，但是内部使用时是bit运算。


bytecode:字节码。类似CPU指令集。是java跨平台的基础。字节码也是java规范中的内容。
Class文件：java源文件可以通过编译生成的class文件。class文件就是字节码组成的。只有有合适的编译器，别的语法也可以生成class文件，在jvm上执行。
执行字节码的是JVM。不同平台有不同的JVM。class是和平台无关的。所以java可以做到write once,run anywhere。

基于字节码有其它语言：Scala、Groovy、Kotlin

JVM规范
JMM规范


classpath:文件夹或者jar包的组合。一般执行java命令的时候都会配，参数是这个程序用到的所有jar包的路径。
java命令会默认把当前路径加入到classpath中。
```


环境变量：
```text
windows
JAVA_HOME=D:\soft\java11
%JAVA_HOME%\bin
```

常用命令：
```cmd
#验证jdk是否安装成功
# java -version
# javac -version

#编译单个java文件
# cd code
# javac Hello.java
# java Hello
```

语法规则：
```text
1.区分大小写
2.一个java源码文件里只能有一个public修饰的类，并且这个类名要和文件名一致
3.一个源文件里只能有一行package，并且要放在第一行的位置
```

```text
public:全局可见
default:当前包可见
private：当前类可见

protected：当前包可见和子类可见（在继承的类中使用）

final + 类：不可被继承
final + 方法：不可被子类覆盖
final + 变量：不可被赋值

构造方法不能final

```

```text
this关键字
this指向当前对象的引用。
用在方法里，指的是调用方法的对象引用。

this(参数)会调用对应的构造方法，只能在构造方法里调用，且只能写在第一行。

super关键字
super指向最近一个父类的引用。
super(参数)调用父类的构造方法，只能写在子类构造方法的第一行，和this(参数)不能同时出现。
```

```text
注释
// xxxx
/* xxxx */
/**
xxxx
xxxx
*/

java doc注释
@version
@date
@author
@since
@param
@return

```

```text
方法签名：方法名+依次参数类型
返回值不属于方法签名
方法签名是一个方法在一个类中的唯一标识

同一个类中，方法名可以相同，方法签名一定不同

对于方法名一样，签名不一样，成为方法重载（overload）

无论是否重载，参数类型可以不完全匹配的规则是实参可以自动类型转换为形参类型
如果参数满足自动类型转换，那么重载的规则就是去找最近的那个。

```

```
构造方法
方法名必须和类名一致
没有返回值
没有显式构造方法，类会有个默认的无参构造方法
如果有显示的构造方法，类就没有了默认的无参构造方法
构造方法无法被.操作符或者普通方法调用，只能通过new语句时间接的调用
为什么构造方法没有返回值？因为new返回的永远是对象的引用，有返回值也没用。

构造方法的重载和普通方法一样
在构造方法里才能调用重载的构造方法，语法：this(参数列表)，并且只能写在第一行，后面可以有其它代码。
构造方法不能调用自己
调用重载的构造方法时，不能使用成员变量，因为这个成员变量还没被初始化，用不了。

```

```text
静态变量（类变量）
使用static修饰的变量
可以不用初始化，类会赋予类型的默认值。
所有代码都可以使用静态变量
静态变量在整个程序里只有一份值。成员变量是每个对象都会有一个。

静态方法（类方法）
使用static修饰的方法
只能使用参数和静态变量。
就是没有this自引用的方法。

静态代码块
static {
	// 代码块
}
静态代码块在每个class初始化的时候被调用一次。从上到下执行。
```

```text
方法覆盖（override， 多态的基础）：
子类方法和父类方法签名一样，并且返回值也一样。

@Override
该注解有助于编译器帮忙检查
该注解表示该方法覆盖了父类的方法或者实现了继承的接口的方法，如果没有就报错。

覆盖的时候，子类的可见性范围要>=父类方法的可见范围
```

# 2 基础语法


## 2.1 基本数据类型

### 2.1.1 整数类型

```text
byte：
占用1个byte
-128-127

short：
占用2个byte
-32768-32767

int：
占用4个byte
-2^31 ~ 2^31-1
-2147483648 ~ 2147483647
java中缺省整数字面量类型是int。

long:
占用8个byte
-9223372036854774808--9223372036854774807
带有L后缀

```

### 2.1.2 浮点数类型
```text
float:
占用4个byte
单精度

double:
占用8个byte
双精度，精度是float的2倍
java中缺省浮点数字面量类型是double。

都拥有符号位
```

### 2.1.3 布尔和字符类型
```text
boolean:
占用1个byte
值域是true和false

char:
占用2个byte，值域是所有字符，比如'a'，'我'
```

### 2.1.4 字面值的8进制和16进制
```text
以0开头的整数为8进制
05=5
011=8

以0x开头的整数就是16进制
0xF=15
0x11=17
```

eg:
```java
byte v1 = 1;
short v2 = 2;
int v3 = 3;
long v4 = 4L; # 4l

int m1 = 011;
int m2 = 0xF;

float v5 = 1.0F; # 1.0f
double v6 = 1.0;

boolean v7 = false;
char v8 = 'a';
```

## 2.2 运算符
```text
注意溢出
返回值的类型可能和参与运算的类型不一样，高低类型的转换（自动类型转换）
强制类型转换

自动类型转换：
不会出现问题的类型转换，比如丢失精度等，语言做自动类型转换，低精度向高精度转

精度排序：double>float>long>int>short>byte

char可以转成int
虽然char和short都是2个byte，但是char是无符号数，超过了short可以表示的范围，所以不能自动转成short。

强制类型转换：
可能会出现问题的转换，语法是：(目标类型)变量值
会造成精度的丢失。


除赋值运算符外，运算符本身不会改变变量的值

用处：
按位运算符：
掩码（Mask）
位运算符：
高效除2

```

```text
取模运算符：%
用来计算余数
小数、负数也可以用来取模

位运算：
按位并：&
按位或：|
按位异或: ^
按位取反：~

位移运算：
>> : 符号位不动，其它位右移，符号位后边补0，又称带符号右移
>>> : 符号位一起右移，左边补0，又称无符号右移
<< : 左移，右边补0。左移没有带符号位说法，因为符号位在最左侧


```

eg:
```java
// 1111 1000
int a = 0xF8;
// 1111 0100
int b = 0xF4;
// 1111 1111
int c = 0xFF;

System.out.println(a & b);
System.out.println(a | b);
System.out.println(a ^ b);
System.out.println(~c);

a = 0x400;
System.out.println(a >> 1); // /2的效果
System.out.println(a >> 2); // /4的效果 
System.out.println(a << 1);

int i = 65;
char c = (char)i;
int i1 = c;

int a = 1;
System.out.println("a++=" + a++); // 1
System.out.println(a); // 2
a = 1;
System.out.println("+=a=" + (++a)); // 2
System.out.println(a); // 2

int b = 10;
System.out.println("b--=" + b--); // 10
System.out.println(b); // 9
b = 10;
System.out.println("--b=" + --b); // 9
System.out.println(b); // 9

// A=65 a=97
System.out.println((char) (97)); // a  
System.out.println((char) (65)); // A

char a = 'A'; // 65
int temp = a;
for (int i = 0; i<=25; i++) {
	char t = (char)(temp+i);
}
```

用位运算来生成掩码eg:
```java
int base = 1; // int有4byte，32个bit位，可以用来表示32种状态
int state_0 = base;
int state_1 = base << 1;
int state_2 = base << 2;
int state_3 = base << 3;

int data = 5;
boolean is_state_0 = (data & state_0) != 0;
boolean is_state_1 = (data & state_1) != 0;
boolean is_state_2 = (data & state_2) != 0;
boolean is_state_3 = (data & state_3) != 0;
```

## 2.3 字符集和编码
```text
字符集（charset）：
字符的集合。字符就是char类型。
GBK包含所有汉字字符。
ASCII包含所有英文字符。
Unicode包含世界上所有的字符，包括UTF-8,UTF-16等类型的编码。

UTF-8（8-bit Unicode Transformation Format）

Unicode和GBK等常用的字符集，都会兼容ASCII。

编码(encoding):
char本质也是数字，将数字映射到字符就是编码。

Java的字符集：
是UTF-16编码的Unicode。
UTF-16用16个bit，即2个byte，所以char类型占用2个byte。
```

eg:
```java
// 输出特殊字符
// 键盘敲不出来，可以在ascii表里找到该字符对于的数字
char c = 数字+''

// 转义符（escape character）
\n
\" = "
\t
\u16进制数 = unicode编码对应的字符

String s = "ab1\"dc\tff\n"
int a = 65;
char c = (char)a;
char c1 = '\u81b9';

```

## 2.4 字符串
```text
字符串可以和任何类型进行 + 运算。
不是基本数据类型
String不是保留字。
String加法不会改变原String变量的值。赋值语句会。

String对象是不可变的。（immutable）
/** The value is used for character storage. */  
private final char value[];
用来存储字符的数组是private final，而且不提供任何修改的方法。


```

## 2.5 逻辑语句
```java
if (boolean值 或者 boolean运算表达式) {
	// todo
} else if (boolean) {
	// todo
} else {
	// todo
}

// 只有一个语句，可以省略{}
```

```java
for (初始语句; 循环体条件表达式; 循环体后语句) {
	// 循环体
	// break;
	// continue
}

for (int i=1;i<=9;i++) {  
	for (int j=1;j<=i;j++) {  
		System.out.print(j+"*"+i+"=" + i*j + "\t");  
		if (j == i) System.out.println();  
	}  
}

while (条件表达式) {
	// 循环体
	// break;
	// continue;
}

警惕死循环 endless loop / infinite loop

switch (值) {
	case 值1：
		// 语句1
		// 语句2
		break;
	default:
		// 语句	
}
如果没有遇到break语句，就会一直执行下去
default是可选的，case都没匹配到就执行default
```

变量的作用域：
```java
int a = 1;
{
	System.out.println(a); // 1
	a++;
	int b = 2;
	System.out.println(b); // 2
}
System.out.println(a); // 2
// System.out.println(b); // 报错，b的生效范围在{}中

int a = 1;  
{  
	int b = 2;  
	{  
		a = 2;  
		int c = 3;  
	}  
	{  
		b = 3;  
		// c = 1;  // error,not in scope
	}  
}  
//b = 3; // error,not in scope
```


```java
java.util.Math
Math.random() 生成0-1的随机数，类型是double

从标准输入读
Scanner sc = new Scanner(System.in);
String s = sc.nextLine(); // 读输入的一行
int i = sc.nextInt(); // 读一个整数

Scanner sc = new Scanner(System.in);  
for (int i=0;i<2;i++) {  
	System.out.println(sc.nextInt());  
}
BigInteger i = sc.nextBigInteger(); // 大数
```

## 2.6 引用数据类型
**java中的数据类型分为基本数据类型和引用数据类型**

```java
instanceof 操作符
if (对象 instanceof TypeCls) {
	// 再进行强制类型转换
	TypeCls c = (TypeCls)对象;
}

覆盖hashCode()同时也要覆盖equals()
覆盖的原则是，equals为true，hashCode也应该相等，这是约定俗成的规范。
即equals为true是hashCode相等的充分非必要条件，hashCode相等是equals相等的必要不充分条件。
```

## 2.7 面向对象
```text
封装
继承
多态
```

### 2.7.1 装箱和拆箱


## 2.8 类Class
```java
// 获取类对象有2种方式
Class cls = TypeCls.class;
Class cls = 对象.getClass();
```

### 2.8.1 反射

## 2.9 枚举类enum
```text
枚举就是有固定个数实例的类。
枚举的父类Enum.class

构造方法必须是private，不写默认就是private。


```

## 2.10 接口
```text
无法被实例化。
方法默认修饰符public abstract，可以省略。
不能定义局部变量，定义的成员变量默认都是public static final，可以省略。

接口可以多继承，extends Interface1, Interface2
继承的接口，可以有重复的方法，但是签名相同时，返回值必须一样，否则编译错误。

在jdk8以后，允许接口中有静态方法、私有方法、带有缺省实现的抽象方法。
缺省的方法实现，用default修饰，可以有方法体

接口中可以有私有的方法，不需要用default修饰。

接口中可以有静态方法，不需要用default修饰。
静态方法可以被实现接口的类继承。

抽象方法中声明抛出的异常是接口方法签名的一部分。

如果一个类实现了2个接口，并且这2个接口里有相同的缺省方法，编译报错。

缺省方法里有this自引用，但是只能调用接口里的方法，或者继承的接口里的方法。

实现有缺省方法的接口之后，面对缺省方法，类可以有3个选择：
1.默默继承，相当于把这部分代码拷贝到当前类中
2.重新声明为abstract，相当于把这部分代码拒之于门外
3.覆盖，重新实现
```

### 2.10.1 常用接口
```java
java.util.Iterator<E>
java.io.Closeable extends AutoCloseable

```

## 2.11 抽象类
```text
接口和类的混合体
不能实例化。

可以有构造方法。

用abstract修饰的类。
抽象类可以继承别的类或者抽象类，也可以实现接口。
可以没有抽象方法，但是仍不能实例化。

定义的抽象方法，可以是protected，也可以是缺省的，这点和接口不一样

父类的引用可以使用子类的引用赋值，抽象类也一样。
```

## 2.12 静态内部类
```text
在类中用static修饰的类

可以有访问控制符修饰。
静态内部类和静态方法、静态变量一样，都是类的静态组成部分。
```

## 2.13 成员内部类
```text
在类中直接定义的类。

内部不能有任何静态成员。否则会造成内外部类初始化的问题
可以有访问修饰符。
和成员变量一样，都是类的组成部分。

可以有final static的基本数据类型变量。

类的内部有一个外部类的引用。
访问外部类成员语法：外部类名.this.属性/方法
```

## 2.14 局部内部类
```text
在类的方法中直接定义的类。

内部不能有任何静态成员。否则会造成内外部类初始化的问题
不能有访问修饰符。
可以被final修饰。
和成员变量一样，都是类的组成部分。

可以有final static的基本数据类型变量。

类的内部有一个外部类的引用。
访问外部类成员语法：外部类名.this.属性/方法

不同于成员内部类，局部内部类可以访问参数和局部变量，但必须是实际的final.

```

## 2.15 匿名类
```text
匿名类是用来创建接口或者抽象类的实例的。
任何一个new对象的地方，都可以使用匿名类。

匿名类可以出现在任何有代码的地方。

方法里的匿名类在访问局部变量时，必须是final的。
```

## 2.16 异常处理
```text
所有异常的父类：Throwable

2类异常Error和Exception

checked exception：语法要求必须try catch或者throws处理的异常
unchecked exception：不一定要代码中处理

Error和RuntimeException是unchecked exception的父类。一般使用RuntimeException

java.lang.Throwable.class
	|-- java.lang.Exception
	|-- java.lang.Error
		|-- java.lang.RuntimeException
			|-- java.lang.NullPointerException
			|-- java.lang.IllegalArgumentException
	
```

```text
try catch finally

finally语法会在return之后一定执行
finally的赋值语句会失效
finally里return语句会覆盖之前的return的值，不建议使用。
```

```text
资源自动释放的try语句
try (语句块) {
	// todo
} catch ...
```

## 2.17 泛型
```text
泛型要慎用！！！

记住2句话：
1.编译器检查并类型擦除
2.使用时的类型转换

泛型类

泛型接口

泛型方法

```

## 2.18 注解
```text
annotation
注解：元数据的搬运工

是给类、方法、成员变量等元素增加元数据（metadata）的方式。换言之，就是描述这些元素的。和注释不同的是，这些描述会被编译器处理。

@Override
@Deprecated

```


## 2.19 集合Collection
```text

```

## 2.20 io

```text

```

## 2.21 lambda表达式

## 2.22 常用包
```text
apache common lang\io
序列化：avro、protobuf
json: jackson、Gson
http: apache http compoent
```