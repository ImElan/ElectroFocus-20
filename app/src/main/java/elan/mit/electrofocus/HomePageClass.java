package elan.mit.electrofocus;

public class HomePageClass
{
    public String story;
    public String link;
    public String caption;
    public String time;
    public String date;


    public HomePageClass()
    {

    }
    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public HomePageClass(String story, String link, String caption,String time,String date)
    {
        this.story = story;
        this.link = link;
        this.caption = caption;
        this.time = time;
        this.date = date;
    }
}
