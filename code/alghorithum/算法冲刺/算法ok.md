---
tags: []
parent: ""
collections: []
$version: 0
$libraryID: 1
$itemKey: SJAQKBUM

---
# 回溯算法

**回溯问题，实际上就是决策树的遍历问题。**

## 回溯算法框架

只需要思考3个问题:

*   1、路径：也就是已经做出的选择。
*   2、选择列表：也就是你当前可以做的选择。
*   3、结束条件：也就是到达决策树底层,无法再做选择的条件。

```
result = [] 
def backtrack(路径, 选择列表):  
    if 满足结束条件:  
        result.add(路径)  
        return  
    for 选择 in 选择列表:  
        # 做选择  
        将该选择从选择列表移除
        路径.add(选择)
        backtrack(路径, 选择列表)  
        # 撤销选择
        路径.remove(选择)
        将该选择再加入选择列表

```

如何遍历一棵树？

各种搜索问题其实都是树的遍历问题

多叉树的遍历框架

    void traverse(TreeNode root) { 
         for (TreeNode child : root.childern)  
             // 前序遍历需要的操作  
             traverse(child);  
             // 后序遍历需要的操作  
    }

归调用
