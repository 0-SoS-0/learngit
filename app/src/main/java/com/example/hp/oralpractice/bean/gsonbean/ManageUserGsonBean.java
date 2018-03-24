package com.example.hp.oralpractice.bean.gsonbean;

/**
 * Created by asus on 2018/3/13.
 */

public class ManageUserGsonBean {
    private String status;
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
