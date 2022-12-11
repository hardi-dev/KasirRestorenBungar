public class Order {
    Menu menu;
    Integer qty;
    double total;

    public Order(Menu menu, Integer qty, double total){
        this.menu = menu;
        this.total = total;
        this.qty = qty;
    }

    public double getTotal() {
        return total;
    }

    public Integer getQty() {
        return qty;
    }

    public Menu getMenu(){
        return menu;
    }
}
