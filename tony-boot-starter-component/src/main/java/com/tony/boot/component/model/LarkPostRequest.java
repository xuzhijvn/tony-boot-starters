/*
 *       Copyright© (2020) Lalamove Co., Ltd.
 */
package com.tony.boot.component.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * 飞书告警请求体
 *
 * @author tony
 * @create 2021-07-19
 * @description:
 */

public class LarkPostRequest {
    @JSONField(name = "msg_type")
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

            public class Href {
                private UrlVal urlVal = new UrlVal();

                public UrlVal getUrlVal() {
                    return urlVal;
                }

                public void setUrlVal(UrlVal urlVal) {
                    this.urlVal = urlVal;
                }

                public class UrlVal {
                    private String url = "https://www.feishu.com";

                    @JSONField(name = "android_url")
                    private String androidUrl = "https://developer.android.com/";

                    @JSONField(name = "ios_url")
                    private String iosUrl = "lark://msgcard/unsupported_action";

                    @JSONField(name = "pc_url")
                    private String pcUrl = "https://www.feishu.com";
                }
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

                private String content = "⚠️警告⚠️";

                public void setContent(String content) {
                    if (content != null) {
                        this.content = "⚠️" + content + "⚠️️";
                    }
                }
            }
        }

        public class Config {
            @JSONField(name = "wide_screen_mode")
            private boolean wideScreenMode = true;

            public boolean isWideScreenMode() {
                return wideScreenMode;
            }

            public void setWideScreenMode(boolean wideScreenMode) {
                this.wideScreenMode = wideScreenMode;
            }
        }
    }
}

