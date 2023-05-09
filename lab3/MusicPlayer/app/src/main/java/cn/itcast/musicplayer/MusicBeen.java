package cn.itcast.musicplayer;

public class MusicBeen {

    private String title;
    private int imageId;
    private int musicId;

    public MusicBeen() {
    }

    public MusicBeen(String title, int imageId, int musicId) {
        this.title = title;
        this.imageId = imageId;
        this.musicId = musicId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public int getMusicId() {
        return musicId;
    }

    public void setMusicId(int musicId) {
        this.musicId = musicId;
    }
}
