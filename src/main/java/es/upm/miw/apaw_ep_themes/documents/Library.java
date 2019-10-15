package es.upm.miw.apaw_ep_themes.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Library implements TreeLibrary{

    @Id
    private String id;

    private String name;

    public Library(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Library{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean isComposite() {
        return false;
    }

    @Override
    public void add(TreeLibrary treeLibrary) {
        //do nothing because is a leaf
    }

    @Override
    public void remove(TreeLibrary treeLibrary) {
        //do nothing because is a leaf
    }

    @Override
    public int size() {
        return 1;
    }
}
