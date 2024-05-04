package Bus;

import Filter.Filter;
import Device.Device;
import ACS.*;
import Item.Item;

import java.util.Objects;

public class OutputBus extends aBus implements OutputInterface{
    private Filter filter;
    public boolean bildToDevice(Device device) {
        this.device = device;
        this.device.addBus(this);
        return true;
    }

    public String activate() {
        return this.getDevice().getID()+"Working\n";
    }

    @Override
    public void outputItembyFilter(Item item) {
        if(canTransport(item)) {
            getDevice().insertItem(item);
        }
    }

    @Override
    public boolean setFilter(Filter filter) {
        this.filter=filter;
        return true;
    }

    public OutputBus() {
        super();
    }

    public OutputBus(Device device, Filter filter,AutoCookingSystem ACS) {
        super(device ,ACS);
        this.filter=filter;
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
        if(filter==null)
        {
            return true;
        }
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
