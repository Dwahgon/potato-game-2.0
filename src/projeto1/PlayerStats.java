package projeto1;

import java.math.BigDecimal;

public class PlayerStats implements java.io.Serializable{
    //====================\\
    //===[DECLARATIONS]===\\
    //====================\\
    
    //==[DATA TO SAVE]==\\
    public BigDecimal money = new BigDecimal("0");
    public int[] itemQntSave, buildingSaves;
    public PotatoSave[] potatoSave;
    public GovernmentSaveData governmentSave;
    
    //===============\\
    //===[METHODS]===\\
    //===============\\
    
    public void SaveData() throws Exception{
        //Save money and government time
        money = Game.money;
        governmentSave = Game.government.GenerateSave();
        
        //Save items
        itemQntSave = Item.RequestSave();
        buildingSaves = Building.RequestSave();
        potatoSave = Potatoes.SaveData();
        
        //Save the game
        SaveLoad.Save(this, "nomeSave.save");
    }
    
    public void LoadData() throws Exception{
        //Set values
        Game.money = money;
        Item.LoadData(this);
        Building.LoadData(this);
        for (int i = 0; i < potatoSave.length; i++)
            potatoSave[i].LoadDataToPotato(Potatoes.allPotatoes[i]);
        Game.government.LoadSave(governmentSave);
    }
}
