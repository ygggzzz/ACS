package Bus;

import Device.Device;
import Filter.Filter;
import Item.Item;


public interface Inputinterface extends Interface{
    Item inputItembyFilter(int x);
    boolean setFilter(Filter filter);


    boolean bildToDevice(Device device);

    String activate();
}
