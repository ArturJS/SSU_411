import java.util.*;

/**
 * Created by nizamutdinovas on 15.01.2016.
 */
public class Friday_part1 {
    public static void main(String[] args) {
        int year = ReadYear();

        Calendar calendar;
        ArrayList list = new ArrayList();
        int index;
        int quantity = 0;
        int friday = 6;

        for (int i = 0; i < 12; i++) {
            calendar = new GregorianCalendar(year, i, 13);
            if (friday == calendar.get(Calendar.DAY_OF_WEEK)) {
                quantity++;
                list.add(calendar.get(Calendar.MONTH));
            }
        }

        String[] month;
        month = "January February March April May June July August September October November December".split("\\s+");

        System.out.println("Count of the fridays 13 in " + year + " year = " + quantity);
        System.out.println("List of month with friday 13:");

        for (int i = 0; i < quantity; i++) {
            index = Integer.parseInt(list.get(i).toString());
            System.out.println(month[index]);
        }
    }

    private static int ReadYear() {
        int year = 0;
        boolean errorFlag;
        String line;
        Scanner scanner;

        do {
            scanner = new Scanner(System.in);
            System.out.print("Type year: ");
            line = scanner.nextLine();
            try {
                year = Integer.valueOf(line);
                if (!isBetween(year, 0, 2017)) {
                    System.out.println("Year should be between [0..2017)!!!");
                    errorFlag = true;
                } else {
                    errorFlag = false;
                }
            } catch (NumberFormatException e) {
                System.out.println("Typed text is not year!!!");
                errorFlag = true;
            }
        } while (errorFlag);

        return year;
    }

    private static boolean isBetween(int x, int a, int b) {
        if (a > b) {
            a ^= b;
            b ^= a;
            a ^= b;
        }

        return x > a && x < b;
    }

}
