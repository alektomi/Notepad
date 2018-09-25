package notepad;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    static Scanner scanner = new Scanner(System.in);
    static List<Person> newContact = new ArrayList<>();


    public static void main(String[] args) {

//        loadContactList(); // load ContactList ir nepieciešams lai info failā nepārrakstās kad mēs veiksim ierakstu un saglabāsim to.

        while (true) { // šeit ieliekām bezgalīgu ciklu, jo
            System.out.println("Enter command:");
            String cmd = scanner.next();
            switch (cmd) {
                case "create":
                    create();
                    break;
                case "list":
                    showList();
                    break;
                case "remove":
                    removeContact();
                    break;
                case "exit":
                    return;
                default:
                    System.out.println("It is not a command!");
            }
        }
    }

    private static void removeContact() {
        System.out.println("Which contact id do you want to delete?");
        int contactID = scanner.nextInt();

    }

    private static void showList() {
        for (Person p : newContact) {
//            System.out.printf("%s %s: %s\n", p.getName(), p.getSurname(), p.getPhone());
            System.out.println(p); // ja sādi, tad būs nesmuki
        }
    }

//    private static void loadContactList() {
//        File file = new File ("contact_list.txt");
//        try (Scanner in = new Scanner(file)) {
//            while (in.hasNext()) {
//                Person contacts = new Person();
//                contacts.getName() = in.next();
//                contacts.getSurname() = in.next();
//                contacts.getPhone() = in.next();
//                newContact.add(contacts);
//            }
//        }
//    }

    private static void create() {
        System.out.println("Insert contact name");
        String name = scanner.next();
        System.out.println("Insert contact surname");
        String surname = scanner.next();
        System.out.println("Insert contact phone number");
        String phone = scanner.next();

        // ievadītu info mēs saglabājam mainīgajā "p" un to pievienojam Listam

        Person p = new Person(); // kad izsaucām šo metodi, mēs reāli izsaucam konstruktoru
        p.setName(name);
        p.setSurname(surname);
        p.setPhone(phone);
        newContact.add(p); // šī rinda saglabā ierakstus Listā

        System.out.println(p); // string lai parādītu uz ekrāna to ko esam ierakstījuši

        saveContactToFile();
    }

    private static void saveContactToFile() {
        File file = new File("contact_list.txt");
        try (PrintWriter out = new PrintWriter(file)) {
            for (Person r : newContact) {
                out.printf("%s %s %s\r\n", r.getName(), r.getSurname(), r.getPhone());
            }
        } catch (IOException e) {
            System.out.println("Cannot save to file");
        }
    }
}
ffff

