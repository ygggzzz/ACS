package Bus;

import Device.Device;
import ACS.*;
import Item.Item;
import Filter.Filter;

public class InputBus extends aBus implements Inputinterface{
    private Filter filter;
    @Override
    public Item inputItembyFilter(int x) {
        return super.getDevice().removeItem(x);
    }

    @Override
    public boolean setFilter(Filter filter) {
        this.filter=filter;
        return true;
    }

    @Override
    public boolean bildToDevice(Device device) {
        return false;
    }

    @Override
    public String activate(String x) {
        return x;
    }

    public InputBus() {
        super();
    }

    public InputBus(Device device,AutoCookingSystem ACS) {
        super(device,ACS);
    }

    @Override
    public Device getDevice() {
        return super.getDevice();
    }

    @Override
    public void setDevice(Device device) {
        super.setDevice(device);
    }


    public Filter getFilter() {
        return this.filter;
    }

    @Override
    public AutoCookingSystem getACS() {
        return super.getACS();
    }

    @Override
    public void setACS(AutoCookingSystem ACS) {
        super.setACS(ACS);
    }

    public boolean canTransport(Item item)
    {
        for(Item m_item:filter.getFilterList())
        {
            if(item.getID()==m_item.getID())
            {
                return true;
            }
        }
        return false;
    }

}
