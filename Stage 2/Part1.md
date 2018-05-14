1.回答: sideLength这个变量在BoxBug这个类中限定每一次bug沿着某一条边走的最远步长（在不遇到障碍或者边界的情况下），在到达这个步长之后，Boxbug就要调转运动方向。  

` ` ` ` ` `  ·举例  

` ` ` ` ` ` // @file: GridWorldCode/projects/BoxBug  
` ` ` ` ` ` // @line: 45
```
	if (steps < sideLength && canMove())
```
2.回答：steps用于记录BoxBug这个类中任一个Boxbug在当前所处的这条边上已经移动的步数  

` ` ` ` ` `  ·举例  

` ` ` ` ` ` // @file: GridWorldCode/projects/BoxBug  
` ` ` ` ` ` // @line: 45~50
```
	if (steps < sideLength && canMove()){
		move();
		steps++;
	}
```
3.回答：因为当bug在某一条边上移动的步数达到最大时候也即**（sideLength == steps）**时候，Boxbug需要转移方向，进入下一条边进行移动；又由于turn()函数调用一次只旋转45°，而Boxbug自身需要旋转90°才可以进入下一条边，所以要旋转两次。

` ` ` ` ` ` turn()函数:  

` ` ` ` ` ` // @file: GridWorldCode/framework/info/gridworld/actor/Bug
` ` ` ` ` ` // @line: 62-65
```
    public void turn()
    {
        setDirection(getDirection() + Location.HALF_RIGHT);
    }
```
` ` ` ` ` `act()函数:
` ` ` ` ` ` // @file: GridWorldCode/projects/BoxBug  
` ` ` ` ` ` // @line: 62-65
```
    else
    {
        turn();
        turn();
        steps = 0;
    }
```
4.回答：由于BoxBug从Bug继承而来，并且在BoxBug中并没有重载Bug的move()函数，所以BoxBug就继承了Bug运动方式，也即可以在直线运动遇到障碍无法行动时候选择调整自己的运动方向。

` ` ` ` ` `代码展示：

` ` ` ` ` ` // @file: GridWorldCode/framework/info/gridworld/actor/Bug
` ` ` ` ` ` // @line: 76-81
```
    Location loc = getLocation();
    Location next = loc.getAdjacentLocation(getDirection());
    if (gr.isValid(next))
        moveTo(next);
    else
        removeSelfFromGrid();
```
5.回答：当BoxBug被创建之后，由于sideLength最大步长已经在构造函数中被限定，同时Boxbug类也未提供其他可用的方法来改变sideLength，所以Boxbug行动时候描绘出的正方形大小无法再被改变。  

` ` ` ` ` `构造函数中限定sideLength:
` ` ` ` ` ` // @file: GridWorldCode/projects/BoxBug  

` ` ` ` ` ` // @line: 34-37  
```
    public BoxBug(int length)
    {
        steps = 0;
        sideLength = length;
    }
```
6.可以；由于Boxbug从Bug继承而来，所以BoxBug拥有对障碍物的判定与处理能力，也即当遇到阻碍自己运动的物体时，Boxbug会作出相应的反应（改变方向）同时自身的运动轨迹也就会发生变化。 
` ` ` ` ` `代码展示：

` ` ` ` ` ` // @file: GridWorldCode/framework/info/gridworld/actor/Bug  

` ` ` ` ` ` // @line: 76-81
```
    Location loc = getLocation();
    Location next = loc.getAdjacentLocation(getDirection());
    if (gr.isValid(next))
        moveTo(next);
    else
        removeSelfFromGrid();
```
7.在三种情况下Boxbug的steps被重新设置为零:  

` ` ` ` ` `①.初次调用构造函数创建一个新的Boxbug对象时候  

` ` ` ` ` `②.当Boxbug遇到障碍物改变自身运动方向之后   

` ` ` ` ` `③ 当Boxbug在某条道路上运动的最大步长达到sideLength的限制，进行转换运动方向之后   

` ` ` ` ` `构造函数中限定初始化steps = 0:
` ` ` ` ` ` // @file: GridWorldCode/projects/BoxBug  

` ` ` ` ` ` // @line: 34-37  
```
    public BoxBug(int length)
    {
        steps = 0;
        sideLength = length;
    }
```
或：
` ` ` ` ` `在Boxbug的act()函数中置steps = 0 :
` ` ` ` ` ` // @file: GridWorldCode/projects/BoxBug  
` ` ` ` ` ` // @line: 43-56
```
    public void act()
    {
        if (steps < sideLength && canMove())
        {
            move();
            steps++;
        }
        else
        {
            turn();
            turn();
            steps = 0;
        }
    }
```    