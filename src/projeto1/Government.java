package projeto1;

import java.io.Serializable;
import java.math.BigDecimal;

public class Government implements Serializable{
    //====================\\
    //===[DECLARATIONS]===\\
    //====================\\
    
    //==[PROPERTIES]==\\
    boolean isActive = true;                                            //Is this government active?
    BigDecimal taxValue = BigDecimal.ZERO,                              //How much does the tax costs
                takedownCost = new BigDecimal("907470847474"),          //How much does it cost to take down the government
                killLeftCost = new BigDecimal("8474740");               //How much does it cost tot kill a leftist
    int taxTime = 60,                                                   //How much time in seconds that the player will be forced to pay taxes
        leftists = 0,                                                   //How many leftists there are
        multiplier = 1;                                                 //Tax value multiplier
    
    //====================\\
    //===[CONSTRUCTORS]===\\
    //====================\\
    
    public Government(){
        UpdateTaxValue();
        UpdateAllUI();
        
        //Create thread
        Thread thread = new Thread(){
            @Override
            public void run(){
                RunnableTax();
            }
        };
        
        //Run thread
        thread.start();
    }
    
    //===============\\
    //===[METHODS]===\\
    //===============\\
    
    //Change the time of the tax payment
    void ChangeTaxTime(int newtime){
        //Chnage the tax time
        taxTime = newtime;
        
        //Update UI
        Game.janela.taxTime.setText(Utils.ConvertToTime(taxTime));
        
        //Disable button if the tax time is above 1 minute
        if(taxTime > 60 && Game.janela.payTax.isEnabled())
            Game.janela.payTax.setEnabled(false);
        else if(taxTime <= 60 && !Game.janela.payTax.isEnabled())
            Game.janela.payTax.setEnabled(true);
    }
    
    //Updates the tax value
    void UpdateTaxValue(){
        //Define the new value of the tax
        BigDecimal newTaxValue = BigDecimal.ZERO;
        
        //Get the tax value of all of the shops
        for (Building shop : Building.allBuildings) {
            for (int i = 0; i < shop.buildingQnt; i++) {
                newTaxValue = newTaxValue.add(shop.potatoType.normalPrice.multiply(new BigDecimal(shop.potatoProduction * (i+1))));
            }
        }
        
        //Multiply the tax value by the multiplier
        newTaxValue = newTaxValue.multiply(new BigDecimal(multiplier));
        
        //Redefine the tax value
        taxValue = newTaxValue;
        
        //Update UI
        Game.janela.taxValue.setText(Utils.stringifyBD(taxValue)+"");
    }
    
    //Brings back the government
    void ActivateGovernment(){
        //Reset status
        Game.GiveMoney(Game.money.negate());
        AddLeftist(-leftists);
        
        //Reset potatoes
        for(Potatoes potato : Potatoes.allPotatoes){
            potato.GivePotato(-potato.qnt);
        }
        
        //Bring back TAX and double it's value
        ChangeTaxTime(60);
        multiplier *= 2;
        UpdateTaxValue();
        
        //UI
        Game.janela.destroyGovernment.setEnabled(true);
        Game.janela.killLeftist.setEnabled(false);
        Game.janela.governmentActivity.setText("Ativo");
        
        //Activate government
        isActive = true;
        
        //Display announcement
        Announcements.DisplayAnnouncement("O governo foi restaurados Seus TD e batatas foram zerados, e você voltará a pagar imposto em dobro!", Announcements.TextStyles.CriticalAlert);
    }
    
    //Destroys the government
    void DeactivateGovernment(){
        //Change the TAX time
        ChangeTaxTime(0);

        //UI
        Game.janela.destroyGovernment.setEnabled(false);
        Game.janela.killLeftist.setEnabled(true);
        Game.janela.payTax.setEnabled(false);
        Game.janela.governmentActivity.setText("Destruído");

        //Start a left factory
        Thread leftFactory = new Thread(){
            public void run(){
                while(!isActive){
                    Utils.waitSeconds(30);

                    //If we reach 100 leftists, bring back government
                    if(leftists<100){
                        AddLeftist(1);
                    }else{
                        ActivateGovernment();
                    }
                }
            }
        };
        leftFactory.start();

        //Deactivate government
        isActive = false;
    }
    
    //Adds a leftist
    void AddLeftist(int qnt){
        //Add a leftist
        leftists += qnt;
        
        //Update UI
        Game.janela.leftistQnt.setText(leftists+"");
        
        Announcements.DisplayAnnouncement("Matado 1 esquerdista com sucesso!", Announcements.TextStyles.GoodAlert);
    }
    
    //Updates all of the UI related to the government
    final void UpdateAllUI(){
        Game.janela.taxTime.setText(Utils.ConvertToTime(taxTime));
        Game.janela.taxValue.setText(Utils.stringifyBD(taxValue)+"");
        Game.janela.takedownCost.setText("TD$ "+Utils.stringifyBD(takedownCost));
        Game.janela.killLeftCost.setText("TD$ "+Utils.stringifyBD(killLeftCost));
    }
    
    //Generates a save
    GovernmentSaveData GenerateSave(){
        //Create data object
        GovernmentSaveData dataToSave = new GovernmentSaveData();
        
        //Set properties
        dataToSave.activity = isActive;
        dataToSave.leftists = leftists;
        dataToSave.multiplier = multiplier;
        dataToSave.taxTime = taxTime;
        
        //Return the created data object
        return dataToSave;
    }
    
    void LoadSave(GovernmentSaveData dataToLoad){
        //Change the tax time
        ChangeTaxTime(dataToLoad.taxTime);
        
        //Load the multiplier
        multiplier = dataToLoad.multiplier;
        
        //Update the tax multiplier
        UpdateTaxValue();
        
        //Bring down the government if the activity is false
        if(!dataToLoad.activity){
            DeactivateGovernment();
            AddLeftist(dataToLoad.leftists);    
        }
    }
    
    //======================\\
    //===[THREAD METHODS]===\\
    //======================\\
    
    //Mehtod responsable for the tax
    void RunnableTax(){
        boolean lost = false;
        while (true){
            //Wait a second
            Utils.waitSeconds(1);

            //If there is tax and the government is active
            if(taxValue.compareTo(BigDecimal.ZERO) == 1 && isActive){
                //Enable paytax button
                if(!Game.janela.payTax.isEnabled() && taxTime <= 60)
                    Game.janela.payTax.setEnabled(true);

                //If tax time is not 0, remove time
                if(taxTime > 0){
                    ChangeTaxTime(taxTime - 1);
                //If it is 0, and the player has money, remove money and reset timer
                }else if(Game.money.compareTo(taxValue) >= 0){
                    Game.GiveMoney(taxValue.negate());
                    ChangeTaxTime(60);
                    Announcements.DisplayAnnouncement("O governo te roubou TD$"+taxValue+" !", Announcements.TextStyles.CriticalAlert);
                //If the time is 0, and the player doesn't have money, close game
                }else if(lost != true){
                    //Try to dlete save
                    try{
                        SaveLoad.DeleteSave("nomeSave.save");
                    }catch(Exception ex){
                        System.out.println("N deu pra deletar save");
                    }

                    //Open lost window
                    lost = true;
                    Lost lostWindow = new Lost();
                    lostWindow.setVisible(true);

                    Game.janela.setVisible(false);
                }
            }
        }
    }
}

class GovernmentSaveData implements Serializable{
    int taxTime, leftists, multiplier;
    boolean activity;
}