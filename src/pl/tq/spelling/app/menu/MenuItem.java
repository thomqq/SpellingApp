package pl.tq.spelling.app.menu;

public class MenuItem {
    private final int pos;
    private String topic;
    private final MenuExecute menuExecute;

    public MenuItem(int pos, String topic, MenuExecute menuExecute) {
        this.pos = pos;
        this.topic = topic;
        this.menuExecute = menuExecute;
    }

    public int getPos() {
        return pos;
    }

    public MenuExecute getMenuExecute() {
        return menuExecute;
    }

    public String getTopic() {
        return topic;
    }

}
