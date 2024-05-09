# 自动烹饪系统ACS

## 大致流程（不含前面初始化的过程）

先在ACS系统里添加请求

```java
public void addRequest(Item item) //添加请求
{
    requestList.add(item);
}
```



然后系统得到请求，先在ACS里的食谱供应器的列表里遍历，找到能生产这个食物的食谱，如果没找到，则到烹饪设备里找，都没找到删除这个请求，返回一个空值，认为没有生产食物

```java
public Item getRequest()
{
    Item item=requestList.getFirst();
    for(RecipeProvider provider:getProvidersList()) {
            Recipe m_item=provider.getRecipe();
            if(Objects.equals(item.getID(), m_item.getID())){
                Item iitem=provider.getC_device().cook();
                if(iitem!=null)
                {
                    requestList.removeFirst();
                    return item;
                }

        }
    }

    for(Device device:deviceList)
    {
        if(device instanceof CookingDevice)
        {
            for(Recipe recipe: ((CookingDevice) device).getRecipes())
            {
                if(Objects.equals(item.getID(), recipe.getID()))
                {
                    Item iitem=((CookingDevice) device).cook();
                    if(iitem!=null)
                    {
                        requestList.removeFirst();
                        return item;
                    }
                }
            }
        }
    }
    System.out.println(requestList.removeFirst().getID()+"can not cook ");
    return null;
}
```



若找到了一份食谱，则调用这个设备内的cook函数，也就是先扫描这个设备所存储的食物，有在所需食物的食谱里的食物，就拿出，没有就到内存里拿

```java
public Item cook() {
    String Source_ID=null; //来源设备名
    String Target_ID; //目标设备名
    Target_ID = getID();
    //boolean flag = false;
    for (Recipe recipe : recipes) //这里认为每个食谱只能做一样菜
    {
        for (Item item : recipe.getFormula()) //在网络中寻找需要的食材，并在对应设备中删去
        {
            Item test=fetchFood(Target_ID,item);
            if(test==null)
            {
                Source_ID = ACS.foundFood(Target_ID, item);
                if(Source_ID==null)
                {
                    System.out.println(getID()+ " lack food");
                    return null;
                }
                Item itest=fetchFood(Target_ID,item);
            }
        }
    }

    for (Recipe recipe : recipes)
    {
        for (Item item : recipe.getFormula())
        {
            for(int i=0;i<getStoreFoodList().size();i++)
            {
                Item m_item=getStoreFoodList().get(i);
                if(Objects.equals(item.getID(), m_item.getID()))
                {
                    removeItem(i);
                    break;
                }
            }
        }
    }
    addFood(recipes.getFirst());
    return recipes.getFirst();
}
```



在内存里通过扫描每一个输入总线和存储总线来确认整个系统里是否有所需的食材，如果没有直接返回一个空值，删除这个请求输出缺少食物，找到了就由烹饪设备的存储总线或输出总线送到烹饪设备中，然后继续这个流程找到下一个食材直到找完或者缺少为止

```java
public Item fetchFood(String Target_ID,Item item)
{//搜索内存找食物
    for(Item m_item:getStoreFoodList())
    {
        if(Objects.equals(m_item.getID(), item.getID()))
        {
            for(aBus bus2:getBusList())
            {
                if(bus2 instanceof OutputBus)
                {
                    ((OutputBus) bus2).outputItembyFilter(item);
                }

                if(bus2 instanceof StorageBus)
                {
                    ((StorageBus) bus2).outputItembyFilter(item);
                }
            }
            return item;
        }
    }

    for (aBus bus : ACS.getBusList())
    {
        if (bus instanceof StorageBus)
        {
            for (int i = 0; i < bus.getDevice().getStoreFoodList().size(); i++)
            {
                Item mm_item = bus.getDevice().getStoreFoodList().get(i);
                if (Objects.equals(item.getID(), mm_item.getID()))
                {
                    String Source_ID = bus.getDevice().getID();
                    Item Iitem = ((StorageBus) bus).inputItembyFilter(i);
                    if (Iitem != null)
                    {
                        ACS.sendLogisticsCommand(Source_ID, Target_ID, item);
                        String s = ((StorageBus) bus).activate();
                        System.out.println(s);
                        for(aBus bus2:getBusList())
                        {
                            if(bus2 instanceof OutputBus)
                            {
                                System.out.println(((OutputBus) bus2).activate());
                                ((OutputBus) bus2).outputItembyFilter(item);
                            }
                        }
                        return item;
                    }
                }
            }
        }

    }
    return null;
}
```



## 两个测试
![1](https://github.com/ygggzzz/ACS/assets/153978396/fd95bbe0-9fd0-41e1-9898-8cfaaf3882e4)

![2](https://github.com/ygggzzz/ACS/assets/153978396/041f1f70-288a-4938-be07-d4bd524ed496)


```java
public static void main(String[] args) {
        //OI功能测试
        staple cooked_rice=new staple("cooked_rice",0);
        staple rice=new staple("rice",0);
        freshWater freshWater=new freshWater("freshWater",0);

        Recipe cook_rice=new Recipe("cooked_rice");
        cook_rice.setFormula(rice);
        cook_rice.setFormula(freshWater);

        AutoCookingSystem ACS=new AutoCookingSystem();

        Device shelf=new Device("shelf1",1000);
        Device sink=new Device("sink2",1000);
        CookingDevice cooker= new CookingDevice("cooker3",1000);

        shelf.insertItem(rice);
        sink.insertItem(freshWater);

        Filter filter1=new Filter();
        filter1=null;
        Filter filter2=new Filter();
        filter2.addFilter(freshWater);
        Filter filter3=new Filter();
        filter3.addFilter(freshWater);
        filter3.addFilter(rice);

        StorageBus bus1=new StorageBus(shelf,filter1,ACS);
        StorageBus bus2=new StorageBus(sink,filter2,ACS);
        OutputBus bus3=new OutputBus(cooker,filter3,ACS);

        cooker.setACS(ACS);

        ACS.addBus(bus1);
        ACS.addBus(bus2);
        ACS.addBus(bus3);
        ACS.addDevice(shelf);
        ACS.addDevice(sink);
        ACS.addDevice(cooker);

        cooker.addBus(bus3);

        cooker.addRecipe(cook_rice);

//        ACS.visitBus(0);
//        ACS.visitBus(1);
//        ACS.visitBus(2);
        ACS.recallFood();
//        ACS.visitNetFood();
        System.out.println();
//        ACS.visitRecipe();

        ACS.addRequest(cooked_rice);

        while(!ACS.getRequestList().isEmpty())
        {
            Item item=ACS.getRequest();
            System.out.println(item.getID()+" is complete");
        }

        System.out.println();
        ACS.recallFood();
        ACS.visitNetFood();
        System.out.println();

        //cooker.visitItem();
    }
```



```java
public static void main(String[] args) {
        //网络简单烹饪功能测试
        Device freeze=new Device("freeze2",100);
        Device sink=new Device("sink2",100);
        CookingDevice cooler=new CookingDevice("cooler3",1);
        CookingDevice shaper=new CookingDevice("shaper414",5);
        Device out=new Device("out1",1000);

        milk cheese=new milk("cheese",0);
        freshWater freshWater=new freshWater("freshWater",0);
        freshWater ice=new freshWater("ice",0);
        flavour DingZhen=new flavour("ZSXB",0);

        Recipe Ice=new Recipe("ice");
        Ice.setFormula(freshWater);
        Recipe ZSXB=new Recipe("ZSXB");
        ZSXB.setFormula(ice);
        ZSXB.setFormula(ice);
        ZSXB.setFormula(cheese);

        Filter filter1=new Filter();
        filter1.addFilter(cheese);
        Filter filter2=new Filter();
        filter2.addFilter(freshWater);
        Filter filter3=new Filter();
        filter3.addFilter(freshWater);
        filter3.addFilter(ice);
        Filter filter4=new Filter();
        filter4=null;
        Filter filter5=new Filter();
        filter5.addFilter(DingZhen);

        AutoCookingSystem ACS=new AutoCookingSystem();

        freeze.insertItem(cheese);
        freeze.insertItem(cheese);
        sink.insertItem(freshWater);
        sink.insertItem(freshWater);

        RecipeProvider provider1=new RecipeProvider();
        provider1.bildToDevice(cooler);
        provider1.setACS(ACS);
        provider1.setRecipe(Ice);
        RecipeProvider provider2=new RecipeProvider();
        provider2.bildToDevice(shaper);
        provider2.setACS(ACS);
        provider2.setRecipe(ZSXB);

        ACS.addProvider(provider1);
        ACS.addProvider(provider2);

        StorageBus bus1=new StorageBus(freeze,filter1,ACS);
        StorageBus bus2=new StorageBus(sink,filter2,ACS);
        StorageBus bus3=new StorageBus(cooler,filter3,ACS);
        StorageBus bus4=new StorageBus(shaper,filter4,ACS);
        StorageBus bus5=new StorageBus(out,filter5,ACS);

        cooler.addBus(bus3);
        shaper.addBus(bus4);

        ACS.addBus(bus1);
        ACS.addBus(bus2);
        ACS.addBus(bus3);
        ACS.addBus(bus4);
        ACS.addBus(bus5);
        ACS.addDevice(freeze);
        ACS.addDevice(sink);
        ACS.addDevice(cooler);
        ACS.addDevice(shaper);
        ACS.addDevice(out);

        cooler.addRecipe(Ice);
        shaper.addRecipe(ZSXB);
        cooler.setACS(ACS);
        shaper.setACS(ACS);

        ACS.visitBus(0);
        ACS.visitBus(1);
        ACS.visitBus(2);
        ACS.visitBus(3);
        ACS.visitBus(4);
        ACS.recallFood();
        ACS.visitNetFood();
        System.out.println();
        ACS.visitRecipe();

        ACS.addRequest(Ice);
        ACS.addRequest(Ice);
        ACS.addRequest(ZSXB);

        while(!ACS.getRequestList().isEmpty())
        {
            Item item = ACS.getRequest();
            if (item != null)
            {
                System.out.println(item.getID() + " is complete");
            }
        }

//        freeze.visitItem();
//        sink.visitItem();
//        cooler.visitItem();
//        shaper.visitItem();
//        out.visitItem();

        System.out.println();
        ACS.recallFood();
        ACS.visitNetFood();
        System.out.println();

    }
```



## 输出结果

![](C:\Users\爷傲奈我何\Desktop\1.jpg)



![](C:\Users\爷傲奈我何\Desktop\2.jpg)



## 一些函数



```java
public void recallFood() //遍历每一个存储总线，将其所有的物品加入到网络中
```

```java
public void visitNetFood() //显示网络中的食物
```

```java
public void visitRecipe() //查看系统总每个食谱
```

```java
public boolean sendLogisticsCommand(String Source_ID,String Target_ID,Item item)
{
    String Commend="LC-FRO_"+"{"+Source_ID+"}"+"-TO_"+"{"+Target_ID+"}"+"-SEND_"+"{"+item.getID()+"}";
    ILS Ils=new ILS();
    Ils.itemTransport(Commend);
    return true;
}
```

```java
public void visitItem() //遍历容器输出物品ID
{
      for(Item item:storeFoodList)
      {
          System.out.println(item.getID());
      }
}
```
