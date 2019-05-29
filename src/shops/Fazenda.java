package shops;
import java.math.BigDecimal;
import projeto1.Building;
import projeto1.Potatoes;

public class Fazenda extends Building {
    public Fazenda() {
        //Define new values
        buildingName = "Fazenda";
        potatoType = Potatoes.allPotatoes[0];
        bPrice = BigDecimal.ZERO;
        bAdditionalPrice = BigDecimal.ONE;
        bAdditionalPricePerPurchase = new BigDecimal(2);
        potatoProduction = 20;
        
        CreateBuildingPanel();
    }
}
