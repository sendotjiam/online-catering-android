package Admin;

public class ChatAdminList {
    private String img_profile;
    private String name;
    private String message_content;
    private String time;

    public ChatAdminList(String img_profile, String name, String message_content, String time) {
        this.img_profile = img_profile;
        this.name = name;
        this.message_content = message_content;
        this.time = time;
    }

    public String getImg_profile() {
        return img_profile;
    }

    public void setImg_profile(String img_profile) {
        this.img_profile = img_profile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage_content() {
        return message_content;
    }

    public void setMessage_content(String message_content) {
        this.message_content = message_content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
