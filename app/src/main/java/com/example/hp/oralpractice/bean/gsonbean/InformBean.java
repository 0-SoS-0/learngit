package com.example.hp.oralpractice.bean.gsonbean;

/**
 * Created by HP on 2017/11/18.
 */

public class InformBean {
    private String  imageUrl;
    private String nickName;
    private String time;
    private int type;
    public InformBean(String imageUrl, String nickname,int type){
        this.imageUrl=imageUrl;
        this.nickName =nickname;
        this.type = type;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public static class ManagePasswordGsonBean {


        /**
         * information : 更改成功
         * status : 1
         */

        private String information;
        private String status;

        public String getInformation() {
            return information;
        }

        public void setInformation(String information) {
            this.information = information;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
