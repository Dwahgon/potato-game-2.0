package potatoGame.productor;

public class ProductorStats {
    private String name;
    private int quantity, level;
    
    public ProductorStats(String name){
	initClass(name, 0, 1);
    }
    
    public ProductorStats(String name, int paramQuantity, int paramLevel){
	initClass(name, paramQuantity, paramLevel);
    }
    
    private void initClass(String paramName, int paramQuantity, int paramLevel){
	this.name = paramName;
	this.quantity = paramQuantity;
	this.level = paramLevel;
    }

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public int getQuantity() {return quantity;}
    public void setQuantity(int quantity) {this.quantity = quantity; }
    public int getLevel() {return level;}
    public void setLevel(int level) {this.level = level;}
}
