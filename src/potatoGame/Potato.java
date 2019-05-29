package potatoGame;

import java.math.BigDecimal;
import potatoGame.userInterface.PotatoTabItem;

public class Potato implements Product{
    private int quantity;
    private BigDecimal currentPrice;
    
    private final BigDecimal basePrice, priceRange;
    private final String name;
    private PotatoTabItem potatoTabItem;
    
    public Potato(String name, double basePrice, double priceRange){
	this.name = name;
	this.quantity = 0;
        this.basePrice = new BigDecimal(basePrice);
        this.priceRange = new BigDecimal(priceRange);
	this.currentPrice = new BigDecimal(basePrice);
    }
   
    public BigDecimal getCurrentPrice() {return currentPrice;}
    public Number getQuantity() {return this.quantity;}
    public String getQuantityPrefix() {return "x";}
    public String getQuantityString() {return this.quantity + this.getQuantityPrefix();}
    public String getName() {return name;}
    
    public void addQuantity(Number paramQuantity) {
	quantity += paramQuantity.intValue();
	updatePotatoTabItem();
    }
    
    public void subtractQuantity(Number paramQuantity){
	quantity -= paramQuantity.intValue();
	updatePotatoTabItem();
    }
    
    public int compareQuantities (Number quantity) {
	return Integer.compare(this.quantity, quantity.intValue());
    }
    
    public void setPotatoTabItem(PotatoTabItem pti){
	potatoTabItem = pti;
    }
    
    private void updatePotatoTabItem(){
	if(potatoTabItem != null)
	    potatoTabItem.updateLabel();
    }
    
    public void buyPotato(int paramQuantity){
	if(new BigDecimal(ExecuteGame.getCurrentPlr().getMoney().getQuantity().toString()).compareTo(currentPrice.multiply(new BigDecimal(paramQuantity))) == -1) return;
	ExecuteGame.getCurrentPlr().getMoney().subtractQuantity(currentPrice.multiply(new BigDecimal(paramQuantity)));
	addQuantity(quantity);
    }
    
    public void sellPotato(int paramQuantity){
	if(quantity < paramQuantity) return;
	
	ExecuteGame.getCurrentPlr().getMoney().addQuantity(currentPrice.multiply(new BigDecimal(paramQuantity)));
	subtractQuantity(paramQuantity);
    }
    
    public void changePrice(){
        BigDecimal diference = new BigDecimal(Math.random() - 1).multiply(priceRange);
        
        currentPrice = basePrice.add(diference);
        updatePotatoTabItem();
    }
}
