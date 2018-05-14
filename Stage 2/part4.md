set10
1.回答：isValid()方法在Grid接口中被定义；在UnBoundedGrid类与BoundedGrid类中被实现。


` ` ` `isValid()的不同实现：
` ` ` ` ` ` // @file: 
GridWorldCode\framework\info\gridworld\grid\BoundedGrid.java  
GridWorldCode\framework\info\gridworld\grid\BoundedGrid.java
` ` ` ` ` ` // @line: 60-64(BoundedGrid) 54-56(UnboundedGrid)
```
    (BoundedGrid)
    public boolean isValid(Location loc)
    {
        return 0 <= loc.getRow() && loc.getRow() < getNumRows()
                && 0 <= loc.getCol() && loc.getCol() < getNumCols();
    }
    (UnboundedGrid)
    public boolean isValid(Location loc)
    {
        return true;
    }
```
2.回答：通过查询发现，直接调用isValid()函数的的方法只有getValidAdjacentLocations()函数；但是却有两个函数getEmptyAdjacentLocations()和getOccupiedAdjacentLocations()调用了函数getValidAdjacentLocations()间接调用isValid()函数。  

` ` ` `getValidAdjacentLocations()的实现以及被调用：
` ` ` ` ` ` // @file:
GridWorldCode\framework\info\gridworld\grid\AbstractGrid.java 

` ` ` ` ` ` // @line: 36-49 54 65
```
    //getValidAdjacentLocations()函数
    public ArrayList<Location> getValidAdjacentLocations(Location loc)
    {
        ArrayList<Location> locs = new ArrayList<Location>();

        int d = Location.NORTH;
        for (int i = 0; i < Location.FULL_CIRCLE / Location.HALF_RIGHT; i++)
        {
            Location neighborLoc = loc.getAdjacentLocation(d);
            if (isValid(neighborLoc))
                locs.add(neighborLoc);
            d = d + Location.HALF_RIGHT;
        }
        return locs;
    }
    //getEmptyAdjacentLocations()函数中的调用
    for (Location neighborLoc : getValidAdjacentLocations(loc))
    //getOccupiedAdjacentLocations()函数中的调用
    for (Location neighborLoc : getValidAdjacentLocations(loc))
```
3.回答：getNeighbors()函数调用了接口Grid()中的get()函数以及getOccupiedAdjacentLocations()函数；get()函数在BoundedGrid类与UnboundedGrid类中被实现；getOccupiedAdjacentLocations()函数在Abstrct()类中被实现。

` ` ` `getValidAdjacentLocations()的实现以及被调用：
` ` ` ` ` ` // @file: 
GridWorldCode\framework\info\gridworld\grid\AbstractGrid.java  
GridWorldCode\framework\info\gridworld\grid\BoundedGrid.java  
GridWorldCode\framework\info\gridworld\grid\UnboundedGrid.java
` ` ` ` ` ` // @line: 62-71 85-91 66-71
```
    //getOccupiedAdjacentLocation()函数
    public ArrayList<Location> getOccupiedAdjacentLocations(Location loc)
    {
        ArrayList<Location> locs = new ArrayList<Location>();
        for (Location neighborLoc : getValidAdjacentLocations(loc))
        {
            if (get(neighborLoc) != null)
                locs.add(neighborLoc);
        }
        return locs;
    }
    //BoundedGrid类中get()函数
    public E get(Location loc)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        return (E) occupantArray[loc.getRow()][loc.getCol()]; // unavoidable warning
    }
    //UnboundedGrid类中get()函数
    public E get(Location loc)
    {
        if (loc == null)
            throw new NullPointerException("loc == null");
        return occupantMap.get(loc);
    }
```
4.回答：因为getEmptyAdjacentLocations()函数的功能是通过使用get()函数找到一个临近的空白位置；而get()函数则是可以返回给定某个位置上是否有物体的存在，如果不存在返回null，只能一次对一个物体进行判断，而不是数个。

` ` ` `getEmptyAdjacentLocations()函数：
` ` ` ` ` ` // @file: 
GridWorldCode\framework\info\gridworld\grid\AbstractGrid.java
` ` ` ` ` ` // @line: 51 - 60
```
    public ArrayList<Location> getEmptyAdjacentLocations(Location loc)
    {
        ArrayList<Location> locs = new ArrayList<Location>();
        for (Location neighborLoc : getValidAdjacentLocations(loc))
        {
            if (get(neighborLoc) == null)
                locs.add(neighborLoc);
        }
        return locs;
    }
```
5.回答：如果Location.HALF_RIGHT被替换为Location.RIGHT，那么邻近的有效位置将从8个减少到四个，东北、东南、西北、西南这四个可供选择的位置将无法被选中。

` ` ` `getValidAdjacentLocations()函数：
` ` ` ` ` ` // @file: 
GridWorldCode\framework\info\gridworld\grid\AbstractGrid.java
` ` ` ` ` ` // @line: 36-49
```
    public ArrayList<Location> getValidAdjacentLocations(Location loc)
    {
        ArrayList<Location> locs = new ArrayList<Location>();

        int d = Location.NORTH;
        for (int i = 0; i < Location.FULL_CIRCLE / Location.HALF_RIGHT; i++)
        {
            Location neighborLoc = loc.getAdjacentLocation(d);
            if (isValid(neighborLoc))
                locs.add(neighborLoc);
            d = d + Location.HALF_RIGHT;
        }
        return locs;
    }
```

set11
1.回答：BoundedGrid类的构造函数当判断到row <= 0 或者 col <= 0时候会抛出一个错误。

` ` ` `BoundedGrid的构造函数：
` ` ` ` ` ` // @file: 
GridWorldCode\framework\info\gridworld\grid\BoundedGrid.java
` ` ` ` ` ` // @line: 39-46
```
    public BoundedGrid(int rows, int cols)
    {
        if (rows <= 0)
            throw new IllegalArgumentException("rows <= 0");
        if (cols <= 0)
            throw new IllegalArgumentException("cols <= 0");
        occupantArray = new Object[rows][cols];
    }
```
2.回答：getNumCols()函数会返回 occupantArray[0].length，而这个返回值也就是列的个数；由于BoundedGrid是个矩阵，所以第一行的列个数也就是整体矩阵的列个数。

` ` ` `getNumCols()函数：
` ` ` ` ` ` // @file: 
GridWorldCode\framework\info\gridworld\grid\BoundedGrid.java
` ` ` ` ` ` // @line: 53-55
```
    public int getNumCols()
    {
        return occupantArray[0].length;
    }
```
3.回答：BoundedGrid中任意一个点的横坐标要小于列数大于等于0，任意一点的纵坐标要小于行数，大于等于0；
点是否有效，从构造函数的限定中就可以看出，构造函数已经限定了Grid的大小，所以就一定要保证点不能越界。

` ` ` `BoundedGrid的构造函数：
` ` ` ` ` ` // @file: 
GridWorldCode\framework\info\gridworld\grid\BoundedGrid.java
` ` ` ` ` ` // @line: 39-46
```
    public BoundedGrid(int rows, int cols)
    {
        if (rows <= 0)
            throw new IllegalArgumentException("rows <= 0");
        if (cols <= 0)
            throw new IllegalArgumentException("cols <= 0");
        occupantArray = new Object[rows][cols];
    }
```
4.回答：从代码上可以看出，这个函数返回一个ArrayList集合，这个集合中元素是已经被actors占有的位置；时间复杂度O（r*c）；  

` ` ` `getOccupiedLocations()函数：
` ` ` ` ` ` // @file: 
GridWorldCode\framework\info\gridworld\grid\BoundedGrid.java
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
                if (get(loc) != null)
                    theLocations.add(loc);
            }
        }

        return theLocations;
    }
```
5.回答：get()函数返回一个E类型的对象;需要这个对象的Location；时间复杂度O(1);

` ` ` `get()函数：
` ` ` ` ` ` // @file: 
GridWorldCode\framework\info\gridworld\grid\BoundedGrid.java
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
6.回答：当放置的位置无效或者放置的实体为null时候put()函数会抛出错误；时间复杂度O(1);
` ` ` `put()函数：
` ` ` ` ` ` // @file: 
GridWorldCode\framework\info\gridworld\grid\BoundedGrid.java
` ` ` ` ` ` // @line: 93-105
```
    public E put(Location loc, E obj)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        if (obj == null)
            throw new NullPointerException("obj == null");

        // Add the object to the grid.
        E oldOccupant = get(loc);
        occupantArray[loc.getRow()][loc.getCol()] = obj;
        return oldOccupant;
    }
```
7.回答：remove()函数返回一个E类型的对象；当移除的是一个无效的位置时候，会抛出一个异常错误；时间复杂度为O(1);
` ` ` `reMove()函数：
` ` ` ` ` ` // @file: 
GridWorldCode\framework\info\gridworld\grid\BoundedGrid.java
` ` ` ` ` ` // @line: 106-117
```
    public E remove(Location loc)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        
        // Remove the object from the grid.
        E r = get(loc);
        occupantArray[loc.getRow()][loc.getCol()] = null;
        return r;
    }
```
8.回答：我觉得这个实现是有效的；当删除，添加，访问某个位置上的某个对象时候，时间复杂度都是O(1)级别的，这就减少了运算量，减少了内存消耗；同时查询时候是需要遍历的，此时时间复杂度为O(r*c)，n^2级别的时间复杂度是可以接受的;所以我觉得实现方法可以接受。
` ` ` `基于4、5、6、7中时间复杂度总结：
` ` ` ` ` ` // @file: GridWorldCode\framework\info\gridworld\grid\BoundedGrid.java

` ` ` ` ` ` // @line: 66 106 93 85
```
    //O(r*c)
    public ArrayList<Location> getOccupiedLocations()
    //O(1)
    public E remove(Location loc)
    //O(1)
    public E put(Location loc, E obj)
    //O(1)
    public E get(Location loc)
```

set12
1.回答：
（1）Location类中必须实现hashCode()函数与equals()函数，由于Location()使用Compare接口中的方法，所以这个CompareTo()函数也需要实现；
（2）如果要使用TreeMap，那么必须包含Map关键字；（3）Location类满足这些需求；

` ` ` `equals()函数与hashCode函数()：
` ` ` ` ` ` // @file: GridWorldCode\framework\info\gridworld\grid\Location.java

` ` ` ` ` ` // @line: 218-221 , 205-212 , 234-246
```
    public int hashCode()
    {
        return getRow() * 3737 + getCol();
    }
    public boolean equals(Object other)
    {
        if (!(other instanceof Location))
            return false;

        Location otherLoc = (Location) other;
        return getRow() == otherLoc.getRow() && getCol() == otherLoc.getCol();
    }
    public int compareTo(Object other)
    {
        Location otherLoc = (Location) other;
        if (getRow() < otherLoc.getRow())
            return -1;
        if (getRow() > otherLoc.getRow())
            return 1;
        if (getCol() < otherLoc.getCol())
            return -1;
        if (getCol() > otherLoc.getCol())
            return 1;
        return 0;
    }
```
2.回答：
（1）由于UnboundedGrid对象实例是无界的，所以所有的位置均为有效位置，所以isValid()函数无法发挥作用判断一个位置是否出界；而get，put，remove函数首先需要判断位置是否有效，这在UnboundedGrid对象实例中是不必要的行为，所以这个几个方法也没有被加入到UnboudedGrid类中；
（2）而BoundedGrid对象实例就不一样了，这个对象实体是有界的，也就需要我们先去判断位置是否出界，所以在BoundedGrid类中，这几个方法仍然可以使用。


` ` ` UnboundedGrid类中isValid()函数：
` ` ` ` ` ` // @file: GridWorldCode\framework\info\gridworld\grid\UnboundedGrid.java

` ` ` ` ` ` // @line: 53 - 56
```
    public boolean isValid(Location loc)
    {
        return true;
    }
```

3.
3.回答：

（1）时间复杂度为 O（1）；  

（2）如果使用HashMap,时间复杂度将会变为 O（log n）；  

` ` ` `关于在HashMap与TreeMap中使用get，put，remove三个方法的时间复杂度分析：  

` ` ` ` ` `// @file: https://docs.oracle.com/javase/8/docs/api/java/util/TreeMap.html
https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html

` ` ` ` ` ` // @line: null   
		
**分析**:
    在本题中put,get,remove方法都实现需要先找到该实体（也即查询），然后再进行操作；使用TreeMap相当于基于二叉排序树对Grid进行构造，构造的依据是横纵坐标的大小，所以在查询时候也是基于横纵坐标的大小进行查找，在这里的查找过程也近似于二分查找，所以时间复杂度为O（log n）；而在使用HashMap时候，直接可以通过下标在O（1）级别的时间复杂度上直接找到该物体，而无需遍历。

4.回答：访问实例对象的方法发生变化，在HashMap方法中，直接通过下标访问对象实例，而在TreeMap中则需要通过树的遍历进行匹配，进而完成对实体对象的访问；

` ` ` `HashMap与TreeMap对比：    
` ` ` ` ` `// @file: https://docs.oracle.com/javase/8/docs/api/java/util/TreeMap.html
https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html

` ` ` ` ` ` // @line: null   


5.回答：可以被用于BoundedGrid；当一个Grid存满时候，如果只使用Array不用ArrayList的话，可以免除对实体对象坐标的存储，这样就可以减少一大部分的内存开销。

` ` ` `Array与ArrayList对比：
` ` ` ` ` `// @file:https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html
https://docs.oracle.com/javase/8/docs/api/java/lang/reflect/Array.html

` ` ` ` ` ` // @line: null   


set13
1.回答：  

（1）SparseBoundedGrid继承UnboundedGrid,所以SparseBoundedGrid实体对象就可以使用UnboundedGrid类中的函数完成相应的功能；  

（2）get(),put(),remove(),getOccupiedLocations()函数可以仍旧使用  

（3）  

1. getNeighbors  O(c) ` ` O(c) ` `  O(1) ` ` O(log n)  

2. getEmptyAdjacentLocations O(c) ` `  O(c) ` `  O(1)` `   O(log n)   

3. getOccupiedAdjacentLocations  O(c) ` `  O(c)` `   O(1)` `   O(log n)   

4. getOccupiedLocations  O(r + n) ` `  O(r + n) ` `  O(n) ` ` O(n)   

5. get  O(c) ` `  O(c) ` `  O(1) ` `  O(log n)   

6. put  O(c) ` `  O(c) ` `  O(1) ` `  O(log n)   

7. remove  O(c) ` `  O(c)` `  O(1) ` `  O(log n)   

` ` ` `HashMap与TreeMap对比：    
` ` ` ` ` `// @file: https://docs.oracle.com/javase/8/docs/api/java/util/TreeMap.html
https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html

` ` ` ` ` ` // @line: null   



2.回答：  

（1）get的时间复杂度O（1）  

（2）当被放置的实体在Grid内时候时间复杂度为O（1）；当需要扩展大小时候，时间复杂度为O（n*n）,n为新Grid维数的大小。

` ` ` `Array的使用：    
` ` ` ` ` `// @file: https://docs.oracle.com/javase/8/docs/api/java/lang/reflect/Array.html

` ` ` ` ` ` // @line: null   