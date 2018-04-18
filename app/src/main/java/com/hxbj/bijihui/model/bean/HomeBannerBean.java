package com.hxbj.bijihui.model.bean;


import java.util.List;

public class HomeBannerBean {

    /**
     * status : 200
     * code : 2000
     * msg : ok
     * data : [{"id":"5ad5a7e717179323f0386ecc","name":"444","coverUrl":"heixiong-club.oss-cn-beijing.aliyuncs.com","detail":"","serialNumber":0,"valid":1,"createTime":"2018-04-17 15:53:00","updateTime":null,"htmlUrl":"heixiong-club.oss-cn-beijing.aliyuncs.com/html/5ad5a7e717179323f0386ecc.html","keyWord":"","start":0,"end":0,"pageCurrent":0,"pageSize":0,"pageCount":0},{"id":"5acdde531717930fdc1d0991","name":"fsadfsdafdsfsd","coverUrl":"heixiong-club.oss-cn-beijing.aliyuncs.com/images/2018041118075768478314loginbg.jpg","detail":"","serialNumber":2,"valid":1,"createTime":"2018-04-11 18:06:47","updateTime":"2018-04-17 15:14:34","htmlUrl":"heixiong-club.oss-cn-beijing.aliyuncs.com/html/5acdde531717930fdc1d0991.html","keyWord":"","start":0,"end":0,"pageCurrent":0,"pageSize":0,"pageCount":0},{"id":"5ad5a7dc17179323f0386ecb","name":"111","coverUrl":"heixiong-club.oss-cn-beijing.aliyuncs.com","detail":"","serialNumber":5,"valid":1,"createTime":"2018-04-17 15:53:00","updateTime":null,"htmlUrl":"heixiong-club.oss-cn-beijing.aliyuncs.com/html/5ad5a7dc17179323f0386ecb.html","keyWord":"","start":0,"end":0,"pageCurrent":0,"pageSize":0,"pageCount":0}]
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
         * id : 5ad5a7e717179323f0386ecc
         * name : 444
         * coverUrl : heixiong-club.oss-cn-beijing.aliyuncs.com
         * detail :
         * serialNumber : 0
         * valid : 1
         * createTime : 2018-04-17 15:53:00
         * updateTime : null
         * htmlUrl : heixiong-club.oss-cn-beijing.aliyuncs.com/html/5ad5a7e717179323f0386ecc.html
         * keyWord :
         * start : 0
         * end : 0
         * pageCurrent : 0
         * pageSize : 0
         * pageCount : 0
         */

        private String id;
        private String name;
        private String coverUrl;
        private String detail;
        private int serialNumber;
        private int valid;
        private String createTime;
        private Object updateTime;
        private String htmlUrl;
        private String keyWord;
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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCoverUrl() {
            return coverUrl;
        }

        public void setCoverUrl(String coverUrl) {
            this.coverUrl = coverUrl;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public int getSerialNumber() {
            return serialNumber;
        }

        public void setSerialNumber(int serialNumber) {
            this.serialNumber = serialNumber;
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

        public String getHtmlUrl() {
            return htmlUrl;
        }

        public void setHtmlUrl(String htmlUrl) {
            this.htmlUrl = htmlUrl;
        }

        public String getKeyWord() {
            return keyWord;
        }

        public void setKeyWord(String keyWord) {
            this.keyWord = keyWord;
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
