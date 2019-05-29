package potatoGame;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class Utils<T extends Number> {
    public static String getFormattedMoney(BigDecimal money){
       return format(money);
    }
    
    public static String getFormattedBigDecimal(BigDecimal bd){
        return new DecimalFormat("#,###,###,##0.00").format(bd);
    }
    
    private static String format(BigDecimal quantity){
        return "TD$ " + new DecimalFormat("#,###,###,##0.00").format(quantity);
    }
            
}
