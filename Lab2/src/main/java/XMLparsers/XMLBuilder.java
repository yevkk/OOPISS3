package XMLparsers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class XMLBuilder <T extends Comparable<T>> {
    protected List<T> list;

    public XMLBuilder() {
        list = new ArrayList<>();
    }

    public List<?> getList() {
        return list;
    }

    public void sort() {
        Collections.sort(list);
    }

    public abstract void buildList(String filename);
}
