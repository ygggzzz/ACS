package RecipeProvider;

import Device.CookingDevice;
import Item.Item;
import java.util.ArrayList;

public class Recipe extends Item{

    //private CookingDevice Cooker;
    private ArrayList<Item> formula=new ArrayList<Item>(); //合成公式

    public Recipe(String ID) {
        super(ID);
    }

    public void setFormula(Item item)
    {
        formula.add(item);
    }

    public ArrayList<Item> getFormula() {
        return formula;
    }

    public void setCanMakeList(Item item)
    {
        formula.add(item);
    }

//    public void setFormulatoFood(Item fstItem,Item secItem) //将secItem加入到fstItem的配方中
//    {
//        setFormula(secItem);
//    }

    public void visitRecipe()
    {
        for(Item item:formula)
        {
            visitFormula();
        }
    }

    public void visitFormula()
    {
        System.out.print(this.getID() +"= " );
        for(Item item:formula)
        {
            System.out.print(item.getID()+"+");
        }
        System.out.println("\n");
    }

    public ArrayList<Item> getCanMakeList() {
        return formula;
    }
}
