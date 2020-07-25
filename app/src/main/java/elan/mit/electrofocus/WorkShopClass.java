package elan.mit.electrofocus;

public class WorkShopClass
{
    public String date;
    public String description;
    public String fee;
    public String photo;
    public String title;
    public String url;
    public String venue;

    public WorkShopClass()
    {

    }

    public WorkShopClass(String date, String description, String fee, String photo, String title, String url, String venue) {
        this.date = date;
        this.description = description;
        this.fee = fee;
        this.photo = photo;
        this.title = title;
        this.url = url;
        this.venue = venue;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }
}
