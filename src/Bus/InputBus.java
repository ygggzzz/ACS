package Bus;

import Device.Device;
import ACS.*;
import Item.Item;
import Filter.Filter;

public class InputBus extends aBus implements Inputinterface{

    @Override
    public Item inputItembyFilter() {
        return null;
    }

    @Override
    public boolean setFilter(Filter filter) {
        return false;
    }

    @Override
    public boolean bildToDevice(Device device) {
        return false;
    }

    @Override
    public String activate() {
        return null;
    }

    public InputBus() {
        super();
    }

    public InputBus(Device device, Filter filter, AutoCookingSystem ACS) {
        super(device, filter, ACS);
    }

    @Override
    public Device getDevice() {
        return super.getDevice();
    }

    @Override
    public void setDevice(Device device) {
        super.setDevice(device);
    }

    @Override
    public Filter getFilter() {
        return super.getFilter();
    }

    @Override
    public AutoCookingSystem getACS() {
        return super.getACS();
    }

    @Override
    public void setACS(AutoCookingSystem ACS) {
        super.setACS(ACS);
    }
}
