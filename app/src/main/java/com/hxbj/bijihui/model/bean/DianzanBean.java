package com.hxbj.bijihui.model.bean;

public class DianzanBean {

    /**
     * status : 200
     * code : 2000
     * msg : ok
     * data : {"id":"5acdd1a01717931cece3a04f","iphone":"15910549092","userId":null,"level":null,"nickname":null,"picUrl":null,"title":"在在","coverUrl":"2018042014390579363681.png","videoUrl":"2018041820035638346407.mp4","grade":"体验","actionType":"初级阶段","complaint":1,"reason":"要fdd fdgdf","thumb":3233,"thumpSet":null,"valid":1,"createTime":"2018-04-11 17:12:12","updateTime":"2018-04-22 13:26:00","sortNumber":0,"type":1,"personal":0,"sortType":null,"thumbType":1,"start":0,"end":0,"pageCurrent":0,"pageSize":0,"pageCount":0}
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
         * id : 5acdd1a01717931cece3a04f
         * iphone : 15910549092
         * userId : null
         * level : null
         * nickname : null
         * picUrl : null
         * title : 在在
         * coverUrl : 2018042014390579363681.png
         * videoUrl : 2018041820035638346407.mp4
         * grade : 体验
         * actionType : 初级阶段
         * complaint : 1
         * reason : 要fdd fdgdf
         * thumb : 3233
         * thumpSet : null
         * valid : 1
         * createTime : 2018-04-11 17:12:12
         * updateTime : 2018-04-22 13:26:00
         * sortNumber : 0
         * type : 1
         * personal : 0
         * sortType : null
         * thumbType : 1
         * start : 0
         * end : 0
         * pageCurrent : 0
         * pageSize : 0
         * pageCount : 0
         */

        private String id;
        private String iphone;
        private Object userId;
        private Object level;
        private Object nickname;
        private Object picUrl;
        private String title;
        private String coverUrl;
        private String videoUrl;
        private String grade;
        private String actionType;
        private int complaint;
        private String reason;
        private int thumb;
        private Object thumpSet;
        private int valid;
        private String createTime;
        private String updateTime;
        private int sortNumber;
        private int type;
        private int personal;
        private Object sortType;
        private int thumbType;
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

        public String getIphone() {
            return iphone;
        }

        public void setIphone(String iphone) {
            this.iphone = iphone;
        }

        public Object getUserId() {
            return userId;
        }

        public void setUserId(Object userId) {
            this.userId = userId;
        }

        public Object getLevel() {
            return level;
        }

        public void setLevel(Object level) {
            this.level = level;
        }

        public Object getNickname() {
            return nickname;
        }

        public void setNickname(Object nickname) {
            this.nickname = nickname;
        }

        public Object getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(Object picUrl) {
            this.picUrl = picUrl;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCoverUrl() {
            return coverUrl;
        }

        public void setCoverUrl(String coverUrl) {
            this.coverUrl = coverUrl;
        }

        public String getVideoUrl() {
            return videoUrl;
        }

        public void setVideoUrl(String videoUrl) {
            this.videoUrl = videoUrl;
        }

        public String getGrade() {
            return grade;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }

        public String getActionType() {
            return actionType;
        }

        public void setActionType(String actionType) {
            this.actionType = actionType;
        }

        public int getComplaint() {
            return complaint;
        }

        public void setComplaint(int complaint) {
            this.complaint = complaint;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public int getThumb() {
            return thumb;
        }

        public void setThumb(int thumb) {
            this.thumb = thumb;
        }

        public Object getThumpSet() {
            return thumpSet;
        }

        public void setThumpSet(Object thumpSet) {
            this.thumpSet = thumpSet;
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

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public int getSortNumber() {
            return sortNumber;
        }

        public void setSortNumber(int sortNumber) {
            this.sortNumber = sortNumber;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getPersonal() {
            return personal;
        }

        public void setPersonal(int personal) {
            this.personal = personal;
        }

        public Object getSortType() {
            return sortType;
        }

        public void setSortType(Object sortType) {
            this.sortType = sortType;
        }

        public int getThumbType() {
            return thumbType;
        }

        public void setThumbType(int thumbType) {
            this.thumbType = thumbType;
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
