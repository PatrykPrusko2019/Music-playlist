
public class Song {
    private String title;
    private int duration;

    public Song (String title, int duration) {
        this.title = title;
        this.duration = duration;
    }

    public String getTitle() {
        return this.title;
    }

    public int getDuration() {
        return this.duration;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }


}
