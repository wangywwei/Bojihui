package com.hxbj.bijihui.model.bean;

public class LiuyanBean {

    /**
     * status : 200
     * code : 2000
     * msg : ok
     * data : {"id":"5add9ebe1717931ea4ecb17e","userId":"5ad6f54e17179320ecd0d752","iphone":"11099998888","detail":"\u2018建行股份股份发","createTime":"2018-04-23 16:52:14","updateTime":null,"start":0,"end":0,"pageCurrent":0,"pageSize":0,"pageCount":0}
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
         * id : 5add9ebe1717931ea4ecb17e
         * userId : 5ad6f54e17179320ecd0d752
         * iphone : 11099998888
         * detail : ‘建行股份股份发
         * createTime : 2018-04-23 16:52:14
         * updateTime : null
         * start : 0
         * end : 0
         * pageCurrent : 0
         * pageSize : 0
         * pageCount : 0
         */

        private String id;
        private String userId;
        private String iphone;
        private String detail;
        private String createTime;
        private Object updateTime;
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

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getIphone() {
            return iphone;
        }

        public void setIphone(String iphone) {
            this.iphone = iphone;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
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
