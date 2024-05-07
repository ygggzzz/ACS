package Device;

import Item.Item;
import java.util.ArrayList;
import Bus.aBus;


public class Device implements Container{
    private String ID;
    private ArrayList<Item> storeFoodList=new ArrayList<Item>();
    private int Capacity;
    private ArrayList<aBus> busList=new ArrayList<aBus>();

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setCapacity(int capacity) {
        Capacity = capacity;
    }

    public void addBus(aBus bus) {
        this.busList.add(bus);
    }

    public Device(String ID, int capacity) {
        this.ID = ID;
        Capacity = capacity;
    }

    public void visitItem() //遍历容器输出ID
    {
          for(Item item:storeFoodList)
          {
              System.out.println(item.getID());
          }
    }

    public ArrayList<Item> getStoreFoodList() {
        return storeFoodList;
    }

    public void addFood(Item item)
    {
        getStoreFoodList().addLast(item);
    }

    @Override
    public boolean insertItem(Item item) {
        storeFoodList.add(item);
        return true;
    }

    public ArrayList<aBus> getBusList() {
        return busList;
    }

    @Override
    public Item removeItem(int x) {
        Item m_item=storeFoodList.get(x);
        storeFoodList.remove(x);
        return m_item;
    }

    @Override
    public int getMaxCapacity() {
        return Capacity;
    }

    @Override
    public boolean isFull() {
        int sum=0;
        for(Item m_item:storeFoodList)
        {
            sum+=m_item.getVolume();
        }
        if(sum==Capacity)
        {
            return true;
        }
        return false;
    }

    public String getID() {
        return ID;
    }

    public int getCapacity() {
        return Capacity;
    }
}
