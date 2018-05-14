set2
1.回答：使用getRow()函数，此题中写法应该是loc1.getRow()  

` ` ` `代码：
` ` ` ` ` ` // @file: GridWorldCode\framework\info\gridworld\grid\Location.java
` ` ` ` ` ` // @line: 110-112
```
    public int getRow()
    {
        return row;
    }
```  

2.回答：b是false

` ` ` `代码：
` ` ` ` ` ` // @file: GridWorldCode\framework\info\gridworld\grid\Location.java
` ` ` ` ` ` // @line: 205-212
```
    public boolean equals(Object other)
    {
        if (!(other instanceof Location))
            return false;

        Location otherLoc = (Location) other;
        return getRow() == otherLoc.getRow() && getCol() == otherLoc.getCol();
    }
```

3.回答：loc3的坐标为(4,4)

` ` ` `代码：
` ` ` ` ` ` // @file: GridWorldCode\framework\info\gridworld\grid\Location.java
` ` ` ` ` ` // @line: 130-169
```
     public Location getAdjacentLocation(int direction)
    {
        // reduce mod 360 and round to closest multiple of 45
        int adjustedDirection = (direction + HALF_RIGHT / 2) % FULL_CIRCLE;
        if (adjustedDirection < 0)
            adjustedDirection += FULL_CIRCLE;

        adjustedDirection = (adjustedDirection / HALF_RIGHT) * HALF_RIGHT;
        int dc = 0;
        int dr = 0;
        if (adjustedDirection == EAST)
            dc = 1;
        else if (adjustedDirection == SOUTHEAST)
        {
            dc = 1;
            dr = 1;
        }
        else if (adjustedDirection == SOUTH)
            dr = 1;
        else if (adjustedDirection == SOUTHWEST)
        {
            dc = -1;
            dr = 1;
        }
        else if (adjustedDirection == WEST)
            dc = -1;
        else if (adjustedDirection == NORTHWEST)
        {
            dc = -1;
            dr = -1;
        }
        else if (adjustedDirection == NORTH)
            dr = -1;
        else if (adjustedDirection == NORTHEAST)
        {
            dc = 1;
            dr = -1;
        }
        return new Location(getRow() + dr, getCol() + dc);
    }   
```
4.回答：从坐标图可以直观看出，（6，5）位于（4，3）的东南方，所以此时dir的值为135°  

` ` ` `代码：
` ` ` ` ` ` // @file: GridWorldCode\framework\info\gridworld\grid\Location.java
` ` ` ` ` ` // @line: 178-190
```
    public int getDirectionToward(Location target)
    {
        int dx = target.getCol() - getCol();
        int dy = target.getRow() - getRow();
        int angle = (int) Math.toDegrees(Math.atan2(-dy, dx));
        int compassAngle = RIGHT - angle;
        compassAngle += HALF_RIGHT / 2;
        if (compassAngle < 0)
            compassAngle += FULL_CIRCLE;
        return (compassAngle / HALF_RIGHT) * HALF_RIGHT;
    }   
```
5.回答：从Location getAdjacentLocation(int direction)的形式上可以看出，这个函数**传入的参数是一个角度**，所以**需要找寻的方向**已经确定；假设此时有某物体位于位置A，先由参数direction确定位置，随后找到在给定方向上最邻近的位置（相对位置为1的位置）。  

` ` ` `代码：
` ` ` ` ` ` // @file: GridWorldCode\framework\info\gridworld\grid\Location.java
` ` ` ` ` ` // @line: 130-169
```
    public Location getAdjacentLocation(int direction)
    {
        // reduce mod 360 and round to closest multiple of 45
        int adjustedDirection = (direction + HALF_RIGHT / 2) % FULL_CIRCLE;
        if (adjustedDirection < 0)
            adjustedDirection += FULL_CIRCLE;

        adjustedDirection = (adjustedDirection / HALF_RIGHT) * HALF_RIGHT;
        int dc = 0;
        int dr = 0;
        if (adjustedDirection == EAST)
            dc = 1;
        else if (adjustedDirection == SOUTHEAST)
        {
            dc = 1;
            dr = 1;
        }
        else if (adjustedDirection == SOUTH)
            dr = 1;
        else if (adjustedDirection == SOUTHWEST)
        {
            dc = -1;
            dr = 1;
        }
        else if (adjustedDirection == WEST)
            dc = -1;
        else if (adjustedDirection == NORTHWEST)
        {
            dc = -1;
            dr = -1;
        }
        else if (adjustedDirection == NORTH)
            dr = -1;
        else if (adjustedDirection == NORTHEAST)
        {
            dc = 1;
            dr = -1;
        }
        return new Location(getRow() + dr, getCol() + dc);
    }   
```

set3
1.回答：
（1）使用grid.getOccupiedLocations()函数，可以找到所有被占有的单元格的位置，这个函数的返回值是一个数组；再用size（）函数计算出数组中元素的准确个数。  

（2）计算出空白的单元格数，可以使用**公式**：
> 未被占用的单元格数 = 总单元格数 - 已经被占用的单元格数(grid.getOccupiedLocation.size())  

且  

> 总单元格数 = grid的长的数目（grid.getNumRows()） *  grid的宽的数目(grid.getNumCols())  


` ` ` `代码：
` ` ` ` ` ` // @file: GridWorldCode\framework\info\gridworld\grid\BoundedGrid.java
` ` ` ` ` ` // @line: 66-83
```
    public ArrayList<Location> getOccupiedLocations()
    {
        ArrayList<Location> theLocations = new ArrayList<Location>();
        for (int r = 0; r < getNumRows(); r++)
        {
            for (int c = 0; c < getNumCols(); c++)
            {
                // If there's an object at this location, put it in the array.
                Location loc = new Location(r, c);
                if (get(loc) != null)
                    theLocations.add(loc);
            }
        }
        return theLocations;
    }
```
2.回答：判断某个点是否在grid内可以使用isValid(Location loc)函数，如果这个点在grid内返回true，否则返回false；所以这里可以通过判断isValid（（10，10））的返回值是true还是false来判断是不是在grid内

` ` ` `代码：
` ` ` ` ` ` // @file: GridWorldCode\framework\info\gridworld\grid\BoundedGrid.java
` ` ` ` ` ` // @line: 60-64
```
    public boolean isValid(Location loc)
    {
        return 0 <= loc.getRow() && loc.getRow() < getNumRows()
                && 0 <= loc.getCol() && loc.getCol() < getNumCols();
    }
```

3.回答：（1）从Grid文件的定义中，可以发现，Grid并不是一个完整的类，而是一个接口，所以Grid并不会提供相应具体的方法来让我们使用，而是只提供了一个方法模型；而其他的类可以通过对这个接口的继承，来实现对方法的调用；同时在不同的类中，不同的继承，可能来自同一个借口的函数的功能也是不一样的。  

（2）我们可以从 AbstractGrid类, BoundedGrid类和 UnboundedGrid类中找到对应方法的实现  


` ` ` `例如：对子类BoundedGrid的定义
` ` ` ` ` ` // @file: GridWorldCode\framework\info\gridworld\grid\BoundedGrid.java
` ` ` ` ` ` // @line: 29
```
    public class BoundedGrid<E> extends AbstractGrid<E>
```
  

` ` ` `例如：对BoundedGrid中get()方法的实现,Grid文件中已经给出了get()函数，但是并没有实现，在BoundedGrid中对get()函数根据需求进行实现
` ` ` ` ` ` // @file: GridWorldCode\framework\info\gridworld\grid\BoundedGrid.java
` ` ` ` ` ` // @line: 85-91
```
    public E get(Location loc)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        return (E) occupantArray[loc.getRow()][loc.getCol()]; // unavoidable warning
    }
```
4.回答：我不认为用数组更好。因为ArrayList是动态的，没有固定的大小，可以在需要时候增加或者减少来降低内存空间的使用；而array不可以，array是静态的，只能固定长度，不方便我们进行数据的删减，同时也可能会造成大量内存空间的浪费或者泄露。  

` ` ` `例如：getOccupation()方法中实现对ArrayList的增长
` ` ` ` ` ` // @file: GridWorldCode\framework\info\gridworld\grid\BoundedGrid.java
` ` ` ` ` ` // @line: 66-83
```
    public ArrayList<Location> getOccupiedLocations()
    {
        ArrayList<Location> theLocations = new ArrayList<Location>();

        // Look at all grid locations.
        for (int r = 0; r < getNumRows(); r++)
        {
            for (int c = 0; c < getNumCols(); c++)
            {
                // If there's an object at this location, put it in the array.
                Location loc = new Location(r, c);
                //这里实现对ArrayList的动态加长
                if (get(loc) != null)
                    theLocations.add(loc);
            }
        }

        return theLocations;
    }
```

set5
1.回答:location、direction、color  

` ` ` `actor类中的私有属性成员：
` ` ` ` ` ` // @file: GridWorldCode\framework\info\gridworld\actor\Actor.java
` ` ` ` ` ` // @line: 31-34
```
    private Grid<Actor> grid;
    private Location location;
    private int direction;
    private Color color;
```

2.回答：当构造一个新的actor类型的对象时候，方向会被默认置为:**North**，颜色会被默认置为:**Blue**  

` ` ` `actor类中的构造函数：
` ` ` ` ` ` // @file: GridWorldCode\framework\info\gridworld\actor\Actor.java
` ` ` ` ` ` // @line: 37-45
```
    public Actor()
    {
        color = Color.BLUE;
        direction = Location.NORTH;
        grid = null;
        location = null;
    }
```

3.回答：因为actor这个类中，不仅定义了方法，而且还对方法进行了具体的实现，所以这个actor是一个类，如果actor是以一个接口的话，那么定义的这些方法再进行实现是不允许被实现的。


` ` ` `actor类中setColor()方法的实现：
` ` ` ` ` ` // @file: GridWorldCode\framework\info\gridworld\actor\Actor.java
` ` ` ` ` ` // @line: 60-64
```
    public void setColor(Color newColor)
    {
        color = newColor;
    }
```
4.回答：
(1)不会，一个actor不可以连续添加两次  
(2)不会，一个actor只允许被删除一次
(3)可以，一个actor先被加入，后被删除，随后再加入，从本质上说自始至终都是这个actor一个实体在进行行动，同时也没有出现多次出现以及多次被删除的情况。

` ` ` `自己写一个ActorRunner进行测试：
` ` ` ` ` ` // @file: Desktop/ActorRunner.java
` ` ` ` ` ` // @line: all
```
    public class ActorRunner 
    { 
        public static void main(String[] args) 
        { 
            ActorWorld world = new ActorWorld();
            Actor a = new Actor(); 
            //（1）test
            world.add(a);    
            //world.add(a);错误
            //(2) test
            a.removeSelfFromGrid(); 
            //a.removeSelfFromGrid(); 错误
            //(3) test
            world.add(a);//正确     
            world.add(new Rock()); 
            world.show(); 
        } 
    } 
```
5.回答：使用actor.setDirection(getDirection() + Location.RIGHT)方法进行实现右转90°  

` ` ` `使用actor类中setDirection()方法的实现转向90°：
` ` ` ` ` ` // @file: GridWorldCode\framework\info\gridworld\actor\Actor.java
` ` ` ` ` ` // @line: 80-86

```
    public void setDirection(int newDirection)
    {
        direction = newDirection % Location.FULL_CIRCLE;
        if (direction < 0)
            direction += Location.FULL_CIRCLE;
    }
```

set6
1.回答：isValid()函数保证了bug不会移动到grid之外

` ` ` `使用isValid()方法保证bug不出界
` ` ` ` ` ` // @file: GridWorldCode\framework\info\gridworld\actor\Bug.java
` ` ` ` ` ` // @line: 78-79

```
    if (gr.isValid(next))
        moveTo(next);
```

2.回答：
>   return (neighbor == null) || (neighbor instanceof Flower);  

这句话保证了**bug不会向岩石上移动**  

` ` ` `使用查找neighbor的方法
` ` ` ` ` ` // @file: GridWorldCode\framework\info\gridworld\actor\Bug.java
` ` ` ` ` ` // @line: 101
```
   return (neighbor == null) || (neighbor instanceof Flower); 
```
3.回答：使用到的接口有isValid()函数和get()函数，这两个函数前者确保bug可以进行正常的移动，后者保证bug可以避开岩石或者再次走到花上面；  

` ` ` `使用get()和isValid()方法
` ` ` ` ` ` // @file: GridWorldCode\framework\info\gridworld\actor\Bug.java
` ` ` ` ` ` // @line: 78-79和101
```
   if (gr.isValid(next)) moveTo(next);
   和
   return (neighbor == null) || (neighbor instanceof Flower);
```
4.回答：canMove()函数中调用了Location类中的getAdjacentLocation()函数。调用这个函数作用是为了找到bug下一个可以移动的位置。  
` ` ` `调用getAdjacentLocation()方法
` ` ` ` ` ` // @file: GridWorldCode\framework\info\gridworld\actor\Bug.java
` ` ` ` ` ` // @line: 97
```
   Location next = loc.getAdjacentLocation(getDirection());
```
5.回答：从Actor类中继承过来三个函数：getGrid()、getLocation()、getDirection()  

` ` ` `继承actor并调用函数
` ` ` ` ` ` // @file: GridWorldCode\framework\info\gridworld\actor\Bug.java
` ` ` ` ` ` // @line: 64、76、96
```
   1. setDirection(getDirection() + Location.HALF_RIGHT);
   2. Location loc = getLocation();
   3. Location loc = getLocation();
```
6.当bug前面的位置位于grid外面时候，使用move方法会让bug把自己从grid中移除  


` ` ` `bug到达边界再次move使用remove删除自己
` ` ` ` ` ` // @file: GridWorldCode\framework\info\gridworld\actor\Bug.java
` ` ` ` ` ` // @line:78-81
```
    if (gr.isValid(next))
        moveTo(next);
    else
        removeSelfFromGrid();
```
7.需要，因为在bug移动过程中，bug的location会被多次改变，从而需要一个变量loc来标记每一次bug移动之前的位置，同时判断下一个位置是否可以进行移动；同时这个loc还可以用于放置一朵花  

` ` ` `canMove()方法中loc变量的使用
` ` ` ` ` ` // @file: GridWorldCode\framework\info\gridworld\actor\Bug.java
` ` ` ` ` ` // @line:76-77
```
    Location loc = getLocation();
    Location next = loc.getAdjacentLocation(getDirection());
```
8.回答：这个是由bug类定义的，bug移动之后会在bug移动之前那个位置放置一朵与自身颜色相同的花
` ` ` `bug设置花颜色以及花的颜色设置方法
` ` ` ` ` ` // @file: GridWorldCode\framework\info\gridworld\actor\Flower.java  
GridWorldCode\framework\info\gridworld\actor\Bug.java

` ` ` ` ` ` // @line:46-49（flower）和82-83（bug）
```
    public Flower(Color initialColor)
    {
        setColor(initialColor);
    }
    Flower flower = new Flower(getColor());//设置花的颜色与bug相同
    flower.putSelfInGrid(gr, loc);//找到bug移动之前的位置放置花
```
9.回答：不会，当bug调用从actor类中继承的removeSelfFromGrid()函数时候，这个过程并未涉及到有产生花的方法； 

` ` ` `关于removeSelfFromGrid()函数
` ` ` ` ` ` // @file: GridWorldCode\framework\info\gridworld\actor\Actor.java
` ` ` ` ` ` // @line:133-146
```
    public void removeSelfFromGrid()
    {
        if (grid == null)
            throw new IllegalStateException(
                    "This actor is not contained in a grid.");
        if (grid.get(location) != this)
            throw new IllegalStateException(
                    "The grid contains a different actor at location "
                            + location + ".");

        grid.remove(location);
        grid = null;
        location = null;
    }
```
10.回答：
> Flower flower = new Flower(getColor());
  flower.putSelfInGrid(gr, loc);  

这简短的两行代码就实现了产生花并设置颜色的功能。  

` ` ` `花的产生以及颜色设定
` ` ` ` ` ` // @file:  
GridWorldCode\framework\info\gridworld\actor\Bug.java

` ` ` ` ` ` // @line:）82-83
```
    Flower flower = new Flower(getColor());
    flower.putSelfInGrid(gr, loc);
```
11.回答：共4次；由于turn一次就只能旋转45°，所以就需要进行4次旋转。  

` ` ` `关于turn()函数调用
` ` ` ` ` ` // @file: GridWorldCode\framework\info\gridworld\actor\Actor.java
` ` ` ` ` ` // @line:61-65
```
    public void turn()
    {
        setDirection(getDirection() + Location.HALF_RIGHT);
    }
```