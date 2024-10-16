# 前言

## 版本管理的演变

### VCS（version control system）出现前

1、用目录贝区别不同版本
2、公共文件容易被覆盖
3、成员沟通成本很高，代码集成效率低下

### 集中式VCS

1、有集中的版本管理服务器
2、具备文件版本管理和分支管理能力
3、集成效率有明显地提高
4、客户端必须时刻和服务器相连

比如：

SVN

<img src="images/image-20241008222614236.png" alt="image-20241008222614236" style="zoom:50%;" />

### 分布式VCS

1、服务端和客户端都有完整的版本库

2、脱离服务端，客户端照样可以管理版本

3、查看历史和版本比较等多数操作，都不需要访问服务器，比集中式VCS更能提高版本管理效率

<img src="images/image-20241008224130948.png" alt="image-20241008224130948" style="zoom:50%;" />

# git

## 特点

1、最优的存储能力

2、非凡的性能

3、开源的

4、很容易做备份

5、支持离线操作

6、很容易定制工作流程



基于git的产品：

GitHub（提供devops能力，发布部署，CI等）

GitLab（开源，可定制CI）



## 安装

https://git-scm.com/downloads



## 配置

配置`user.name` 和`user.email`

```shell
git config --global user.name "your_name"
git config --global user.email "your_email@domain.com"
```



config的三个作用域

缺省等同于`local`

```shell
# loca只对某个仓库有效
git config --local
# global对当前用户所有仓库有效
git config --global    
# system对系统所有登录的用户有效
git config --system
```

显示config的配置，加`--list`
```shell
git config --list --local
git config --list --global
git config --list --system

git config --local user.name

git config --local -l
```

        建Git仓库
初始化仓库

两种场景：
```shell
# 1.把已有的项目代码纳入Git管理
cd 项目代码所在的文件夹
git init

# 2.新建的项目直接用Git管理
cd 某个文件夹
git init your_project  # 会在当前路径下创建和项目名称同名的文件夹
cd your_project
```



<img src="images/image-20241008230640206.png" alt="image-20241008230640206" style="zoom:50%;" />

添加文件到暂存区

```shell
git add 文件1 文件2 目录1 目录2
git add .
git add -u  # 当前所有未跟踪的文件都加入到暂存区
```



提交文件

```shell
git commit -m "xxx"
git commit -m"xxx"

# 2个操作合并
git commit -am "xxxx" # add + commit
```

重命名文件

```shell
mv 1.txt 2.txt
git rm 1.txt
git add 2.txt
git status

# 把暂存区的所有工作回退
git reset --hard
git mv 1.txt 2.txt
git status
```

## 查看log

```shell
# 默认查看当前分支
git log
git log --oneline
git log -n1
git log -n4
git log -n4 --oneline
git log -n4 --oneline --all
git log -n4 --oneline --graph

# 查看所有分支
git log --all

# 查看具体分支
git log dev1
git log --oneline dev1
git log -n1 dev1

# 图形化方式
git log --all --graph
```

## 分支

```shell
git branch
git branch -a
git branch -vv
git branch -av

# 根据commit点创建分支
git checkout -b dev1 41c534sd2
git checkout -b dev1 tag1
git checkout -b dev1 master

# 切换分支
git checkout master
```

查看帮助

```shell
git help --web log
```

图形界面工具

`gitk`

## .git目录

```shell
# .git/HEAD文件
cat .git/HEAD
ref: refs/heads/dev1

git checkout master
cat .git/HEAD
ref: refs/heads/master

# .git/config文件
配置文件
cat .git/config

# .git/refs目录
cd .git/refs
heads  tags
存放分支  存放标签（里程碑）
cd .git/refs/heads
master dev1
cat master
41a3d33s3s

cd .git/refs/tags
tag1 tag2
cat tag1
54sf21s2

# 查看对象类型
git cat-file -t 41a3d33s3s
commit
git cat-file -t 54sf21s2
tag

# 查看对象内容
git cat-file -p 41a3d33s3s
git cat-file -p 54sf21s2

# .git/objects目录
ae b2 73 4a pack(打包目录)
cd ae
73ksffs92312kds8123k1214k2983
# 查看类型
git cat-file -t ae73ksffs92312kds8123k1214k2983
tree
git cat-file -p ae73ksffs92312kds8123k1214k2983
blob 4f0ksd923s09ffsd3sdf 1.txt
git cat-file -t 4f0ksd923s09ffsd3sdf
blob
git cat-file -p 4f0ksd923s09ffsd3sdf
1.txt的内容
```

## git对象的关系

commit

tree

blob



一个`commit`对象里面会包含有 `tree`（相当于文件夹）、`parent`、`author`和`committer`。

一个`tree`里面会包含`tree`和`blob`（具体的内容）。

一个`blob`指的就是具体的文件内容。在git中，`blob`与文件名无关，只与文件内容有关，即文件内容相同，在git中是同一个`blob`。



Git 对象 是 Git 的最小组成单位，git 的所有核心底层命令实际上都是在操作 git 对象。

`git add` 命令，就是把文件快照存储成 `blob` 对象。

`git commit` 命令，就是把提交的文件列表和提交信息分别存储成 `tree` 对象和 `commit` 对象。

`git checkout -b`创建分支命令，就是创建一个指针指向 `commit` 对象。



参考：

https://blog.csdn.net/yao_94/article/details/88648468

https://xiaowenxia.github.io/git-inside/2020/12/06/git-internal.objects/index.html



## 分离头指针

`detached HEAD`

可以继续提交commit，但是当切换到分支进行开发时，如果没有把当前commit绑定tag或者分支，git就可能会清理掉这个commit。

```shell
git checkout commitidxxx
做了修改
git commit -am "xxx"

git checkout master
提示：
git checkout dev2 commitxxxx
```

## git差异

```shell
git diff commit1 commit2
git diff HEAD HEAD^
git diff HEAD HEAD~1

git diff HEAD HEAD^^
git diff HEAD HEAD~2
```



## 常用操作

### 删除不需要的分支

```shell
git branch -av
gitk --all

# 删除分支
git branch -d 分支名称
git branch -D 分支名称

```



### 修改最新commit的message

```shell
git commit --amend
:wq!
```

### 修改旧commit的message

```shell
c2
c1
c0

如何修改c1?

git rebase -i c0
pick c2
r c1
pick c0
...
:wq!

# r, reward <commit> = use commit, but edit the commit message
```

注意：

已经提交到远程仓库的commit就不要随意`变基 rebase`了。

### 把连续的多个commit整理成一个

```shell
c3
c2
c1
c0

如何合并c1和c2

git rebase -i c0
pick c3
s c2
s c1
pick c0
:wq!

# s, squash <commit> = use commit, but meld into previous commit.
```

### 把间隔的几个commit整理成一个

```shell
git log --graph
c3
c2
c1
c0

如何把c1和c3合并

git rebase -i c0
pick c3
s c1
pick c2
pick c0
:wq!

git rebase --continue

```

### 比较暂存区和HEAD所含文件的差异

```shell
git diff --cached
```

### 比较工作区和暂存区所含文件的差异

```shell
git diff
git diff -- 文件名1
git diff -- 目录1/文件名2
git diff -- 文件名1 文件名3
```

### 让暂存区恢复成和HEAD一样

```shell
git status

use "git reset HEAD <file>..." to unstage 

git reset HEAD
git status

# 恢复部分文件
git reset HEAD -- 文件1
git reset HEAD -- 文件1 文件2
git reset HEAD -- 目录1/文件3
```

### 让工作区恢复成和暂存区一样

```shell
git status

use "git checkout -- <file>..." to discard changes in working directory
```

### 消除最近的几次提交（回退）

```shell
c4
c3
c2
c1
c0

回退到c2

git reset --hard c2

# 回退所有工作区和暂存区的修改
git reset --hard
```

### 查看不同提交的指定文件的差异

```shell
git diff dev master

git diff dev master -- 文件1

git diff commit1 commit2 -- 文件1
```

### 删除文件

```shell
# 方式1
rm file1
git add file1
git status
deleted file1

# 方式2
git rm file1
git status
deleted file1
```

### 工作区缓存(stash)

```shell
# 把工作区的内容临时缓存起来
git stash
git status

git stash list

# 使用缓存到工作区，但是不删除
git stash apply
git stash list

# 使用缓存到工作区，同时删除缓存
git stash pop
git stash list

git stash pop stash@{0}
git stash list
```

### 指定不需要git管理的文件（ignore）

```shell
vi .gitignore
```

### 将git仓库备份到本地

常用的传输协议

<img src="images/image-20241010234534666.png" alt="image-20241010234534666" style="zoom:70%;" />

直观区别：哑协议传输进度不可见；智能协议传输可见。
传输速度：智能协议比哑协议传输速度快。

<img src="images/image-20241010234950727.png" alt="image-20241010234950727" style="zoom:50%;" />

这个图有一种美感！！

```shell
git clone /path/a/b/c/.git aaa.git
git clone files:///path/a/b/c/.git bbb.git

git remote -v
git remote add aaa /path/a/b/c/.git
git remote add bbb files:///path/a/b/c/.git

git push --set-upstream aaa master
```



## Github使用

```shell
ls ~/.ssh

ssh-keygen -t rsa -b 4096 -C "your email"
ls ~/.ssh
id_rsa
id_rsa.pub

pbcopy < ~/.ssh/id_rsa.pub

git remote add github_1 ssh@xxxxxxx
git remote remove <name>
git remote rename <old> <new>

# 把本地所有分支都push到远端仓库
git push github_1 --all

# git pull = git fetch + merge
git pull

git fetch github_1 master
git checkout master
git merge github_1/master
报错：fatal: refusing to merge unrelated histories
git merge --allow-unrelated-histories github_1/master
# 然后可以push到远端了
git push github_1 master
```

## 多人协作

```shell
git clone -b master git_url <name>

cd <name>
git config --add --local user.name "xxx"

```

## 危险操作

**禁止向集成分支执行push -f操作**

**禁止向集成分支执行变更历史的操作（rebase）**

```shell
git push --force
git push -f

```

## Github

成功的因素：

1、找到一个需要解决的大问题

让Git更容易使用是GitHub的目标，但这并不是最终目标。

GitHub真正的愿景是使协作和编写软件更容易。

2、不断解决用户痛点

公司不仅致力于解决疑难问题，而且还致力于解决所有软件开发人员遇到的痛苦问题。

开发者需要一个更好、更直观的版本控制系统，它具有解决人类问题的巨大潜力，即轻松、安全和远程协作软件项目。



### 高级搜索

Advanced search

![image-20241016232339903](images/image-20241016232339903.png)

`rust in:readme stars:>1000 language:Go`

https://docs.github.com/en/search-github/github-code-search/understanding-github-code-search-syntax



## 组织

项目组成员

权限控制

Admin、Write、Read



## 开发模式

1、主干分支开发

<img src="images/image-20241016234321937.png" alt="image-20241016234321937" style="zoom: 50%;" />

2、Git Flow

<img src="images/image-20241016234405020.png" alt="image-20241016234405020" style="zoom: 67%;" />

3、Github Flow

<img src="images/image-20241016234519873.png" alt="image-20241016234519873" style="zoom:50%;" />

4、Gitlab Flow

**生产分支发布**

![image-20241016234952203](images/image-20241016234952203.png)



**不同环境分支发布**

<img src="images/image-20241016234838704.png" alt="image-20241016234838704" style="zoom:50%;" />

**不同发布分支**

<img src="images/image-20241016234759116.png" alt="image-20241016234759116" style="zoom:50%;" />

## 启用issue跟踪需求和任务

issue单

创建dashboard

code review

通过CI发布release包

github wiki



## GitLab

开源

强大的CI集成

Ruby开发。



应用部署到AWS上



# 参考

《玩转Git三剑客》极客时间



