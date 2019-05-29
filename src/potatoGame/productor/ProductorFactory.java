package potatoGame.productor;

import potatoGame.productList.ProductList;

public class ProductorFactory extends ProductorRequirements{
    public ProductorFactory(ProductorStats paramProductorStats, ProductList paramBaseCost, ProductList paramIncrementCost){
	super(paramProductorStats, paramBaseCost, paramIncrementCost);
    }
    
    public void build(){
	if(!getCurrentRequirement().removeItems()) return;
	
	productorStats.setLevel(productorStats.getLevel() + 1);
    }
}
