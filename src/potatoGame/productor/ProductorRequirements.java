package potatoGame.productor;

import potatoGame.productList.ProductList;

abstract class ProductorRequirements {
    protected final ProductList baseCost, costIncrement;
    protected final ProductorStats productorStats;

    public ProductorRequirements(ProductorStats paramProductorStats, ProductList paramBaseCost, ProductList paramCostIncrement){
	productorStats = paramProductorStats;
	baseCost = paramBaseCost;
	costIncrement = paramCostIncrement;
    }
    
    public ProductList getCurrentRequirement(){
	return createIncrementedBaseCost(productorStats.getLevel());
    }
    
    protected ProductList createIncrementedBaseCost(int multiplier){
	ProductList pl = new ProductList(baseCost);
	
	
	pl.getProductListItems().forEach((pli) -> {
	    pli.incrementQuantity(costIncrement.getProductListItemByProduct(pli.getProduct()).getProductQuantity(), multiplier);
	});
	
	return pl;
    }
    
    public ProductorStats getProductorStats() {return productorStats;}
    public ProductList getBaseCost() {return baseCost; }
    public ProductList getIncrementCost() {return costIncrement;}
}
