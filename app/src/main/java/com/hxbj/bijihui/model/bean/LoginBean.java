package com.hxbj.bijihui.model.bean;

public class LoginBean {

    /**
     * status : 200
     * code : 2000
     * msg : ok
     * data : {"id":"5ad55b251717931ee475604b","type":"普通成员","iphone":"17600996535","password":"4297f44b13955235245b2497399d7a93","nickname":"","picUrl":"","sex":"","birthday":"","profession":"","level":"新手","experience":0,"punch":0,"valid":0,"createTime":"2018-04-17 10:25:41","updateTime":null,"keyWord":"","manager":"否","oldCourse":0,"newCourse":0,"soundUrl":null,"start":0,"end":0,"pageCurrent":0,"pageSize":0,"pageCount":0}
     */

    private int status;
    private int code;
    private String msg;
    private DataBean data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 5ad55b251717931ee475604b
         * type : 普通成员
         * iphone : 17600996535
         * password : 4297f44b13955235245b2497399d7a93
         * nickname :
         * picUrl :
         * sex :
         * birthday :
         * profession :
         * level : 新手
         * experience : 0
         * punch : 0
         * valid : 0
         * createTime : 2018-04-17 10:25:41
         * updateTime : null
         * keyWord :
         * manager : 否
         * oldCourse : 0
         * newCourse : 0
         * soundUrl : null
         * start : 0
         * end : 0
         * pageCurrent : 0
         * pageSize : 0
         * pageCount : 0
         */

        private String id;
        private String type;
        private String iphone;
        private String password;
        private String nickname;
        private String picUrl;
        private String sex;
        private String birthday;
        private String profession;
        private String level;
        private int experience;
        private int punch;
        private int valid;
        private String createTime;
        private Object updateTime;
        private String keyWord;
        private String manager;
        private int oldCourse;
        private int newCourse;
        private String soundUrl;
        private int start;
        private int end;
        private int pageCurrent;
        private int pageSize;
        private int pageCount;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getIphone() {
            return iphone;
        }

        public void setIphone(String iphone) {
            this.iphone = iphone;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getProfession() {
            return profession;
        }

        public void setProfession(String profession) {
            this.profession = profession;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public int getExperience() {
            return experience;
        }

        public void setExperience(int experience) {
            this.experience = experience;
        }

        public int getPunch() {
            return punch;
        }

        public void setPunch(int punch) {
            this.punch = punch;
        }

        public int getValid() {
            return valid;
        }

        public void setValid(int valid) {
            this.valid = valid;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public Object getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(Object updateTime) {
            this.updateTime = updateTime;
        }

        public String getKeyWord() {
            return keyWord;
        }

        public void setKeyWord(String keyWord) {
            this.keyWord = keyWord;
        }

        public String getManager() {
            return manager;
        }

        public void setManager(String manager) {
            this.manager = manager;
        }

        public int getOldCourse() {
            return oldCourse;
        }

        public void setOldCourse(int oldCourse) {
            this.oldCourse = oldCourse;
        }

        public int getNewCourse() {
            return newCourse;
        }

        public void setNewCourse(int newCourse) {
            this.newCourse = newCourse;
        }

        public String getSoundUrl() {
            return soundUrl;
        }

        public void setSoundUrl(String soundUrl) {
            this.soundUrl = soundUrl;
        }

        public int getStart() {
            return start;
        }

        public void setStart(int start) {
            this.start = start;
        }

        public int getEnd() {
            return end;
        }

        public void setEnd(int end) {
            this.end = end;
        }

        public int getPageCurrent() {
            return pageCurrent;
        }

        public void setPageCurrent(int pageCurrent) {
            this.pageCurrent = pageCurrent;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getPageCount() {
            return pageCount;
        }

        public void setPageCount(int pageCount) {
            this.pageCount = pageCount;
        }
    }
}
