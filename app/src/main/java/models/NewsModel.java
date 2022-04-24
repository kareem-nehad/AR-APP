package models;

public class NewsModel {
    String id, image, title, subTitle, body;
    long date;
    //TODO: Add date variable

    public NewsModel(String id, String image, String title, String subTitle, String body,long date) {
        this.id = id;
        this.image = image;
        this.title = title;
        this.subTitle = subTitle;
        this.body = body;
        this.date = date;
    }

    public NewsModel(String id, String image, String title, String subTitle, long date) {
        this.id = id;
        this.image = image;
        this.title = title;
        this.subTitle = subTitle;
        this.date = date;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
