import java.util.Calendar;
import java.util.Locale;

/**
 * Created by nizamutdinovas on 15.01.2016.
 */
public class Friday {
    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar.getTime());
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 13);
        System.out.println(calendar.getTime());
        String str = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.US);
        System.out.println(str);
    }
}
