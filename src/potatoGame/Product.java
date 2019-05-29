package potatoGame;

public interface Product {
    public void addQuantity(Number quantity);
    public void subtractQuantity(Number quantity);
    public int compareQuantities (Number quantity);
    public Number getQuantity();
    public String getQuantityString();
    public String getQuantityPrefix();
    public String getName();
}
