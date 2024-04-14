package Filter;

import Item.Item;
import java.util.ArrayList;

public class Filter {
    private ArrayList<Item> FilterList=new ArrayList<Item>();
    public void addFilter(Item item)
    {
        FilterList.add(item);
    }

    public ArrayList<Item> getFilterList() {
        return FilterList;
    }
}
