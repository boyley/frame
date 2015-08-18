package com.bogle.frame.weixin.defines;

/**
 * Created by Administrator on 2015/7/13.
 */
public enum Event {
    // 事件类型，subscribe(订阅)、unsubscribe(取消订阅)

    /**
     * 关注/取消关注事件
     * <p/>
     * 用户在关注与取消关注公众号时，微信会把这个事件推送到开发者填写的URL。方便开发者给用户下发欢迎消息或者做帐号的解绑。
     * <p/>
     * 微信服务器在五秒内收不到响应会断掉连接，并且重新发起请求，总共重试三次
     * <p/>
     * 关于重试的消息排重，推荐使用FromUserName + CreateTime 排重。
     * <p/>
     * 假如服务器无法保证在五秒内处理并回复，可以直接回复空串，微信服务器不会对此作任何处理，并且不会发起重试。
     * <p/>
     * 推送XML数据包示例：
     * <p/>
     * <xml>
     * <ToUserName><![CDATA[toUser]]></ToUserName>
     * <FromUserName><![CDATA[FromUser]]></FromUserName>
     * <CreateTime>123456789</CreateTime>
     * <MsgType><![CDATA[event]]></MsgType>
     * <Event><![CDATA[subscribe]]></Event>
     * </xml>
     */
    SUBSCRIBE {
        @Override
        public String toString() {
            return "subscribe";//subscribe(订阅)
        }

    },
    UNSUBSCRIBE {
        @Override
        public String toString() {
            return "unsubscribe";//unsubscribe(取消订阅)
        }
    },

    /**
     * 用户扫描带场景值二维码时，可能推送以下两种事件：
     * <p/>
     * 如果用户还未关注公众号，则用户可以关注公众号，关注后微信会将带场景值关注事件推送给开发者。
     * 如果用户已经关注公众号，则微信会将带场景值扫描事件推送给开发者。
     * 1. 用户未关注时，进行关注后的事件推送
     * <p/>
     * 推送XML数据包示例：
     * <p/>
     * <xml><ToUserName><![CDATA[toUser]]></ToUserName>
     * <FromUserName><![CDATA[FromUser]]></FromUserName>
     * <CreateTime>123456789</CreateTime>
     * <MsgType><![CDATA[event]]></MsgType>
     * <Event><![CDATA[subscribe]]></Event>
     * <EventKey><![CDATA[qrscene_123123]]></EventKey>
     * <Ticket><![CDATA[TICKET]]></Ticket>
     * </xml>
     * 参数说明：
     * <p/>
     * 参数	描述
     * ToUserName	开发者微信号
     * FromUserName	发送方帐号（一个OpenID）
     * CreateTime	消息创建时间 （整型）
     * MsgType	消息类型，event
     * Event	事件类型，subscribe
     * EventKey	事件KEY值，qrscene_为前缀，后面为二维码的参数值
     * Ticket	二维码的ticket，可用来换取二维码图片
     */
    KEY_QRSCENE {
        @Override
        public String toString() {
            return "qrscene_";//事件KEY值，qrscene_为前缀，后面为二维码的参数值
        }
    },

    /**
     * 上报地理位置事件
     * <p/>
     * 用户同意上报地理位置后，每次进入公众号会话时，都会在进入时上报地理位置，或在进入会话后每5秒上报一次地理位置，公众号可以在公众平台网站中修改以上设置。上报地理位置时，微信会将上报地理位置事件推送到开发者填写的URL。
     * <p/>
     * 推送XML数据包示例：
     * <p/>
     * <xml>
     * <ToUserName><![CDATA[toUser]]></ToUserName>
     * <FromUserName><![CDATA[fromUser]]></FromUserName>
     * <CreateTime>123456789</CreateTime>
     * <MsgType><![CDATA[event]]></MsgType>
     * <Event><![CDATA[LOCATION]]></Event>
     * <Latitude>23.137466</Latitude>
     * <Longitude>113.352425</Longitude>
     * <Precision>119.385040</Precision>
     * </xml>
     * 参数说明：
     * <p/>
     * 参数	描述
     * ToUserName	开发者微信号
     * FromUserName	发送方帐号（一个OpenID）
     * CreateTime	消息创建时间 （整型）
     * MsgType	消息类型，event
     * Event	事件类型，LOCATION
     * Latitude	地理位置纬度
     * Longitude	地理位置经度
     * Precision	地理位置精度
     */
    LOCATION {
        @Override
        public String toString() {
            return "LOCATION";
        }
    },


    /**
     * 2. 用户已关注时的事件推送
     * <p/>
     * 推送XML数据包示例：
     * <p/>
     * <xml>
     * <ToUserName><![CDATA[toUser]]></ToUserName>
     * <FromUserName><![CDATA[FromUser]]></FromUserName>
     * <CreateTime>123456789</CreateTime>
     * <MsgType><![CDATA[event]]></MsgType>
     * <Event><![CDATA[SCAN]]></Event>
     * <EventKey><![CDATA[SCENE_VALUE]]></EventKey>
     * <Ticket><![CDATA[TICKET]]></Ticket>
     * </xml>
     * 参数说明：
     * <p/>
     * 参数	描述
     * ToUserName	开发者微信号
     * FromUserName	发送方帐号（一个OpenID）
     * CreateTime	消息创建时间 （整型）
     * MsgType	消息类型，event
     * Event	事件类型，SCAN
     * EventKey	事件KEY值，是一个32位无符号整数，即创建二维码时的二维码scene_id
     * Ticket	二维码的ticket，可用来换取二维码图片
     */
    SCAN {
        @Override
        public String toString() {
            return "SCAN";
        }
    },
    /**
     * 3.审核事件推送
     * <p/>
     * 新创建的门店在审核通过后，会以事件形式推送给商户填写的回调URL（登陆公众平台进入“开发者中心”设置）
     * <p/>
     * 微信服务器在五秒内收不到响应会断掉连接，并且重新发起请求，总共重试三次。 关于重试的消息排重，推荐使用FromUserName + CreateTime 排重。
     * <p/>
     * 假如服务器无法保证在五秒内处理并回复，可以直接回复空串，微信服务器不会对此作任何处理，并且不会发起重试。
     * <p/>
     * 推送XML数据包示例：
     * <p/>
     * <xml>
     * <ToUserName><![CDATA[toUser]]></ToUserName>
     * <FromUserName><![CDATA[fromUser]]></FromUserName>
     * <CreateTime>1408622107</CreateTime>
     * <MsgType><![CDATA[event]]></MsgType>
     * <Event><![CDATA[poi_check_notify]]></Event>
     * <UniqId><![CDATA[123adb]]></UniqId>
     * <PoiId><![CDATA[123123]]></PoiId>
     * <Result><![CDATA[fail]]></Result>
     * <Msg><![CDATA[xxxxxx]]></Msg>
     * </xml>
     */
    POI_CHECK_NOTIFY {
        @Override
        public String toString() {
            return "poi_check_notify";
        }
    },
    /**
     * 卡券通过审核
     */
    CARD_PASS_CHECK {
        @Override
        public String toString() {
            return "card_pass_check";
        }
    },
    /**
     * 卡券未通过审核
     */
    CARD_NOT_PASS_CHECK {
        @Override
        public String toString() {
            return "card_not_pass_check";
        }
    },
    /**
     * 用户领取卡券
     * 领取事件推送
     * <p/>
     * 用户在领取卡券时，微信会把这个事件推送到开发者填写的URL。 推送XML数据包示例：
     * <p/>
     * <xml> <ToUserName><![CDATA[toUser]]></ToUserName>
     * <FromUserName><![CDATA[FromUser]]></FromUserName>
     * <FriendUserName><![CDATA[FriendUser]]></FriendUserName>
     * <CreateTime>123456789</CreateTime>
     * <MsgType><![CDATA[event]]></MsgType>
     * <Event><![CDATA[user_get_card]]></Event>
     * <CardId><![CDATA[cardid]]></CardId>
     * <IsGiveByFriend>1</IsGiveByFriend>
     * <UserCardCode><![CDATA[12312312]]></UserCardCode>
     * <OuterId>0</OuterId>
     * </xml>
     * <p/>
     * 参数说明：
     * <p/>
     * 参数	说明
     * ToUserName	开发者微信号。
     * FromUserName	领券方帐号（一个OpenID）。
     * FriendUserName	赠送方账号（一个OpenID），"IsGiveByFriend”为1时填写该参数。
     * CreateTime	消息创建时间 （整型）。
     * MsgType	消息类型，event
     * Event	事件类型，user_get_card(用户领取卡券)。
     * CardId	卡券ID。
     * IsGiveByFriend	是否为转赠，1代表是，0代表否。
     * UserCardCode	code序列号。自定义code及非自定义code的卡券被领取后都支持事件推送。
     * OldUserCardCode	转赠前的code序列号。
     * OuterId	领取场景值，用于领取渠道数据统计。可在生成二维码接口及添加JS API接口中自定义该字段的整型值。
     */
    USER_GET_CARD {
        @Override
        public String toString() {
            return "user_get_card";
        }
    },
    /**
     * 删除事件推送
     * <p/>
     * 用户在删除卡券时，微信会把这个事件推送到开发者填写的URL。 推送XML数据包示例：
     * <p/>
     * <xml> <ToUserName><![CDATA[toUser]]></ToUserName>
     * <FromUserName><![CDATA[FromUser]]></FromUserName>
     * <CreateTime>123456789</CreateTime>
     * <MsgType><![CDATA[event]]></MsgType>
     * <Event><![CDATA[user_del_card]]></Event>
     * <CardId><![CDATA[cardid]]></CardId>
     * <UserCardCode><![CDATA[12312312]]></UserCardCode>
     * </xml>
     * 参数说明：
     * <p/>
     * 参数	          说明
     * ToUserName	开发者微信号。
     * FromUserName	发送方帐号（一个OpenID）
     * CreateTime	消息创建时间 （整型）。
     * MsgType	消息类型，event
     * Event	事件类型，user_del_card(用户删除卡券)
     * CardId	卡券ID。
     * UserCardCode	code序列号。自定义code及非自定义code的卡券被领取后都支持事件推送。
     */
    USER_DEL_CARD {
        @Override
        public String toString() {
            return "user_del_card";
        }
    },
    /**
     * 核销事件推送
     * <p/>
     * 卡券被核销时，微信会把这个事件推送到开发者填写的URL。 推送XML数据包示例：
     * <p/>
     * <xml> <ToUserName><![CDATA[toUser]]></ToUserName>
     * <FromUserName><![CDATA[FromUser]]></FromUserName>
     * <CreateTime>123456789</CreateTime>
     * <MsgType><![CDATA[event]]></MsgType>
     * <Event><![CDATA[user_consume_card]]></Event>
     * <CardId><![CDATA[cardid]]></CardId>
     * <UserCardCode><![CDATA[12312312]]></UserCardCode>
     * <ConsumeSource><![CDATA[(FROM_API)]]></ConsumeSource>
     * </xml>
     * 参数	说明
     * ToUserName	开发者微信号。
     * FromUserName	发送方帐号（一个OpenID）。
     * CreateTime	消息创建时间 （整型）。
     * MsgType	消息类型，event。
     * Event	事件类型，user_consume_card(核销事件)
     * CardId	卡券ID。
     * UserCardCode	卡券Code码。
     * ConsumeSource	核销来源。支持开发者统计API核销（FROM_API）、公众平台核销（FROM_MP）、卡券商户助手核销（FROM_MOBILE_HELPER）（核销员微信号）
     */
    USER_CONSUME_CARD {
        @Override
        public String toString() {
            return "user_consume_card";
        }
    },
    /**
     * 进入会员卡事件推送
     * <p/>
     * 用户在进入会员卡时，微信会把这个事件推送到开发者填写的URL。 推送XML数据包示例：
     * <p/>
     * <xml> <ToUserName><![CDATA[toUser]]></ToUserName>
     * <FromUserName><![CDATA[FromUser]]></FromUserName>
     * <CreateTime>123456789</CreateTime>
     * <MsgType><![CDATA[event]]></MsgType>
     * <Event><![CDATA[user_view_card]]></Event>
     * <CardId><![CDATA[cardid]]></CardId>
     * <UserCardCode><![CDATA[12312312]]></UserCardCode>
     * </xml>
     * 参数说明：
     * <p/>
     * 参数	说明
     * ToUserName	开发者微信号。
     * FromUserName	发送方帐号（一个OpenID）。
     * CreateTime	消息创建时间 （整型）。
     * MsgType	消息类型，event。
     * Event	事件类型，user_view_card(核销事件)
     * CardId	卡券ID。
     * UserCardCode	商户自定义code值。非自定code推送为空串。
     */
    USER_VIEW_CARD {
        @Override
        public String toString() {
            return "user_view_card";
        }
    },
    /**
     * 从卡券进入公众号会话事件推送
     * <p/>
     * 用户在卡券里点击查看公众号进入会话时（需要用户已经关注公众号），微信会把这个事件推送到开发者填写的URL。开发者可识别从卡券进入公众号的用户身份。 推送XML数据包示例：
     * <p/>
     * <xml> <ToUserName><![CDATA[toUser]]></ToUserName>
     * <FromUserName><![CDATA[FromUser]]></FromUserName>
     * <CreateTime>123456789</CreateTime>
     * <MsgType><![CDATA[event]]></MsgType>
     * <Event><![CDATA[user_enter_session_from_card]]></Event>
     * <CardId><![CDATA[cardid]]></CardId>
     * <UserCardCode><![CDATA[12312312]]></UserCardCode>
     * </xml>
     * 参数说明：
     * <p/>
     * 参数	说明
     * ToUserName	开发者微信号。
     * FromUserName	发送方帐号（一个OpenID）
     * CreateTime	消息创建时间 （整型）。
     * MsgType	消息类型，event
     * Event	事件类型，user_enter_session_from_card(用户从卡券进入公众号会话)
     * CardId	卡券ID。
     * UserCardCode	Code码。
     */
    USER_ENTER_SESSION_FROM_CARD {
        @Override
        public String toString() {
            return "user_enter_session_from_card";
        }
    },
    CLICK {
        @Override
        public String toString() {
            return "CLICK";
        }
    },
    VIEW {
        @Override
        public String toString() {
            return "VIEW";
        }
    },
    SCANCODE_PUSH {
        @Override
        public String toString() {
            return "scancode_push";
        }
    },
    SCANCODE_WAITMSG {
        @Override
        public String toString() {
            return "scancode_waitmsg";
        }
    },
    PIC_SYSPHOTO {
        @Override
        public String toString() {
            return "pic_sysphoto";
        }
    },
    PIC_PHOTO_OR_ALBUM {
        @Override
        public String toString() {
            return "pic_photo_or_album";
        }
    },
    PIC_WEIXIN {
        @Override
        public String toString() {
            return "pic_weixin";
        }
    },
    LOCATION_SELECT {
        @Override
        public String toString() {
            return "location_select";
        }
    },
    MEDIA_ID {
        @Override
        public String toString() {
            return "media_id";
        }
    },
    VIEW_LIMITED {
        @Override
        public String toString() {
            return "view_limited";
        }
    }

}
