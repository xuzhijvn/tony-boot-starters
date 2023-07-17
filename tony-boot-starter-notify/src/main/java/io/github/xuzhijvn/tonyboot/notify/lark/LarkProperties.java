/*
 *       Copyright© (2020).
 */
package io.github.xuzhijvn.tonyboot.notify.lark;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author tony
 * @create 2021-07-28
 * @description:
 */
@ConfigurationProperties(prefix = "tony.notify.lark")
public class LarkProperties {
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
