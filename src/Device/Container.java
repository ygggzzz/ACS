package Device;

import Item.Item;
import java.lang.Iterable;

public interface Container{
    boolean insertItem(Item item);
    Item removeItem(int x);
    int getMaxCapacity();
    boolean isFull();

}
