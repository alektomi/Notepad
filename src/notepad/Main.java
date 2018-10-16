package notepad;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class Main {
    public final static String DATE_FORMAT = "dd.MM.yyyy"; // Zadaem format daty; public = dostupnaja vsem; final = tol'ko zdesj my zadaem znachenija; UPPER case = t.k. jeto konstanta
    public final static DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT); // formater dat
    public final static String TIME_FORMAT = "HH:mm";
    public final static DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern(TIME_FORMAT); // formater vremja


    static Scanner scanner = new Scanner(System.in);
    static Map<Integer, Record> recordList = new LinkedHashMap<>();


    public static void main(String[] args) {

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
                case "reminder":
                case "cr":
                    createReminder();
                    break;
                case "list":
                    showList();
                    break;
                case "find":
                    find();
                    break;
                case "show":
                    showByID();
                    break;
                case "help":
                    help();
                    break;
                case "remove":
                    removeContact();
                    break;
                case "alarm":
                    createAlarm();
                    break;
                case "expired":
                case "ex":
                    showExpired();
                    break;
                case "exit":
                    return;
                default:
                    System.out.println("It is not a command! Use command help to get more information.");
            }
        }
    }

    private static void showExpired() {
        for (Record r : recordList.values()) {
            if (r instanceof Expirable) {
                Expirable e = (Expirable) r; //v skobkah ja nasilno podtverzhdaju, chto r vsegda Expirable
                if (e.isExpired()) {
                    System.out.println(r);
                }
            }
        }
    }

    private static void createAlarm() {
        var alarm = new Alarm();
        addRecord(alarm);
    }


    private static void showByID() {
        System.out.print("Insert ID for value: ");
        int id = scanner.nextInt();

//        Record record = recordList.get(id);
//        System.out.println(id);

        System.out.println(recordList.get(id)); // cikl ne nuzhen, tak kak my rabotaem s map i associativnym listom
    }


    private static void createReminder() {
        var reminder = new Reminder();
        addRecord(reminder);
    }


    private static void find() {
        System.out.println("What to look for?");
        String str = askString();

        for (Record r : recordList.values()) {
            if (r.hasSubstring(str)) {
                System.out.println(r);
            }
        }
    }


    private static void createNote() {
        NoteText note = new NoteText();
        addRecord(note);
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
        recordList.remove(contactID);
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
        for (Record p : recordList.values()) {
            System.out.println(p);
        }
    }


    private static void createPerson() {
        Person person = new Person(); // kad izsaucām šo metodi, mēs reāli izsaucam konstruktoru
        addRecord(person);
    }


    private static void addRecord(Record record) { // šī metode aizvieto tās rindas kodā kuras ir vienādas un atkārtojas
        //vienā vietā kodā mēs iekrāsojām rindas kuras mēs vēlamies pārtaisīt par metodi un ar Ctrl+Alt+M
        //piešķiram nosaukumu metodei;
        //izvēlamies ka tas ir Record
        //lai būtu saprotamāk, mainām sākotnējās mainīgās vērtību nosaukumu uz, kā šajā gadījumā, uz person
        record.askQuestions();
        recordList.put(record.getId(), record); // šī rinda saglabā ierakstus Listā
        System.out.println("You have created a record");
        System.out.println(record); // string lai parādītu uz ekrāna to ko esam ierakstījuši
    }


    public static String askString() {
        var result = new ArrayList<String>(); // VAR nozīmē variable. Java pati sapratīs, ka tas ir saraksts.
        var word = scanner.next();
        if (word.startsWith("\"")) {
            do {
                result.add(word);
                if (word.endsWith("\"")) {
                    String str = String.join(" ", result);// delimiter = razdelitelj; join = perevodit spisok v stroku
                    return str.substring(1, str.length() - 1);  // ar šo mēs dzēšam nevajadzīgas iekavas. 1 = pirmās ieklavas vārda sākumā; aiz komata mēs atrodam ieraksta garumu un atņemam pēdējās iekavas
                }
                word = scanner.next(); // esli kavychka zakanchivaets, to my vozvrashhaem rezultat; esli net, sprashivaem sledujushee slovo
            } while (true);
        } else {
            return word; // atgriezt to, ko ievadīja juzers.
        }
    }


    public static String insertPhone() {
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


    public static LocalDate askDate() {
        String d;
        LocalDate date;
        do {
            try {
                d = askString();
                date = LocalDate.parse(d, DATE_FORMATTER);
            } catch (DateTimeParseException e) {
                System.out.println("Wrong date format. You must insert date in dd.MM.yyyy format.");
                continue;
            }
            return date;
        } while (true);
    }


    public static LocalTime askTime() {
        String t;
        LocalTime time;
        do {
            try {
                t = askString();
                time = LocalTime.parse(t, TIME_FORMATTER);
            } catch (DateTimeParseException e) {
                System.out.println("Wrong time format. You must insert time in HH:mm format.");
                continue;
            }
            return time;
        } while (true);
    }
}






