package notepad;

import java.time.LocalDate;

public class Pet extends Record implements withBirthDay {
    private String name;
    private LocalDate birthday;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Pet ID: " + getId() +
                ", Name: " + name +
                ", Birthday: " + birthday.format(Main.DATE_FORMATTER);
    }


    @Override
    public boolean hasSubstring(String str) {
        return name.contains(str)
                || birthday.format(Main.DATE_FORMATTER).contains(str);
    }

    @Override
    public void askQuestions() {
        System.out.print("Insert Pet name: ");
        name = Main.askString();
        System.out.print("Insert Pet birthday date: ");
        birthday = Main.askDate();
    }

    @Override
    public boolean hasBirthday() {
        LocalDate date = LocalDate.now();
        return birthday.isEqual(date);
    }
}
