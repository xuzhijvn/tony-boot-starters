/*
 *       Copyright© (2020).
 */
package com.tony.component;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author tony
 * @create 2021-07-28
 * @description:
 */
@ConfigurationProperties(prefix = "risk.tony.component")
public class GlobalDefaultProperties {

    private String pointcut;
    private String excludeException = "";
    private Feishu feishu;
    //private Boolean currentUser;

//    public Boolean getCurrentUser() {
//        return currentUser;
//    }

//    public void setCurrentUser(Boolean currentUser) {
//        this.currentUser = currentUser;
//    }

    public String getExcludeException() {
        return excludeException;
    }

    public void setExcludeException(String excludeException) {
        this.excludeException = excludeException;
    }

    public String getPointcut() {
        return pointcut;
    }

    public void setPointcut(String pointcut) {
        this.pointcut = pointcut;
    }

    public Feishu getFeishu() {
        return feishu;
    }

    public void setFeishu(Feishu feishu) {
        this.feishu = feishu;
    }

    public static class Feishu {
        private String webhooks;
        private Kibana kibana;

        public String getWebhooks() {
            return webhooks;
        }

        public void setWebhooks(String webhooks) {
            this.webhooks = webhooks;
        }

        public Kibana getKibana() {
            return kibana;
        }

        public void setKibana(Kibana kibana) {
            this.kibana = kibana;
        }

        public static class Kibana {
            private String url;
            private String sname;
            private String stype;

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getSname() {
                return sname;
            }

            public void setSname(String sname) {
                this.sname = sname;
            }

            public String getStype() {
                return stype;
            }

            public void setStype(String stype) {
                this.stype = stype;
            }
        }
    }

}
