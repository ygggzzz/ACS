package Bus;

import ACS.*;
import Device.Device;
import Filter.Filter;
import Item.Item;

public abstract class aBus {
    protected Device device;
    //private Filter filter;
    private AutoCookingSystem ACS;


    public aBus() {
    }

    public aBus(Device device, AutoCookingSystem ACS) {
        this.device = device;
        //this.filter = filter;
        this.ACS = ACS;
    }

    public Device getDevice() {
        return device;
    }


//    public Filter getFilter() {
//        return filter;
//    }

//    public void setFilter(Filter filter) {
//        this.filter = filter;
//    }

    public AutoCookingSystem getACS() {
        return ACS;
    }

    public void setACS(AutoCookingSystem ACS) {
        this.ACS = ACS;
    }

    public boolean addItem(Item item)
    {
        device.insertItem(item);
        return true;
    }

    public Item getItem(int x)
    {
        return device.getStoreFoodList().get(x);
    }


}
