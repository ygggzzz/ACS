package Device;

import ACS.AutoCookingSystem;
import Item.Item;
import RecipeProvider.*;

import java.util.ArrayList;
import java.util.Objects;

public class CookingDevice extends Device{

    private Recipe recipe;
    private AutoCookingSystem ACS;

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
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

    public CookingDevice(String ID, int capacity) {
        super(ID, capacity);
    }

    public Item cook()
    {
        String Source_ID; //来源设备名
        String Target_ID; //目标设备名
        Target_ID=getID();
        for(Item item:recipe.getCanMakeList()) //这边认为每个食谱只能做一样菜
        {
            for(Item m_item: item.getFormula()) //在网络中寻找需要的食材，并在对应设备中删去
            {
                for(RecipeProvider provider:ACS.getProvidersList())
                {
                    for(int i=0;i<provider.getC_device().getStoreFoodList().size();i++)
                    {
                        Item mm_item=provider.getC_device().getStoreFoodList().get(i);
                        if(Objects.equals(m_item.getID(), mm_item.getID()))
                        {
                            Source_ID=provider.getC_device().getID();
                            ACS.sendLogisticsCommand(Source_ID,Target_ID,m_item);
                            provider.removeItem(i);
                        }
                    }
                }
            }
        }
        return recipe.getCanMakeList().getFirst();
    }


}
