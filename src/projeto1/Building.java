package projeto1;
import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.math.BigDecimal;
import shops.*;
import javax.swing.*;

public class Building implements Serializable{
    //====================\\
    //===[DECLARATIONS]===\\
    //====================\\
    
    //==[STATIC]==\\
    public static Building[] allBuildings = new Building[5];
    
    //==[PROPERTIES]==\\
    public String buildingName;
    public Potatoes potatoType;
    public BigDecimal bPrice, bAdditionalPrice, bAdditionalPricePerPurchase;
    public int potatoProduction, buildingQnt = 0;
    protected boolean purchasable = true;
    private BigDecimal initialPrice = BigDecimal.ZERO;
    
    //==[GUI]==\\
    protected JLabel productionUI;
    protected BuildingShop buildingShop;
    
    //======================\\
    //===[INITIALIZATION]===\\
    //======================\\
    
    //Initialize the class
    static void Initialize(){
        //Create buildings and store them in the allBuildings table
        allBuildings[0] = new Fazenda();
        allBuildings[1] = new AutomatedFarm();
        allBuildings[2] = new FuturisticFarm();
        allBuildings[3] = new MSTLands();
        allBuildings[4] = new PotatoTumorizor();
        
        //Set the initial price of the building
        for(Building build : allBuildings){
            if(build.bPrice != null)
                build.initialPrice = build.initialPrice.add(build.bPrice);
        }
        
        //Criar layout
        Game.janela.CreateBuildingMenuLayout(allBuildings);
    }
    
    //Creates the UI for the building
    protected void CreateBuildingPanel(){
        buildingShop = new BuildingShop(potatoType, this);
    }
    
    //=====================\\
    //===[STATIC METHODS]==\\
    //=====================\\
    
    //Returns an item with the given name
    public static Building RequestBuilding(String buildingName){
        //Looks for the item and gets the item with a same name
        for (Building build : allBuildings) {
            if(build.buildingName.equals(buildingName))
                return build;
        }
        
        //If it doesn't find an item with the same name, return null
        return null;
    }
    
    static int[] RequestSave(){
        int[] dataToSave = new int[allBuildings.length];
        
        for (int i = 0; i < dataToSave.length; i++) {
            dataToSave[i] = allBuildings[i].buildingQnt;
        }
        
        return dataToSave;
    }
    
    static void LoadData(PlayerStats ps){
        for (int i = 0; i < allBuildings.length; i++)
            allBuildings[i].AddBuilding(ps.buildingSaves[i]);
    }
    
    //===============\\
    //===[MEHTODS]===\\
    //===============\\
    
    //Give 1 of this building to the player
    public void AddBuilding(int qnt){
        //Add a building
        buildingQnt += qnt;
        
        //Upgrade the production of the potato
        potatoType.UpgradeProduction(potatoProduction * qnt);
        
        //Update the price
        UpdateBuildingPrices();
        
        //Change the tax value
        Game.government.UpdateTaxValue();

        //Change the UI of the building shop frame
        buildingShop.taxUI.setText("TD$ " + Utils.stringifyBD(previousTaxValue()));
        buildingShop.taxUI.setToolTipText("TD$ " +  Utils.stringifyBD(previousTaxValue()));
        productionUI.setText(potatoProduction * buildingQnt+" b/s");
        buildingShop.qntUI.setText(buildingQnt + "");
    }
    
    //Purchases this building
    void PurchaseBuilding(){
        //Create building if th player has money
        if(Game.money.compareTo(bPrice) >= 0){
            //Take the money from the user
            Game.GiveMoney(bPrice.negate()); // -bPrice
            
            //Add a building
            AddBuilding(1);
            
            //Change the price of this building and it's additional prices
            UpdateBuildingPrices();
            
            Announcements.DisplayAnnouncement("Comprado um "+buildingName+" com sucesso!", Announcements.TextStyles.GoodAlert);
        }else
            Announcements.DisplayAnnouncement("Você não tem dinheiro suficiente!", Announcements.TextStyles.CriticalAlert);
    }
    
    //Updates the price of the shop
    void UpdateBuildingPrices(){
        if(!purchasable) return;
        
        //Reset the price of the building      
        double startPrice = initialPrice.doubleValue();
        double endPrice = initialPrice.doubleValue() + bAdditionalPrice.doubleValue() + bAdditionalPrice.doubleValue() * buildingQnt;
        double calculatePrice = (startPrice + endPrice) * buildingQnt / 2;
        
        bPrice = new BigDecimal(calculatePrice);
        
        
        //Update the price indicator of the building's shop frame
        buildingShop.priceUI.setText("TD$ " + Utils.stringifyBD(bPrice));
        buildingShop.priceUI.setToolTipText("TD$ " + Utils.stringifyBD(bPrice));
    }
    
    //=======================\\
    //===[PRIVATE METHODS]===\\
    //=======================\\
    
    //Returns the tax value of the next building
    BigDecimal newTaxValue(){
        return potatoType.normalPrice.multiply(new BigDecimal(potatoProduction *  buildingQnt));
    }
    
    //Returns the tax value of the next building
    BigDecimal previousTaxValue(){
        return potatoType.normalPrice.multiply(new BigDecimal(potatoProduction *  (buildingQnt+1)));
    }
    
    //Buys a shop and updates the UI
    void BuyShop(){
        //Purchase the building
        PurchaseBuilding();
        
        //Update the price indicator of the building's shop frame
        buildingShop.priceUI.setText("TD$ " + Utils.stringifyBD(bPrice));
        buildingShop.priceUI.setToolTipText("TD$ " + Utils.stringifyBD(bPrice));
    }
}

//The shop panel for the building
class BuildingShop extends JPanel{
    //DECLARATIONS
    JLabel taxUI, qntUI, priceUI;
    
    public BuildingShop(Potatoes type, Building building){
        //Create components
        JComponent[] allComponents = new JComponent[12];
        priceUI = new JLabel();
        taxUI = new JLabel();
        qntUI = new JLabel();
        JLabel title = new JLabel();
        JLabel priceLabel = new JLabel();
        JLabel potatoTypeLabel = new JLabel();
        JLabel potatoType = new JLabel();
        JLabel efficiencyLabel = new JLabel();
        JLabel efficiency = new JLabel();
        JLabel qntLabel = new JLabel();
        JLabel taxLabel = new JLabel();
        JButton buyBuilding = new JButton();
        allComponents[0] = priceUI;
        allComponents[1] = taxUI;
        allComponents[2] = qntUI;
        allComponents[3] = title;
        allComponents[4] = priceLabel;
        allComponents[5] = potatoTypeLabel;
        allComponents[6] = potatoType;
        allComponents[7] = efficiencyLabel;
        allComponents[8] = efficiency;
        allComponents[9] = qntLabel;
        allComponents[10] = taxLabel;
        allComponents[11] = buyBuilding;
        
        //Configure panel
        setSize(180, 190);
        setBorder(BorderFactory.createBevelBorder(0));
        
        //Define the price string
        String priceString = (building.purchasable) ? Utils.stringifyBD(building.bPrice) : "NA";
        
        //Set texts
        title.setText(building.buildingName);
        priceLabel.setText("Preço:");
        priceUI.setText("TD$ " + priceString);
        potatoTypeLabel.setText("Tipo:");
        potatoType.setText(building.potatoType.name);
        efficiencyLabel.setText("Eficiência:");
        efficiency.setText(building.potatoProduction+" b/s");
        qntLabel.setText("Quantidade: ");
        qntUI.setText(building.buildingQnt+"");
        taxLabel.setText("Imposto: ");
        taxUI.setText("TD$ "+Utils.stringifyBD(building.previousTaxValue()));
        buyBuilding.setText("Comprar");
        
        //Add Alignments to the text
        title.setHorizontalAlignment(SwingConstants.CENTER);
        
        //Add tooltips
        priceUI.setToolTipText("TD$ "+priceString);
        taxUI.setToolTipText("TD$ "+Utils.stringifyBD(building.previousTaxValue()));
                
        //Add components to the panel and show them
        for (JComponent component : allComponents) {
            add(component);
            component.setVisible(true);
        }
        
        //Add functionality to the button
        buyBuilding.addActionListener((ActionEvent ae) -> {
            if(building.purchasable)
                building.BuyShop();
        });
        
        buyBuilding.setEnabled(building.purchasable);
            
        //Create a layout for the shop
        setLayout(null);
        
        //Set bounds and locations of the gui
        title.setBounds(0, 5, 180, 20);
        priceLabel.setBounds(10, 35, 70, 20);
        priceUI.setBounds(90, 35, 80, 20);
        potatoTypeLabel.setBounds(10, 55, 70, 20);
        potatoType.setBounds(90, 55, 80, 20);
        efficiencyLabel.setBounds(10, 75, 70, 20);
        efficiency.setBounds(90, 75, 80, 20);
        taxLabel.setBounds(10, 95, 70, 20);
        taxUI.setBounds(90, 95, 80, 20);
        qntLabel.setBounds(10, 115, 70, 20);
        qntUI.setBounds(90, 115, 80, 20);
        buyBuilding.setBounds(10, 160,160, 20);
    }
}