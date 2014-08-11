package goudarzi.ha.headytunes;

public class Songs {

    private String name;
    private String description;
    private String artist;
    private String tags;
    private String image;

    public Songs() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

     public String getTags() {
        return tags;
     }

     public void setTags(String tags) {
        this.tags = tags;
     }

     public String getImage() {
        return image;
     }

     public void setImage(String image) {
        this.image = image;
     }
}

