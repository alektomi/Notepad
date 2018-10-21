package notepad;

import java.time.LocalDate;

public class Person extends Record implements withBirthDay { // extends nasleduet vse iz Record, a Person ego rasshiraet.
    private String name;
    private String surname;
    private String phone;
    private String email;
    private LocalDate birthday;


    // tā kā mēs norobežojām mainīgās vērtības mums jānodefinē getters un setters.
// ar labo peles taustinu izsaucam Generate -> Setters and Getters -> iekāsojām tās vērtības kurām jāizveido getters un setters.


    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // šeit ielikām kursoru un ar labo taustiņu Generate -> toString -> iekrāsojām visu un Ok.
    // tas ir speciālais metods, kas parādā kā objektam jāformatējas stringā.
    // bez sīs metodes mēs redzētu tikai contakta ID
    @Override
    public String toString() {
        return "Person " +
                "id: " + getId() +
                ", Name: " + name +
                ", Surname: " + surname +
                ", phone: " + phone +
                ", e-mail: " + email +
                ", birthday: " + birthday.format(Main.DATE_FORMATTER);
    }

    @Override
    public boolean hasSubstring(String str) { // šīs bolean pārbauda vai tas ko ievadīja findā atrodas kaut vienā no atribūtiem
        return name.contains(str)
                || surname.contains(str)
                || phone.contains(str)
                || email.contains(str)
                || birthday.format(Main.DATE_FORMATTER).contains(str);
    }

    @Override
    public void askQuestions() {
        System.out.print("Insert contact name: ");
        name = Main.askString();
        System.out.print("Insert contact surname: ");
        surname = Main.askString();
        System.out.print("Insert contact phone number: ");
        phone = Main.insertPhone();
        System.out.print("Insert contact e-mail address: ");
        email = Main.askString();
        System.out.print("Insert contact birthday date: ");
        birthday = Main.askDate();
    }

    @Override
    public boolean hasBirthday() {
        LocalDate date = LocalDate.now();
        return birthday.isEqual(date);
    }
}
