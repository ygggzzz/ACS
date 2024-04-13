package Bus;

import Device.Device;
import Filter.Filter;
import Item.Item;


public interface Inputinterface extends Interface{
    Item inputItembyFilter();
    boolean setFilter(Filter filter);


    boolean bildToDevice(Device device);
}
