package Item;

import java.util.ArrayList;

public abstract class Item {
    private String ID;
    private int volume;

    public Item(String ID) {
        this.ID = ID;
    }
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




}
