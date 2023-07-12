/*
 *       Copyright© (2020) Lalamove Co., Ltd.
 */
package com.tony.boot.notify.lark;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tony.boot.tools.utils.spring.SpringUtils;
import org.slf4j.MDC;

import java.lang.reflect.Method;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 飞书告警请求体
 *
 * @author tony
 * @create 2021-07-19
 * @description:
 */

public class LarkRequest {

    @JsonProperty("msg_type")
    private String msgType = "interactive";
    private Card card;

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public static class Card {
        private Config config = new Config();
        private Header header = new Header();
        private List<Element> elements;

        public Config getConfig() {
            return config;
        }

        public void setConfig(Config config) {
            this.config = config;
        }

        public Header getHeader() {
            return header;
        }

        public void setHeader(Header header) {
            this.header = header;
        }

        public List<Element> getElements() {
            return elements;
        }

        public void setElements(List<Element> elements) {
            this.elements = elements;
        }

        public static class Element {
            private String tag = "markdown";
            private String content;
            private Href href = new Href();

            public String getTag() {
                return tag;
            }

            public void setTag(String tag) {
                this.tag = tag;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public Href getHref() {
                return href;
            }

            public void setHref(Href href) {
                this.href = href;
            }

            public static class Href {
                private UrlVal urlVal = new UrlVal();

                public UrlVal getUrlVal() {
                    return urlVal;
                }

                public void setUrlVal(UrlVal urlVal) {
                    this.urlVal = urlVal;
                }

                public static class UrlVal {
                    private String url = "https://www.feishu.com";

                    //@JSONField(name = "android_url")
                    private String androidUrl = "https://developer.android.com/";

                    //@JSONField(name = "ios_url")
                    private String iosUrl = "lark://msgcard/unsupported_action";

                    //@JSONField(name = "pc_url")
                    private String pcUrl = "https://www.feishu.com";

                    public String getUrl() {
                        return url;
                    }

                    public void setUrl(String url) {
                        this.url = url;
                    }

                    public String getAndroidUrl() {
                        return androidUrl;
                    }

                    public void setAndroidUrl(String androidUrl) {
                        this.androidUrl = androidUrl;
                    }

                    public String getIosUrl() {
                        return iosUrl;
                    }

                    public void setIosUrl(String iosUrl) {
                        this.iosUrl = iosUrl;
                    }

                    public String getPcUrl() {
                        return pcUrl;
                    }

                    public void setPcUrl(String pcUrl) {
                        this.pcUrl = pcUrl;
                    }

                    @Override
                    public String toString() {
                        return "UrlVal{" +
                                "url='" + url + '\'' +
                                ", androidUrl='" + androidUrl + '\'' +
                                ", iosUrl='" + iosUrl + '\'' +
                                ", pcUrl='" + pcUrl + '\'' +
                                '}';
                    }
                }

                @Override
                public String toString() {
                    return "Href{" +
                            "urlVal=" + urlVal +
                            '}';
                }
            }

            @Override
            public String toString() {
                return "Element{" +
                        "tag='" + tag + '\'' +
                        ", content='" + content + '\'' +
                        ", href=" + href +
                        '}';
            }
        }

        public static class Header {
            private Title title = new Title();
            private String template = "blue";

            public Title getTitle() {
                return title;
            }

            public void setTitle(Title title) {
                this.title = title;
            }

            public String getTemplate() {
                return template;
            }

            public void setTemplate(String template) {
                this.template = template;
            }

            public static class Title {
                private String tag = "plain_text";

                public String getTag() {
                    return tag;
                }

                public void setTag(String tag) {
                    this.tag = tag;
                }

                public String getContent() {
                    return content;
                }

                private String content = "🤖";

                public void setContent(String content) {
                    if (content != null) {
                        this.content = "🤖 " + content;
                    }
                }

                @Override
                public String toString() {
                    return "Title{" +
                            "tag='" + tag + '\'' +
                            ", content='" + content + '\'' +
                            '}';
                }
            }

            @Override
            public String toString() {
                return "Header{" +
                        "title=" + title +
                        ", template='" + template + '\'' +
                        '}';
            }
        }

        public static class Config {
            //@JSONField(name = "wide_screen_mode")
            private boolean wideScreenMode = true;

            public boolean isWideScreenMode() {
                return wideScreenMode;
            }

            public void setWideScreenMode(boolean wideScreenMode) {
                this.wideScreenMode = wideScreenMode;
            }

            @Override
            public String toString() {
                return "Config{" +
                        "wideScreenMode=" + wideScreenMode +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "Card{" +
                    "config=" + config +
                    ", header=" + header +
                    ", elements=" + elements +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "LarkPostRequest{" +
                "msgType='" + msgType + '\'' +
                ", card=" + card +
                '}';
    }


    public static LarkRequest build(String titleName, String content, Color color) {
        List<LarkRequest.Card.Element> elements = new ArrayList<>();
        LarkRequest.Card.Element element = new LarkRequest.Card.Element();
        element.setContent(content);
        elements.add(element);
        LarkRequest.Card card = new LarkRequest.Card();
        card.setElements(elements);
        LarkRequest.Card.Header header = new LarkRequest.Card.Header();
        LarkRequest.Card.Header.Title title = new LarkRequest.Card.Header.Title();
        title.setContent(titleName);
        header.setTitle(title);
        header.setTemplate(color != null ? color.getValue() : header.getTemplate());
        card.setHeader(header);
        LarkRequest larkRequest = new LarkRequest();
        larkRequest.setCard(card);
        return larkRequest;
    }

    public static String buildDefaultContent(Object message, String logUrl,
                                             Throwable throwable, Method method, Object[] objects) {
        String content = "**环境** 👉 " + SpringUtils.getActiveProfile();

        if (method != null) {
            content += "\n ---\n**方法** 👉 " + method;
        }
        if (objects != null && objects.length != 0) {
            content += "\n ---\n**参数** 👉 " + Arrays.toString(objects);
        }
        if (throwable != null && throwable.getClass() != null) {
            content += "\n ---\n**异常类型** 👉 " + throwable.getClass();
        }
        if (message != null) {
            content += "\n ---\n**异常** 👉 " + message;
        }
        if (logUrl != null) {
            content += "\n ---\n[**日志传送门🚪**](" + logUrl + ")\n";
        }
        return content;
    }

    public String buildKibanaLogUrl(final String env, final String url, final String sname, final String stype) {
        String logEnv = env;
        if ("pre".equals(env)) {
            logEnv = "pre2";
        }
        if ("未知".equals(env)) {
            logEnv = "stg";
        }
        Instant now = Instant.now();
        Instant now1 = now.minusSeconds(2);
        Instant now2 = now.plusSeconds(10);
        String hllTid = MDC.get("HLL_TID");
        if (hllTid == null) {
            hllTid = "''";
        } else {
            hllTid = "%22" + hllTid + "%22";
        }
        return url + "?_g=(filters:!(\\),refreshInterval:(pause:!t,value:0\\)," +
                "time:(from:'" + now1 + "',to:'" + now2 + "'\\)\\)" +
                "&_a=(columns:!(message,request\\),filters:!(\\),interval:auto,query:(language:kuery,query:" + hllTid + "\\)," +
                "senv:" + logEnv + ",sname:" + sname + ",sort:!(\\),stype:" + stype + "\\)";
    }
}

