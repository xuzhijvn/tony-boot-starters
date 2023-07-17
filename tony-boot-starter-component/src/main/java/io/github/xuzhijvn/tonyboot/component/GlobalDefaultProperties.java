/*
 *       Copyright© (2020).
 */
package io.github.xuzhijvn.tonyboot.component;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author tony
 * @create 2021-07-28
 * @description:
 */
@ConfigurationProperties(prefix = "tony.component.ex-handle")
public class GlobalDefaultProperties {

    private String pointcut;
    private String excludeException = "";
    private Lark lark;
    private String excludePackages = "";
    private String limitPackages = "";

    public String getExcludePackages() {
        return excludePackages;
    }

    public void setExcludePackages(String excludePackages) {
        this.excludePackages = excludePackages;
    }

    public String getLimitPackages() {
        return limitPackages;
    }

    public void setLimitPackages(String limitPackages) {
        this.limitPackages = limitPackages;
    }

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

    public Lark getLark() {
        return lark;
    }

    public void setLark(Lark lark) {
        this.lark = lark;
    }

    public static class Lark {
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
