public class Main {

    public static void main(String[] args) {
        Kasir kasir = new Kasir();
        kasir.printHeader();

        kasir.lineBreak();
        kasir.printListOfMenu();

        kasir.lineBreak();
        kasir.printOrderForms();

        kasir.lineBreak();
        kasir.printOrderList();

        kasir.printTotal();
        kasir.printFooter();

    }

}
