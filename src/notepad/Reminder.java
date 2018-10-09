package notepad;

import java.time.LocalDate;
import java.time.LocalTime;

public class Reminder extends NoteText { //nasleduet ot NoteText, t.k. Reminder privjazan k Note
    private LocalDate date;
    private LocalTime time;

    @Override
    public void askQuestions() {
        super.askQuestions(); // atnākot uz šo, java pajautās note tekstu no Note, jo ask question nasleduetsja. Potom sprosit datu i vremja
        System.out.println("Enter reminder date: ");
        date = Main.askDate();
        System.out.println("Enter reminder time: ");
        time = Main.askTime();
    }

    @Override
    public boolean hasSubstring(String str) {
        return super.hasSubstring(str) // super pozvoljaet bratj metod u predka.
                || date.format(Main.DATE_FORMATTER).contains(str) // beret datu i formatiruet ee iz daty v stroku i zatem ishhe to cto user hochet
                || time.format(Main.TIME_FORMATTER).contains(str);
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Reminder " +
                "id: " + getId() + ", " +
                "note: " + getNote() + ", " +
                "Date: " + date.format(Main.DATE_FORMATTER) + ", " +
                "Time: " + time.format((Main.TIME_FORMATTER)) ;
    }
}
