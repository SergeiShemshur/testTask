import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class CalendarUtils {

    private static final String COLOR_RESET = "\u001B[0m";
    private static final String COLOR_WEEKEND = "\u001B[33m";
    private static final String COLOR_TODAY = "\u001B[36m";
    private static final String COLOR_WORKDAY = "\u001B[30m";
    private static final String COLOR_MONTH = "\u001B[35m";

    private CalendarUtils() {
    }

    public static void printMonth(LocalDate currentDate) {
        int today = currentDate.getDayOfMonth();
        LocalDate tempDate;

        System.out.printf(COLOR_MONTH + "\n%21s\n" + COLOR_RESET, currentDate.getMonth());
        printNameDayOfWeek();
        printIndent(currentDate);

        for (int i = 1; i <= currentDate.getMonth().length(currentDate.isLeapYear()); i++) {
            tempDate = currentDate.withDayOfMonth(i);
            if (today == tempDate.getDayOfMonth()) {
                printDay(COLOR_TODAY, tempDate);
            } else {
                if (isWeekend(tempDate)) {
                    printDay(COLOR_WEEKEND, tempDate);
                } else
                    printDay(COLOR_WORKDAY, tempDate);
            }
        }
    }

    public static void printNameDayOfWeek() {
        DayOfWeek[] days = DayOfWeek.values();
        for (DayOfWeek day : days) {
            System.out.printf("%5s", day.getDisplayName(TextStyle.SHORT, Locale.UK));
        }
        System.out.println();
    }

    private static void printIndent(LocalDate dateNow) {
        LocalDate indent = dateNow.withDayOfMonth(1);
        if (!indent.getDayOfWeek().equals(DayOfWeek.MONDAY))
            while (!indent.getDayOfWeek().equals(DayOfWeek.MONDAY)) {
                indent = indent.minusDays(1);
                System.out.print("     ");
            }
    }

    public static boolean isWeekend(LocalDate localDate) {
        DayOfWeek day = localDate.getDayOfWeek();
        return (day.equals(DayOfWeek.SATURDAY) || day.equals(DayOfWeek.SUNDAY));
    }

    private static void printDay(String ansiColor, LocalDate localDate) {
        System.out.printf(ansiColor + "%5d" + COLOR_RESET, localDate.getDayOfMonth());
        if (localDate.getDayOfWeek().equals(DayOfWeek.SUNDAY))
            System.out.println();
    }
}