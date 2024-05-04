package Bus;

import Device.Device;
import ACS.*;
import Item.Item;
import Filter.Filter;

import java.util.Objects;

public class InputBus extends aBus implements Inputinterface{
    private Filter filter;
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
        this.device = device;
        this.device.addBus(this);
        return true;
    }

    @Override
    public String activate() {
        return this.getDevice().getID()+"Working";
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
