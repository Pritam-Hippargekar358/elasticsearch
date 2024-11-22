package tech.elastic.dto;

public class Authors {
    private String name;

    public Authors() {
    }

    public Authors(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Authors{" +
                "name='" + name + '\'' +
                '}';
    }
}
