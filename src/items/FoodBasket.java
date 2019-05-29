package items;

import java.math.BigDecimal;
import projeto1.Item;
import projeto1.Potatoes;
import projeto1.Recipe;

public class FoodBasket extends Lootbox{
    public FoodBasket(){
        //Define basic properties
        name = "Cesta Básica";
        
        //Define the loot that this lootbox will give
        Item sugar = Item.RequestItem("Açucar");
        Potatoes normalPotato = Potatoes.RequestPotato("Normal");
        Potatoes sweetPotato = Potatoes.RequestPotato("Doce");
        
        //Define recipe
        recipe = new Recipe();
        recipe.AddCost(new BigDecimal(725.5));
        recipe.AddPotatoCost(normalPotato, 125);
        
        //Comum
        comumLoot.add(new ItemLoot(sugar, 2, 10));
        comumLoot.add(new PotatoLoot(normalPotato, 100, 200));
        comumLoot.add(new PotatoLoot(sweetPotato, 10, 20));
        
        //Rare
        rareLoot.add(new ItemLoot(sugar, 10, 30));
        rareLoot.add(new PotatoLoot(normalPotato, 200, 400));
        rareLoot.add(new PotatoLoot(sweetPotato, 20, 40));
        
        //Very rare
        rareLoot.add(new ItemLoot(sugar, 50, 100));
        rareLoot.add(new PotatoLoot(normalPotato, 800, 1200));
        rareLoot.add(new PotatoLoot(sweetPotato, 50, 100));
        rareLoot.add(new MoneyLoot(new BigDecimal(300), new BigDecimal(800)));
        
        //Legendery
        rareLoot.add(new ItemLoot(sugar, 500, 1000));
        rareLoot.add(new PotatoLoot(normalPotato, 2000, 4000));
        rareLoot.add(new PotatoLoot(sweetPotato, 200, 400));
        rareLoot.add(new MoneyLoot(new BigDecimal(800), new BigDecimal(2000)));
    }
}
