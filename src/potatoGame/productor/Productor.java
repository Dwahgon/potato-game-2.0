package potatoGame.productor;

public class Productor {
    private final ProductorStats productorStats;

    private final ProductorFactory productorFactory;
    private final ProductorLevel productorLevel;
    private final ProductionProcess productionProcess;

    private boolean active;
    
    public Productor(ProductorStats productorStats, ProductorLevel productorLevel, ProductorFactory productorFactory, ProductionProcess productionProcess){
	this.productorStats = productorStats;
	
	this.productorLevel = productorLevel;
	this.productorFactory = productorFactory;
	this.productionProcess = productionProcess;
	
	this.active = true;
    }
    
    public ProductorStats getProductorStats() {return productorStats;}
    public ProductorFactory getProductorFactory() {return productorFactory;}
    public ProductorLevel getProductorLevel() {return productorLevel;}
    public ProductionProcess getProductionProcess() {return productionProcess;}

    public void buildProductor(){
	productorFactory.build();
    }
    
    public void upgradeProductor(){
	productorLevel.upgrade();
    }
    
    public void processProductor(double time){
	if (!active) return;
	
	
	productionProcess.decrementTime(time);
    }
    
    public boolean isActive(){
	return active;
    }
    
    public void setActive(boolean bln){
	active = bln;
	
	if (active) return;
	
	productionProcess.resetTime();
    }
}
