package Device;

import ACS.AutoCookingSystem;
import Bus.*;
import Item.Item;
import RecipeProvider.*;

import java.util.ArrayList;
import java.util.Objects;

public class CookingDevice extends Device{

    private AutoCookingSystem ACS;

    private ArrayList<Recipe> recipes=new ArrayList<Recipe>();

    public ArrayList<Recipe> getRecipes() {
        return recipes;
    }

    public void addRecipe(Recipe recipe) {
        recipes.add(recipe);
    }

    @Override
    public void setID(String ID) {
        super.setID(ID);
    }

    @Override
    public void setCapacity(int capacity) {
        super.setCapacity(capacity);
    }

    @Override
    public ArrayList<aBus> getBusList() {
        return super.getBusList();
    }

    @Override
    public void addBus(aBus bus) {
        super.addBus(bus);
    }

    @Override
    public void visitItem() {
        super.visitItem();
    }

    @Override
    public ArrayList<Item> getStoreFoodList() {
        return super.getStoreFoodList();
    }

    @Override
    public boolean insertItem(Item item) {
        return super.insertItem(item);
    }

    @Override
    public Item removeItem(int x) {
        return super.removeItem(x);
    }

    @Override
    public int getMaxCapacity() {
        return super.getMaxCapacity();
    }

    @Override
    public boolean isFull() {
        return super.isFull();
    }

    @Override
    public String getID() {
        return super.getID();
    }

    @Override
    public int getCapacity() {
        return super.getCapacity();
    }

    public void setACS(AutoCookingSystem ACS) {
        this.ACS = ACS;
    }

    @Override
    public void addFood(Item item) {
        super.addFood(item);
    }

    public CookingDevice(String ID, int capacity) {
        super(ID, capacity);
    }

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

//    public void sendItemToDevice(Item item,Device device)
//    {
//        for (int i = 0; i < getStoreFoodList().size(); i++)
//        {
//            Item m_item= getStoreFoodList().get(i);
//            if(Objects.equals(item.getID(), m_item.getID()))
//            {
//                device.insertItem(item);
//                getStoreFoodList().remove(i);
//                return ;
//            }
//        }
//    }
}
