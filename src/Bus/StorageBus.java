package Bus;

import Filter.Filter;
import Device.Device;
import ACS.*;
import Item.Item;

import java.util.Objects;

public class StorageBus extends aBus implements Inputinterface,OutputInterface{
    private Filter filter;
    private AutoCookingSystem ACS;
    @Override
    public Item inputItembyFilter(int x) { //不符合回溯
        if (canTransport(getItem(x))) {
            return super.getDevice().removeItem(x);
        }
        return null;
    }

    @Override
    public boolean setFilter(Filter filter) {
        this.filter=filter;
        return true;
    }

    @Override
    public boolean bildToDevice(Device device) {
        return true;
    }

    @Override
    public String activate(String x) {
        return x;
    }



    @Override
    public void outputItembyFilter(Item item) {
        if(canTransport(item)) {
            getDevice().insertItem(item);
        }
    }


    public StorageBus() {
        super();
    }

    public StorageBus(Device device,  AutoCookingSystem ACS) {
        super(device, ACS);
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
            if(Objects.equals(item.getID(), m_item.getID()))
            {
                return true;
            }
        }
        return false;
    }
}
