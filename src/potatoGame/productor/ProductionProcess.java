package potatoGame.productor;

public class ProductionProcess {

    private final ProductorStats productorStats;
    private final ProductorOutput productorOutput;
    private final ProductorInput productorInput;
    private final double baseTime, timeIncrement;

    private double time;

    public ProductionProcess(ProductorStats paramProductorStats, ProductorOutput paramProductorOutput, ProductorInput paramProductorInput, double paramBaseTime, double paramTimeIncrement) {
	productorStats = paramProductorStats;
	productorOutput = paramProductorOutput;
	productorInput = paramProductorInput;
	baseTime = paramBaseTime;
	timeIncrement = paramTimeIncrement;
	time = baseTime;
    }
    
    public double getProductionTime(){
	return baseTime - timeIncrement * productorStats.getLevel();
    }
    
    public double getProductorProgress(){
	return (getProductionTime()-time)/getProductionTime();
    }
    
    public ProductorOutput getProductorOutput(){return productorOutput;}
    public ProductorInput getProductorInput(){return productorInput;}
    
    public void decrementTime(double decrement){
	time -= decrement;
	
	if (time > 0) return;
	
	resetTime();
	productorInput.inputProducts();
	productorOutput.outputProducts();
    }
    
    public void resetTime(){
	time = baseTime - timeIncrement * productorStats.getLevel();
    }
}
