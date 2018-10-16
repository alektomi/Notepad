package notepad;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Reminder extends Alarm implements Expirable{ //nasleduet ot Alarm v tom chisle i Expirable. Zdesj tolko dlja logiki i ne neset nagruzki.
    private LocalDate date;


    @Override
    public void askQuestions() {
        super.askQuestions(); // atnākot uz šo, java pajautās note tekstu no Note, jo ask question nasleduetsja. Potom sprosit datu i vremja
        System.out.println("Enter reminder date: ");
        date = Main.askDate();
    }

    @Override
    public boolean hasSubstring(String str) {
        return super.hasSubstring(str) // super pozvoljaet bratj metod u predka.
                || date.format(Main.DATE_FORMATTER).contains(str);// beret datu i formatiruet ee iz daty v stroku i zatem ishhe to cto user hochet;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }


    @Override
    public String toString() {
        return "Reminder " +
                "id: " + getId() + "; " +
                "note: " + getNote() + "; " +
                "Date: " + date.format(Main.DATE_FORMATTER) + "; " +
                "Time: " + getTime().format(Main.TIME_FORMATTER);
    }

    @Override
    public boolean isExpired() {
        LocalTime time = getTime();
        LocalDateTime dt = LocalDateTime.of(date, time);
        LocalDateTime now = LocalDateTime.now();
        return now.isAfter(dt);
    }

}
