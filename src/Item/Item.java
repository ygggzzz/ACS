package Item;

import java.util.ArrayList;

public abstract class Item {
    private String ID;
    private int volume;
    private ArrayList<Item> formula=new ArrayList<Item>(); //合成公式

    public Item(String ID,int volume) {
        this.ID = ID;
        this.volume=volume;
    }

    public int getVolume() {
        return volume;
    }

    public String getID() {
        return ID;
    }

    public void setFormula(Item item)
    {
        formula.add(item);
    }

    public ArrayList<Item> getFormula() {
        return formula;
    }

    public void visitFormula()
    {
        System.out.print(this.ID+"= " );
        for(Item item:formula)
        {
            System.out.print(item.getID()+"+");
        }
        System.out.println("\n");
    }

}
