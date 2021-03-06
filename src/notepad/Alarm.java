package notepad;

import java.time.LocalTime;

public class Alarm extends NoteText implements Expirable{ // s implements my objavljaem mnozhestvennoe nasledovanie. Posle implemetns interfeisy mozhno perechisljat' cherez zapatuju
    private LocalTime time;

    @Override
    public void askQuestions() {
        super.askQuestions();
        System.out.println("Enter reminder time: ");
        time = Main.askTime();
    }

    @Override
    public boolean hasSubstring(String str) {
       return super.hasSubstring(str)
        || time.format(Main.TIME_FORMATTER).contains(str);
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
                "id: " + getId() + "; " +
                "text: " + getNote() + "; " +
                "Time: " + time.format(Main.TIME_FORMATTER);
    }

    @Override
    public boolean isExpired() {
        LocalTime now = LocalTime.now();
        return time.isBefore(now);
    }

}
