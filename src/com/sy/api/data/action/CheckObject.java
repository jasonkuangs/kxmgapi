package com.sy.api.data.action;
/**
 * @ProjectName: ad
 * @Package: com.example.demo
 * @ClassName: CheckObject
 * @Description: 校验请求参数
 * @Author: hingbox@163.com
 * @CreateDate: 2018/10/18 11:27
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/10/18 11:27
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class CheckObject {
        private String code;
        private String message;
        private String resultcode;
        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public CheckObject() {
        }

        public CheckObject(String code, String message) {
            this.code = code;
            this.message = message;

        }
}
