set 7
1.回答：Critter中实现的**函数**有  
1. act()
2. getActors()
3. processActors()
4. getMoveLocations()
5. selectMoveLocation()
6. makeMove()  

**共六个函数**

` ` ` `六个函数原型：
` ` ` ` ` ` // @file: GridWorldCode\framework\info\gridworld\actor\Critter.java
` ` ` ` ` ` // @line: 38、56、71、88、104、125
```
    1. public void act()
    2. public ArrayList<Actor> getActors()
    3. public void processActors(ArrayList<Actor> actors)
    4. public ArrayList<Location> getMoveLocations()
    5. public Location selectMoveLocation(ArrayList<Location> locs)
    6. public void makeMove(Location loc)
```
2.回答：这五个基本行为分别是：  

1. getActors()
2. processActors()
3. getMoveLocations()
4. selectMoveLocation()
5. makeMove()  

` ` ` `五个函数原型：
` ` ` ` ` ` // @file: GridWorldCode\framework\info\gridworld\actor\Critter.java
` ` ` ` ` ` // @line: 56、71、88、104、125
```
    1. public ArrayList<Actor> getActors()
    2. public void processActors(ArrayList<Actor> actors)
    3. public ArrayList<Location> getMoveLocations()
    4. public Location selectMoveLocation(ArrayList<Location> locs)
    5. public void makeMove(Location loc)
```

3.回答：需要重载，因为Critter的子类调用getActors()函数时候，得到的应该是子类对象类型，而非父类对象类型，同时满足条件要求的actors也是不一样的，所以需要重载。  

举例说明：拿**Critter**与**CrabCritter**做个对比

` ` ` `Critter中的getActors()函数：
` ` ` ` ` ` // @file: GridWorldCode\framework\info\gridworld\actor\Critter.java
` ` ` ` ` ` // @line: 56-59 
```
    public ArrayList<Actor> getActors()
    {
        return getGrid().getNeighbors(getLocation());
    } 
```
**与** 

` ` ` `CrabCritter中的getActors()函数：
` ` ` ` ` ` // @file: GridWorldCode\projects\critters\CrabCritter.java
` ` ` ` ` ` // @line: 44-57 
```
    public ArrayList<Actor> getActors()
    {
        ArrayList<Actor> actors = new ArrayList<Actor>();
        int[] dirs =
            { Location.AHEAD, Location.HALF_LEFT, Location.HALF_RIGHT };
        for (Location loc : getLocationsInDirections(dirs))
        {
            Actor a = getGrid().get(loc);
            if (a != null)
                actors.add(a);
        }
        return actors;
    }
```

>**从两个函数的对比可以明显的发现，两个方法是完全不同的**

4.回答：  
1. 一个critters可以吃掉其他的actors 
2. 一个critters可以改变其他actors的颜色 
3. 一个critters让其他actors进行移动.

` ` ` `一个简单例子：Critter移动rock函数：
` ` ` ` ` ` // @file: GridWorldCode\framework\info\gridworld\actor\Critter.java
` ` ` ` ` ` // @line: 75-76 
```
    public void processActors(ArrayList<Actor> actors)
    {
        for (Actor a : actors)
        {
            if (!(a instanceof Rock) && !(a instanceof Critter))
                a.removeSelfFromGrid();
        }
    }
```
5.回答：促使Critter移动的三个函数以及功能依次是：  

1. getMoveLocations()
   功能:得到Critterz周围可以进行移动的空白区域
2. selectMoveLocation(ArrayList<Location> locs)
   功能:找到一个空白区域并返回位置
3. makeMove(Location loc)
   功能:移动Critter到第2步中找到的空白位置

` ` ` `三个函数具体执行过程：
` ` ` ` ` ` // @file: GridWorldCode\framework\info\gridworld\actor\Critter.java
` ` ` ` ` ` // @line: 88-90、104-111、125-131 
```
    public ArrayList<Location> getMoveLocations()
    {
        return getGrid().getEmptyAdjacentLocations(getLocation());
    }//先找到周围空白位置
    public Location selectMoveLocation(ArrayList<Location> locs)
    {
        int n = locs.size();
        if (n == 0)
            return getLocation();
        int r = (int) (Math.random() * n);
        return locs.get(r);
    }//确定某个空白位置
    public void makeMove(Location loc)
    {
        if (loc == null)
            removeSelfFromGrid();
        else
            moveTo(loc);
    }//完成移动
```
6.回答：由于Critter从Actors继承而来，所以可以调用Actors的默认构造函数，进而实现在runner类中进行Critter实例的创建。



` ` ` `Critter从Actors继承而来：
` ` ` ` ` ` // @file: GridWorldCode\framework\info\gridworld\actor\Critter.java  
GridWorldCode\framework\info\gridworld\actor\Actor.java
` ` ` ` ` ` // @line: 37(Critter)和39-45(Actor) 
```
    public class Critter extends Actor
    和
    public Actor()
    {
        color = Color.BLUE;
        direction = Location.NORTH;
        grid = null;
        location = null;
    }
```
set 8
1.回答：由于ChameleonCritter继承了Critter类，也就继承了Critter类中的函数，所以可以使用Critter中的方法；但是由于ChameleonCritter对makeMove()函数以及processActors()函数进行了重载，这也就导致了ChameleonCritter对象的运动方式与Critter不同，比如随机的将自身的颜色更改为自己前方或者后方Aactor的颜色,同时还会在周围没有actors时候加深自身颜色。  

` ` ` `ChameleonCritter中重载ProcessActors()函数：
` ` ` ` ` ` // @file: GridWorldCode\projects\critters\ChameleonCritter.java
` ` ` ` ` ` // 36-45（processActors）和50-54（maekMove） 
```
    public void processActors(ArrayList<Actor> actors)
    {
        int n = actors.size();
        if (n == 0)
            return;
        int r = (int) (Math.random() * n);

        Actor other = actors.get(r);
        setColor(other.getColor());
    }
    public void makeMove(Location loc)
    {
        setDirection(getLocation().getDirectionToward(loc));
        super.makeMove(loc);
    }
```
2.回答：由于当ChameleonCritter调用makeMove()函数进行移动时候，首先进行方向的改变，然后再调用父类的makeMove()函数移动到一个指定的位置。所以这里要使用super.makeMove()方法来调用父类方法。

` ` ` `子类makeMove()函数：
` ` ` ` ` ` // @file: GridWorldCode\projects\critters\ChameleonCritter.java
` ` ` ` ` ` // @line: 50-54 
```
    public void makeMove(Location loc)
    {
        setDirection(getLocation().getDirectionToward(loc));
        super.makeMove(loc);
    }
```

3.回答：要实现ChameleonCritter在移动前的位置上放置花，首先需要设置一个变量loc记录原来的位置，随后在ChameleonCritter完成移动之后，在loc上放置花并设置这个花的颜色与ChameleonCritter颜色相同。

` ` ` `自定义ChameleonCritter对象生成花函数makeFlower(Location loc)：
` ` ` ` ` ` // @file: Desktop\makeFlower.java
` ` ` ` ` ` // @line: all
```
    public void makeFlower(Location loc) 
    { 
        Location Loc_old = getLocation(); //找到旧位置
        setDirection(getLocation().getDirectionToward(loc)); //找到ChemoleonCritter对象新位置
        super.makeMove(loc); //让ChemoleonCritter移动
        if(!oldLoc.equals(loc)) 
            Flower flower = new Flower(getColor()); //设置颜色
            flower.putSelfInGrid(getGrid(), Loc_old);//把花放在旧位置上
        }
    } 
    
```
4.回答：因为ChameleonCritter继承Critter，Critter继承Actor，所以不管是Critter还是ChameleonCritter，在使用getActor()函数时候，都是为了得到地图上已经有的Actor类型实例对象，所以ChameleonCritter类没必要对getActor()函数进行重载。

` ` ` `Critter中getActor()函数
` ` ` ` ` ` // @file: GridWorldCode\framework\info\gridworld\actor\Critter.java
` ` ` ` ` ` // @line: 56-59
```
    public ArrayList<Actor> getActors()
    {
        return getGrid().getNeighbors(getLocation());
    }
 
    
```
5.回答：Actor类

` ` ` `Actor类中getLocation()函数
` ` ` ` ` ` // @file: GridWorldCode\framework\info\gridworld\actor\Actor.java
` ` ` ` ` ` // @line: 102-104
```
    public Location getLocation()
    {
        return location;
    }
    
```

6.回答：通过使用Actor类中getGrid()函数


` ` ` `Actor类中getGrid()函数
` ` ` ` ` ` // @file: GridWorldCode\framework\info\gridworld\actor\Actor.java
` ` ` ` ` ` // @line: 92-95
```
    public Grid<Actor> getGrid()
    {
        return grid;
    }
```

set9
1.回答：因为CrabCritter类从Critter类继承而来，并且二者吃东西的行为是一样的，都是去吃掉actors，所以并不需要对processActors()函数进行重载。

` ` ` `Critter中的processActors()函数
` ` ` ` ` ` // @file:GridWorldCode\framework\info\gridworld\actor\Critter.java
` ` ` ` ` ` // @line: 71-78
```
    public void processActors(ArrayList<Actor> actors)
    {
        for (Actor a : actors)
        {
            if (!(a instanceof Rock) && !(a instanceof Critter))
                a.removeSelfFromGrid();
        }
    }
```

2.回答：首先CrabCritter先使用getActor()函数找到自己左前方前方右前方的actor对象，随后使用processActors()函数把他们吃掉。其余位置的actor对象并不会被吃掉。

` ` ` `CrabCritter中的getActor()函数
` ` ` ` ` ` // @file:GridWorldCode\projects\critters\CrabCritter.java
` ` ` ` ` ` // @line: 44-57
```
    public ArrayList<Actor> getActors()
    {
        ArrayList<Actor> actors = new ArrayList<Actor>();
        int[] dirs =
            { Location.AHEAD, Location.HALF_LEFT, Location.HALF_RIGHT };
        for (Location loc : getLocationsInDirections(dirs))
        {
            Actor a = getGrid().get(loc);
            if (a != null)
                actors.add(a);
        }
        return actors;
    }
```

3.回答：为了找到给定方向数组里面所给的对应方向上是否存在着可以吃的actors，如果存在就加入到actor实体集中，返回信息表示哪个方向上actors是有效的可以被吃掉的。

` ` ` `CrabCritter中的getLocationsInDirections()函数
` ` ` ` ` ` // @file:GridWorldCode\projects\critters\CrabCritter.java
` ` ` ` ` ` // @line: 101-114
```
    public ArrayList<Location> getLocationsInDirections(int[] directions)
    {
        ArrayList<Location> locs = new ArrayList<Location>();
        Grid gr = getGrid();
        Location loc = getLocation();
        for (int d : directions)
        {
            Location neighborLoc = loc.getAdjacentLocation(getDirection() + d);
            if (gr.isValid(neighborLoc))
                locs.add(neighborLoc);
        }
        return locs;
    }
```
4.回答：(4,3)(4,4)(4,5)三个位置。

` ` ` `CrabCritter中的getActor()函数
` ` ` ` ` ` // @file:GridWorldCode\projects\critters\CrabCritter.java
` ` ` ` ` ` // @line: 44-57
```
    public ArrayList<Actor> getActors()
    {
        ArrayList<Actor> actors = new ArrayList<Actor>();
        int[] dirs =
            { Location.AHEAD, Location.HALF_LEFT, Location.HALF_RIGHT };
        for (Location loc : getLocationsInDirections(dirs))
        {
            Actor a = getGrid().get(loc);
            if (a != null)
                actors.add(a);
        }
        return actors;
    }
```
5.回答：  

相似性：  
1. 二者均不可以在移动中改变方向
2. 可以随机选择自己移动的位置

差异性：  

1. CrabCritter只可以左右移动，并且在不能移动时候可以改变运动方向
2. Critter可以向八个方向移动


` ` ` `CrabCritter中的makeMove()函数与Critter的makeMove()函数
` ` ` ` ` ` // @file:GridWorldCode\projects\critters\CrabCritter.java
和GridWorldCode\framework\info\gridworld\actor\Critter.java
` ` ` ` ` ` // @line: 125-131（Critter）和77-91（CribCitter）
```
    //Critter的makeMove()函数
    public void makeMove(Location loc)
    {
        if (loc == null)
            removeSelfFromGrid();
        else
            moveTo(loc);
    }
    //CrabCritter的makemMove()函数
    public void makeMove(Location loc)
    {
        if (loc.equals(getLocation()))
        {
            double r = Math.random();
            int angle;
            if (r < 0.5)
                angle = Location.LEFT;
            else
                angle = Location.RIGHT;
            setDirection(getDirection() + angle);
        }
        else
            super.makeMove(loc);
    }
```
6.回答：当传入的参数loc == CrabCritter此时所处的位置时候。

` ` ` `CrabCritter中的makeMove()函数
` ` ` ` ` ` // @file:GridWorldCode\projects\critters\CrabCritter.java
` ` ` ` ` ` // @line: 77-91（CribCitter）
```
    //CrabCritter的makemMove()函数
    public void makeMove(Location loc)
    {
        if (loc.equals(getLocation()))
        {
            double r = Math.random();
            int angle;
            if (r < 0.5)
                angle = Location.LEFT;
            else
                angle = Location.RIGHT;
            setDirection(getDirection() + angle);
        }
        else
            super.makeMove(loc);
    }
```
7.回答：CrabCritter类的processActor()函数从Critter继承而来，儿Critter中的processActor()函数定义了Critter不能吃掉rock与critter，所以一个CrabCritter对象也就不能吃掉另一个CrabCritter对象。

` ` ` `Critter中的processActors()函数
` ` ` ` ` ` // @file:GridWorldCode\framework\info\gridworld\actor\Critter.java
` ` ` ` ` ` // @line: 71-78
```
    public void processActors(ArrayList<Actor> actors)
    {
        for (Actor a : actors)
        {
            if (!(a instanceof Rock) && !(a instanceof Critter))
                a.removeSelfFromGrid();
        }
    }
```

