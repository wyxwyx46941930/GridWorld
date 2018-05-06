README.md
[toc]
###文件架构
Part5
|->1 
` ` ` `|-> Linklist-Grid(链表实现地图) 
` ` ` ` ` ` ` `|-> SparseBoundedGrid.java
` ` ` ` ` ` ` `|-> SparseGridNode.java
` ` ` `|-> Hash-Grid(哈希实现地图)  
` ` ` ` ` ` ` `|->SparseBoundedGrid2.java      

|->2  
` ` ` `|-> UnboundedGrid.java(无边界地图)

|->3 
` ` ` `|-> SparseGridRnnner.java(Runner主类)

|->README

###编译环境配置
1. 打开eclipse，新建javaproject，给定任意命名
2. 在project的path中添加gridworld.jar
3. 将project中java compiler的compile compliance level更换为1.5
4. 在同一目录下顺次导入五个java文件
5. 运行Runner文件
