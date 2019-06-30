package pl.tq.spelling.app.menu;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Menu {
    private List<MenuItem> menuItems = new ArrayList<>();
    private HashMap< Integer, MenuItem > posToMeneItem = new HashMap<>();

    public void add(MenuItem menuItem) {
        menuItems.add(menuItem);
        posToMeneItem.put(menuItem.getPos(), menuItem);
    }

    public void printMenu(PrintStream out) {
        menuItems.stream().forEach( item -> {
            System.out.println(item.getPos() + "." + item.getTopic());
        });
    }

    public void executePos(int pos) throws Exception {
        MenuItem item = posToMeneItem.get(pos);
        if( item == null) {
            printErrorChoose();
        } else {
            item.getMenuExecute().execute();
        }
    }

    private void printErrorChoose() {
        System.out.println("Wybierz poprawna pozycje.");
    }
}
