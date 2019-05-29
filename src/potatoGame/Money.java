package potatoGame;

import java.math.BigDecimal;
import potatoGame.userInterface.MoneyFrame;

import java.text.DecimalFormat;
public class Money implements Product {
    private BigDecimal quantity;
    private MoneyFrame moneyFrame;
    private String name;
    
    public Money(String name, Number money){
	this.quantity = new BigDecimal(money.toString());
	this.name = name;
    }
    
    public String getName(){return name;}
    public String getQuantityPrefix() {return "TD$ ";}
    public String getQuantityString() {return getQuantityPrefix() + Utils.getFormattedMoney(quantity); }
    
    private void updateMoneyFrame(){
	if(moneyFrame != null)
	    moneyFrame.update();
    }
    
    public void addQuantity(Number amount){
	this.quantity = this.quantity.add(new BigDecimal(amount.toString()));
	updateMoneyFrame();
    }
    
    public void subtractQuantity(Number amount){
	this.quantity = this.quantity.subtract(new BigDecimal(amount.toString()));
	updateMoneyFrame();
    }
    
    public Number getQuantity(){
	return new BigDecimal(this.quantity.toString());
    }
    
    public int compareQuantities(Number amount){
	return this.quantity.compareTo(new BigDecimal(amount.toString()));
    }
    
    public void setMoneyFrame(MoneyFrame paramMoneyFrame){
	moneyFrame = paramMoneyFrame;
    }
}
