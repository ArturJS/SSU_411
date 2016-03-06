import java.util.*;

/**
 * Created by Артур on 29.01.2016.
 */
public class Friday_part2 {
    public static void main(String[] args) {
        int month = ReadMonth();
        int quantity = 0;
        int friday = 6;
        ArrayList list = new ArrayList();
        Calendar calendar;

        for (int i = 1800; i < 2016; i++) {
            calendar = new GregorianCalendar(i, month - 1, 13);
            if (friday == calendar.get(Calendar.DAY_OF_WEEK)) {
                list.add(calendar.get(Calendar.YEAR));
                quantity++;
            }
        }

        System.out.println("Count of years = " + quantity);
        System.out.println("List of years with friday 13 in this month:");

        for (int i = 0; i < quantity; i++) {
            System.out.println(Integer.parseInt(list.get(i).toString()));
        }

    }

    private static int ReadMonth() {
        boolean errorFlag;
        int month = 0;
        String line;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.print("Type number of month: ");
            line = scanner.nextLine();

            try {
                month = Integer.valueOf(line);

                if (!isBetween(month, 0, 13)) {
                    System.out.println("Month should be between [1..12]!!!");
                    errorFlag = true;
                } else {
                    errorFlag = false;
                }
            } catch (NumberFormatException e) {
                System.out.println("Typed text is not a month!!!");
                errorFlag = true;
            }
        } while (errorFlag);

        return month;
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
