# 1 回溯算法
回溯算法其实就是DFS。
```text
解决一个回溯问题，实际上就是一个决策树的遍历过程。
只需要思考3个问题：
1、路径：也就是已经做出的选择。
2、选择列表：也就是你当前可以做的选择。
3、结束条件：也就是到达决策树底层，无法再做选择的条件。

不像动态规划存在重叠子问题可以优化，回溯算法就是纯暴力穷举，复杂度一般都很高。
```

## 1.1 回溯算法的框架
```java
result = []  
def backtrack(路径, 选择列表):  
 if 满足结束条件:  
 result.add(路径)  
 return  
  
 for 选择 in 选择列表:  
	 做选择  
	 backtrack(路径, 选择列表)  
	 撤销选择
```

## 1.2 算法例题
全排列
N 皇后

## 1.3 参考
https://mp.weixin.qq.com/s?__biz=MzAxODQxMDM0Mw==&mid=2247484709&idx=1&sn=1c24a5c41a5a255000532e83f38f2ce4&chksm=9bd7fb2daca0723be888b30345e2c5e64649fc31a00b05c27a0843f349e2dd9363338d0dac61&scene=21#wechat_redirect

