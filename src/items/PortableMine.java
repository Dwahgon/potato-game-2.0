package items;

import java.math.BigDecimal;
import projeto1.Item;
import projeto1.Recipe;

public class PortableMine extends Lootbox{
    public PortableMine(){
        //Define basic properties
        name = "Minas Portateis";
        
        //Define the loot that this lootbox will give
        Item iron = Item.RequestItem("Ferro");
        Item uranium = Item.RequestItem("Urânio");
        
        //Define recipe
        recipe = new Recipe();
        recipe.AddCost(new BigDecimal(3425.75));
        
        //Comum
        comumLoot.add(new ItemLoot(iron, 10, 20));
        
        //Rare
        rareLoot.add(new ItemLoot(iron, 30, 70));
        rareLoot.add(new ItemLoot(uranium, 5, 20));
        rareLoot.add(new MoneyLoot(new BigDecimal(725), new BigDecimal(2545)));
        
        //Very rare
        veryRareLoot.add(new ItemLoot(iron, 50, 100));
        veryRareLoot.add(new ItemLoot(uranium, 10, 40));
        veryRareLoot.add(new MoneyLoot(new BigDecimal(5465), new BigDecimal(8756)));
        
        //Legendery
        legendaryLoot.add(new ItemLoot(iron, 100, 200));
        legendaryLoot.add(new ItemLoot(uranium, 40, 80));
        legendaryLoot.add(new MoneyLoot(new BigDecimal(12986), new BigDecimal(18746)));
    }
}
