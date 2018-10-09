package notepad;

public abstract class Record {
    private static int count = 0;
    private int id; // private norobežo pieeju šai mainīgai vērtībai no citām klasēm.

    public Record() { // tas ir konstruktors. saucas ar lielo burtu, tā kā klase. šajā gadījumā mēs pārnesam viņu no Person tapēc pārde'vejam par Record
        count++;
        id = count;
    }

    public abstract boolean hasSubstring(String str); //u ljubogo recorda dolzhen bytj abstraktnyj metod substring.

    public abstract void askQuestions();

    public int getId() {
        return id;
    }
}
