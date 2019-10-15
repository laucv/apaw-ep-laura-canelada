package es.upm.miw.apaw_ep_themes.documents;

import java.util.ArrayList;
import java.util.List;

public class TreeLibraryComposite implements TreeLibrary {

    private String id;

    private String name;

    private List<TreeLibrary> treeLibraryList;

    public TreeLibraryComposite(String id, String name) {
        this.id = id;
        this.name = name;
        this.treeLibraryList = new ArrayList<>();
    }

    @Override
    public boolean isComposite() {
        return true;
    }

    @Override
    public void add(TreeLibrary treeLibrary) {
        treeLibraryList.add(treeLibrary);
    }

    @Override
    public void remove(TreeLibrary treeLibrary) {
        treeLibraryList.remove(treeLibrary);
    }

    @Override
    public int size() {
        return this.treeLibraryList.size();
    }
}
