package com.hxbj.bijihui.model.bean;

public class OssBean {

    /**
     * status : 200
     * code : 2000
     * msg : ok
     * data : {"SecurityToken":"CAISigJ1q6Ft5B2yfSjIpY7nLdnTprBWgvGzbU/pskUxOfp2hbzo0zz2IHBFeXJhA+0bt/g0nGxX7PgblrgpE8YVHRacMJErs8oKr17/PNJrWwQeKPhW5qe+EE2/VjTJvqaLEdibIfrZfvCyESem8gZ43br9cxi7QlWhKufnoJV7b9MRLGbaAD1dH4UUXEgAzvUXLnzML/2gHwf3i27LdipStxF7lHl05NbYoKiV4QGMi0bhmK1H5dazAOD9MZc1ZM4lAo/rg7UmK/GZ6kMKtUgWrpURpbdf5DLKsuuaB1Rs+BicO4LWiIY/fVIiN/JnRvcY96Cgyqcm4/axkJ/sjh1JPOxTTzR1xn5qQ3ZVchqAAXSwnNCF37Kd67GyAp8S1DeLBD7FiXJQlgBOzh5Gsj8R49WpdP+AMsx6D/uMZr8ZEvcygdFvZVBe1MnoDqr5PqpD+4vrT/+BGKy3ICxtfYOgBOABQ/f/qVSrTl9qGK2DTk923W+gRECeC235Lvm4HOZwEHZZCoe0aQ1qGlOC6KzZ","AccessKeyId":"STS.DMRfcgKow53XoiXVEd5uZjyC1","AccessKeySecret":"8bLzJnaapziZr9iqzxLqR76cxaKToLRNWWBPeQk5AjuA"}
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
         * SecurityToken : CAISigJ1q6Ft5B2yfSjIpY7nLdnTprBWgvGzbU/pskUxOfp2hbzo0zz2IHBFeXJhA+0bt/g0nGxX7PgblrgpE8YVHRacMJErs8oKr17/PNJrWwQeKPhW5qe+EE2/VjTJvqaLEdibIfrZfvCyESem8gZ43br9cxi7QlWhKufnoJV7b9MRLGbaAD1dH4UUXEgAzvUXLnzML/2gHwf3i27LdipStxF7lHl05NbYoKiV4QGMi0bhmK1H5dazAOD9MZc1ZM4lAo/rg7UmK/GZ6kMKtUgWrpURpbdf5DLKsuuaB1Rs+BicO4LWiIY/fVIiN/JnRvcY96Cgyqcm4/axkJ/sjh1JPOxTTzR1xn5qQ3ZVchqAAXSwnNCF37Kd67GyAp8S1DeLBD7FiXJQlgBOzh5Gsj8R49WpdP+AMsx6D/uMZr8ZEvcygdFvZVBe1MnoDqr5PqpD+4vrT/+BGKy3ICxtfYOgBOABQ/f/qVSrTl9qGK2DTk923W+gRECeC235Lvm4HOZwEHZZCoe0aQ1qGlOC6KzZ
         * AccessKeyId : STS.DMRfcgKow53XoiXVEd5uZjyC1
         * AccessKeySecret : 8bLzJnaapziZr9iqzxLqR76cxaKToLRNWWBPeQk5AjuA
         */

        private String SecurityToken;
        private String AccessKeyId;
        private String AccessKeySecret;

        public String getSecurityToken() {
            return SecurityToken;
        }

        public void setSecurityToken(String SecurityToken) {
            this.SecurityToken = SecurityToken;
        }

        public String getAccessKeyId() {
            return AccessKeyId;
        }

        public void setAccessKeyId(String AccessKeyId) {
            this.AccessKeyId = AccessKeyId;
        }

        public String getAccessKeySecret() {
            return AccessKeySecret;
        }

        public void setAccessKeySecret(String AccessKeySecret) {
            this.AccessKeySecret = AccessKeySecret;
        }
    }
}
