package com.example.tommy.tommysyamranata_1202153368_studycase5;


//disini sebagai recycle view
public class ListItem {
    private String todo, desc, prior;

    //  Konstruktor
    public ListItem(String todo, String desc, String prior) {
        this.todo = todo;
        this.desc = desc;
        this.prior = prior;
    }
    // Method setter getter
    public String getTodo() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPrior() {
        return prior;
    }

    public void setPrior(String prior) {
        this.prior = prior;
    }
}
