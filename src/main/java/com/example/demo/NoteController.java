package com.example.demo;

import com.example.demo.Entity.Note;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@Controller
@RequestMapping("/note")
public class NoteController {
    private List<Note> notes = new ArrayList<>();

    @GetMapping("/list")
    public String getNoteList(Model model) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<!DOCTYPE html>");
        stringBuilder.append("<html lang=\"en\">");
        stringBuilder.append("<head>");
        stringBuilder.append("<meta charset=\"UTF-8\">");
        stringBuilder.append("<title>Note List</title>");
        stringBuilder.append("</head>");
        stringBuilder.append("<body>");
        stringBuilder.append("<h1>Note List</h1>");
        stringBuilder.append("<ul>");
        for (Note note : notes) {
            stringBuilder.append("<li>");
            stringBuilder.append(note.getTitle()).append(" - ").append(note.getContent());
            stringBuilder.append("<a href=\"/note/edit?id=").append(note.getId()).append("\">Edit</a> | ");
            stringBuilder.append("<form action=\"/note/delete\" method=\"post\">");
            stringBuilder.append("<input type=\"hidden\" name=\"id\" value=\"").append(note.getId()).append("\">");
            stringBuilder.append("<button type=\"submit\">Delete</button>");
            stringBuilder.append("</form>");
            stringBuilder.append("</li>");
        }
        stringBuilder.append("</ul>");
        stringBuilder.append("<a href=\"/\">Back to Home</a>");
        stringBuilder.append("</body>");
        stringBuilder.append("</html>");
        model.addAttribute("noteListHtml", stringBuilder.toString());
        return "note_list";
    }
    @GetMapping("/edit")
    public String editNote(@RequestParam Long id, Model model) {
        Note noteToEdit = notes.stream().filter(note -> note.getId().equals(id)).findFirst().orElse(null);
        if (noteToEdit != null) {
            StringBuilder htmlBuilder = new StringBuilder();
            htmlBuilder.append("<!DOCTYPE html>");
            htmlBuilder.append("<html lang=\"en\">");
            htmlBuilder.append("<head>");
            htmlBuilder.append("<meta charset=\"UTF-8\">");
            htmlBuilder.append("<title>Edit Note</title>");
            htmlBuilder.append("</head>");
            htmlBuilder.append("<body>");
            htmlBuilder.append("<h1>Edit Note</h1>");
            htmlBuilder.append("<form action=\"/note/edit\" method=\"post\">");
            htmlBuilder.append("<input type=\"hidden\" name=\"id\" value=\"").append(noteToEdit.getId()).append("\">");
            htmlBuilder.append("<label for=\"title\">Title:</label><br>");
            htmlBuilder.append("<input type=\"text\" id=\"title\" name=\"title\" value=\"").append(noteToEdit.getTitle()).append("\"><br>");
            htmlBuilder.append("<label for=\"content\">Content:</label><br>");
            htmlBuilder.append("<textarea id=\"content\" name=\"content\" rows=\"4\" cols=\"50\">").append(noteToEdit.getContent()).append("</textarea><br>");
            htmlBuilder.append("<button type=\"submit\">Save</button>");
            htmlBuilder.append("</form>");
            htmlBuilder.append("<a href=\"/note/list\">Cancel</a>");
            htmlBuilder.append("</body>");
            htmlBuilder.append("</html>");
            model.addAttribute("editNoteHtml", htmlBuilder.toString());
            return "note_edit";
        } else {
            return "redirect:/note/list";
        }
    }
    @PostMapping("/edit")
    public String updateNote(@ModelAttribute Note updatedNote) {
        notes.stream().filter(note -> note.getId().equals(updatedNote.getId())).
                forEach(note -> {
                    note.setTitle(updatedNote.getTitle());
                    note.setContent(updatedNote.getContent());
                });
        return "redirect:/note/list";
    }
    @PostMapping("/delete")
    public String deleteNote(@RequestParam Long id) {
        notes.removeIf(note -> note.getId().equals(id));
        return "redirect:/note/list";
    }


/////////////////////////////////////////////////////////////
}
