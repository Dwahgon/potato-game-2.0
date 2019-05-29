package potatoGame;

import potatoGame.userInterface.MainFrame;

public class ExecuteGame {
    private static PlayerStats currentPlr;
    private static MainFrame mainFrame;
    private static GameContent gameContent;
    
    public static void main(String[] args) {
	currentPlr = new PlayerStats(0);
	
	gameContent = new GameContent(currentPlr);

	mainFrame = new MainFrame(gameContent, currentPlr);
	
        int productorDelay = 50, potatoPriceChangeDelay = 6000;
	double currentProductorDelay = System.currentTimeMillis() + productorDelay, currentPotatoPriceChangeDelay = System.currentTimeMillis() + potatoPriceChangeDelay;
	
	while (true) {
	    if(System.currentTimeMillis() >= currentProductorDelay){
		currentProductorDelay = System.currentTimeMillis() + productorDelay;
		processProductors(productorDelay/1000.0);
	    }
            
            if(System.currentTimeMillis() >= currentPotatoPriceChangeDelay){
                currentPotatoPriceChangeDelay = System.currentTimeMillis() + potatoPriceChangeDelay;
                changePotatoPrice();
            }
	}
    }

    public static PlayerStats getCurrentPlr() {
	return currentPlr;
    }
    
    private static void processProductors(double time){
	gameContent.getProductors().forEach(productor ->{
	    productor.processProductor(time);
	    mainFrame.getProductorTab().updateProductorProgress(productor);
	});
    }
    
    private static void changePotatoPrice(){
        gameContent.getPotatoes().forEach(potato ->{
            potato.changePrice();
        });
    }
}
