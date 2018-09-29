package notepad;

public class Person {
    private static int count = 0;
    private int id; // private norobežo pieeju šai mainīgai vērtībai no citām klasēm.
    private String name;
    private String surname;
    private String phone;
    private String email;

    public Person() { // tas ir konstruktors. saucas ar lielo burtu, tā kā klase
        count++;
        id = count;
    }

    // tā kā mēs norobežojām mainīgās vērtības mums jānodefinē getters un setters.
// ar labo peles taustinu izsaucam Generate -> Setters and Getters -> iekāsojām tās vērtības kurām jāizveido getters un setters.

    public int getId() {
        return id;
    }

//    public void setId(int id) { // šo metodi dzēšam, jo mēs aizliedzam, lai manuāli varētu iedot ID.
//        this.id = id;
//    }

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

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    // šeit ielikām kursoru un ar labo taustiņu Generate -> toString -> iekrāsojām visu un Ok.
    // tas ir speciālais metods, kas parādā kā objektam jāformatējas stringā.
    // bez sīs metodes mēs redzētu tikai contakta ID
    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", count='" + count + '\'';
    }
}
