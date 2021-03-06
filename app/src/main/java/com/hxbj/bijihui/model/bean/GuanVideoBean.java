package com.hxbj.bijihui.model.bean;

import java.util.List;

public class GuanVideoBean {

    /**
     * status : 200
     * code : 2000
     * msg : ok
     * data : [{"id":"5ad84c1a1717931e14d3f8ef","iphone":"0","userId":null,"level":null,"nickname":null,"picUrl":null,"title":"44","coverUrl":"http://heixiong-club.oss-cn-beijing.aliyuncs.com/images/2018042014373234043135.jpg","videoUrl":"http://heixiong-club.oss-cn-beijing.aliyuncs.com/video/2018041915581456746401.mp4","grade":"体验","actionType":"初级阶段","complaint":0,"reason":"","thumb":0,"thumpSet":null,"valid":1,"createTime":"2018-04-19 15:58:18","updateTime":"2018-04-20 14:37:32","sortNumber":1000,"type":0,"personal":1,"sortType":null,"thumbType":0,"start":0,"end":0,"pageCurrent":0,"pageSize":0,"pageCount":0},{"id":"5adbf43f17179305b832d2bd","iphone":"17600996535","userId":"5ad7127a1717931a3c0316f6","level":"会员","nickname":"回眸","picUrl":"http://heixiong-club.oss-cn-beijing.aliyuncs.com/images/2018041912003149134190.jpg","title":null,"coverUrl":"http://heixiong-club.oss-cn-beijing.aliyuncs.com/images/null","videoUrl":"http://heixiong-club.oss-cn-beijing.aliyuncs.com/video/2018042210323158453940.mp4","grade":null,"actionType":null,"complaint":0,"reason":null,"thumb":0,"thumpSet":null,"valid":1,"createTime":"2018-04-22 10:32:31","updateTime":null,"sortNumber":0,"type":1,"personal":0,"sortType":null,"thumbType":0,"start":0,"end":0,"pageCurrent":0,"pageSize":0,"pageCount":0}]
     */

    private int status;
    private int code;
    private String msg;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 5ad84c1a1717931e14d3f8ef
         * iphone : 0
         * userId : null
         * level : null
         * nickname : null
         * picUrl : null
         * title : 44
         * coverUrl : http://heixiong-club.oss-cn-beijing.aliyuncs.com/images/2018042014373234043135.jpg
         * videoUrl : http://heixiong-club.oss-cn-beijing.aliyuncs.com/video/2018041915581456746401.mp4
         * grade : 体验
         * actionType : 初级阶段
         * complaint : 0
         * reason :
         * thumb : 0
         * thumpSet : null
         * valid : 1
         * createTime : 2018-04-19 15:58:18
         * updateTime : 2018-04-20 14:37:32
         * sortNumber : 1000
         * type : 0
         * personal : 1
         * sortType : null
         * thumbType : 0
         * start : 0
         * end : 0
         * pageCurrent : 0
         * pageSize : 0
         * pageCount : 0
         */

        private String id;
        private String iphone;
        private String userId;
        private String level;
        private String nickname;
        private String picUrl;
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

        private boolean isSelect;

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }

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

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
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
