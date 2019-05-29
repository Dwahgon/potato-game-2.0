package potatoGame.productList;

import java.text.DecimalFormat;
import potatoGame.Product;

public class ProductListItem <T extends Number>{
    private T productQuantity;
    
    private final Product product;
    private final CalculateNumber<T> quantityCalculator;
    
    public ProductListItem(Product product, T productQuantity, CalculateNumber<T> quantityType){
	this.product = product;
	this.productQuantity = productQuantity;
	this.quantityCalculator = quantityType;
    }
    
    public Product getProduct(){return product;}
    public T getProductQuantity(){return productQuantity;}
    public String getProductQuantityString(){return product.getQuantityPrefix() + quantityCalculator.format(productQuantity);}
    
    public ProductListItem deepClone(){
	return new ProductListItem<>(this.product, this.productQuantity, this.quantityCalculator);
    }
    
    public void incrementQuantity(T quantity){
	productQuantity = quantityCalculator.add(productQuantity, quantityCalculator.convertToT(quantity));
    }
    
    public void incrementQuantity(T quantity, T multiplier){
	productQuantity = quantityCalculator.add(productQuantity, quantityCalculator.mult(quantityCalculator.convertToT(quantity), quantityCalculator.convertToT(multiplier)));
    }
    
    public void multiplyQuantity(T multiplier){
	productQuantity = quantityCalculator.mult(productQuantity, quantityCalculator.convertToT(multiplier));
    }
}
