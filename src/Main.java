import ACS.*;
import Bus.*;
import Device.*;
import Filter.*;
import Item.*;
import RecipeProvider.*;
import java.util.Scanner;

/*
缺少第二个测试
 */

public class Main {
    public static void main(String[] args) {
        //OI功能测试
        staple cooked_rice=new staple("cooked_rice",0);
        staple rice=new staple("rice",0);
        freshWater freshWater=new freshWater("freshWater",0);

        Recipe cook_rice=new Recipe("cooked_rice");
        cook_rice.setFormula(rice);
        cook_rice.setFormula(freshWater);

        AutoCookingSystem ACS=new AutoCookingSystem();

        Device shelf=new Device("shelf1",1000);
        Device sink=new Device("sink2",1000);
        CookingDevice cooker= new CookingDevice("cooker3",1000);

        shelf.insertItem(rice);
        sink.insertItem(freshWater);

        Filter filter1=new Filter();
        filter1=null;
        Filter filter2=new Filter();
        filter2.addFilter(freshWater);
        Filter filter3=new Filter();
        filter3.addFilter(freshWater);
        filter3.addFilter(rice);

        StorageBus bus1=new StorageBus(shelf,filter1,ACS);
        StorageBus bus2=new StorageBus(sink,filter2,ACS);
        OutputBus bus3=new OutputBus(cooker,filter3,ACS);

        cooker.setACS(ACS);

        ACS.addBus(bus1);
        ACS.addBus(bus2);
        ACS.addBus(bus3);
        ACS.addDevice(shelf);
        ACS.addDevice(sink);
        ACS.addDevice(cooker);

        cooker.addRecipe(cook_rice);

//        ACS.visitBus(0);
//        ACS.visitBus(1);
//        ACS.visitBus(2);
        ACS.recallFood();
//        ACS.visitNetFood();
        System.out.println();
//        ACS.visitRecipe();

        ACS.addRequest(cooked_rice);

        while(!ACS.getRequestList().isEmpty())
        {
            Item item=ACS.getRequest();
            System.out.println(item.getID()+" is complete");
        }
    }
}