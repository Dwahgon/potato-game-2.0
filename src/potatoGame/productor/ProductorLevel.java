package potatoGame.productor;

import potatoGame.productList.ProductList;

public class ProductorLevel extends ProductorRequirements{
    private final int maxLevel;
    
    public ProductorLevel(int maxLevel, ProductorStats paramProductorStats, ProductList baseUpgradeCost, ProductList incrementUpgradeCost){
	super(paramProductorStats, baseUpgradeCost, incrementUpgradeCost);
	
	this.maxLevel = maxLevel;
    }
    
    public int getMaxLevel(){return maxLevel;}
    
    public void upgrade(){
	if(productorStats.getLevel() == maxLevel) return;
	if(!getCurrentRequirement().removeItems()) return;
	
	productorStats.setLevel(productorStats.getLevel() + 1);
    }
}
