package com.example.demo.Entity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

//@Entity

//@Table(name = "Note")
public class Note {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    //    @Column(title = "title",nullable = false, length = 200)
    private String title;
    //    @Column(content = "content",nullable = false, length = 200)
    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
