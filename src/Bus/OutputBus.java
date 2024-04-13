package Bus;

import Filter.Filter;
import Device.Device;
import ACS.*;
import Item.Item;

public class OutputBus extends aBus implements OutputInterface{
    @Override
    public boolean bildToDevice(Device device) {
        return false;
    }

    @Override
    public String activate() {
        return null;
    }

    @Override
    public void outputItembyFilter(Item item) {

    }

    @Override
    public boolean setFilter(Filter filter) {
        return false;
    }

    public OutputBus() {
        super();
    }

    public OutputBus(Device device, Filter filter, AutoCookingSystem ACS) {
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
