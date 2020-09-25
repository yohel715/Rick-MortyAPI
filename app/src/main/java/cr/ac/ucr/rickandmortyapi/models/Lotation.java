package cr.ac.ucr.rickandmortyapi.models;

public class Lotation {

    private String name;
    private String url;

    public Lotation(String name) {
        this.name = name;
    }

    public Lotation(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "Lotation{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

}
