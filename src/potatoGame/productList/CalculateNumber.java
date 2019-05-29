package potatoGame.productList;

public interface CalculateNumber<T extends Number> {
    public T convertToT(Number n);
    public T add(T a, T b);
    public T mult(T a, T b);
    public String format(T a);
}
