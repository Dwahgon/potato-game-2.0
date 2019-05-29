package potatoes;
import java.math.BigDecimal;
import projeto1.Potatoes;

public class NormalPotato extends Potatoes {
    public NormalPotato() {
        //Define new values
        this.name = "Normal";
        this.normalPrice = new BigDecimal("5");
        this.currentPrice = new BigDecimal("5");
        this.priceRange = new BigDecimal("2");
        
        //Initialize the UI
        this.InitializeUI();
    }
}
