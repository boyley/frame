package com.bogle.frame.weixin.defines;

/**
 * 事件消息
 * Created by Administrator on 2015/5/25.
 */
public enum MsgType {

//    public static final String MESSAGETYPE_TEXT = "text";//文本消息
//    public static final String MESSAGETYPE_IMAGE = "image";//图片消息
//    public static final String MESSAGETYPE_VOICE = "voice";//语音消息
//    public static final String MESSAGETYPE_VIDEO = "video";//视频消息
//    public static final String MESSAGETYPE_SHORTVIDEO = "shortvideo";//小视频消息
//    public static final String MESSAGETYPE_LOCATION = "location";//地理位置消息
//    public static final String MESSAGETYPE_LINK = "link";//图片消息
//    public static final String MESSAGETYPE_EVENT = "event";//时间类型
//
//    public static final String MESSAGETYPE_NEWS = "news";//回复图文消息


    MESSAGETYPE_TEXT {
        @Override
        public String toString() {
            return "text";//文本消息
        }
    },
    MESSAGETYPE_IMAGE {
        @Override
        public String toString() {
            return "image";//图片消息
        }
    },
    MESSAGETYPE_VOICE {
        @Override
        public String toString() {
            return "voice";//语音消息
        }
    },
    MESSAGETYPE_VIDEO {
        @Override
        public String toString() {
            return "video";//视频消息
        }
    },
    MESSAGETYPE_MUSIC {
        @Override
        public String toString() {
            return "music";//音乐消息
        }
    },
    MESSAGETYPE_SHORTVIDEO {
        @Override
        public String toString() {
            return "shortvideo";//小视频消息
        }
    },
    MESSAGETYPE_LOCATION {
        @Override
        public String toString() {
            return "location";//地理位置消息
        }
    },
    MESSAGETYPE_LINK {
        @Override
        public String toString() {
            return "link";///图片消息
        }
    },
    MESSAGETYPE_EVENT {
        @Override
        public String toString() {
            return "event";//事件类型
        }
    },
    MESSAGETYPE_NEWS {
        @Override
        public String toString() {
            return "news";//回复图文消息
        }
    },

}
