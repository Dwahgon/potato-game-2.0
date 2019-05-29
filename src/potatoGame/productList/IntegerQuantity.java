package potatoGame.productList;

import java.text.DecimalFormat;

public class IntegerQuantity implements CalculateNumber<Integer> {
    public static final IntegerQuantity INSTANCE = new IntegerQuantity();
    private IntegerQuantity(){}
    public Integer add(Integer a, Integer b){ return a+b; }
    public Integer mult(Integer a, Integer b) { return a * b; }
    public Integer convertToT(Number n){ return n.intValue();}
    public String format(Integer a){ return a.toString(); }
}
