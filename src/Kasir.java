import java.util.ArrayList;
import java.util.Scanner;

public class Kasir {

    final int LONGEST_MENU_NAME_COUNT = 23;
    final int LONGEST_MENU_PRICE_COUNT = 8;

    Integer guestCount;
    String guestName;

    Menu nasiGoreng = new Menu("Nasi Goreng Spesial", 9999.99);
    Menu ayamBakar = new Menu("Ayam Bakar Spesial", 12345.67);
    Menu steakSirloin = new Menu("Steak Sirloin Spesial", 21108.40);
    Menu kwetiawSiam = new Menu("Kwetiaw Siram Spesial", 13579.14);
    Menu kambingGuling = new Menu("Kambing Guling Spesial", 98765.43);


    ArrayList<Order> orders = new ArrayList<Order>();

    public Integer getGuestCount() {
        return guestCount;
    }

    public String getGuestName() {
        return guestName;
    }

    public void lineBreak(){
        System.out.println();
    }

    private String createSpacing(String word, int longest){
        String spaces = " ";
        int wordLength = word.length();
        int diff = longest - wordLength;
        return spaces.repeat(diff);
    }


    public void printHeader(){
        Scanner input = new Scanner(System.in);

        System.out.println("Selamat Siang....");

        System.out.print("Pesan untuk berapa orang : ");
        guestCount = input.nextInt();

        System.out.print("Pesan atas nama          : ");
        guestName = input.next();
    }

    public void printListOfMenu(){
        System.out.println("Menu Spesial Hari ini");
        System.out.println("====================== \n");

        printMenuItem(1, nasiGoreng);
        printMenuItem(2, ayamBakar);
        printMenuItem(3, steakSirloin);
        printMenuItem(4, kwetiawSiam);
        printMenuItem(5, kambingGuling);
    }

    private void printMenuItem(int number, Menu item){

        System.out.print(number + ". ");
        System.out.print(item.getName());

        System.out.print(createSpacing(item.getName(), LONGEST_MENU_NAME_COUNT));
        System.out.print("                                  ");

        System.out.print("@ Rp.");
        System.out.print(createSpacing(String.valueOf(item.getPrice()), LONGEST_MENU_PRICE_COUNT));
        System.out.println(item.getPrice());
    }

    public void printOrderForms(){
        System.out.println("Pesanan Anda [Batas pesanan 0-10 porsi]");

        Order orderNasiGoreng = getOrderInput(1, nasiGoreng);
        orders.add(orderNasiGoreng);

        Order orderAyamBakar = getOrderInput(2, ayamBakar);
        orders.add(orderAyamBakar);

        Order orderSteakSirloin = getOrderInput(3, steakSirloin);
        orders.add(orderSteakSirloin);

        Order orderKwetiawSiam = getOrderInput(4, kwetiawSiam);
        orders.add(orderKwetiawSiam);

        Order orderKambingGuling = getOrderInput(5, kambingGuling);
        orders.add(orderKambingGuling);

    }

    private Order getOrderInput(int number, Menu item){

        Scanner input = new Scanner(System.in);

        System.out.print(number + ". ");
        System.out.print(item.getName());
        System.out.print(createSpacing(item.getName(), LONGEST_MENU_NAME_COUNT) + " = ");

        Integer qty = input.nextInt();
        double total = qty * item.getPrice();

        return new Order(item, qty, total);

    }


    public void printOrderList(){
        System.out.println("Pembelian :");

        for (int i = 0; i < orders.size(); i++){
            printOrderItem(i+1, orders.get(i));
        }
    }

    private void printOrderItem(int number, Order order){
        System.out.print(number + ". ");

        String menuName = order.getMenu().getName();
        System.out.print(menuName);

        System.out.print(createSpacing(menuName, LONGEST_MENU_NAME_COUNT));
        System.out.print("       ");

        System.out.print(order.qty + " porsi * Rp. ");

        String price = String.format("%.2f", order.menu.getPrice());
        System.out.print(price);
        System.out.print(createSpacing(price, 10));

        String total = String.format("%.2f", order.total);
        System.out.print("=   Rp. " + createSpacing(total, 13));
        System.out.print(total);
        System.out.print(createSpacing(total, 10));
        System.out.println();
    }

    public void printTotal(){
        double total = 0;
        for(int i = 0; i < orders.size(); i++){
            total += orders.get(i).getTotal();
        }
        String totalStr = String.format("%.2f", total);

        double discount = getDiscount(total);
        String discountStr = String.format("%.2f", discount);

        double totalAfterDisc = total - discount;
        String totalAfterDiscStr = String.format("%.2f", totalAfterDisc);

        double totalPerGuest = totalAfterDisc / getGuestCount();
        String totalPerGuestStr = String.format("%.2f", totalPerGuest);


        System.out.println("=================================================================================");
        System.out.print("Total Pembelian " + " ".repeat(LONGEST_MENU_NAME_COUNT + 18));
        System.out.print("=   Rp. " + createSpacing(totalStr, 13));
        System.out.print(totalStr);
        System.out.print(createSpacing(totalStr, 10));
        System.out.println();

        System.out.print("Disc. 10% <Masa Promosi> " + " ".repeat(LONGEST_MENU_NAME_COUNT + 9));
        System.out.print("=   Rp. " + createSpacing(discountStr, 13));
        System.out.print(discountStr);
        System.out.print(createSpacing(discountStr, 10));
        System.out.println();
        System.out.println("=================================================================================");

        System.out.print("Total Pembelian Setelah disc 10%" + " ".repeat(LONGEST_MENU_NAME_COUNT + 2));
        System.out.print("=   Rp. " + createSpacing(totalAfterDiscStr, 13));
        System.out.print(totalAfterDiscStr);
        System.out.print(createSpacing(totalAfterDiscStr, 10));
        System.out.println();

        System.out.print("Pembelian Per orang <untuk " + getGuestCount() + " orang>"+ " ".repeat(LONGEST_MENU_NAME_COUNT - 1));
        System.out.print("=   Rp. " + createSpacing(totalPerGuestStr, 13));
        System.out.print(totalPerGuestStr);
        System.out.print(createSpacing(totalPerGuestStr, 10));
        System.out.println();

    }

    public double getDiscount(double total){
        return (total*10)/100;
    }

    public void printFooter(){
        System.out.println("=================================================================================");
        System.out.println("                        Terima kasih atas kunjungan Anda...                      ");
        System.out.println("                          ...Tekan ENTER untu keluar...                          ");
    }

}
