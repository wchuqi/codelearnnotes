# 1 BFS
```text
BFS 算法都是用「队列」这种数据结构，每次将一个节点周围的所有节点加入队列。

本质上就是一幅「图」，让你从一个起点，走到终点，问最短路径。这就是 BFS 的本质。

BFS 相对 DFS 的最主要的区别是：BFS 找到的路径一定是最短的，但代价就是空间复杂度比 DFS 大很多。


队列是BFS 的核心数据结构。
```

```java
// 计算从起点 start 到终点 target 的最近距离  
int BFS(Node start, Node target) {  
 Queue<Node> q; // 核心数据结构  
 Set<Node> visited; // 避免走回头路  
  
 q.offer(start); // 将起点加入队列  
 visited.add(start);  
 int step = 0; // 记录扩散的步数  
  
 while (q not empty) {  
	 int sz = q.size();  
	 /* 将当前队列中的所有节点向四周扩散 */  
	 for (int i = 0; i < sz; i++) {  
	 Node cur = q.poll();  
	 /* 划重点：这里判断是否到达终点 */  
	 if (cur is target)  
		 return step;  
	 /* 将 cur 的相邻节点加入队列 */  
	 for (Node x : cur.adj())  
		 if (x not in visited) {  
			 q.offer(x);  
			 visited.add(x);  
		 }  
	 }  
	 /* 划重点：更新步数在这里 */  
	 step++;  
 }  
}
```

参考：
https://mp.weixin.qq.com/s?__biz=MzAxODQxMDM0Mw==&mid=2247485134&idx=1&sn=fd345f8a93dc4444bcc65c57bb46fc35&chksm=9bd7f8c6aca071d04c4d383f96f2b567ad44dc3e67d1c3926ec92d6a3bcc3273de138b36a0d9&scene=21#wechat_redirect

