package com.sence.bean.response;

import java.util.List;

public class PCityBean {

    /**
     * reason : OK
     * result : [{"id":"11","name":"北京市","FullName":"北京市","ParentId":"","zip":""},{"id":"12","name":"天津市","FullName":"天津市","ParentId":"","zip":""},{"id":"13","name":"河北省","FullName":"河北省","ParentId":"","zip":""},{"id":"14","name":"山西省","FullName":"山西省","ParentId":"","zip":""},{"id":"15","name":"内蒙古自治区","FullName":"内蒙古自治区","ParentId":"","zip":""},{"id":"21","name":"辽宁省","FullName":"辽宁省","ParentId":"","zip":""},{"id":"22","name":"吉林省","FullName":"吉林省","ParentId":"","zip":""},{"id":"23","name":"黑龙江省","FullName":"黑龙江省","ParentId":"","zip":""},{"id":"31","name":"上海市","FullName":"上海市","ParentId":"","zip":""},{"id":"32","name":"江苏省","FullName":"江苏省","ParentId":"","zip":""},{"id":"33","name":"浙江省","FullName":"浙江省","ParentId":"","zip":""},{"id":"34","name":"安徽省","FullName":"安徽省","ParentId":"","zip":""},{"id":"35","name":"福建省","FullName":"福建省","ParentId":"","zip":""},{"id":"36","name":"江西省","FullName":"江西省","ParentId":"","zip":""},{"id":"37","name":"山东省","FullName":"山东省","ParentId":"","zip":""},{"id":"41","name":"河南省","FullName":"河南省","ParentId":"","zip":""},{"id":"42","name":"湖北省","FullName":"湖北省","ParentId":"","zip":""},{"id":"43","name":"湖南省","FullName":"湖南省","ParentId":"","zip":""},{"id":"44","name":"广东省","FullName":"广东省","ParentId":"","zip":""},{"id":"45","name":"广西壮族自治区","FullName":"广西壮族自治区","ParentId":"","zip":""},{"id":"46","name":"海南省","FullName":"海南省","ParentId":"","zip":""},{"id":"50","name":"重庆市","FullName":"重庆市","ParentId":"","zip":""},{"id":"51","name":"四川省","FullName":"四川省","ParentId":"","zip":""},{"id":"52","name":"贵州省","FullName":"贵州省","ParentId":"","zip":""},{"id":"53","name":"云南省","FullName":"云南省","ParentId":"","zip":""},{"id":"54","name":"西藏自治区","FullName":"西藏自治区","ParentId":"","zip":""},{"id":"61","name":"陕西省","FullName":"陕西省","ParentId":"","zip":""},{"id":"62","name":"甘肃省","FullName":"甘肃省","ParentId":"","zip":""},{"id":"63","name":"青海省","FullName":"青海省","ParentId":"","zip":""},{"id":"64","name":"宁夏回族自治区","FullName":"宁夏回族自治区","ParentId":"","zip":""},{"id":"65","name":"新疆维吾尔自治区","FullName":"新疆维吾尔自治区","ParentId":"","zip":""},{"id":"71","name":"台湾省","FullName":"台湾省","ParentId":"","zip":""},{"id":"81","name":"香港特别行政区","FullName":"香港特别行政区","ParentId":"","zip":""},{"id":"82","name":"澳门特别行政区","FullName":"澳门特别行政区","ParentId":"","zip":""}]
     * error_code : 0
     */

    private String reason;
    private int error_code;
    private List<ResultBean> result;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * id : 11
         * name : 北京市
         * FullName : 北京市
         * ParentId :
         * zip :
         */

        private String id;
        private String name;
        private String FullName;
        private String ParentId;
        private String zip;

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

        public String getFullName() {
            return FullName;
        }

        public void setFullName(String FullName) {
            this.FullName = FullName;
        }

        public String getParentId() {
            return ParentId;
        }

        public void setParentId(String ParentId) {
            this.ParentId = ParentId;
        }

        public String getZip() {
            return zip;
        }

        public void setZip(String zip) {
            this.zip = zip;
        }
    }
}
