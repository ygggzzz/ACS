package Device;

import ACS.AutoCookingSystem;
import Bus.aBus;
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
                Source_ID = ACS.foundFood(Target_ID, item);
            }
        }
        if (Source_ID != null) {
            return recipes.getFirst();
        }
        System.out.println("lack food");
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
