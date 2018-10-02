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
        return "NoteText{" +
                "id=" + getId() + "," +
                "note='" + note + '\'' +
                '}';
    }

    @Override
    public boolean hasSubstring(String str) {
        return note.contains(str);
    }
}
