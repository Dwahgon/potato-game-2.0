package projeto1;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Utils {
    /**
     * 
     * @param num Number
     * @param decimals How many decimals will the number have
     * @return 'num' rounded with 'decimals' decimals 
     */
    public static double round(double num, int decimals){
        //Throw an error if the user wants a negative amount of decimals
        if (decimals < 0) throw new IllegalArgumentException();
        
        //Creates a new BigDecimal, and round it by decimals
        BigDecimal bd = new BigDecimal(num);
        bd = bd.setScale(decimals, RoundingMode.HALF_UP);
        
        //Return the doubled value
        return bd.doubleValue();
    }
    
    /**
     * 
     * @param seconds Time in seconds
     * @return A string formatted in "00:00"
     */
    public static String ConvertToTime(int seconds){
        //Declare variables
        int secs = seconds, mins = 0;
        
        //Convert each 60 seconds into mins
        if(secs >= 60){
            mins = secs/ 60;

            if (mins>0){
                secs -= mins * 60;
            }
        }
        
        //Return the time
        if(secs>9)
            return mins + ":" + secs;
        else
           return mins + ":0" + secs; 
    }
    
    /**
     * Pauses the execution
     * @param secs The time the execution is paused
     */
    public static void waitSeconds(long secs){
        try {
            TimeUnit.SECONDS.sleep(secs);
        } catch (InterruptedException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * 
     * @param bd The BigDecimal you want to stringify
     * @return a String representation of the BigDecimal
     */
    public static String stringifyBD(BigDecimal bd){
        DecimalFormat df2 = new DecimalFormat( "#,###,###,##0.00" );
        
        return df2.format(bd.doubleValue());
    }
    
    public static boolean isInteger(String s) {
        try { 
            Integer.parseInt(s); 
        } catch(NumberFormatException | NullPointerException e) { 
            return false; 
        }
        // only got here if we didn't return false
        return true;
    }
    
    /**
     * 
     * @param bd The BigDecimal you wanto to convert to scientific notation
     * @return A string representation of the scientific notation of the BigDecimal, represented as num x 10^num. Or a normal string representation of the BigDedcimal if the BigDecimal is below 1000
     */
    private static String toScientificNotation(BigDecimal bd){
        if(bd.compareTo(new BigDecimal(1000))>0){
            double firstNum = bd.divide(new BigDecimal(Math.pow(10,(bd.setScale(0, RoundingMode.HALF_UP).toString().length()-3)))).doubleValue();
            int exp = bd.setScale(0, RoundingMode.HALF_UP).toString().length() - 1;

            return Utils.round(firstNum/100.0, 2) + " x 10^"+exp;
        }else
            return bd.setScale(2, RoundingMode.HALF_UP).toString();
    }
    
    
}
