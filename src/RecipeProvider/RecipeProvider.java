package RecipeProvider;

import Bus.Interface;
import Device.*;
import ACS.*;
import Item.Item;

public class RecipeProvider implements Interface,Container {
    private CookingDevice C_device; //做菜机器
    private AutoCookingSystem ACS;
    private Recipe recipe;

    private void launchAutoCooking(CookingDevice CookDevice)  //可以将cookingDevice返回的物品不经过内存直接发配往另一个cookingDevice对应的供应器
    {
        CookDevice.insertItem( this.C_device.cook());
    }

    public RecipeProvider(CookingDevice c_device, AutoCookingSystem ACS, Recipe recipe) {
        C_device = c_device;
        this.ACS = ACS;
        this.recipe = recipe;
    }

    public CookingDevice getC_device() {
        return C_device;
    }

    public AutoCookingSystem getACS() {
        return ACS;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    @Override
    public boolean bildToDevice(Device device) {
        return false;
    }

    @Override
    public String activate(String x) {
        return x;
    }

    @Override
    public boolean insertItem(Item item) {
        return false;
    }

    @Override
    public Item removeItem(int x) {
        return null;
    }

    @Override
    public int getMaxCapacity() {
        return 0;
    }

    @Override
    public boolean isFull() {
        return false;
    }
}
