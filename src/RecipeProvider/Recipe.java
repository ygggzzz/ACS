package RecipeProvider;

import Device.CookingDevice;
import Item.Item;
import java.util.ArrayList;

public class Recipe {

    //private CookingDevice Cooker;
    ArrayList<Item> canMakeList=new ArrayList<Item>();


    public void setCanMakeList(Item item)
    {
        canMakeList.add(item);
    }

    public void setFormulatoFood(Item fstItem,Item secItem) //将secItem加入到fstItem的配方中
    {
        fstItem.setFormula(secItem);
    }

    public void visitRecipe()
    {
        for(Item item:canMakeList)
        {
            item.visitFormula();
        }
    }

    public ArrayList<Item> getCanMakeList() {
        return canMakeList;
    }
}
