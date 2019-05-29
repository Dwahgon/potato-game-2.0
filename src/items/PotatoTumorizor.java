package items;

import java.math.BigDecimal;
import projeto1.Item;
import projeto1.Recipe;
import projeto1.Announcements;
import projeto1.Building;
import projeto1.Potatoes;

public class PotatoTumorizor extends Item{
    public PotatoTumorizor(){
        name = "Tumorizador de Batatas";
        
        recipe = new Recipe();
        recipe.AddCost(new BigDecimal("125750"));
        recipe.AddItemIngredient(Item.RequestItem("Açucar"), 853);
        recipe.AddItemIngredient(Item.RequestItem("Urânio"), 97);
        recipe.AddItemIngredient(Item.RequestItem("Ferro"), 264);
        recipe.AddPotatoCost(Potatoes.RequestPotato("Doce"), 6755);
    }
    
    //Adds 1 potato tumorizor
    public void UseItem(){
        //Remove the item
        GiveItem(-1);
        
        //Add 1 Building
        Building.RequestBuilding("Tumorizador de Batatas").AddBuilding(1);
        
        //Announce this to the player
        Announcements.DisplayAnnouncement("Você usou "+name+": Construído 1 "+name+" com sucesso!", Announcements.TextStyles.Normal);
    }
}
