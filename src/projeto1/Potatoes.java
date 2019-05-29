package projeto1;
import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import potatoes.*;

public class Potatoes implements Serializable{
    //====================\\
    //===[DECLARATIONS]===\\
    //====================\\
    
    //Static//
    public static Potatoes[] allPotatoes = new Potatoes[2];
    
    //Properties//
    public String name;
    public int qnt = 0, production = 0;
    public BigDecimal normalPrice, currentPrice, priceRange;
    
    //UI
    public JLabel productionUI;
    public PotatoUI potatoUi;
    
    //======================\\
    //===[INITIALIZATION]===\\
    //======================\\
    
    //Creates the diferent types of potatoes
    static void Initialize(){
        //Create the different potato types
        allPotatoes[0] = new NormalPotato();
        allPotatoes[1] = new SweetPotato();
        
        //Create the potato menu
        Game.janela.CreatePotatoMenu(allPotatoes);
        
        //Initialize threads
        StartThreads();
    }
    
    //Initialize threads
    static void StartThreads(){
        //Produce potatoes
        Thread produce = new Thread(){
            public void run(){
                ProducePotato();
             }
        };
        
        // Update the potato price
        Thread updatePotatoPrice = new Thread(){
            public void run(){
                UpdatePotatoPrice();
            }
        };
        
        //Start threads
        produce.start();
        updatePotatoPrice.start();
    }
    
    //Updates the UI
    final protected void InitializeUI(){
        potatoUi = new PotatoUI(this);
    }
    
    //=====================\\
    //===[STATIC METHODS]==\\
    //=====================\\
    
    //Returns a potato with the given name
    public static Potatoes RequestPotato(String potatoName){
        //Looks for the potato and returns the potato with the same name
        for (Potatoes potato : allPotatoes) {
            if(potato.name.equals(potatoName))
                return potato;
        }
        
        //If it doesn't find an item with the same name, return null
        return null;
    }
    
    static PotatoSave[] SaveData(){
        PotatoSave[] dataToSave = new PotatoSave[Potatoes.allPotatoes.length];
        
        for (int i = 0; i < allPotatoes.length; i++) {
            dataToSave[i] = allPotatoes[i].CreatePotatoSave();
        }
        
        return dataToSave;
    }
    
    //===============\\
    //===[METHODS]===\\
    //===============\\
    
    //Give potatoes
    public void GivePotato(int potatoesToGive){
        //Give potatoes
        qnt += potatoesToGive;
        
        //Update UI
        potatoUi.potatoQntUI.setText(qnt+"");
    }
    
    //Changes the price of the potatoes
    void ChangePrice(){
        //Generate a new price
        BigDecimal changePrice = new BigDecimal((Math.random()*2-1) + "").multiply(priceRange);
        
        //Change the price of the potato
        currentPrice = normalPrice.add(changePrice);
        
        //Update the UI
        potatoUi.potatoPriceUI.setText("TD$ " + Utils.stringifyBD(currentPrice.multiply(new BigDecimal(Game.janela.multiplier))));
    }
    
    //Upgrades the production of this potato
    void UpgradeProduction(int additionalProduction){
        production += additionalProduction;
        
        productionUI.setText(production + " b/s");
    }
    
    //Attempts to buy the potato
    void BuyPotato(){
        //Get the window multiplier
        int multiplier = Game.janela.multiplier;
        
        //If the user has more money than the price of the potatoes that he wants to buy, buy potatoes
        if(currentPrice.multiply(new BigDecimal(multiplier)).compareTo(Game.money)  <= 0){
            //Give the multiplier of potatoes to the player
            GivePotato(multiplier);
            
            //Remove money from the user
            Game.GiveMoney(currentPrice.multiply(new BigDecimal(multiplier)).negate()); // - currentPrice * multiplier
            
            //Show announcement
            Announcements.DisplayAnnouncement("Comprado "+multiplier+" " + name + "(s) com sucesso!", Announcements.TextStyles.GoodAlert);
        }else
            Announcements.DisplayAnnouncement("Você não tem dinheiro o suficiente para comprar esta(s) batata(s)", Announcements.TextStyles.CriticalAlert);
    }
    
    //Sells potatoes
    void SellPotato(){
        //Get the window multiplier
        int multiplier = Game.janela.multiplier;
        
        if(qnt >= multiplier){
            //Remove potatoes
            GivePotato(-multiplier);
            
            //Goves the user money
            Game.GiveMoney(currentPrice.multiply(new BigDecimal(multiplier))); //currentPrice * multiplier
            
            //Display an annoncement
            Announcements.DisplayAnnouncement("Vendido "+multiplier+" Batata(s) "+name+"(s) com sucesso!", Announcements.TextStyles.GoodAlert);
        }else
            //Show announcement
            Announcements.DisplayAnnouncement("Você não tem batatas suficientes!", Announcements.TextStyles.CriticalAlert);
    }
    
    //Sells all potatoes
    void SellAllPotato(){
        if(qnt > 0){
            //Give the money of the sold potatoes to the player
            Game.GiveMoney(currentPrice.multiply(new BigDecimal(qnt))); // currentPrice * qnt
            
            //Announce
            Announcements.DisplayAnnouncement("Vendido "+qnt+" Batata(s) "+name+"(s) com sucesso!", Announcements.TextStyles.GoodAlert);
            
            //Remove all potatoes from the player
            GivePotato(-qnt);
        }else
            //Show announcement
            Announcements.DisplayAnnouncement("Você não tem nenhuma batata!", Announcements.TextStyles.CriticalAlert);
    }
    
    private PotatoSave CreatePotatoSave(){
        PotatoSave newDataSave = new PotatoSave();
        
        //Save the stats
        newDataSave.qnt = qnt;
        newDataSave.currentPrice = currentPrice;
        
        return newDataSave;
    }
    
    //======================\\
    //===[THREAD METHODS]===\\
    //======================\\
    
    private static void ProducePotato(){
        while (true){
            //Wait a second
            Utils.waitSeconds(1);

            //Give potatoes to the player
            for (Potatoes potato : Potatoes.allPotatoes) {
                potato.GivePotato(potato.production);
            }

         }
    }
    
    private static void UpdatePotatoPrice(){
        while (true){
            //Wait a second
            Utils.waitSeconds(30);

            //Change the price of the potatoes
            for (Potatoes potato : Potatoes.allPotatoes) {
                potato.ChangePrice();
            }
        } 
    }
}

class PotatoSave implements Serializable{
    int qnt;
    BigDecimal currentPrice = BigDecimal.ZERO;
    
    
    public void LoadDataToPotato(Potatoes potato){
        //Load statss
        potato.GivePotato(qnt);
        potato.currentPrice = currentPrice;
        
        //Update UI
        potato.potatoUi.potatoPriceUI.setText("TD$ " + Utils.stringifyBD(currentPrice.multiply(new BigDecimal(Game.janela.multiplier))));
    }
}

//Create the UI pf this potato on the potatoes menu
class PotatoUI extends JPanel{
    public JLabel potatoPriceUI, potatoQntUI;
    
    public PotatoUI(Potatoes potato){
        //Creation
            JComponent[] allComponents = new JComponent[7];
            JLabel potatoNameLabel = new JLabel();
            potatoQntUI = new JLabel();
            JLabel priceLabel = new JLabel();
            potatoPriceUI = new JLabel();
            JButton buyPotato = new JButton();
            JButton sellPotato = new JButton();
            JButton sellAllPotato = new JButton();
            allComponents[0] = potatoNameLabel;
            allComponents[1] = potatoQntUI;
            allComponents[2] = priceLabel;
            allComponents[3] = potatoPriceUI;
            allComponents[4] = buyPotato;
            allComponents[5] = sellPotato;
            allComponents[6] = sellAllPotato;
            
            //Set panel
            setSize(669, 23);
            
            //Set texts
            potatoNameLabel.setText("Batata "+potato.name+": ");
            potatoQntUI.setText(potato.qnt+"");
            priceLabel.setText("Preço: ");
            potatoPriceUI.setText("TD$ "+Utils.stringifyBD(potato.currentPrice));
            buyPotato.setText("Comprar");
            sellPotato.setText("Vender");
            sellAllPotato.setText("Vender Todos");
            
            //Add components to panel and show them
            for (JComponent component : allComponents) {
                add(component);
                component.setVisible(true);
            }
            
            //Add functionality to the buttons
            buyPotato.addActionListener((ActionEvent evt) -> {
                potato.BuyPotato();
            });
            sellPotato.addActionListener((ActionEvent evt) -> {
                potato.SellPotato();
            });
            sellAllPotato.addActionListener((ActionEvent evt) -> {
                potato.SellAllPotato();
            });
            
            //Define layout
            setLayout(null);
            
            //Set the positions
            potatoNameLabel.setBounds(0, 0, 100, 23);
            potatoQntUI.setBounds(110, 0, 100, 23);
            priceLabel.setBounds(225, 0, 35, 23);
            potatoPriceUI.setBounds(260, 0, 100, 23);
            buyPotato.setBounds(369, 0, 75, 23);
            sellPotato.setBounds(449, 0, 75, 23);
            sellAllPotato.setBounds(529, 0, 100, 23);
    }
}