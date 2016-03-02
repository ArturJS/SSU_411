import java.util.*;

/**
 * Created by Артур on 29.01.2016.
 */
public class Friday_part2 {
    public static void main(String[] args) {
        boolean errorFlag;
        int month = 0;
        String line;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.print("Type month: ");
            line = scanner.nextLine();

            try {
                month = Integer.valueOf(line);

                if (!(month > 0 && month < 13)) {
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

        int quantity = 0;
        int friday = 6;
        ArrayList list = new ArrayList();
        for (int i = 1800; i < 2016; i++) {
            Calendar calendar = new GregorianCalendar(i, month - 1, 13);
            int number = calendar.get(Calendar.DAY_OF_WEEK);
            if (friday == number) {
                list.add(calendar.get(Calendar.YEAR));
                quantity++;
            }
        }
        System.out.println("List of years with friday 13 in this month:");
        for (int i = 0; i < quantity; i++) {
            Object value = list.get(i);
            int a = Integer.parseInt(value.toString());
            System.out.println(a);
        }
        System.out.println("Count of years = " + quantity);

    }
}
