package com.hxbj.bijihui.model.bean;

import java.util.List;

public class GuanVideoBean {

    /**
     * status : 200
     * code : 2000
     * msg : ok
     * data : [{"id":"5acdd05717179324c8b5c45a","userId":"0","title":"到地","videoUrl":"http://heixiong-club.oss-cn-beijing.aliyuncs.com/video/2018041820040716587727.mp4","grade":"体验课程","actionType":"肩部动作","complaint":222,"reason":"下大雨城","thumb":111,"valid":1,"createTime":"","updateTime":"2018-04-18 20:03:57","sortNumber":0,"type":1,"start":0,"end":0,"pageCurrent":0,"pageSize":0,"pageCount":0},{"id":"5acdcaa117179326b0b10148","userId":"0","title":"23333","videoUrl":"http://heixiong-club.oss-cn-beijing.aliyuncs.com/video/2018041820041618699196.mp4","grade":"体验课程","actionType":"移动步伐","complaint":0,"reason":"3333o一","thumb":0,"valid":1,"createTime":"","updateTime":"2018-04-19 15:11:26","sortNumber":0,"type":1,"start":0,"end":0,"pageCurrent":0,"pageSize":0,"pageCount":0},{"id":"5acdd1a01717931cece3a04f","userId":"0","title":"在在","videoUrl":"http://heixiong-club.oss-cn-beijing.aliyuncs.com/video/2018041820035638346407.mp4","grade":"体验课程","actionType":"移动步伐","complaint":1,"reason":"要fdd fdgdf","thumb":3232,"valid":1,"createTime":"2018-04-11 17:12:12","updateTime":"2018-04-19 15:11:26","sortNumber":0,"type":1,"start":0,"end":0,"pageCurrent":0,"pageSize":0,"pageCount":0}]
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
         * id : 5acdd05717179324c8b5c45a
         * userId : 0
         * title : 到地
         * videoUrl : http://heixiong-club.oss-cn-beijing.aliyuncs.com/video/2018041820040716587727.mp4
         * grade : 体验课程
         * actionType : 肩部动作
         * complaint : 222
         * reason : 下大雨城
         * thumb : 111
         * valid : 1
         * createTime :
         * updateTime : 2018-04-18 20:03:57
         * sortNumber : 0
         * type : 1
         * start : 0
         * end : 0
         * pageCurrent : 0
         * pageSize : 0
         * pageCount : 0
         */

        private String id;
        private String userId;
        private String title;
        private String videoUrl;
        private String grade;
        private String actionType;
        private int complaint;
        private String reason;
        private int thumb;
        private int valid;
        private String createTime;
        private String updateTime;
        private int sortNumber;
        private int type;
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

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
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
