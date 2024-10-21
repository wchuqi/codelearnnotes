

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

CentOS （Community Enterprise Operating System）

2个图形化桌面系统：

Ubuntu

Debian



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
man 7 man

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

**一切皆文件**

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



## 用户和权限管理

`useradd`   新建用户

`userdel`   删除用户

`passwd`   修改用户密码

`usermod`  修改用户属性
`chage`     修改用户密码过期信息



`groupadd`    新建用户组
`groupdel`     删除用户组



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





# 服务管理



# shell脚本



# 文本操作





# 常用服务搭建





# 课件

https://github.com/geektime-geekbang/geekbanglinux/