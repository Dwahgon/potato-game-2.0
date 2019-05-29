package potatoGame;

public class PlayerStats {
    private Government government;
    
    private final Money money;
    
    public PlayerStats(double startMoney){
        this.money = new Money("Temeres Delatados", 0);
    }
    
    public Money getMoney(){return this.money;}
}
