package cr.ac.ucr.rickandmortyapi.models;

public class Location {

    private int id;
    private String type;
    private String dimension;
    private String name;
    private String url;
    private String created;

    public Location() {
    }

    public Location(int id, String type, String dimension, String name, String url, String created) {
        this.id = id;
        this.type = type;
        this.dimension = dimension;
        this.name = name;
        this.url = url;
        this.created = created;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getDimension() {
        return dimension;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getCreated() {
        return created;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", dimension='" + dimension + '\'' +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", created='" + created + '\'' +
                '}';
    }
}
