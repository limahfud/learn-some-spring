package me.mahfud.example.response.vo;

public class UserDetail {

    private long id;
    private String title;

    public UserDetail() {

    }

    public UserDetail(long id, String name) {
        this.id = id;
        this.title = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
