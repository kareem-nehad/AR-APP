package models;

public class NewsModel {
    String id, image, title, subTitle, body;
    //TODO: Add date variable

    public NewsModel(String id, String image, String title, String subTitle, String body) {
        this.id = id;
        this.image = image;
        this.title = title;
        this.subTitle = subTitle;
        this.body = body;
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
