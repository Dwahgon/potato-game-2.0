package items;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import projeto1.*;

public class Lootbox extends Item{
    //All of the loot rarities
    protected List<Loot> comumLoot = new ArrayList<>();
    protected List<Loot> rareLoot = new ArrayList<>();
    protected List<Loot> veryRareLoot = new ArrayList<>();
    protected List<Loot> legendaryLoot = new ArrayList<>();
    
    //The lootbox has been added
    public void UseItem(){
        //Generate a random number
        double randomRarity = Math.random();
        Loot wonLoot = null;
        
        //The generated number is a comum
        if(randomRarity > 0.4 && comumLoot.size() > 0){
            //Get a random loot to give to the player
            int randomLoot = (int)(Math.random() * comumLoot.size());
            wonLoot = comumLoot.get(randomLoot);
        }else if(randomRarity <= 0.40 && randomRarity > 0.17 && rareLoot.size() > 0){
            //Get a random loot to give to the player
            int randomLoot = (int)(Math.random() * rareLoot.size());
            wonLoot = rareLoot.get(randomLoot);
        }else if(randomRarity <= 0.17 && randomRarity > 0.03 && veryRareLoot.size() > 0){
            //Get a random loot to give to the player
            int randomLoot = (int)(Math.random() * veryRareLoot.size());
            wonLoot = veryRareLoot.get(randomLoot);
        }else if(randomRarity <= 0.03 && randomRarity > 0 && legendaryLoot.size() > 0){
            //Get a random loot to give to the player
            int randomLoot = (int)(Math.random() * legendaryLoot.size());
            wonLoot = legendaryLoot.get(randomLoot);
        }
        
        //Reward the player witht the chosen loot, if there was no loot, announce that he won nothing
        if(wonLoot != null)
            RewardLoot(wonLoot);
        else
            Announcements.DisplayAnnouncement("Você abriu "+name+": Você ganhou nada!", Announcements.TextStyles.CriticalAlert);
        
        //Remove 1 lootbox when used
        GiveItem(-1);
    }
    
    //Reward the loot to the player
    private void RewardLoot(Loot lootToReward){
        //If the reward is money, give money
        if(lootToReward instanceof MoneyLoot){
            BigDecimal randomMoneyReward = ((MoneyLoot) lootToReward).RandomMoneyPrize();
            Announcements.DisplayAnnouncement("Você abriu "+name+": Ganhastes TD$ "+Utils.stringifyBD(randomMoneyReward)+"", Announcements.TextStyles.GoodAlert);
            Game.GiveMoney(randomMoneyReward);
        }
        //If the reward is an item, give item to the player
        else if(lootToReward instanceof ItemLoot){
            int itemQuantityPrize = ((ItemLoot) lootToReward).RandomItemQuantityPrize();
            Announcements.DisplayAnnouncement("Você abriu "+name+": Ganhastes "+itemQuantityPrize+" "+((ItemLoot) lootToReward).prize.name, Announcements.TextStyles.GoodAlert);
            ((ItemLoot) lootToReward).prize.GiveItem(itemQuantityPrize);
        }
        //If the reward is potatoes, reward him with potatoes
        else if(lootToReward instanceof PotatoLoot){
            int potatoQuantityPrize = ((PotatoLoot) lootToReward).RandomPotatoQuantityPrize();
            Announcements.DisplayAnnouncement("Você abriu "+name+": Ganhastes "+potatoQuantityPrize+" Batatas "+((PotatoLoot) lootToReward).prize.name+"s", Announcements.TextStyles.GoodAlert);
            ((PotatoLoot) lootToReward).prize.GivePotato(potatoQuantityPrize);
        }
    }
}

//Loot class
class Loot implements Serializable{}

//Item loot
class ItemLoot extends Loot{
    Item prize;
    int minQnt, maxQnt;
    
    //Constructor
    public ItemLoot(Item winnableItem, int minimalQnt, int maximalQnt){
        prize = winnableItem;
        minQnt = minimalQnt;
        maxQnt = maximalQnt;
    }
    
    //Returns a number between minQnt and maxQnt
    int RandomItemQuantityPrize(){
        return (int) (minQnt + (maxQnt - minQnt) * Math.random());
    }
}

class MoneyLoot extends Loot{
    BigDecimal minQnt, maxQnt;
    
    //Constructor
    public MoneyLoot(BigDecimal minimalQnt, BigDecimal maximalQnt){
        minQnt = minimalQnt;
        maxQnt = maximalQnt;
    }
    
    BigDecimal RandomMoneyPrize(){
        //minQnt + (maxQnt - minQnt) * Math.random()
        BigDecimal randomNumber = new BigDecimal(Math.random()).setScale(2, RoundingMode.HALF_EVEN);
        return minQnt.add(maxQnt.subtract(minQnt).multiply(randomNumber));
    }
}

class PotatoLoot extends Loot{
    Potatoes prize;
    int minQnt, maxQnt;
    
    //Constructor
    public PotatoLoot(Potatoes winnablePotato, int minimalQnt, int maximalQnt){
        prize = winnablePotato;
        minQnt = minimalQnt;
        maxQnt = maximalQnt;
    }
    
    //Returns a number between minQnt and maxQnt
    int RandomPotatoQuantityPrize(){
        return (int) (minQnt + (maxQnt - minQnt) * Math.random());
    }
}
