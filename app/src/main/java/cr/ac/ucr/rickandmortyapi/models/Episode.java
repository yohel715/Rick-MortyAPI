package cr.ac.ucr.rickandmortyapi.models;

public class Episode {

    private int id;
    private String name;
    private String air_date;
    private String episode;
    private String url;
    private String created;

    public Episode() {
    }

    public Episode(int id, String name, String air_date, String episode, String url, String created) {
        this.id = id;
        this.name = name;
        this.air_date = air_date;
        this.episode = episode;
        this.url = url;
        this.created = created;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAir_date() {
        return air_date;
    }

    public String getEpisode() {
        return episode;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setAir_date(String air_date) {
        this.air_date = air_date;
    }

    public void setEpisode(String episode) {
        this.episode = episode;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "Episode{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", air_date='" + air_date + '\'' +
                ", episode='" + episode + '\'' +
                ", url='" + url + '\'' +
                ", created='" + created + '\'' +
                '}';
    }
}
