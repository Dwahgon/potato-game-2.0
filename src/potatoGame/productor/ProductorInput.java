package potatoGame.productor;

import potatoGame.productList.ProductList;
import potatoGame.productList.ProductListItem;

public class ProductorInput extends ProductorRequirements {
    public ProductorInput(ProductorStats paramProductorStats, ProductList paramBaseOutput, ProductList paramOutputIncrement){
	super(paramProductorStats, paramBaseOutput, paramOutputIncrement);
    }
    
    public ProductorInput(ProductorStats paramProductorStats){
	super(paramProductorStats, new ProductList(), new ProductList());
    }
    
    public ProductList getCurrentRequirement(){
	ProductList pl = createIncrementedBaseCost(productorStats.getLevel());
	
	for (ProductListItem pli : pl.getProductListItems()){
	    pli.multiplyQuantity(productorStats.getQuantity());
	}
	
	return pl;
    }
    
    public void inputProducts(){
	getCurrentRequirement().removeItems();
    }
}
