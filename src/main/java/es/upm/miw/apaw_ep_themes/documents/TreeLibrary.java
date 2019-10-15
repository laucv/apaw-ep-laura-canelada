package es.upm.miw.apaw_ep_themes.documents;

public interface TreeLibrary {

    boolean isComposite();

    void add(TreeLibrary treeLibrary);

    void remove(TreeLibrary treeLibrary);

    int size();
}
