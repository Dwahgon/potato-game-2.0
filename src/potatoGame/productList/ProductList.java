package potatoGame.productList;

import java.util.ArrayList;
import java.util.Arrays;
import potatoGame.Product;

public class ProductList {
    private ArrayList<ProductListItem> productListItems;
    
    public ProductList(ProductListItem... paramProductListItems){
	ArrayList<ProductListItem> list = new ArrayList<>(Arrays.asList(paramProductListItems));
	
	this.addProductListItems(list);
    }
    
    public ProductList(ProductList productList){
	productListItems = new ArrayList<>();
	
	productList.getProductListItems().forEach(productListItem -> {
	    this.productListItems.add(productListItem.deepClone());
	});
	
	
    }
    
    private void addProductListItems(ArrayList<ProductListItem> productListItems){
	this.productListItems = new ArrayList<>();
	
	productListItems.stream().filter((productListItem) -> (!this.alreadyHasProductListItem(productListItem))).forEachOrdered((productListItem) -> {
	    this.productListItems.add(productListItem);
	});
    }
    
    private boolean alreadyHasProductListItem(ProductListItem paramProductListItem){
	return productListItems.stream().anyMatch((productListItem) -> (productListItem.getProduct() == paramProductListItem.getProduct()));
    }
    
    public ArrayList<ProductListItem> getProductListItems(){
	return this.productListItems;
    }
    
    public ProductListItem getProductListItemByProduct(Product product){
	for (ProductListItem productListItem : this.productListItems){
	    if(productListItem.getProduct() == product)
		return productListItem;
	}
	
	return null;
    }
    
    public void giveItems(){
	productListItems.forEach(productListItem -> {
	    productListItem.getProduct().addQuantity(productListItem.getProductQuantity());
	});
    }
    
    private boolean canRemoveItems(){
	return productListItems.stream().noneMatch((productListItem) -> (productListItem.getProduct().compareQuantities(productListItem.getProductQuantity()) == -1));
    }
    
    public ArrayList<ProductListItem> itemsMissing(){
	ArrayList<ProductListItem> list = new ArrayList<>();
	
	this.productListItems.stream().filter((productListItem) -> (productListItem.getProduct().compareQuantities(productListItem.getProductQuantity()) == -1));
	
	return list;
    }
    
    public void incrementProductList(ProductList productList){
	this.productListItems.forEach(productListItem -> {
	    productListItem.incrementQuantity(productList.getProductListItemByProduct(productListItem.getProduct()).getProductQuantity());
	});
    }
    
    public boolean removeItems(){
	if (!this.canRemoveItems()) return false;
	
	productListItems.forEach(productListItem -> {
	    productListItem.getProduct().subtractQuantity(productListItem.getProductQuantity());
	});
	
	return true;
    }
}

