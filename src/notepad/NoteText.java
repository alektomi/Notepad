package notepad;

public class NoteText extends Record {
    private String note;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "NoteText " +
                "id: " + getId() + ", " +
                "Note: " + note ;
    }

    @Override
    public boolean hasSubstring(String str) {
        return note.contains(str);
    }

    @Override
    public void askQuestions() {
        System.out.println("Take a note: ");
        note = Main.askString();
    }
}
