package potatoes;
import java.math.BigDecimal;
import projeto1.Potatoes;

public class SweetPotato extends Potatoes{
    public SweetPotato(){
        //Define new values
        this.name = "Doce";
        this.normalPrice = new BigDecimal("20");
        this.currentPrice = new BigDecimal("20");
        this.priceRange = new BigDecimal("10");
        
        //Initialize the UI
        this.InitializeUI();
    }
}
