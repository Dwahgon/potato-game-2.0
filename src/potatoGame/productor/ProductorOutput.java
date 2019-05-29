package potatoGame.productor;

import potatoGame.productList.ProductList;
import potatoGame.productList.ProductListItem;

public class ProductorOutput extends ProductorRequirements{
    public ProductorOutput(ProductorStats paramProductorStats, ProductList paramBaseOutput, ProductList paramOutputIncrement){
	super(paramProductorStats, paramBaseOutput, paramOutputIncrement);
    }
    
    public ProductList getCurrentRequirement(){
	ProductList pl = createIncrementedBaseCost(productorStats.getLevel());
	
	for (ProductListItem pli : pl.getProductListItems()){
	    pli.multiplyQuantity(productorStats.getQuantity());
	}
	
	return pl;
    }
    
    public void outputProducts(){
	getCurrentRequirement().giveItems();
    }
}
