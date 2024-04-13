package Bus;
import Device.Device;
import Filter.Filter;
import Item.Item;

public interface OutputInterface extends Interface{
    boolean bildToDevice(Device device);

    void outputItembyFilter(Item item);

    boolean setFilter(Filter filter);

}
