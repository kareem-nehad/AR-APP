package models;

public class WikiModel {
    String id, title, image, model, category, body;

    public WikiModel(String id, String title, String image) {
        this.id = id;
        this.title = title;
        this.image = image;
    }

    public WikiModel(String title, String image, String model, String category, String body) {
        this.title = title;
        this.image = image;
        this.model = model;
        this.category = category;
        this.body = body;
    }

    public WikiModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
