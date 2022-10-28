package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Objects;

// Represents an enemy character, and contains the notes created or that character
public class Enemy implements Writable {
    private final String name; // the name of the enemy character
    private final ArrayList<Notes> listOfNotes = new ArrayList<>(); // list of notes against the enemy

    public Enemy(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Notes> getListOfNotes() {
        return listOfNotes;
    }

    //REQUIRES: note with title that does not currently exist within listOfNotes
    //MODIFIES: this
    //EFFECTS: adds a new note to the end of listOfNotes
    public void addNoteToList(Notes note) {
        this.listOfNotes.add(note);
    }

    //REQUIRES: name of note that already exists, new title that does not exist within listOfNotes
    //MODIFIES: this
    //EFFECTS: edits an already existing note
    public void editNote(String name, String title, String paragraph) {
        Notes noteEdit = new Notes("Title", "Paragraph");
        for (int i = 0; i < listOfNotes.size(); i++) {
            if (Objects.equals(listOfNotes.get(i).getTitle(), name)) {
                noteEdit = listOfNotes.get(i);
                i = listOfNotes.size();
            }
        }
        noteEdit.editTitle(title);
        noteEdit.editParagraph(paragraph);
    }

    //REQUIRES: name of the note that already exists
    //MODIFIES: this
    //EFFECTS: deletes a note using the title of the note
    public boolean deleteNote(String name) {
        for (int i = 0; i < listOfNotes.size(); i++) {
            if (Objects.equals(listOfNotes.get(i).getTitle(), name)) {
                listOfNotes.remove(i);
                return true;
            }
        }
        return false;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Enemy Name", name);
        json.put("Notes", notesToJson());
        return json;
    }

    private JSONArray notesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Notes note : listOfNotes) {
            jsonArray.put(note.toJson());
        }

        return jsonArray;
    }
}
