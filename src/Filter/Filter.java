package Filter;

import Item.Item;
import java.util.ArrayList;

public class Filter {
    private ArrayList<Item> FilterList=new ArrayList<Item>();

    boolean filterItem(Item item)
    {
        for(Item m_item:FilterList)
        {
            if(m_item==item)
            {
                return true;
            }
        }
        return false;
    }

}
