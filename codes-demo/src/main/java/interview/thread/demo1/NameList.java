package interview.thread.demo1;

import java.util.ArrayList;
import java.util.List;

/**
 * https://www.nowcoder.com/questionTerminal/25deb8d21e7d442e86c90302d6e03133
 */
public class NameList {
    private List names = new ArrayList();

    public synchronized void add(String name) {
        names.add(name);
    }

    public synchronized void printAll() {
        for (int i = 0; i < names.size(); i++) {
            System.out.print(names.get(i) + "");
        }
    }

    public static void main(String[] args) {
        final NameList sl = new NameList();
        for (int i = 0; i < 2; i++) {
            new Thread() {
                public void run() {
                    sl.add("A");
                    sl.add("B");
                    sl.add("C");
                    sl.printAll();
                }
            }.start();
        }
    }
}