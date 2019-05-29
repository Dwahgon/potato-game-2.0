package potatoGame.productList;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class BigDecimalQuantity implements CalculateNumber<BigDecimal> {
    public static final BigDecimalQuantity INSTANCE = new BigDecimalQuantity();
    private BigDecimalQuantity(){}
    public BigDecimal add(BigDecimal a, BigDecimal b){ return a.add(b); }
    public BigDecimal mult(BigDecimal a, BigDecimal b) { return a.multiply(b); }
    public BigDecimal convertToT(Number n){ return new BigDecimal(n.toString());}
    public String format(BigDecimal a){ return new DecimalFormat("#,###,###,##0.00").format(a); }
}
