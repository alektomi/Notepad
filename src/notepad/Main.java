package notepad;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static List<Record> recordList = new ArrayList<>(); //Person zamenili na Record


    public static void main(String[] args) {

//        loadContactList(); // load ContactList ir nepieciešams lai info failā nepārrakstās kad mēs veiksim ierakstu un saglabāsim to.

        while (true) { // šeit ieliekām bezgalīgu ciklu, jo
            System.out.println("Enter command (insert -> help for help):");
            String command = scanner.next();
            switch (command) {
                case "createPerson":
                case "cp":
                    createPerson();
                    break;
                case "createNote":
                case "note":
                    createNote();
                    break;
                case "list":
                    showList();
                    break;
                case "find":
                    find();
                    break;
                case "help":
                    help();
                    break;
                case "remove":
                    removeContact();
                    break;
                case "exit":
                    return;
                default:
                    System.out.println("It is not a command! Use command help to get more information.");
            }
        }
    }

    private static void find() {
        System.out.println("What to look for?");
        String str = askString();

        for (Record r : recordList) {
            if (r.hasSubstring(str)){
                System.out.println(r);
            }
        }
    }

    private static void createNote() {
        System.out.println("Take a note: ");
        String note = askString();

        NoteText n = new NoteText();
        n.setNote(note);
        recordList.add(n);

        System.out.println(n);
    }

    private static void help() {
        System.out.println("For new contact creation insert command createPerson;");
        System.out.println("To see the whole list of contacts insert command list;");
        System.out.println("To delete a contact from the List insert command remove;");
        System.out.println("To stop editing insert command exit;");
    }

    // 1. variants:
//    // jāizveido cikls, lai meklē katra kontakta ID un indeksu pēc visa saraksta. kad atrod un izdzēš, cikls beidzās.
    private static void removeContact() {
        System.out.println("Which contact id do you want to delete?");
        int contactID = scanner.nextInt();
        for (int i = 0; i < recordList.size(); i++) {
            Record p = recordList.get(i);
            if (contactID == p.getId()) {
                recordList.remove(i);
                break;
            }
        }
    }

    // 2. variants:
    // metode mazāk efektīva, bet strādā
//    private static void removeContact() {
//        System.out.println("Which contact id do you want to delete?");
//        int contactID = scanner.nextInt();
//for (Person p : recordList) {
//    if (contactID == p.getId()) {
//        recordList.remove(p);
//        break;
//    }
//}

    private static void showList() {

//        int maxLen = findMaxNameLen();

        for (Record p : recordList) {
            System.out.println(p);
//            for (int i = 0; i < (maxLen - (p.getName().length() + p.getSurname().length())); i++) {
//                System.out.print(" ");
//            }
//            System.out.printf("  Phone: %s; E-mail: %s\n", p.getPhone(), p.getEmail());
//            System.out.println(p); // ja sādi, tad būs nesmuki
        }
    }

//    private static int findMaxNameLen() {
//        int result = 0;
//        for (Person p : recordList) {
//            if (result < p.getName().length() + p.getSurname().length()) {
//                result = p.getName().length() + p.getSurname().length();
//            }
//        }
//        return result;
//    }


//    private static void loadContactList() {
//        File file = new File("contact_list.txt");
//        try (Scanner in = new Scanner(file)) {
//            while (in.hasNext()) {
//                Person contacts = new Person();
//                contacts.setName(in.next());
//                contacts.setSurname(in.next());
//                contacts.setPhone(in.next());
//                contacts.setEmail(in.next());
//                recordList.add(contacts);
//            }
//        } catch (IOException e) {
//            System.out.println("Cannot load from file");
//        }
//    }

    private static void createPerson() {
        System.out.print("Insert contact name: ");
        String name = askString();
        System.out.print("Insert contact surname: ");
        String surname = askString();
        System.out.print("Insert contact phone number: ");
        String phone = insertPhone();
        System.out.print("Insert contact e-mail address: ");
        String email = askString();

        // ievadītu info mēs saglabājam mainīgajā "p" un to pievienojam Listam

        Person p = new Person(); // kad izsaucām šo metodi, mēs reāli izsaucam konstruktoru
        p.setName(name);
        p.setSurname(surname);
        p.setPhone(phone);
        p.setEmail(email);
        recordList.add(p); // šī rinda saglabā ierakstus Listā

//        recordList.sort(Comparator.comparing(p0 -> p0.getName()));

        System.out.println(p); // string lai parādītu uz ekrāna to ko esam ierakstījuši

//        saveContactToFile();
    }

    private static String askString() {
        var result = new ArrayList<String>(); // VAR nozīmē variable. Java pati sapratīs, ka tas ir saraksts.
        var word = scanner.next();
        if (word.startsWith("\"")) {
            do {
                result.add(word);
                if (word.endsWith("\"")) {
                    String str = String.join(" ", result);// delimiter = razdelitelj; join = perevodit spisok v stroku
                    return str.substring(1, str.length()-1);  // ar šo mēs dzēšam nevajadzīgas iekavas. 1 = pirmās ieklavas vārda sākumā; aiz komata mēs atrodam ieraksta garumu un atņemam pēdējās iekavas
                }
                word = scanner.next(); // esli kavychka zakanchivaets, to my vozvrashhaem rezultat; esli net, sprashivaem sledujushee slovo
            } while (true);
        } else {
            return word; // atgriezt to, ko ievadīja juzers.
        }
    }

    private static String insertPhone() {
        String insertPhone;

        do {
            insertPhone = scanner.next();
            if (insertPhone.length() > 8 || insertPhone.length() < 8) {
                System.out.println("Phone number must contain 8 digits!");
                continue;
            } else {
                return insertPhone;
            }
        } while (true);
    }

//    private static void saveContactToFile() {
//        File file = new File("contact_list.txt");
//        try (PrintWriter out = new PrintWriter(file)) {
//            for (Person p : recordList) {
//                out.printf("%s %s %s %s\r\n", p.getName(), p.getSurname(), p.getPhone(), p.getEmail());
//            }
//        } catch (IOException e) {
//            System.out.println("Cannot save to file");
//        }
//    }
}


