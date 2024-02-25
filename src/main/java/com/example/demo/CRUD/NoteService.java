package com.example.demo.CRUD;

import com.example.demo.Entity.Note;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
@Service
public class NoteService {
    private final List<Note> note = new ArrayList<>();
    private final Random random = new Random();

    public List<Note> listAll() {
        return note;
    }
    public Note add(Note note) {
        long ranId;
        do {
            ranId = random.nextInt(1000);
        } while (getById(ranId) != null);
        note.setId(ranId);
        this.note.add(note);
        return note;
    }
    public void deleteById(long id) {
        Note delNote = getById(id);
        if (delNote == null) {
            throw new IllegalArgumentException("Note not found!!");
        }
        note.remove(delNote);
    }
    public void update(Note note) {
        Note existNote = getById(note.getId());
        if (existNote == null) {
            throw new IllegalArgumentException("Note not found!!");
        }
        existNote.setTitle(note.getTitle());
        existNote.setContent(note.getContent());
    }
    public Note getById(long id) {
        for (Note note : note) {
            if (note.getId() == id) {
                return note;
            }
        }
        return null;
    }
}