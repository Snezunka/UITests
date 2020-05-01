package helpers;

import java.time.LocalDate;
import java.time.Month;

public class DateHelper {

    public String getTodayDate() {
        LocalDate currentDate = LocalDate.now();
        Month month = currentDate.getMonth();
        int day = currentDate.getDayOfMonth();
        return month.toString().toLowerCase() + "_" + day;
    }

    public String getTomorrowMonth() {
        LocalDate tomorrowDate = LocalDate.now().plusDays(1);
        Month month = tomorrowDate.getMonth();
        return month.toString().toLowerCase();
    }

    public int getTomorrowDay() {
        LocalDate tomorrowDate = LocalDate.now().plusDays(1);
        return tomorrowDate.getDayOfMonth();
    }
}
