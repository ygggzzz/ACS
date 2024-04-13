package ACS;

import Bus.*;
import RecipeProvider.*;
import java.util.ArrayList;
import Item.Item;


public class AutoCookingSystem {
    private ArrayList<aBus> BusList=new ArrayList<aBus>();
    private ArrayList<RecipeProvider> ProvidersList=new ArrayList<RecipeProvider>();
    private ArrayList<Item> FoodList=new ArrayList<Item>();
    private ILS Ils;

    public void addRequest(Item item) //添加请求
    {

    }


    public ArrayList<RecipeProvider> getProvidersList() {
        return ProvidersList;
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
