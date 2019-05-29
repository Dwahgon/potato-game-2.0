package projeto1;
import items.*;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.io.Serializable;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class Item implements Serializable{
    //===================\\
    //===[DECLARATIONS]==\\
    //===================\\
    
    //Static
    public static Item[] allItems = new Item[6];
    
    //UI
    public InventoryItemFrame invUI;
    public ShopItemFrame shopUI;
    
    //Properties
    public String name;
    protected int qnt;
    protected Recipe recipe;
    
    //=====================\\
    //===[INITIALIZATION]==\\
    //=====================\\
    
    //Creates all items
    static void Initialize(){
        //Create all items
        allItems[0] = new Sugar();
        allItems[1] = new Uranium();
        allItems[2] = new Iron();
        allItems[3] = new PotatoTumorizor();
        allItems[4] = new FoodBasket();
        allItems[5] = new PortableMine();
        
        for (Item i : allItems) {
            i.CreateUI();
        }
    }
    
    //Creates all of the UIs
    void CreateUI(){
        //Create an inventory UI
        invUI = new InventoryItemFrame(this);
        
        //Create the shop UI if its purchasable
        shopUI = (recipe != null) ? new ShopItemFrame(this) : null;
    }
    
    //=====================\\
    //===[STATIC METHODS]==\\
    //=====================\\
    
    //Returns an item with the given name
    public static Item RequestItem(String itemName){
        //Looks for the item and gets the item with a same name
        for (Item item : allItems) {
            if(item.name.equals(itemName))
                return item;
        }
        
        //If it doesn't find an item with the same name, return null
        return null;
    }
    
    static int[] RequestSave(){
        //Create array to store all of the items
        int[] itemQnt = new int[allItems.length];
        
        //Store the item quantities
        for (int i = 0; i < allItems.length; i++)
            itemQnt[i] = allItems[i].qnt;
        
        //Return
        return itemQnt;
    }
    
    //Loads the data to the items
    static void LoadData(PlayerStats ps){
        for (int i = 0; i < allItems.length; i++)
            allItems[i].GiveItem(ps.itemQntSave[i]);
        
    }
    
    //=============\\
    //===[METHOS]==\\
    //=============\\
    
    //Uses the item
    protected void RequestToUseItem(){
        if(qnt > 0)
            UseItem();
        else
            Announcements.DisplayAnnouncement("Você não possui nenhum "+name+"!", Announcements.TextStyles.CriticalAlert);
    }
    
    //Attempts to craft item
    protected void CraftItem(){
        //If it is craftable, take resources and add this item
        if(recipe.IsCraftable()){
            recipe.CraftRecipe();
            
            GiveItem(1);
            
            Announcements.DisplayAnnouncement("Craftado 1 "+name+" com sucesso!", Announcements.TextStyles.GoodAlert);
        }else
            Announcements.DisplayAnnouncement("Você não possui recursos suficientes!", Announcements.TextStyles.CriticalAlert);
    }
    
    public void GiveItem(int quantity){
        qnt += quantity;
            
        invUI.qntUI.setText(qnt+"");
    }
    
    //=====================\\
    //===[PRIVATE METHOS]==\\
    //=====================\\
    
    //Uses this item
    protected void UseItem(){
        Announcements.DisplayAnnouncement("Você usou "+name+": Não aconteceu nada!", Announcements.TextStyles.Normal);
    }
}

//====================\\
//===[OTHER CLASSES]==\\
//====================\\

class InventoryItemFrame extends JPanel{
    JLabel qntUI;
    
    public InventoryItemFrame(Item item){
        //Create components
        JLabel title = new JLabel();
        JLabel qntLabel = new JLabel();
        JButton buyButton = new JButton();
        qntUI = new JLabel();
        
        //Set frame
        setSize(140, 100);
        setBorder(BorderFactory.createBevelBorder(0));
        
        //Set texts and tooltips
        title.setText(item.name);
        title.setToolTipText(item.name);
        qntLabel.setText("Quantidade: ");
        qntUI.setText(item.qnt+"");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        buyButton.setText("Usar");
        
        //Add and show the components
        add(title);
        add(qntLabel);
        add(qntUI);
        add(buyButton);
        title.setVisible(true);
        qntLabel.setVisible(true);
        qntUI.setVisible(true);
        buyButton.setVisible(true);
        
        buyButton.addActionListener((ActionEvent ae) -> {
            item.RequestToUseItem();
        });
        
        //Define layout
        setLayout(null);
        
        //Set positions and sizes of the components
        title.setBounds(10, 10, 120, 23);
        qntLabel.setBounds(10, 33, 70, 23);
        qntUI.setBounds(75, 33, 50, 23);
        buyButton.setBounds(10, 67, 120, 23);
    }
}

class ShopItemFrame extends JPanel{
    public ShopItemFrame(Item item){
        //Create components
        JLabel title = new JLabel();
        JPanel ingredientsPanel = new JPanel();
        JScrollPane ingredientsScroll = new JScrollPane(ingredientsPanel);
        JLabel resourceLabel = new JLabel();
        JButton buyButton = new JButton();
        
        //Set frame
        setSize(185, 200);
        setBorder(BorderFactory.createBevelBorder(0));
        
        //Set texts and tooltips
        title.setText(item.name);
        resourceLabel.setText("Recursos Necessários:");
        title.setToolTipText(item.name);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        buyButton.setText("Comprar");
        
        //Add and show the components
        add(title);
        add(ingredientsScroll);
        add(buyButton);
        add(resourceLabel);
        title.setVisible(true);
        ingredientsScroll.setVisible(true);
        ingredientsPanel.setVisible(true);
        resourceLabel.setVisible(true);
        buyButton.setVisible(true);
        
        //Fill ingredientsScroll
        ingredientsPanel.setLayout(null);
        FillScrollPanel(ingredientsPanel, item);
        
        //Add action listeners to buttons
        buyButton.addActionListener((ActionEvent ae) -> {
            item.CraftItem();
        });
        
        //Define layout
        setLayout(null);
        
        //Set sizes
        title.setBounds(10, 10, 165, 23);
        resourceLabel.setBounds(10, 33, 165, 23);
        ingredientsScroll.setBounds(10, 53, 165, 110);
        buyButton.setBounds(10, 170, 165, 23);
    }
    
    //Fills the scrolling panel of the UI
    private void FillScrollPanel(JPanel panelToFill, Item item){
        int cont = 0;
        
        //Create UI for cost
        if(item.recipe.cost != null){
            CreateIngredientLabel("Temeres Delatados: ", "TD$ "+Utils.stringifyBD(item.recipe.cost), panelToFill, cont);
            cont++;
        }
        
        //Create UI for potatoes
        for (Recipe.PotatoI potato : item.recipe.potatoes) {
            CreateIngredientLabel("Batata "+potato.potato.name+":", "Qnt: " + potato.qnt, panelToFill, cont);
            cont++;
        }
        
        
        //Create UI for items
        for (Recipe.ItemI itm : item.recipe.items) {
            CreateIngredientLabel(itm.item.name+":", "Qnt: "+itm.qnt, panelToFill, cont);
            cont++;
        }
        
        //
        panelToFill.setPreferredSize(new Dimension(130, cont*40));
    }
    
    private void CreateIngredientLabel(String name, String cost, JPanel panel, int cont){
        //Create components
        JLabel label = new JLabel();
        JLabel amount = new JLabel();
        
        //Set texts
        label.setText(name);
        label.setToolTipText(name);
        amount.setText(cost);
        amount.setToolTipText(cost);
        
        //Add and show
        panel.add(label);
        panel.add(amount);
        label.setVisible(true);
        amount.setVisible(true);
        
        //Set bounds
        label.setBounds(5, cont*40, 120, 23);
        amount.setBounds(20, 15+cont*40, 120, 23);
    }
}