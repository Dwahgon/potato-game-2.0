package projeto1;

import java.io.Serializable;
import java.util.List;
import java.math.BigDecimal;
import java.util.ArrayList;

public class Recipe implements Serializable{
    List<ItemI> items = new ArrayList<ItemI>();
    BigDecimal cost;
    List<PotatoI> potatoes = new ArrayList<PotatoI>();
    
    class ItemI implements Serializable{
        Item item;
        int qnt;
    }
    
    class PotatoI implements Serializable{
        Potatoes potato;
        int qnt;
    }
    
    //=====================\\
    //===[DEFINE RECIPE]===\\
    //=====================\\
    
    public void AddItemIngredient(Item itemToAdd, int qnt){
        //Adds the item to the list
        ItemI newItemIngredient = new ItemI();
        newItemIngredient.item = itemToAdd;
        newItemIngredient.qnt = qnt;
        
        //Adds to the list
        items.add(newItemIngredient);
    }
    
    public void AddCost(BigDecimal newCost){
        //Adds a cost in money for this recipe
        cost = newCost;
    }
    
    public void AddPotatoCost(Potatoes potato, int qnt){
        //Adds the potato to the list
        PotatoI newPotato = new PotatoI();
        newPotato.potato = potato;
        newPotato.qnt = qnt;
        
        //Adds to the list
        potatoes.add(newPotato);
    }
    
    //=================\\
    //===[FUNCTIONS]===\\
    //=================\\
    
    //Check if the user has all of the items for this recipe
    boolean IsCraftable(){
        //Checks if the user has the items necessery to craft this item
        if(items.size() > 0){
            for (ItemI ingredient : items) {
                if(ingredient.item.qnt < ingredient.qnt)
                    return false;
            }
        }
        
        //Checks if the user has enough money to craft this item
        if(cost != null){
            if(cost.compareTo(Game.money) == 1)
                return false;
        }
        
        //Checks if the user has enough potatoes to craft this item
        if(potatoes.size() > 0){
            for (PotatoI ingredient : potatoes) {
                if(ingredient.potato.qnt < ingredient.qnt)
                    return false;
            }
        }
        
        //Return true if the other returns has not been fire, indicating the item is craftable
        return true;
    }
    
    //=================\\
    //===[METHODS]===\\
    //=================\\
    
    //Uses all of the ingredients of this recipe
    void CraftRecipe(){
        //Removes items
        if(items.size() > 0){
            for (ItemI ingredient : items) 
                ingredient.item.GiveItem(-ingredient.qnt);
        }
        
        //Remove money
        if(cost != null)
            Game.GiveMoney(cost.negate());
        
        //Remove potato
        if(potatoes.size() > 0){
            for (PotatoI ingredient : potatoes) {
                ingredient.potato.GivePotato(-ingredient.qnt);
            }
        }
    }
}

