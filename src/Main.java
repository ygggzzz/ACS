import ACS.*;
import Bus.*;
import Device.*;
import Filter.*;
import Item.*;
import RecipeProvider.*;
import java.util.Scanner;

/*
未完成：
关于优先级,对于存储总线，物品优先从低优先级处取出，并放入高优先级处。
暂时没考虑容器容积问题(类似mc物品栏)
物品类有点问题,不用建子类(应该)
 */

public class Main {
//    public static void main(String[] args) {
//        //OI功能测试
//        staple cooked_rice=new staple("cooked_rice",0);
//        staple rice=new staple("rice",0);
//        freshWater freshWater=new freshWater("freshWater",0);
//
//        Recipe cook_rice=new Recipe("cooked_rice");
//        cook_rice.setFormula(rice);
//        cook_rice.setFormula(freshWater);
//
//        AutoCookingSystem ACS=new AutoCookingSystem();
//
//        Device shelf=new Device("shelf1",1000);
//        Device sink=new Device("sink2",1000);
//        CookingDevice cooker= new CookingDevice("cooker3",1000);
//
//        shelf.insertItem(rice);
//        sink.insertItem(freshWater);
//
//        Filter filter1=new Filter();
//        filter1=null;
//        Filter filter2=new Filter();
//        filter2.addFilter(freshWater);
//        Filter filter3=new Filter();
//        filter3.addFilter(freshWater);
//        filter3.addFilter(rice);
//
//        StorageBus bus1=new StorageBus(shelf,filter1,ACS);
//        StorageBus bus2=new StorageBus(sink,filter2,ACS);
//        OutputBus bus3=new OutputBus(cooker,filter3,ACS);
//
//        cooker.setACS(ACS);
//
//        ACS.addBus(bus1);
//        ACS.addBus(bus2);
//        ACS.addBus(bus3);
//        ACS.addDevice(shelf);
//        ACS.addDevice(sink);
//        ACS.addDevice(cooker);
//
//        cooker.addBus(bus3);
//
//        cooker.addRecipe(cook_rice);
//
////        ACS.visitBus(0);
////        ACS.visitBus(1);
////        ACS.visitBus(2);
//        ACS.recallFood();
////        ACS.visitNetFood();
//        System.out.println();
////        ACS.visitRecipe();
//
//        ACS.addRequest(cooked_rice);
//
//        while(!ACS.getRequestList().isEmpty())
//        {
//            Item item=ACS.getRequest();
//            System.out.println(item.getID()+" is complete");
//        }
//
//        System.out.println();
//        ACS.recallFood();
//        ACS.visitNetFood();
//        System.out.println();
//
//        //cooker.visitItem();
//    }

    public static void main(String[] args) {
        //网络简单烹饪功能测试
        Device freeze=new Device("freeze2",100);
        Device sink=new Device("sink2",100);
        CookingDevice cooler=new CookingDevice("cooler3",1);
        CookingDevice shaper=new CookingDevice("shaper414",5);
        Device out=new Device("out1",1000);

        milk cheese=new milk("cheese",0);
        freshWater freshWater=new freshWater("freshWater",0);
        freshWater ice=new freshWater("ice",0);
        flavour DingZhen=new flavour("ZSXB",0);

        Recipe Ice=new Recipe("ice");
        Ice.setFormula(freshWater);
        Recipe ZSXB=new Recipe("ZSXB");
        ZSXB.setFormula(ice);
        ZSXB.setFormula(ice);
        ZSXB.setFormula(cheese);

        Filter filter1=new Filter();
        filter1.addFilter(cheese);
        Filter filter2=new Filter();
        filter2.addFilter(freshWater);
        Filter filter3=new Filter();
        filter3.addFilter(freshWater);
        filter3.addFilter(ice);
        Filter filter4=new Filter();
        filter4=null;
        Filter filter5=new Filter();
        filter5.addFilter(DingZhen);

        AutoCookingSystem ACS=new AutoCookingSystem();

        freeze.insertItem(cheese);
        freeze.insertItem(cheese);
        sink.insertItem(freshWater);
        sink.insertItem(freshWater);

        RecipeProvider provider1=new RecipeProvider();
        provider1.bildToDevice(cooler);
        provider1.setACS(ACS);
        provider1.setRecipe(Ice);
        RecipeProvider provider2=new RecipeProvider();
        provider2.bildToDevice(shaper);
        provider2.setACS(ACS);
        provider2.setRecipe(ZSXB);

        ACS.addProvider(provider1);
        ACS.addProvider(provider2);

        StorageBus bus1=new StorageBus(freeze,filter1,ACS);
        StorageBus bus2=new StorageBus(sink,filter2,ACS);
        StorageBus bus3=new StorageBus(cooler,filter3,ACS);
        StorageBus bus4=new StorageBus(shaper,filter4,ACS);
        StorageBus bus5=new StorageBus(out,filter5,ACS);

        cooler.addBus(bus3);
        shaper.addBus(bus4);

        ACS.addBus(bus1);
        ACS.addBus(bus2);
        ACS.addBus(bus3);
        ACS.addBus(bus4);
        ACS.addBus(bus5);
        ACS.addDevice(freeze);
        ACS.addDevice(sink);
        ACS.addDevice(cooler);
        ACS.addDevice(shaper);
        ACS.addDevice(out);

        cooler.addRecipe(Ice);
        shaper.addRecipe(ZSXB);
        cooler.setACS(ACS);
        shaper.setACS(ACS);

        ACS.visitBus(0);
        ACS.visitBus(1);
        ACS.visitBus(2);
        ACS.visitBus(3);
        ACS.visitBus(4);
        ACS.recallFood();
        ACS.visitNetFood();
        System.out.println();
        ACS.visitRecipe();

        ACS.addRequest(Ice);
        ACS.addRequest(Ice);
        ACS.addRequest(ZSXB);

        while(!ACS.getRequestList().isEmpty())
        {
            Item item = ACS.getRequest();
            if (item != null)
            {
                System.out.println(item.getID() + " is complete");
            }
        }

//        freeze.visitItem();
//        sink.visitItem();
//        cooler.visitItem();
//        shaper.visitItem();
//        out.visitItem();

        System.out.println();
        ACS.recallFood();
        ACS.visitNetFood();
        System.out.println();

    }
}