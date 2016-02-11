import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class Calendar {

    public final String COLOR_RESET = "\u001B[0m";
    public final String COLOR_WEEKEND = "\u001B[33m";
    public final String COLOR_TODAY = "\u001B[36m";
    public final String COLOR_WORKDAY = "\u001B[30m";

    public static void main(String[] args) {
        Calendar c = new Calendar();
        c.printMonth(LocalDate.now());
    }

    public void printMonth(LocalDate dateNow) {
        int today = dateNow.getDayOfMonth();
        boolean isLeapYear = dateNow.isLeapYear();

        System.out.printf("\n%21s\n", dateNow.getMonth());
        printNameDayOfWeak();
        printIndent(dateNow);

        LocalDate tempDate;
        for (int i = 1; i <= dateNow.getMonth().length(isLeapYear); i++) {
            tempDate = dateNow.withDayOfMonth(i);
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

    public void printNameDayOfWeak() {
        DayOfWeek[] days = DayOfWeek.values();
        for (DayOfWeek day : days) {
            System.out.printf("%5s", day.getDisplayName(TextStyle.SHORT, Locale.UK));
        }
        System.out.println();
    }

    private void printIndent(LocalDate dateNow) {
        LocalDate indent = dateNow.withDayOfMonth(1);
        if (!indent.getDayOfWeek().equals(DayOfWeek.MONDAY))
            while (!indent.getDayOfWeek().equals(DayOfWeek.MONDAY)) {
                indent = indent.minusDays(1);
                System.out.print("     ");
            }
    }

    public boolean isWeekend(LocalDate localDate) {
        DayOfWeek day = localDate.getDayOfWeek();
        return (day.equals(DayOfWeek.SATURDAY) || day.equals(DayOfWeek.SUNDAY));
    }

    private void printDay(String ansiColor, LocalDate localDate) {
        System.out.printf(ansiColor + "%5d" + COLOR_RESET, localDate.getDayOfMonth());
        if (localDate.getDayOfWeek().equals(DayOfWeek.SUNDAY))
            System.out.println();
    }
}