package ACS;

import Bus.*;
import RecipeProvider.*;
import java.util.ArrayList;
import java.util.Objects;

import Item.Item;


public class AutoCookingSystem {
    private ArrayList<aBus> BusList=new ArrayList<aBus>(); //这边需要依据优先级来排序，需要aBus继承自定义排序
    //供应器--内存 存储总线--寄存器 输入输出总线--输入输出设备
    private ArrayList<RecipeProvider> ProvidersList=new ArrayList<RecipeProvider>();
    private ArrayList<Item> FoodList=new ArrayList<Item>();
    private ArrayList<Item> requestList=new ArrayList<Item>();
    private ILS Ils;

    public void addRequest(Item item) //添加请求
    {
        requestList.add(item);
    }

    public Item getRequest()
    {
        Item item=requestList.getFirst();
        for(RecipeProvider provider:getProvidersList()) {
            for(Item m_item:provider.getRecipe().getCanMakeList()) {
                if(Objects.equals(item.getID(), m_item.getID())){
                    provider.getC_device().cook();
                }
            }
        }

        requestList.removeFirst();
        return item;
    }


    public String foundFood(String Target_ID,Item m_item) //访问内存找食材去烹饪 寻找连接输入总线或存储总线的设备
    {
        for(aBus bus:getBusList())
        {
            if(bus instanceof InputBus) {
                for (int i = 0; i < bus.getDevice().getStoreFoodList().size(); i++) {
                    Item mm_item = bus.getDevice().getStoreFoodList().get(i);
                    if (Objects.equals(m_item.getID(), mm_item.getID())) {
                        String Source_ID = bus.getDevice().getID();
                        sendLogisticsCommand(Source_ID, Target_ID, m_item);
                        Item Iitem=((InputBus) bus).inputItembyFilter(i);
                        if(Iitem !=null) {
                            return Source_ID;
                        }
                    }
                }
            }
            if(bus instanceof StorageBus)
            {
                for (int i = 0; i < bus.getDevice().getStoreFoodList().size(); i++) {
                    Item mm_item = bus.getDevice().getStoreFoodList().get(i);
                    if (Objects.equals(m_item.getID(), mm_item.getID())) {
                        String Source_ID = bus.getDevice().getID();
                        sendLogisticsCommand(Source_ID, Target_ID, m_item);
                        Item Iitem=((StorageBus) bus).inputItembyFilter(i);
                        if(Iitem !=null) {
                            return Source_ID;
                        }
                    }
                }
            }
        }
        return null;
    }

    public ArrayList<aBus> getBusList() {
        return BusList;
    }

    public ArrayList<RecipeProvider> getProvidersList() {
        return ProvidersList;
    }

    public boolean removeFood(RecipeProvider provider,Item item)
    {
          for(int i=0;i<provider.getC_device().getStoreFoodList().size();i++)
          {
              Item m_item=provider.getC_device().getStoreFoodList().get(i);
              if(Objects.equals(m_item.getID(), item.getID()))
              {
                  provider.getC_device().getStoreFoodList().remove(i);
                  return true;
              }
          }
          return false;
    }

    public void recallFood() //遍历每一个设备，将其所有的物品加入到网络中
    {
        for(RecipeProvider provider:ProvidersList)
        {
            for(Item item:provider.getC_device().getStoreFoodList())
            {
                FoodList.add(item);
            }
        }
    }

    public void visitNetFood() //显示网络中的食物
    {
        for(Item item:FoodList)
        {
            System.out.print(item.getID()+" ");
        }
    }


    public void addBus(aBus bus)
    {
        BusList.add(bus);
    }

    public void addProvider(RecipeProvider recipeProvider)
    {
        ProvidersList.add(recipeProvider);
    }

    public void visitBus(int i) //访问输出线类型及绑定设备ID
    {
        aBus buss=BusList.get(i);
        if(buss instanceof InputBus)
        {
            System.out.print("Input ");
        }
        else if(buss instanceof OutputBus)
        {
            System.out.print("Output ");
        }
        else if(buss instanceof StorageBus)
        {
            System.out.print("Stragae ");
        }
        System.out.println(buss.getDevice().getID());
    }

    public void visitRecipe()
    {
        for(RecipeProvider provider:ProvidersList)
        {
            provider.getRecipe().visitRecipe();
        }
    }

    public void visitCookingDevice() //显示所有食物供给器
    {
        for(RecipeProvider provider:ProvidersList)
        {
            System.out.print(provider.getC_device().getID());
        }
    }

    public boolean sendLogisticsCommand(String Source_ID,String Target_ID,Item item)
    {
        String Commend="LC-FRO_"+Source_ID+"-TO_"+Target_ID+"-SEND_"+item.getID();
        Ils.itemTransport(Commend);
        return true;
    }

    public class ILS {
        public void itemTransport (String command) {
//do something
            System.out.println(command);
        }
    }
}
