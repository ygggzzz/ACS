package Bus;

import ACS.*;
import Device.Device;
import Filter.Filter;
public abstract class aBus {
    private Device device;
    private Filter filter;
    private AutoCookingSystem ACS;


    public aBus() {
    }

    public aBus(Device device, Filter filter, AutoCookingSystem ACS) {
        this.device = device;
        this.filter = filter;
        this.ACS = ACS;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public Filter getFilter() {
        return filter;
    }

//    public void setFilter(Filter filter) {
//        this.filter = filter;
//    }

    public AutoCookingSystem getACS() {
        return ACS;
    }

    public void setACS(AutoCookingSystem ACS) {
        this.ACS = ACS;
    }
}
