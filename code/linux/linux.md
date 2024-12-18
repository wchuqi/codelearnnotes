

# 简介

## 版本

内核版本

https://www.kernel.org/

内核版本分为三个部分：

主版本号、次版本号、末版本号

次版本号是奇数为开发版，偶数为稳定版



发行版本

RedHat Enterprise Linux8

Fedora

Euler

CentOS （Community Enterprise Operating System）(基于redhat)

2个图形化桌面系统：

Ubuntu

Debian



**linux系统中“万物皆文件”。**



## 虚拟机

iso镜像下载：

https://www.virtualbox.org/wiki/Downloads

https://mirrors.aliyun.com/centos/7/isos/x86_64/





# 系统操作

## 运行级别

运行级别说明：

0：关机

1：单用户【找回丢失密码】

2：多用户状态没有网络服务

3：多用户状态有网络服务

4：系统未使用保留给用户

5：图形界面

6：系统重启

常用运行级别是3和5，也可以指定默认运行级别。



```shell
# 非图形化登录系统
init 3
# 退出登录
exit

# 关机
init 0

# 切换用户
su - root
```





指定运行级别：

`init 0~6`  

查看当前运行级别：

`systemctl get-default`

`system set-default multi-user.target`表示切换到运行级别3，等价于 `init 3`

`system set-default graphical.target`表示切换到运行级别5，等价于 `init 5`



```shell
[root@localhost ~]# cat /etc/inittab 
# inittab is no longer used when using systemd.
#
# ADDING CONFIGURATION HERE WILL HAVE NO EFFECT ON YOUR SYSTEM.
#
# Ctrl-Alt-Delete is handled by /usr/lib/systemd/system/ctrl-alt-del.target
#
# systemd uses 'targets' instead of runlevels. By default, there are two main targets:
#
# multi-user.target: analogous to runlevel 3
# graphical.target: analogous to runlevel 5
#
# To view current default target, run:
# systemctl get-default
#
# To set a default target, run:
# systemctl set-default TARGET.target
#
```

```shell
[root@localhost ~]# systemctl get-default
graphical.target
```



## 找回root密码



## 终端使用

1、图形终端

2、命令行终端

3、远程终端（ssh、vnc）



## 常用目录

/ ：根目录

/root ：root用户的家目录

/home/username：普通用户的家目录

/etc：配置文件目录

/bin：命令目录

/sbin：管理命令目录

/usr/bin /usr/sbin：系统预装的其它命令



## 命令

### 帮助命令

`man`

`help`

`info`



```shell
# man 是manual 的缩写
# man 帮助用法演示
man ls

# man 也是一条命令，分为9章，可以使用man命令获得 man的帮助
man 7 man # 获取第7章帮助
man 1 ls  # 获取第1章帮助
man 1 passwd
man 5 passwd
man -a passwd

# 通过关键字搜索
man -a passwd

[root@localhost ~]# man -a passwd
--Man-- next: passwd(5) [ view (return) | skip (Ctrl-D) | quit (Ctrl-C) ]

```



```shell
# shell（命令解释器）自带的命令称为内部命令，其他的是外部命令
# 内部命令使用 help 帮助
help cd

# 外部命令使用help帮助
ls --help

# 查看命令类型，是否是内部或者外部命令
[root@localhost ~]# type cd
cd is a shell builtin
[root@localhost ~]# type ls
ls is aliased to `ls --color=auto'

```

```shell
# info帮助比help更详细，作为help的补充
info ls
```

### 文件和目录的操作

```shell
# 显示当前目录名称
pwd
# 更改当前的操作目录
cd 绝对路径
cd 相对路径
cd ..
cd .
cd -  # 回到上次的目录

# 查看当前目录下的文件
ls [选项] 参数
常用参数：
-l 长格式显示文件
-a 显示隐藏文件
-r 逆序显示
-t 按照时间顺序显示
-R 递归显示

# 建立目录
mkdir 目录1 目录2
mkdir -p /a/b/c/d/e/f
-p: 建立多级目录

# 删除目录
rmdir 目录1 # 只能删除空目录，非空目录报错
# 删除非空目录
rm -r -rf 目录1 目录2
rm -rf 目录xxxx # 要谨慎使用
-r: 删除目录（包括目录下的所有文件）
-f: 删除文件不进行提示，直接删除

# 复制文件和目录
cp [选项] 文件路径
-r: 复制目录
-p: 保留用户、权限、时间等文件属性
-a: 保留所有文件属性，等同 -dpR
cp 文件1 文件1_bak
cp -r 目录1 目录2

# 移动文件
mv [选项] 源文件 目标文件
mv [选项] 源文件 目录
mv 文件1 文件2 # 文件重命名
mv 文件1 目录1

# 查看文本
cat: 文本内容显示到终端
head: 查看文件开头
tail: 查看文件结尾
-f: 文件内容更新后，显示同步更新
wc: 统计文件内容

cat 文件1
head 文件1
head -10 文件1
tail 文件1
tail -3 文件1
wc -l file1

more file1
less file1

# 打包，压缩和解压缩
tar: 打包命令
c: 打包
x: 解包
f: 指定操作类型为文件
gzip
bzip2
扩展名：
.tar.gz  .tar.bz2  .tgz

# 打包
tar cf 1.tar /etc/aaa/bb
ls -lh 1.tar
# 打包并压缩
tar czf 1.tar.gz /etc/aaa/bb  # 压缩更快
ls -lh 1.tar.gz

tar cjf 1.tar.bz2 /etc/aaa/bb  # 压缩更小
ls -lh 1.tar.bz2

[root@localhost /]# tar cf 1.tar etc
[root@localhost /]# tar czf 1.tar.gz etc
[root@localhost /]# tar cjf 1.tar.bz2 etc
[root@localhost /]# ls -lh 1.tar.*
-rw-r--r--. 1 root root 11M Oct 21 08:13 1.tar.bz2
-rw-r--r--. 1 root root 12M Oct 21 08:13 1.tar.gz
[root@localhost /]#

# 解包
tar xf 1.tar.gz -C /tmp/dir1
```



```shell
[root@localhost ~]# ls /etc/sysconfig/network-scripts/
ifcfg-ens33  ifdown-ppp       ifup-ib      ifup-Team
ifcfg-lo     ifdown-routes    ifup-ippp    ifup-TeamPort
ifdown       ifdown-sit       ifup-ipv6    ifup-tunnel
ifdown-bnep  ifdown-Team      ifup-isdn    ifup-wireless
ifdown-eth   ifdown-TeamPort  ifup-plip    init.ipv6-global
ifdown-ib    ifdown-tunnel    ifup-plusb   network-functions
ifdown-ippp  ifup             ifup-post    network-functions-ipv6
ifdown-ipv6  ifup-aliases     ifup-ppp
ifdown-isdn  ifup-bnep        ifup-routes
ifdown-post  ifup-eth         ifup-sit
[root@localhost ~]#
```



### 通配符

```shell
# shell内建的符号
# 用途：操作多个相似（有简单规律）的文件

# 常用通配符

*: 匹配任何字符串 
?: 匹配1个字符串
[xyz]: 匹配xyz任意一个字符
[a-z]: 匹配一个范围
[!xyz]或[^xyz]: 不匹配 
```

## VI/VIM

四种模式：
1、正常模式（Normal-mode）

2、插入模式（nsert-mode）

3、命令模式（command-mode）

4、可视模式（Visual-mode）



vim命令后默认进行正常模式

ESC



插入模式：

i：当前光标

I：光标所在行的头

o：光标的下一行

O：光标的上一行

a：光标的前一个

A：光标所在行的最后



可视模式：

v



命令模式：

:



h：←

l：→

k：↑

j：↓



yy：复制整行

p：粘贴



3yy：复制光标所在向下3行



y$：复制光标到行的结尾



dd：剪切一行

d$：剪切光标到行的结尾



u：撤销操作

ctrl + r：重做



x：删除光标所在的字符

r：替换光标所在的字符



10 + G：光标移动到指定的行

g：光标移动到第一行

G：光标移动到最后一行

^：光标移动到行开头

$：光标移动到行结尾





`:w /tmp/1.txt`

`:w`

`:wq`

`:q!`

`:!ipconfig`  ：在vi模式下执行shell命令

`/aa`：查找，n，调到下一个，shift + n，跳到上一个

`:s/old/new`：针对光标所在字符进行替换

`:%s/old/new`：针对光标所在行进行替换

`:%s/old/new/g`：多次替换

`3,5s/old/new/g`：针对3-5行进行替换

`:set nohlsearch`：去掉高亮显示



`set nu`：显示行号

`set nonu`



配置vim

`vim /etc/vimrc`

```shell
# 在文件的最后一行添加配置
set nu
```



三种进入可视模式的方式：
1、字符可视模式
2、行可视模式
3、`ctrl+v`   块可视模式

配合d和I（大写i）命令可以进行块的便利操作



## 用户管理

用户和用户组可以有三种关系【一对一、一对多、多对多】（即：一个用户对应一个用户组；一个用户可以对应多个用户组，同样一个用户组也可以对应多个用户）。



| **序号** | **操作用户命令**      | **说明**                                                     |
| -------- | --------------------- | ------------------------------------------------------------ |
| 1        | `useradd 用户名称`    | 【`useradd coffeemilk`】表示创建coffeemilk用户               |
| 2        | `passwd 用户名称`     | 【`passwd coffeemilk`】表示给coffeemilk用户设置或修改密码    |
| 3        | `id 用户名称`         | 【`id coffeemilk`】表示查看coffeemilk用户是否存在            |
| 4        | `lchage -l 用户名称`  | 【`lchage -l coffeemilk`】表示查看coffeemilk用户状态信息（注意：这里命令中的是小写字母l，不是数字1或者字母i） |
| 5        | `usermod -L 用户名称` | 【`usermod -L coffeemilk`】表示锁定用户coffeemilk，不允许登录系统（注意：如果该用户在执行锁定命令前已经登录，那么已经登录的还是可以正常操作；但是退出后就无法登录了） |
| 6        | `usermod -U 用户名称` | 【`usermod -U coffeemilk`】表示解除用户coffeemilk的锁定状态，可以登录系统 |
| 7        | `userdel -r 用户名称` | 【`userdel -r coffeemilk`】表示删除用户及其对应相关的配置内容 |

| **序号** | **操作用户组命令**                      | **说明**                                                     |
| -------- | --------------------------------------- | ------------------------------------------------------------ |
| 1        | `groupadd 用户组名称`                   | 【`groupadd testgroup`】表示添加testgroup用户组              |
| 2        | `cat /etc/group`                        | 表示查看当前有哪些用户组，最新添加的用户组在该文件的最下面   |
| 3        | `usermod -g 用户组名称 用户名称`        | 【`usermod -g testgroup coffeemilk`】表示将用户coffeemilk的主组coffeemilk强制修改为testgroup（注意：如果该用户拥有多个组，只需要使用该命令即可指定为一个主组） |
| 4        | `usermod -G 用户组名称 用户名称`        | 【`usermod -G devgroup coffeemilk`】表示给用户coffeemilk在添加一个用户组devgroup |
| 5        | `useradd -g 用户组名称 用户名称`        | 【`useradd -g devgroup testuser`】表示在创建用户testuser时指定用户组为devgroup |
| 6        | `groupmod -n 新用户组名称 旧用户组名称` | 【`groupmod -n dev devgroup`】表示将旧用户名称devgroup修改为新的名称dev |
| 7        | `groupdel 用户组名称`                   | 【`groupdel testgroup`】表示删除用户组testgroup(注意：删除用户组的时候需要先将使用了该用户组的用户修改到其他用户组，才能够删除成功) |

`useradd`   新建用户

`userdel`   删除用户

`passwd`   修改用户密码

`usermod`  修改用户属性
`chage`     修改用户密码过期信息



`groupadd`    新建用户组
`groupdel`     删除用户组



`chown/chgrp -G` 将某目录下的所有子目录或文件同时修改属主或属组

`chmod -R` 更改该目录下的所有子目录或文件同时改权限





查看用户：

`id root`

```shell
# 添加用户
useradd whh
id whh

# 查看用户家目录
ls /root
ls -al /home/whh

# 查看用户配置
cat /etc/passwd
# 查看用户密码
cat /etc/shadow

# 设置密码
passwd whh

# 删除用户
userdel whh
userdel -r whh # 同时删除家目录

# 修改用户家目录
usermod -d /home/w11 whh

# 修改用户组
groupadd g1
usermod -g g1 whh

# 切换用户
su - whh
```



```shell
# 切换用户
su
su -USERNAME   # 使用login shell方式切换用户

# 以其他用户身份执行命令
sudo
visudo # 设置需要使用sudo的用户（组）

# 30min后关闭
shutdown -h 30
# 撤销关闭
shutdown -c
```



### /etc/passwd文件

有几种不同的身份验证方案可以在Linux系统上使用，最常用和标准的方案是针对 `/etc/passwd`和 `/etc/shadow`文件进行身份验证。



`/etc/passwd`是一个纯文本文件，包含系统上所有用户账户信息。它归root所有，具有644权限。

该文件只能由`root`或具有`sudo`权限的用户修改，该文件对所有用户可读。

`/etc/shadow`文件中的记录行与`/etc/passwd`一一对应

每行代表一个用户配置。

应避免手动修改 `/etc/passwd`文件。要修改用户帐户，使用`usermod`命令，要添加新用户，请使用`useradd`命令。

```bash
cat /etc/passwd
```

通常，第一行描述`root`用户，然后是系统和普通用户帐户。新增用户信息在文件末尾。

`/etc/passwd`文件每行包含七个逗号分隔的字段：

```shell
mark:x:1001:1001:mark,,,:/home/mark:/bin/bash
[--] - [--] [--] [-----] [--------] [--------]
|    |   |    |     |         |        |
|    |   |    |     |         |        +-> 7. Login shell
|    |   |    |     |         +----------> 6. Home directory
|    |   |    |     +--------------------> 5. comment
|    |   |    +--------------------------> 4. GID
|    |   +-------------------------------> 3. UID
|    +-----------------------------------> 2. Password
+----------------------------------------> 1. Username
```

1、用户名：登录系统时键入的字符串。每个用户名必须是机器上唯一的字符串。用户名的最大长度限制为32个字符。

2、密码：在旧的Linux系统中，用户的加密密码存储在`/etc/passwd`文件中。在大多数现代系统中，此字段设置为`x`，用户密码存储在`/etc/shadow`文件中。

3、UID：用户ID，是一个唯一的数字标识，用于在系统中区分不同的用户。

4、GID：组ID，表示用户所属的主组。在Linux系统中，用户可以被分配到不同的组中，每个组都有一组特定的权限。通常，组的名称与用户的名称相同。用户组信息在`/etc/groups`文件中。

5、`comment`：用户全名或描述信息，通常用于存储用户的真实姓名、联系方式等。此字段包含逗号分隔值列表，其中包含以下信息：用户全名或应用程序名称； 房间号； 工作电话号码； 家庭电话号码； 其他联系方式。

6、主目录：用户家目录的绝对路径，即用户登录后所处的目录。它包含用户的文件和配置。默认情况下，用户主目录以用户名称命名，并在`/home`目录下创建。

7、登录shell：用户登录后用于执行命令的解释器。在大多数Linux发行版上，默认登录shell是Bash。



### /etc/shadow文件

`/etc/shadow`文件同样是一个纯文本文件，它存储了系统中所有用户的密码信息。

与`/etc/passwd`文件不同，`/etc/shadow`文件的访问权限更为严格，通常只有root用户才能直接访问。这是因为密码信息是非常敏感的，必须得到妥善的保护。



`/etc/shadow`文件中的每一行对应一个用户，包含了九个字段，字段之间同样用冒号`:`分隔。



这九个字段的含义分别是：

1. `name`：用户名，与`/etc/passwd`文件中的用户名相对应。
2. `encrypted_password`：加密后的用户密码。这是密码信息的核心部分，经过特定的加密算法处理后存储在这里。
3. `last_change`：密码最后一次修改的时间，通常用自1970年1月1日以来的天数表示。
4. `min_days`：两次密码修改之间所需的最小天数。
5. `max_days`：密码保持有效的最大天数。
6. `warn_days`：在密码到期前多少天开始警告用户。
7. `inactive_days`：密码过期后多少天账户将被禁用。
8. `expire_date`：账户到期日期，通常为空，表示账户不会过期。
9. `reserved`：保留字段，通常留空。

### /etc/group文件

`/etc/group`文件是有关于系统管理员对用户和用户组管理的文件，用户组的所有信息都存放在`/etc/group`文件中。

格式是由冒号隔开若干个字段，这些字段具体如下：

**组名:口令:组标识号:组内用户列表**

具体解释：

**组名：**组名是用户组的名称，由字母或数字构成。与`/etc/passwd`中的登录名一样，组名不应重复。

**口令：**口令字段存放的是用户组加密后的口令字。一般Linux系统的用户组都没有口令，即这个字段一般为空，或者是*。

**组标识号：**组标识号与用户标识号类似，也是一个整数，被系统内部用来标识组。别称`GID`。

**组内用户列表：**是属于这个组的所有用户的列表，不同用户之间用逗号分隔。这个用户组可能是用户的主组，也可能是附加组。



## 文件权限

Linux系统中任何内容都可以用文件表示，linux对文件设计了一套权限进行管理。

Linux是采用用户、用户组、其他用户组成的权限体系（简称：ugo）。

每个用户权限使用三个字符表示，共9个字符，一位都不能少，没有权限的字符使用空权限（-）表示。

文件权限共有11个字符，从左向右共分为5段：

| **字符**             | **说明**                                                     |
| -------------------- | ------------------------------------------------------------ |
| **第1个字符**        | **第1个字符表示【文件类型】**（其中【-】表示文件、【d】表示目录、【l】表示软链接(也就是windows里面的快捷方式)、【b】表示设备） |
| **第2、3、4个字符**  | **第2、3、4个字符表示文件【所属用户的权限】（user）**        |
| **第5、6、7个字符**  | **第5、6、7个字符表示文件【所属用户组的权限】（group）**     |
| **第8、9、10个字符** | **第8、9、10个字符表示文件【所属其他用户的权限】（other）**  |
| **第11个字符**       | **第11个字符表示【开启selinux的状态下创建的；即这个文件受到selinux的保护】** |

权限值说明：

| **序号** | **权限字符串值** | **说明**                                   |
| -------- | ---------------- | ------------------------------------------ |
| r        | **4**            | 全称（read）表示拥有文件的读取权限         |
| w        | **2**            | 全称（write）表示拥有文件的写入权限        |
| x        | **1**            | 全称（executable）表示拥有文件的可执行权限 |
| -        | **0**            | 表示空权限，表示没有文件的任何操作权限     |

**ugo权限字符串值组合对照表**

| **ugo权限字符串值组合** | **ugo权限字符串组合值** | **ugo权限说明**      |
| ----------------------- | ----------------------- | -------------------- |
| ---                     | **0**                   | 空权限               |
| --x                     | **1**                   | 执行权限             |
| -w-                     | **2**                   | 写入权限             |
| -wx                     | **3**                   | 写入和执行权限       |
| r--                     | **4**                   | 读取权限             |
| r-x                     | **5**                   | 读取和执行权限       |
| rw-                     | **6**                   | 读取和写入权限       |
| rwx                     | **7**                   | 读取、写入和执行权限 |

修改文件权限

```shell
# 修改文件、目录权限
chmod
chmod u+x /tmp/testfile
chmod 755 /tmp/testfile
# 更改属主、属组
chown

ls -ld /test
chown whh /test
chown :g1 /test
chown whh:g1 /test
chgrp whh /test

# 可以单独更改属组，不常用
chgrp
```

### 特殊权限

文件特殊权限是对一般权限的补充（由于管理员不受一般权限的控制，可以通过特殊权限来控制），特殊权限会对管理员生效。

```shell
# 用于二进制可执行文件，执行命令时取得文件属主权限，如/usr/bin/passwd
SUID
# 用于目录，在该目录下创建新的文件和目录，权限自动更改为该目录的属组
SGID
# 用于目录，该目录下新建的文件和目录，仅root和自己可以删除，如/
SBIT
```

#### SUID

**SUID 针对所有者的特殊权限。**

设置此特殊权限后，所有者的执行权限字符用s或S表示（不用x或-表示）。



**s和S的区别如下**

在配置特殊权限时，如果文件所有者没有执行权限，则配置SUID特殊权限后，文件所有者对此文件的执行权限为S（即如果所有者的权限为r--，配置suid特殊权限后，权限为r-S）。

在配置特殊权限时，如果文件所有者有执行权限，则配置SUID特殊权限后，文件所有者对此文件的执行权限为s（即如果所有者的权限为r-x，配置suid特殊权限后，权限为r-s）。



**针对文件设置SUID权限**

会让此文件的执行者临时获取到文件所有者的权限来完成某些工作。

SUID必须只能是设置在二进制的可执行文件、脚本上，无法设置到目录上。



**SUID权限举例**

`/etc/shadow`文件保存的是用户的密码信息，普通用户没有此文件的权限，但是可以通过`passwd`修改密码。

`passwd`的命令文件的所有者执行权限为s，这个s就指的是命令的SUID权限，当某个用户执行`passwd`命令的时候，他就临时获取到passwd文件所有者的权限。

passwd文件所有者是管理员，而管理员不受一般权限控制，所以用户可以通过`passwd`更改密码；如果用户不使用passwd，则由于用户没有权限，无法修改命令。

```shell
[root@localhost /]# ll /etc/passwd
-rw-r--r--. 1 root root 2305 Oct 20 08:44 /etc/passwd
[root@localhost /]# which passwd
/usr/bin/passwd
[root@localhost /]# ll /usr/bin/passwd
-rwsr-xr-x. 1 root root 27856 Mar 31  2020 /usr/bin/passwd
[root@localhost /]#
```

#### SGID

设置此特殊权限后，文件所属组执行权限为s或S（不用x或-表示，s和S的区别类似SUID）。



**针对文件设置SGID权限**

让命令的执行者可以临时获取到文件所有组的权限来完成某些工作（ 基本上不会使用）。

SGID针对文件权限类似于SUID，只不过SGID使得用户临时获取用户组的权限。



**针对目录设置权限**

目录下新建的文件的所有组，会自动继承目录的所有组，一般会把SGID的权限设置到目录上面。

正常情况，新建文件的所有者和所有组都是自己（即使用新建命令的命令执行者）。



**SGID针对目录设置权限举例**

在管理员下创建/etc/abc目录，并为此目录配置SGIB权限

然后登录到admin用户下，在/etc/abc目录下创建文件和目录，发现文件和目录的所属组都继承了/etc/abc的所属组，都为root。

```shell
[root@localhost tmp]# mkdir whh
[root@localhost tmp]# ls -ld whh
drwxr-xr-x. 2 root root 6 Oct 23 04:50 whh
[root@localhost tmp]# chmod 2777 whh
[root@localhost tmp]# ls -ld whh
drwxrwsrwx. 2 root root 6 Oct 23 04:50 whh
[root@localhost tmp]#

# 切换到其它用户下，进入到该目录时，用于root用户组的权限。
```

#### SBID

设置此特殊权限后，文件的其它用户的执行权限为t或T（不用x或-表示，t和T的区别类似SUID） ，SBID可以称为保护位。



**针对文件或目录设置SBID**

设置SBID特殊权限后，就可以确保用户只能够删除自己的文件，而不能删除其它用户的文件。

root不受特殊权限的控制，即root可以删除任何用户创建的文件。



**SBID权限举例**

```shell
# 配置文件/etc/123的一般权限为rwxrwxrwx，并配置特殊权限SBID
# 综合得到文件的权限就为rwxrwxrwt
chmod 1777 /etc/123
# 此时其它用户虽然有文件的权限，但是由于SBID权限的显示，使得用户无法删除此文件

```

#### 配置特殊权限

```shell
# 参数方式
suid  u+s
sgid  g+s
sbit  o+t
chmod u+s 文件名   # 设置文件配置suid权限

# 数字方式
suid = 4
sgid = 2
sbit = 1

# 在通过数字方式配置特殊权限时，需要特殊权限和一般权限结合来配置
chmod 1743 文件 # 为文件设置特殊权限为1（SBID），一般权限为743
chmod 5451 文件  # 为文件设置特殊权限为5（SUID+SGID），一般权限为451

# 即：如果要配置特殊权限SBID，原先的文件权限为rwxrwxrwx，此时配置命令就为
chmod 1777 文件名 
# 为此文件配置SBID特殊权限（最开头的1数字就代表特殊权限，此处指的是SBID；如果为5表示配置SUID和SBID），配置完成后文件的权限就会变为rwxrwxrwt
```

### umask

**umask 修改文件的权限掩码**

```shell
# 修改/查看文件的权限掩码
umask 【参数】 【权限】
-p: 完整打印umask内容
-S: 以符号的形式显示权限掩码

# 什么是umask值？
umask值表示文件的默认权限掩码，通过该掩码定义了不同用户创建文件/目录的默认权限

# 默认umask值
通过umask直接查看该用户默认的umask值
root用户的umask值为0022；普通用户的umask值为0002

[root@localhost tmp]# umask
0022

第一位代表特殊位（对应文件的特殊权限，只有配置了SUID、SGID、SBID此值才有效），暂不考虑
第二位代表文件的用户权限
第三位代表文件的用户组权限
第四位代表文件的其它用户权限
```

文件创建后的默认权限等于`666-umask`对应的默认值（如果umask为奇数，则默认权限等于`0666-umask值+1`）。

目录创建后的默认权限等于`777-umask`对应的默认值。

普通用户：默认文件权限为664，默认目录权限为775。

Root用户：默认文件权限为644，默认目录权限为755。

**修改umask值**

```shell
# 临时修改umask值
umask 权限掩码
umask 002 # 修改该用户的umask值为002

# 永久修改umask值
通过修改/etc/bashrc或者/etc/profile文件实现永久修改umask值
vim /etc/profile
# By default, we want umask to get set. This sets it for login shell
# Current threshold for system reserved uid/gids is 200
# You could check uidgid reservation validity in
# /usr/share/doc/setup-*/uidgid file
if [ $UID -gt 199 ] && [ "`/usr/bin/id -gn`" = "`/usr/bin/id -un`" ]; then
    umask 002  # 普通用户
else
    umask 022  # root用户
fi
```

### 文件的隐藏权限

文件的隐藏权限，默认看不到的权限（对管理员也生效）。

`chattr` 配置/删除文件的隐藏权限。

```shell
# 配置文件隐藏权限
chattr +参数 文件名
# 删除文件隐藏权限  
chattr - 参数 文件名  
i: 无法对文件进行修改（只可以修改此文件的子文件，不能新建和删除此文件）
a: 仅允许向文件追加内容，无法覆盖、删除内容
u: 删除文件，可以恢复（保留在硬盘中的数据）
s: 彻底删除文件，不可恢复（用0填充原文件所在硬盘区域）
S: 文件内容变更后立即同步到硬盘
A: 不再修改这个文件或者目录的最后访问时间
b: 不再修改这个文件或者目录的存取时间（atime）
D: 检查压缩文件中的错误
d: 使用dump命令备份时忽略本文件/目录
c: 默认将文件或目录进行压缩
x: 可以直接访问压缩文件的内容

chattr +a abc  # 文件abc只可以追加内容
```

`lsattr` 查看文件的隐藏权限

```shell
lsattr 文件
-a 查看指定目录中全部文件的隐藏属性，包括隐藏文件
-d 查看指定目录的隐藏属性
-D 显示属性的名称、默认值
-E  显示从用户设备数据库中获得属性的当前值

lsattr abc # 查看文件abc的隐藏权限
```

### 文件访问控制列表权限

文件访问控制列表权限，`facl（file access control list）`。

与一般、特殊、隐藏权限的区别：

一般权限、特殊权限、隐藏权限是对于所有人或某些人做限制的。

一般权限对某些人做限制。

特殊权限对所有人做限制。

隐藏权限对所有人做限制。



文件访问控制列表能够针对一个用户以及一个文件来做精准的权限。

当同一用户的一般权限与`setfacl`产生了歧义，`setfacl`的优先级最高。



**setfacl 管理文件的facl**

```shell
# 对文件设置facl
setfacl 【参数】 文件名
# 此文件针对某个用户单独设置权限
setfacl -m u:用户名:权限 文件名
# 删除此文件的所有扩展facl
setfacl -b 文件名                               

-m 修改权限（权限设置不允许使用数字法）
u  对用户进行设定
g  对用户组进行设定

-R  对目录设定facl
-b  删除所有扩展facl
-x  删除某个facl
-X  从文件中读取facl并删除
-k  移除默认facl
-d  设置默认的ACL规则

# 为用户admin配置针对文件qwe的rwx权限
setfacl -m u:admin:rwx qwe
# 删除用户admin针对文件qwe的facl
setfacl -x u:admin qwe
```

**getfacl 查看文件的facl**

```shell
# 查看文件已经设置了哪些文件访问权限
getfacl 【参数】 文件名
-a 同getfacl 文件名
-c 显示文件的facl，不显示注释标题
-R 显示目录的facl
-d 显示文件默认的facl

getfacl -a qwe  # 显示qwe文件的facl

gerfacl -d qwe  # 显示qwe文件的默认facl
```

### 文件类型

```shell
- 普通文件
d 目录文件
b 块特殊文件 (硬盘、内存、光盘等)
c 字符特殊文件 (在/dev目录见的比较多)
l 符号链接
f 命名管道
s 套接字文件
```

### SElinux权限

安全增强型 Linux（SELinux）是一种采用[安全](https://www.redhat.com/zh/topics/security)架构的 [Linux® 系统](https://www.redhat.com/zh/topics/linux/what-is-linux)，它能够让管理员更好地管控哪些人可以访问系统。它最初是作为 [Linux 内核](https://www.redhat.com/zh/topics/linux/what-is-the-linux-kernel)的一系列[补丁](https://www.redhat.com/zh/topics/linux/what-is-linux-kernel-live-patching)，由美国国家安全局（NSA）利用 Linux 安全模块（LSM）开发而成。

SELinux 定义了每个人对系统上的应用、进程和文件的访问控制。利用安全策略（一组告知 SELinux 哪些能访问，哪些不能访问的规则）来强制执行策略所允许的访问。 

SElinux是负责Linux系统安全的，但是启用它后会变得很麻烦，安全和便利是冲突的；红帽系的系统(redhat、centos等)都默认都有这个机制且是启用的；一般来说这个内容我们是关闭的，有关安全方面的控制可以通过其他方法来控制。

https://www.redhat.com/zh/topics/linux/what-is-selinux

```shell
MAC（强制访问控制）与DAC（自主访问控制）

查看SELinux的命令
getenforce
/usr/sbin/sestatus

pS -Z and ls -Z and id -Z

关闭SELinux
setenforce 0
/etc/selinux/sysconfig
```





```shell
# 查看Linux的Selinux状态命令：
sestatus

# 禁用Linux系统的SELinux
# 使用具有超级管理员权限的账户登录Linux，一般是root用户，使用如下命令进行打开修改

# 1、打开SELinux配置文件命令:
vim /etc/selinux/config
# This file controls the state of SELinux on the system.
# SELINUX= can take one of these three values:
#     enforcing - SELinux security policy is enforced.
#     permissive - SELinux prints warnings instead of enforcing.
#     disabled - No SELinux policy is loaded.
SELINUX=enforcing  # 修改这里，改为disabled
# SELINUXTYPE= can take one of three values:
#     targeted - Targeted processes are protected,
#     minimum - Modification of targeted policy. Only selected processes are protected. 
#     mls - Multi Level Security protection.
SELINUXTYPE=targeted

# 2、SELinux修改完成后重启Linux系统（只有重启系统后才会生效）重启系统命令是：
reboot

# 3、Linux重启完成后再查看一下SELinux的状态是否为禁用命令：
sestatus
```

### 权限修改

```shell
# chmod -权限字母 文件名称
# 示例1、移除所有人读取newfile.txt文件的权限
chmod -r newfile.txt     
# 示例2、移除所有人写newfile.txt文件的权限
chmod -w newfile.txt
# 示例3、移除所有人执行newfile.txt文件的权限
chmod -x newfile.txt

# chmod +权限字母 文件名称	
# 示例1、添加所有人读取newfile.txt文件的权限
chmod +r newfile.txt     
# 示例2、添加所有人写newfile.txt文件的权限
chmod +w newfile.txt
# 示例3、添加所有人执行newfile.txt文件的权限
chmod +x newfile.txt

# chmod u±权限字母 文件名称	
# 示例1、添加文件所有者读取newfile.txt文件的权限
chmod u+r newfile.txt   
# 示例2、移除文件所有者读取newfile.txt文件的权限
chmod u-r newfile.txt 
# 示例3、添加文件所有者读取和写入newfile.txt文件的权限
chmod u+rw newfile.txt       
# 示例4、移除文件所有者读取和写入newfile.txt文件的权限
chmod u-rw newfile.txt 

# chmod g±权限字母 文件名称	
# 示例1、添加文件所属组读取newfile.txt文件的权限
chmod g+r newfile.txt   
# 示例2、移除文件所属读取newfile.txt文件的权限
chmod g-r newfile.txt 
# 示例3、添加文件所属读取和写入newfile.txt文件的权限
chmod g+rw newfile.txt       
# 示例4、移除文件所属读取和写入newfile.txt文件的权限
chmod g-rw newfile.txt

# chmod o±权限字母 文件名称	
# 示例1、添加其他用户读取newfile.txt文件的权限
chmod o+r newfile.txt   
# 示例2、移除其他用户读取newfile.txt文件的权限
chmod o-r newfile.txt 
# 示例3、添加其他用户读取和写入newfile.txt文件的权限
chmod o+rw newfile.txt          
# 示例4、移除其他用户读取和写入newfile.txt文件的权限
chmod o-rw newfile.txt

# chmod 权限字母值 文件名称	
# 例1、给文件newfile.txt所有者添加读写权限、文件所属组添加读权限、其他用户添加读权限（-rw-r--r-- 644）
chmod 644 newfile.txt
# 示例2、给文件newfile.txt所有者添加读写权限、文件所属组添加读权限、其他用户添加读权限（-rw-rw--r-- 664）
chmod 664 newfile.txt
# 示例3、给目录testdir所有者添加读写执行权限、文件所属组添加读和执行权限、其他用户添加读和执行权限（-rwxr-xr-x 755）
chmod 755 newfile.txt
```



# 网络管理

## 网络状态查看

2套工具包。`net-tools VS iproute`
net-tools

- ifconfig
- route
- netstat

iproute2

- ip
- ss

```shell
ifconfig

eth0 第一块网卡（网络接口）

你的第一个网络接口可能叫做下面的名字
eno1    板载网卡
ens33   PCI-E网卡
enpos3  无法获取物理信息的PCI-E网卡
CentoS7 使用了一致性网络设备命名，以上都不匹配则使用eth0


# 查看指定网卡
ifconfig eth0
```

**网络接口命名修改**

 网卡命名规则受`biosdevname`和`net.ifnames`两个参数影响
 编辑`/etc/default/grub`文件，增加`biosdevname=0 net.ifnames=0`
 更新grub
 `#grub2-mkconfig -o /boot/grub2/grub.cfg`
重启
 `#reboot`

```shell
vim /etc/default/grub
RUB_TIMEOUT=5
GRUB_DISTRIBUTOR="$(sed 's, release .*$,,g' /etc/system-release)"
GRUB_DEFAULT=saved
GRUB_DISABLE_SUBMENU=true
GRUB_TERMINAL_OUTPUT="console"
GRUB_CMDLINE_LINUX="crashkernel=auto rhgb quiet"
GRUB_DISABLE_RECOVERY="true"

修改：
GRUB_CMDLINE_LINUX="crashkernel=auto rhgb quiet"
为
GRUB_CMDLINE_LINUX="crashkernel=auto rhgb quiet biosdevname=0 net.ifnames=0"
```



|       | biosdevname | net.ifnames | 网卡名 |
| ----- | ----------- | ----------- | ------ |
| 默认  | 0           | 1           | ens33  |
| 组合1 | 1           | 0           | em1    |
| 组合2 | 0           | 0           | eth0   |

**查看网络情况**

```shell
# 查看网卡物理连接情况
mii-tool eth0
```

**查看网关情况**

```shell
# 查看网关
route -n
使用 -n 参数不解析主机名
```



## 网络配置

```shell
# 网络配置
ifconfig <接口> <IP地址> [netmask子网码]
ifup <接口>
ifdown <接口>
```

```shell
# ip命令
ip addr ls
ifconfig

ip link set dev eth0 up
ifup eth0

ip addr add 10.0.0.1/24 dev eth1
ifconfig eth1 10.0.0.1 netmask 255.255.255.0
ip route add 10.0.0/24 via 192.168.0.1
route add -net 10.0.0.0 netmask 255.255.255.0 gw 192.168.0.1
```



```shell
# 修改ip
ifconfig eth0 新ip  # 这里子网掩码自动生成
# 修改ip，同时指定子网掩码
ifconfig eth1 10.0.0.1 netmask 255.255.255.0

# 启动网卡
ifconfig eth0 up
或者
ifup eth0
```

## 路由命令

```shell
# 网关配置
# 添加网关
route add default gw <网关ip>
route add -host <指定ip> gW <网关ip>
route add -net <指定网段> netmask <子网码> gw <网关ip>

# 删除网关
route del default gw 新网关
route add default gw 新网关
# 指定访问特定ip走哪个网关
route add -host 特定ip gw 特定网关
# 指定访问特定网段走哪个网关
route add -net 特定ip netmask 子网掩码 gw 特定网关
```



## 网络故障排除

常用命令：

```shell
ping
traceroute
mtr
nslookup
telnet
tcpdump
netstat
ss
```

```shell
ping www.google.com

traceroute -w 1 www.google.com

mtr (My Traceroute)
mtr

nslookup www.google.com

yum install telnet -y
telnet www.google.com 80

tcpdump -i any -n port 80
tcpdump -i any -n host ip地址
tcpdump -i any -n host ip地址 and port 80
tcpdump -i any -n host ip地址 and port 80 -w /tmp/file.dump

netstat -ntpl
netstat -ano
类似
ss -ntpl

```



## 网络服务管理

```shell
网络服务管理程序分为两种，分别为SySV和systemd

service network start|stop|restart|status

chkconfig --list network

systemctl list-unit-files NetworkManager.service
systemctl start|stop|restart|status NetworkManger
systemctl enable|disable NetworkManger

```



## 常用网络配置文件

```shell
# 网络配置文件
ifcfg-eth0
/etc/hosts

# 查看网络服务状态
service network status

chkconfig --list network
chkconfig --level 2345 network off

# 网络配置文件
cd /etc/sysconfig/network-scripts/
ls ifcfg-*
ifcfg-eth0 ifcfg-lo

# 修改主机名称
hostname
hostname 新主机名  # 临时生效

hostnamectl set-hostname 新主机名  # 永久生效
同时需要在/etc/hosts文件加一行
127.0.0.1 新主机名
需要重启
reboot
```

# 软件安装

## 软件包管理器

包管理器是方便软件安装、卸载，解决软件依赖关系的重要工具。
Centos、RedHat使用yum包管理器，软件安装包格式为rpm。
Debian、 Ubuntu使用apt包管理器，软件安装包格式为deb。



## rpm包和rpm命令

```shell
# rpm包格式
vim-common-7.4.10-5.el7.×86_64.rpm
vim-common 软件名称 
7.4.10-5   软件版本 
el7        系统版本 
x86_64     平台
```

```shell
# rpm命令
rpm命令常用参数
-q 查询软件包
-i 安装软件包
-e 卸载软件包

rpm -qa | more
rmp -q vim-common
```



## yum仓库

```shell
# rpm包的问题
# 需要自己解决依赖关系
# 软件包来源不可靠
Centos yum源
http://mirror.centos.org/centos/7/
# 国内镜像
https://opsx.alibaba.com/mirror
```

```shell
# yum配置文件
vim /etc/yum.repos.d/centos-Base.repo
[base]
name=CentoS-$releasever - Base - mirrors.aliyun.com
failovermethod=priority
baseurl=http://mirrors.aliyun.com/centos/$releasever/os/$basearch/
		http://mirrors.aliyuncs.com/centos/$releasever/os/$basearch/
		http://mirrors.cloud.aliyuncs.com/centos/$releasever/os/$basearch/
gpgcheck=1
gpgkey=http://mirrors.aliyun.com/centos/RPM-GPG-KEY-CentoS-7

wget -O /etc/yum.repos.d/centos-Base.repo http://mirrors.aliyun.com/repo/Centos-7.repo
```

```shell
# yum命令

# 常用选项
install 安装软件包
remove 卸载软件包
list|grouplist 查看软件包
update 升级软件包

yum makecache
```





## 源代码编译安装

```shell
# 源码方式安装
# 二进制安装

# 源代码编译安装
wget https://openresty.org/download/openresty-1.15.8.1.tar.gz
tar -zxf openresty-VERSION.tar.gz
cd openresty-VERSION/
./configure --prefix=/usr/local/openresty
make -j2
make install

yum install gcc gcc-c++
yum install pcre-devel
yum install openssl-devel -y

gmake -j2
gmake install
```



## 内核升级

```shell
# rpm格式内核

# 查看内核版本
uname -r

# 升级内核版本
yum install kernel-3.10.0
# 升级已安装的其他软件包和补丁
yum update

# 源码编译方式安装内核
# 安装依赖包
yum install gcc gcc-c++ make ncurses-devel openssl-devel elfutils-libelf-devel
# 下载并解压缩内核
https://www.kernel.org
tar xvf linux-5.1..10.tar.×Z -C /usr/src/kernels
# 配置内核编译参数
cd /usr/src/kernels/linux-5.1.10/
make menuconfig | allyesconfig | allnoconfig
# 使用当前系统内核配置
cp /boot/config-kernelversion.platform /usr/src/kernels/linux-5.1.10/.config
# 查看CPU
lscpu
# 编译
make -j2 all
# 安装内核
make modules_install
make install

# 编译内核至少需要10g的磁盘
```



```shell
yum install epel-release -y
yum install kernel
yum install kernel-3.10.0
```





## grub配置文件

```shell
# grub是什么

# grub配置文件
/etc/default/grub
/etc/grub.d/
/boot/grub2/grub.cfg

grub2-mkconfig -o /boot/grub2/grub.cfg  

# 使用单用户进入系统（忘记root密码）
ls /sysroot
mount -o remount,rw /sysroot
chroot /sysroot
ls /
echo 新密码 | passwd --stdin root
```



```shell
# 查看引导内核
[root@localhost tmp]# grub2-editenv list
saved_entry=CentOS Linux (3.10.0-1160.el7.x86_64) 7 (Core)

# 查看引导内核
grep ^menu /boot/grub2/grub.cfg

# 修改默认引导内核
grub2-set-default 0
grub2-set-default 1
grub2-set-default 2

reboot
```



# 进程管理

## 进程的概念与进程查看

进程

> 运行中的程序，从程序开始运行到终止的整个生命周期是可管理的。
>
> C程序的启动是从main函数开始的
> `int main(int agrc，char *argv）`
>
> 终止的方式并不唯一，分为正常终止和异常终止。
> 正常终止也分为从main返回、调用exit等方式。
> 异常终止分为调用abort、接收信号等。



```shell
# 查看命令
ps
pstree
top

1、进程也是树形结构
2、进程和权限有着密不可分的关系
```

```shell
man ps
ps -ef | more
ps -eLf # 显示thread

pstree | more
```

top命令使用

#todo#



## 进程的控制命令

```shell
# 调整进程优先级
nice 范围从-20到19，值越小优先级越高，抢占资源就越多
renice 重新设置优先级

# 进程的作业控制
jobs
& 符号
```



```shell
vim a.sh
#!/bin/bash
echo $$ # 打印当前进程pid
# 死循环
while :
do
	:
done
:wq!

chmod u+x a.sh
./a.sh
ctrl + z # 进程后台临时挂起，不再执行，状态是stopped

./a.sh &  # 后台执行
jobs # 查询后台执行的进程
fg 1 # 把编号是1的后台进程调用到前台执行
bg 1 # 把编号是1的进程调用到后台执行

# 查询指定进程
top -p 进程pid

# 设置优先级
nice -n 10 ./a.sh
renice -n 15 进程pid
```





## 进程的通信方式：信号

```shell
# 信号是进程间通信方式之一，典型用法是：终端用户输入中断命令，通过信号机制停止一个程序的运行。

使用信号的常用快捷键和命令
# 查看所有信号
kill -l

SIGINT  通知前台进程组终止进程  等价 ctrl+ C
SIGKILL 立即结束程序，不能被阻塞和处理 等价 kill -9 pid
```





## 守护进程和系统日志

使用 `nohup` 与 `&` 符号配合运行一个命令

```shell
tail -f /var/log/messages
```





`nohup`命令使进程忽略`hangup`（挂起）信号



守护进程（`daemon`）和一般进程有什么差别呢？



## screen

```shell
# 安装
yum install screen

# 运行
screen
tail -f /var/log/messages
ctrl + a

screen -ls
screen -r xxxxx

# 系统日志
/var/log/messages
# 内核启动日志
/var/log/dmesg
# 安全日志
/var/log/secure
# 计划任务日志
/var/log/cron
```



使用`screen`命令
`screen`进入`screen`环境
`ctrl+a d` 退出（`detached`）`screen`环境
`screen -ls`查看`screen`的会话
`screen -r sessionid`恢复会话



## 服务管理工具systemctl

```shell
# 服务（提供常见功能的守护进程）集中管理工具
service
systemctl
```

```shell
# service的启动脚本
cd /etc/init.d/
vim network

# systemd
cd /usr/lib/systemd/system
vim sshd.service

# systemctl常见操作
systemctl start|stop|restart|reload|enable|disable 服务名称

# 软件包安装的服务单元 
/usr/lib/systemd/system/

# 查看服务级别
[root@localhost tmp]# cd /usr/lib/systemd/system
[root@localhost system]# ls -l runlevel*.target
lrwxrwxrwx. 1 root root 15 Oct 20 08:39 runlevel0.target -> poweroff.target
lrwxrwxrwx. 1 root root 13 Oct 20 08:39 runlevel1.target -> rescue.target
lrwxrwxrwx. 1 root root 17 Oct 20 08:39 runlevel2.target -> multi-user.target
lrwxrwxrwx. 1 root root 17 Oct 20 08:39 runlevel3.target -> multi-user.target
lrwxrwxrwx. 1 root root 17 Oct 20 08:39 runlevel4.target -> multi-user.target
lrwxrwxrwx. 1 root root 16 Oct 20 08:39 runlevel5.target -> graphical.target
lrwxrwxrwx. 1 root root 13 Oct 20 08:39 runlevel6.target -> reboot.target
[root@localhost system]#
```



# 内存和磁盘管理

## 内存和磁盘使用率查看

```shell
# 查看内存常用命令
free
top

# 查看磁盘
fdisk
df
du

du和ls的区别
du计算的是真实占用的磁盘大小
```



```shell
free -g
free -m
```

```shell
# 查看磁盘
fdisk -l
或者
parted -l

ls -l /dev/sd?
ls -l /dev/sd??

# 创建一个指定大小的空文件
dd if=/dev/zero bs=4M count=10 of=afile # 创建40m大小的afile文件
ls -lh afile
40m
du -h afile
40m

dd if=/dev/zero bs=4M count=10 seek=20 of=afile # 创建40m大小的afile文件，每次跳过20个4m
ls -lh afile
120m
du -h afile
40m
```

## ext4文件系统

```shell
Linux支持多种文件系统，常见的有:
ext4
xfs
NTFS（需安装额外软件）
```

```shell
ext4文件系统基本结构比较复杂
超级块
超级块副本
i节点（inode）
数据块（datablock）

自己理解：
i节点记录的是文件的信息
数据块记录的是实际数据
i节点指向多个数据块，节点链的方式组织数据。
超级块里存的是文件的统计信息
超级块副本存的是数据的备份
ls统计的是i节点里的信息
du统计的是数据块

# 查看文件对应的i节点
ls -i
```



```shell
# i节点和数据块的操作
touch afile
ls -li afile
0k
du -h afile
0k

echo 123 > afile
ls -li afile
4k
du -h afile
4k

在文件所在的父目录上mv移动文件时，文件的i节点值不会变。
mv到不同父目录时，i节点值发生改变。

mv执行很快，哪怕是几百G的文件，也能瞬间完成。
原理就是只需要修改i节点的值，不用修改数据块。

ln是不能跨文件系统操作的。
vim afile # 会修改文件的i节点值，是因为vim操作会形成一个新的文件 afile.swap
echo afile # 不会修改i节点值

rm afile # 只是断开i节点和数据块的连接，i节点还在，可以用来做磁盘恢复

ln afile bfile
ls -l -i afile bfile

# 创建软连接可以跨文件系统
ln -s afile bfile
ls -li 

```





## 磁盘配额的使用

```shell
用户磁盘配额

# xfs文件系统的用户磁盘配额
quota

mkfs.xfs -f /dev/sdb1
mkdir /mnt/disk1
mount -o uquota,gquota /dev/sdb1 /mnt/disk1

chmod 1777 /mnt/disk1
xfs_quota -x -c 'report -ugibh' /mnt/disk1
xfs_quota -x -c 'limit -u isoft=5 ihard=10 user1' /mnt/disk1
```



## 内存配额限制 cgroup

//todo



## 磁盘的分区与挂载

```shell
# 常用命令
fdisk
mkfs
parted
mount

# 常见配置文件
/etc/fstab
```



```shell
fdisk /dev/vdc
m
n
p
1
d
w

fdisk -l

# 格式化
mkfs.ext4 /dev/vdc1

mkdir /mnt/aaa
mount /dev/vdc1 /mnt/aaa

vim /etc/fstab
/dev/vdc1 /mnt/aaa ext4 defaults 0 0

# 对超过2T的磁盘进行分区，需要使用parted
```





## 交换分区（虚拟内存）的查看与创建

```shell
# 增加交换分区的大小
mkswap
swapon

# 使用文件制作交换分区
dd if=/dev/zero bs=4M count=1024 of=/swapfile
```



```shell
# 使用本次磁盘来扩展swap
# 新增分区
ls /dev/vdc1
mkswap /dev/vdc1
swapon /dev/vdc1
swapoff /dev/vdc1

# 使用空文件来扩展swap
dd if=/dev/zero bs=4M count=1024 of=/swapfile
chmod 600 /swapfile
mkswap /swapfile
swapon /swapfile

# 将swap写入到配置文件
vim /etc/fstab
/dev/vdc1 swap swap defaults 0 0
/swapfile swap swap defaults 0 0
```



## RAID与软件RAID技术

**磁盘阵列**

将多块硬盘组合起来



```shell
# RAID的常见级别及含义
RAID 0 # Striping 条带方式，提高单盘吞吐率
RAID 1 # mirroring 镜像方式，提高可靠性
RAID 5 # 有奇偶校验
RAID 10 # 是RAID 1 与 RAID 0 的结合

```

```shell
一般使用RAID控制卡来做RAID，不推荐使用软件做RAID的方式

# 软件RAID的使用
yum install mdadm

mdadm -C /dev/md0 -a yes -l1 -n2 /dev/vdc1 /dev/vdc2 /dev/vdc3
或者
mdadm -C /dev/md0 -a yes -l1 -n2 /dev/vd[b,c]1

/dev/md0相当于是上层结构，分别对应下面的分区，如果分区直接做了挂载，raid就失效了。

# 查看
mdadm -D /dev/md0

# 写入配置文件
echo DEVICE /dev/vdc1 /dev/vdc2 /dev/vdc3 >> /etc/mdadm.conf
echo DEVICE /dev/vd[b,c]1 >> /etc/mdadm.conf
mdadm -Evs >> /etc/mdadm.conf

# 格式化
mkfs.xfs /dev/md0
# 挂载
mount /opt /dev/md0

# 停掉raid
mdadm --stop /dev/md0
```

## 逻辑卷管理

逻辑卷和文件系统的关系

为Linux创建逻辑卷

动态扩容逻辑卷



```shell
# 创建物理卷
pvcreate /dev/vdc1 /dev/vdd1 /dev/vde1
或者
pvcreate /dev/vd[c,d,e]1 # 通配符
Physical volume "/dev/vdd1" successfully created

# 查看物理卷
pvs

# 创建卷组
vgcreate vg1 /dev/vdc1 /dev/vdd1
Volume group "vg1" successfully created
# 查看卷组
vgs

# 创建逻辑卷组
lvcreate -L 100M -n lv1 vg1
Logical volume "lv1" created

# 查看逻辑卷组
lvs

mkdir /mnt/test
mkfs.xfs /dev/vg1/lv1
# 挂载
mount /dev/vg1/lv1 /mnt/test
```

```shell
vgextend vg1 /dev/vdf1
Volume group "vg1" successfully extended

lvextend -L +100G /dev/vg1/lv1
Logical volume vg1/lv1 successfully resized

xfs_growfs /dev/vg1/lv1
```



**缩容有危险，不建议**



## 系统综合状态查看

```shell
# 使用sar命令查看系统综合状态

# 使用第三方命令查看网络流量
yum install epel-release
yum install iftop
iftop -P
```

```shell
# 每隔1s查看cpu使用情况，采集10次
sar -u 1 10

# 每隔1s查看内存使用情况，采集10次
sar -r 1 10

# 每隔1s查看io使用情况，采集10次
sar -b 1 10

# 每隔1s查看磁盘使用情况，采集10次
sar -d 1 10

# 每隔1s查看进程使用情况，采集10次
sar -q 1 10
```

```shell
# 查看网络流量
iftop -P

```





# 服务管理



# shell

## 什么是shell

shell是命令解释器，用于解释用户对操作系统的操作

```shell
# Shel有很多
cat /etc/shells

Centos7默认使用的Shell是bash
```

## linux启动过程

分为6个步骤

`BIOS -> MBR -> BootLoader(grub) -> kernel -> systemd -> 系统初始化 -> shell`

```shell
dd if=/dev/vda of=mbr.bin bs=446 count=1
hexdump -C mbr.bin

dd if=/dev/vda of=mbr2.bin bs=512 count=1
hexdump -C mbr2.bin | more


uname -a
# 查看内核
uname -r

file /tmp/1.log

```



## Shell脚本

**UNIX的哲学：一条命令只做一件事**

为了组合命令和多次执行，使用脚本文件来保存需要执行的命令

赋予该文件执行权限（`chmod u+rx filename`）

```shell
# 标准的shell脚本要包含哪些元素

#!/bin/bash # Sha-Bang
命令
#号开头的注释

chmod u+rx filename # 可执行权限

# 执行命令
bash ./filename.sh
./filename.sh  # 默认用#!指定的解释器执行

# 下面这2种方式对当前环境是有影响的，上面这2种没有影响
source ./filename.sh
.filename.sh
```



## 内建命令和外部命令

内建命令不需要创建子进程

内建命令对当前Shell生效



## 管道和重定向

管道与管道符

子进程与子shell

重定向符号

```shell
管道和信号一样，也是进程通信的方式之一
匿名管道（管道符）是Shell编程经常用到的通信工具
管道符是“|”，将前一个命令执行的结果传递给后面的命令
ps | cat
echo 123 | ps
```

```shell
一个进程默认会打开标准输入、标准输出、错误输出三个文件描述符

输入重定向符号 "<"
read var < /path/to/a/file

输出重定向符号 ">" ">>" "2>" "&>"
echo 123 >/path/to/a/file

输入和输出重定向组合使用
cat >/path/to/a/file <<EOF
I am $USER
EOF

wc -l
wc -l < /etc/passwd

read var
123
echo $var
123

vim 1.txt
123
:wq

read var < 1.txt
echo $var


```

## 变量

变量的定义

```shell
# 变量名的命名规则
字母、数字、下划线
不以数字开头
```



变量的赋值

```shell
# 为变量赋值的过程，称为变量替换
变量名=变量值
a=123

# 使用let为变量赋值，右边可计算
let a=10+20

# 将命令赋值给变量
l=ls

# 将命令结果赋值给变量，使用$()或者``
letc=$(ls -l /etc)

# 变量值有空格等特殊字符可以包含在""或''
```



变量的引用

```shell
# 变量的引用
${变量名} # 称作对变量的引用

echo ${变量名} # 查看变量的值
${变量名} # 在部分情况下可以省略为 $变量名
```



变量的作用范围

```shell
# 变量的默认作用范围

# 变量的导出
export

# 变量的删除
unset
```



系统环境变量

```shell
# 环境变量：每个Shell打开都可以获得到的变量

set和env命令

$?
$$
$0

$PATH
$PS1

# 位置变量
$1
$2
$n
```

```shell
# 查看当前系统环境变量
env | more

echo $USER
echo $UID
echo $PATH

echo $PS1

set | more

echo $? # 上一条命令的返回值
echo $$ # 当前进程的pid 
echo $0 # 当前进程名称

# 脚本传参
echo $1
echo $2
echo ${10}

export PATH=$PATH:/root

export JAVA_HOEM=XXX
export PATH=$PATH:$JAVA_HOME
```





环境变量配置文件

```shell
# 配置文件
/etc/profile
/etc/profile.d/
~/.bash_profile
~/.bashrc
/etc/bashrc

加载顺序：
/etc/profile
bash_profile
.bashrc
/etc/bashrc
```



##  数组

```shell
# 定义数组
IPTS=( 10.0.0.1 10.0.0.2 10.0.0.3 )

# 显示数组的所有元素
echo ${IPTS[@]}

# 显示数组元素个数
echo ${#IPTS[@]}

# 显示数组的第一个元素
echo ${IPTS[0]}
```



## 转义和引用

特殊字符

```shell
# 特殊字符：一个字符不仅有字面意义，还有元意（meta-meaning）
# 注释
; 分号
\ 转义符号
"和引号'
```



转义

```shell
# 单个字符前的转义符号
\n \r \t # 单个字母的转义
\$ \" \\ # 单个非字母的转义
```



引用

```shell
# 常用的引用符号
" 双引号
' 单引号
` 反引号
```

```shell
a=11
echo '$a'
$a
echo "$a"
11

```

## 运算符

赋值运算符

```shell
= # 赋值运算符，用于算数赋值和字符串赋值

# 使用unset取消为变量的赋值

# = 除了作为赋值运算符还可以作为测试操作符
```



算数运算符

```shell
# 基本运算符
+ 
- 
* 
/ 
**
%

# 使用expr进行运算
expr 4+5
```



数字常量

```shell
# 数字常量的使用方法
let "变量名=变量值"

变量值使用0开头为八进制

变量值使用0x开头为十六进制
```



双圆括号

```shell
# 双圆括号是let命令的简化
(( a=10 ))

(( a++ ))

echo $((10+20))
```

## 特殊符号

引号

```shell
' 完全引用
" 不完全引用
` 执行命令
```



括号

```shell
() (()) $()
# 单独使用圆括号会产生一个子shell
( xyZ=123 )

echo $(( 1+2 ))
3

a=${ls /tmp}
echo $a


# 数组初始化
IPS=( ip1 ip2 ip3 )

[] [[]]
单独使用方括号是测试(test)或数组元素功能
两个方括号表示测试表达式
[ 5 -gt 4 ]
echo $?
0
[ 5 -gt 6 ]
echo $?
1

[[ 5 > 4 ]]
echo $?
0
[[ 5 > 6 ]]
echo $?
1

<> > < # 重定向符号

{}
# 输出范围
echo {0..9}
1 2 3 4 5 6 7 8 9
# 文件复制
cp /etc/passwd{,.bak}
等同
cp /etc/passwd /etc/passwd.bak


```



运算和逻辑符号

```shell
# 算数运算符
+ - * / %

# 比较运算符
> < =

# 逻辑运算符
&& || !
```



转义符号

```shell
\ 转义某字符

\n 普通字符转义之后有不同的功能

\' 特殊字符转义之后，当做普通字符来使用
```



其他符号

```shell
# 注释符

; 命令分隔符
case语句的分隔符要转义;;

: 空指令

. 和source命令相同

~ 家目录

, 分隔目录

* 通配符

? 条件测试或通配符

$ 取值符号

| 管道符

& 后台运行

_ 空格
```

## 测试和判断

退出与退出状态

```shell
# 退出程序命令
exit
exit 10 # 返回10给Shell，返回值非0位不正常退出

$? # 判断当前Shell前一个进程是否正常退出
```



测试命令test

```shell
test命令用于检查文件或者比较值

test可以做以下测试：
文件测试
整数比较测试
字符串测试

test测试语句可以简化为[]符号

[]符号还有扩展写法[[]] 支持&& || < >
```



```shell
# 重要！！！！
man test

test -f /etc/passed
echo $?
0
test -f /etc/passed11
echo $?
1

[ -d /etc/ ]
echo $?
0
[ -d /etc11/ ]
echo $?
1

[ 5 -gt 4 ]
echo $?
0
[ 5 -gt 6 ]
echo $?
1

# 直接用>必须要2个[[]]
[[ 5 > 4 ]]
echo $?
0
[[ 5 > 6 ]]
echo $?
1

```





使用if-then语句

```shell
if-then语句的基本用法

if [ 测试条件成立 ] 或 命令返回值是否为0 
then 执行相应命令
fi 结束
```





使用if-then-else语句

```shell
if-then-else语句可以在条件不成立时也运行相应的命令

if [ 测试条件成立 ]
then 执行相应命令
else 测试条件不成立，执行相应命令
fi结束

if-then-else语句可以在条件不成立时也运行相应的命令
if [ 测试条件成立 ]
then 执行相应命令
elif [ 测试条件成立 ]
then 执行相应命令
else 测试条件不成立，执行相应命令
fi 结束
```





嵌套if的使用

```shell
if条件测试中可以再嵌套if条件测试
if [ 测试条件成立 ]
then 执行相应命令
	if [ 测试条件成立 ]
	then 执行相应命令
	fi
fi 结束
```



分支

```shell
case语句和select语句可以构成分支

Case "$变量" in
	"情况1" )
	命令...;;
    "情况2" )
    命令...;;
	* )
	命令...;;
esac
```

```shell
#!/bin/bash

# case demo

case "$1" in
	"start"|"START")
		echo $0 start...
	;;
	"stop")
		echo $0 stop...
	;;
	"restart"|"reload")
	 	echo $0 restart...
	;;
	*)
		echo "Usage: $0 {start|stop|reload}"
	;;
esac
```



## 循环

使用for循环遍历命令的执行结果

```shell
# for循环的语法

for 参数 in 列表
do 执行的命令
done 封闭一个循环

使用反引号或$()方式执行命令，命令的结果当作列表进行处理

列表中包含多个变量，变量用空格分隔
对文本处理，要使用文本查看命令取出文本内容，默认逐行处理，如果文本出现空格会当做多行处理
```

```shell
for i in {1..9}
do
	echo $i
done
1
2
3
4
...

for i in {1..9}; do echo $i; done

ls a.log
a.log

basename a.log
a.log
basename a.log .log
a

touch a.log b.log c.log
for filename in `ls *.log`
do 
	mv $filename $(basename $filename .log).txt
done
```



使用for循环遍历变量和文件的内容

C语言风格的for命令

```shell
for ((变量初始化; 循环判断条件; 变量变化))
do
     循环执行的命令
done
```

```shell
for ((i=1; i<10; i++))
do
	echo $i
done


```



while循环

```shell
while test测试是否成立
do
    命令
done
```



死循环

```shell
a=1
while [ $a -lt 10 ]
do
	echo $a
done

while [ $a -lt 10 ]; do ((a++)); echo $a; done

while :
do 
	echo aaa
done
```





until循环

```shell
until循环与while循环相反，循环测试为假时，执行循环，为真时循环停止
```



break和continue语句

```shell
循环和循环可以嵌套
循环中可以嵌套判断，反过来也可以嵌套
循环可以使用break和continue语句在循环中退出
```

```shell
for name in /etc/profile.d/*.sh
do
	echo $name
done

for name in /etc/profile.d/*.sh
do
	if [ -x $name ]; then
		. $name
	fi
done

for i in {1..9}
do 
	if [ $i -eq 5 ]; then
		break
		# continue
	fi
	echo $i
done
```



使用循环对命令行参数的处理

```shell
命令行参数可以使用 $1 $2 .. ${10} $n 进行读取
$0 代表脚本名称
$* 和 $@ 代表所有位置参数
$# 代表位置参数的数量
```

```shell
#!/bin/bash

for i in $*
do
	if [ "$i" = "help" ]; then
		echo $i
	fi
done

while [ $# -ge 1 ]
do
	if [ "$i" = "help" ]; then
		echo $i
	fi
	shift
done
```

## 函数

### 自定义函数

```shell
函数用于“包含”重复使用的命令集合

# 自定义函数
function fname() {
	命令
}

# 函数的执行
fname

# 函数作用范围的变量
local 变量名

# 函数的参数
$1 $2 $3 ... $n
```

```shell
checkpid() {
	local i
	for i in $*; do
		[ -d "/proc/$i" ] && return 0
	done
	return 1
}
```



### 系统函数库

```shell
系统自建了函数库，可以在脚本中引用
/etc/init.d/functions

自建函数库
使用 source 函数脚本文件 “导入”函数
```



```shell
vim /etc/init.d/functions
source /etc/init.d/functions

echo_success

vim /etc/profile
vim .bashrc
vim .bash_profile

```







# 文本操作





# 常用服务搭建





# 课件

https://github.com/geektime-geekbang/geekbanglinux/



# 参考

https://www.cnblogs.com/twlwbiubiu/p/17856038.html



