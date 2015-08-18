package com.bogle.frame.weixin.message;


import com.bogle.frame.weixin.message.menu.ScanCodeInfo;
import com.bogle.frame.weixin.message.menu.SendLocationInfo;
import com.bogle.frame.weixin.message.menu.SendPicsInfo;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/5/25.
 */
@XStreamAlias("xml")
public class Message implements Serializable {

    // -------------------------------------------------------------通用信息----------------------------------------------------
    // 接收方帐号（收到的OpenID）
    @XStreamAlias("ToUserName")
    private String toUserName;
    // 开发者微信号
    @XStreamAlias("FromUserName")
    private String fromUserName;
    // 消息创建时间 （整型）
    @XStreamAlias("CreateTime")
    private long createTime;//消息创建时间 （整型）
    // 消息类型（text/music/news）
    @XStreamAlias("MsgType")
    private String msgType;
    //消息id，64位整型
    @XStreamAlias("MsgId")
    private String msgId;
    //消息id，64位整型
    @XStreamAlias("MsgID")
    private String msgID;
    @XStreamAlias("Status")
    private String status;
    // -------------------------------------------------------------通用信息----------------------------------------------------

    ////////////////////////////////////////////////////////////////请求//////////////////////////////////////////////////////////////
    /**
     * 文本消息
     * <xml>
     * <ToUserName><![CDATA[toUser]]></ToUserName>
     * <FromUserName><![CDATA[fromUser]]></FromUserName>
     * <CreateTime>1348831860</CreateTime>
     * <MsgType><![CDATA[text]]></MsgType>
     * <Content><![CDATA[this is a test]]></Content>
     * <MsgId>1234567890123456</MsgId>
     * </xml>
     */
    //文本消息
    @XStreamAlias("Content")
    private String content;//文本消息内容


    /**
     * 图片消息
     * <xml>
     * <ToUserName><![CDATA[toUser]]></ToUserName>
     * <FromUserName><![CDATA[fromUser]]></FromUserName>
     * <CreateTime>1348831860</CreateTime>
     * <MsgType><![CDATA[image]]></MsgType>
     * <PicUrl><![CDATA[this is a url]]></PicUrl>
     * <MediaId><![CDATA[media_id]]></MediaId>
     * <MsgId>1234567890123456</MsgId>
     * </xml>
     */
    //图片消息
    @XStreamAlias("PicUrl")
    private String picUrl;//图片链接


    @XStreamAlias("MediaId")
    private String mediaId;//图片消息媒体id，可以调用多媒体文件下载接口拉取数据。

    /**
     * 语音消息
     * <p/>
     * <xml>
     * <ToUserName><![CDATA[toUser]]></ToUserName>
     * <FromUserName><![CDATA[fromUser]]></FromUserName>
     * <CreateTime>1357290913</CreateTime>
     * <MsgType><![CDATA[voice]]></MsgType>
     * <MediaId><![CDATA[media_id]]></MediaId>
     * <Format><![CDATA[Format]]></Format>
     * <MsgId>1234567890123456</MsgId>
     * </xml>
     */
    @XStreamAlias("Format")
    private String format;//语音格式，如amr，speex等
    /**
     * 视频消息
     * <p/>
     * <xml>
     * <ToUserName><![CDATA[toUser]]></ToUserName>
     * <FromUserName><![CDATA[fromUser]]></FromUserName>
     * <CreateTime>1357290913</CreateTime>
     * <MsgType><![CDATA[video]]></MsgType>
     * <MediaId><![CDATA[media_id]]></MediaId>
     * <ThumbMediaId><![CDATA[thumb_media_id]]></ThumbMediaId>
     * <MsgId>1234567890123456</MsgId>
     * </xml>
     */
    @XStreamAlias("ThumbMediaId")
    private String thumbMediaId;//视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据。

    /**
     * 地理位置消息
     * <p/>
     * <xml>
     * <ToUserName><![CDATA[toUser]]></ToUserName>
     * <FromUserName><![CDATA[fromUser]]></FromUserName>
     * <CreateTime>1351776360</CreateTime>
     * <MsgType><![CDATA[location]]></MsgType>
     * <Location_X>23.134521</Location_X>
     * <Location_Y>113.358803</Location_Y>
     * <Scale>20</Scale>
     * <Label><![CDATA[位置信息]]></Label>
     * <MsgId>1234567890123456</MsgId>
     * </xml>
     */
    @XStreamAlias("Location_X")
    private String locationX;//	地理位置维度
    @XStreamAlias("Location_Y")
    private String locationY;//	地理位置经度
    @XStreamAlias("Scale")
    private String scale;//地图缩放大小
    @XStreamAlias("Label")
    private String label;//	地理位置信息

    /**
     * 链接消息
     * <p/>
     * <xml>
     * <ToUserName><![CDATA[toUser]]></ToUserName>
     * <FromUserName><![CDATA[fromUser]]></FromUserName>
     * <CreateTime>1351776360</CreateTime>
     * <MsgType><![CDATA[link]]></MsgType>
     * <Title><![CDATA[公众平台官网链接]]></Title>
     * <Description><![CDATA[公众平台官网链接]]></Description>
     * <Url><![CDATA[url]]></Url>
     * <MsgId>1234567890123456</MsgId>
     * </xml>
     */
    @XStreamAlias("Title")
    private String title;//	消息标题
    @XStreamAlias("Description")
    private String description;//	消息描述
    @XStreamAlias("Url")
    private String url;//	消息链接


    //接收事件推送
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
    @XStreamAlias("Event")
    private String event;//事件类型，subscribe(订阅)、unsubscribe(取消订阅)

    /**
     * 扫描带参数二维码事件
     * <p/>
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
     */
    @XStreamAlias("EventKey")
    private String eventKey;//	事件KEY值，qrscene_为前缀，后面为二维码的参数值
    @XStreamAlias("Ticket")
    private String ticket;//二维码的ticket，可用来换取二维码图片
    @XStreamAlias("Latitude")
    private String latitude;//	地理位置纬度
    @XStreamAlias("Longitude")
    private String longitude;//	地理位置经度
    @XStreamAlias("Precision")
    private String precision;//地理位置精度

    /**
     * 接收语音识别结果
     * 开通语音识别功能，用户每次发送语音给公众号时，微信会在推送的语音消息XML数据包中，增加一个Recongnition字段。
     * <p/>
     * 注：由于客户端缓存，开发者开启或者关闭语音识别功能，对新关注者立刻生效，对已关注用户需要24小时生效。开发者可以重新关注此帐号进行测试。
     * <p/>
     * 开启语音识别后的语音XML数据包如下：
     * <p/>
     * <xml>
     * <ToUserName><![CDATA[toUser]]></ToUserName>
     * <FromUserName><![CDATA[fromUser]]></FromUserName>
     * <CreateTime>1357290913</CreateTime>
     * <MsgType><![CDATA[voice]]></MsgType>
     * <MediaId><![CDATA[media_id]]></MediaId>
     * <Format><![CDATA[Format]]></Format>
     * <Recognition><![CDATA[腾讯微信团队]]></Recognition>
     * <MsgId>1234567890123456</MsgId>
     * </xml>
     */
    @XStreamAlias("Recognition")
    private String recognition;//语音识别结果，UTF8编码


    ////////////////////////////////////////////////////////////////响应//////////////////////////////////////////////////////////////
    /**
     * 回复图片消息
     * <p/>
     * <xml>
     * <ToUserName><![CDATA[toUser]]></ToUserName>
     * <FromUserName><![CDATA[fromUser]]></FromUserName>
     * <CreateTime>12345678</CreateTime>
     * <MsgType><![CDATA[image]]></MsgType>
     * <Image>
     * <MediaId><![CDATA[media_id]]></MediaId>
     * </Image>
     * </xml>
     */
    @XStreamAlias("Image")
    private Media image;

    /**
     * 回复语音消息
     * <p/>
     * <xml>
     * <ToUserName><![CDATA[toUser]]></ToUserName>
     * <FromUserName><![CDATA[fromUser]]></FromUserName>
     * <CreateTime>12345678</CreateTime>
     * <MsgType><![CDATA[voice]]></MsgType>
     * <Voice>
     * <MediaId><![CDATA[media_id]]></MediaId>
     * </Voice>
     * </xml>
     */
    @XStreamAlias("Voice")
    private Media voice;

    /**
     * 回复视频消息
     * <p/>
     * <xml>
     * <ToUserName><![CDATA[toUser]]></ToUserName>
     * <FromUserName><![CDATA[fromUser]]></FromUserName>
     * <CreateTime>12345678</CreateTime>
     * <MsgType><![CDATA[video]]></MsgType>
     * <Video>
     * <MediaId><![CDATA[media_id]]></MediaId>
     * <Title><![CDATA[title]]></Title>
     * <Description><![CDATA[description]]></Description>
     * </Video>
     * </xml>
     */
    @XStreamAlias("Video")
    private Media video;

    /**
     * 音乐消息
     * <xml>
     * <ToUserName><![CDATA[toUser]]></ToUserName>
     * <FromUserName><![CDATA[fromUser]]></FromUserName>
     * <CreateTime>12345678</CreateTime>
     * <MsgType><![CDATA[music]]></MsgType>
     * <Music>
     * <Title><![CDATA[TITLE]]></Title>
     * <Description><![CDATA[DESCRIPTION]]></Description>
     * <MusicUrl><![CDATA[MUSIC_Url]]></MusicUrl>
     * <HQMusicUrl><![CDATA[HQ_MUSIC_Url]]></HQMusicUrl>
     * <ThumbMediaId><![CDATA[media_id]]></ThumbMediaId>
     * </Music>
     * </xml>
     */
    @XStreamAlias("Music")
    private Media music;

    /**
     * 回复图文消息
     * <p/>
     * <xml>
     * <ToUserName><![CDATA[toUser]]></ToUserName>
     * <FromUserName><![CDATA[fromUser]]></FromUserName>
     * <CreateTime>12345678</CreateTime>
     * <MsgType><![CDATA[news]]></MsgType>
     * <ArticleCount>2</ArticleCount>
     * <Articles>
     * <item>
     * <Title><![CDATA[title1]]></Title>
     * <Description><![CDATA[description1]]></Description>
     * <PicUrl><![CDATA[picurl]]></PicUrl>
     * <Url><![CDATA[url]]></Url>
     * </item>
     * <item>
     * <Title><![CDATA[title]]></Title>
     * <Description><![CDATA[description]]></Description>
     * <PicUrl><![CDATA[picurl]]></PicUrl>
     * <Url><![CDATA[url]]></Url>
     * </item>
     * </Articles>
     * </xml>
     */
    @XStreamAlias("Articles")
    private List<Item> articles = new ArrayList<Item>();

    @XStreamAlias("ArticleCount")
    private Integer articleCount;//	图文消息个数，限制为10条以内

    /**
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
    @XStreamAlias("UniqId")
    private String uniqId;//商户自己内部ID，即字段中的sid

    @XStreamAlias("PoiId")
    private String poiId;//微信的门店ID，微信内门店唯一标示ID

    @XStreamAlias("Result")
    private String result;//审核结果，成功succ 或失败fail

    @XStreamAlias("Msg")
    private String msg;//成功的通知信息，或审核失败的驳回理由

    /**
     * 审核事件推送
     * 生成的卡券通过审核时，微信会把这个事件推送到开发者填写的URL。
     * <xml> <ToUserName><![CDATA[toUser]]></ToUserName>
     * <FromUserName><![CDATA[FromUser]]></FromUserName>
     * <CreateTime>123456789</CreateTime>
     * <MsgType><![CDATA[event]]></MsgType>
     * <Event><![CDATA[card_pass_check]]></Event>  //不通过为card_not_pass_check
     * <CardId><![CDATA[cardid]]></CardId>
     * </xml>
     * ToUserName	开发者微信号
     * FromUserName	发送方帐号（一个OpenID）
     * CreateTime	消息创建时间 （整型）
     * MsgType	消息类型，event
     * Event	事件类型，card_pass_check(卡券通过审核)、card_not_pass_check（卡券未通过审核）
     * CardId	卡券ID
     */
    @XStreamAlias("CardId")
    private String cardId;//卡券ID

    @XStreamAlias("IsGiveByFriend")
    private Integer giveByFriend;//是否为转赠，1代表是，0代表否。

    @XStreamAlias("FriendUserName")
    private String friendUserName;//赠送方账号（一个OpenID），"IsGiveByFriend”为1时填写该参数。

    @XStreamAlias("UserCardCode")
    private String userCardCode;//code序列号。自定义code及非自定义code的卡券被领取后都支持事件推送。

    @XStreamAlias("OldUserCardCode")
    private String oldUserCardCode;//转赠前的code序列号。

    @XStreamAlias("OuterId")
    private String outerId;//领取场景值，用于领取渠道数据统计。可在生成二维码接口及添加JS API接口中自定义该字段的整型值。


    @XStreamAlias("ScanCodeInfo")
    private ScanCodeInfo scanCodeInfo;//扫描信息

    @XStreamAlias("SendPicsInfo")
    private SendPicsInfo sendPicsInfo;//发送的位置信息

    @XStreamAlias("SendLocationInfo")
    private SendLocationInfo sendLocationInfo;//发送的位置信息


    @XStreamAlias("ConsumeSource")
    private String consumeSource;

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getThumbMediaId() {
        return thumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        this.thumbMediaId = thumbMediaId;
    }

    public String getLocationX() {
        return locationX;
    }

    public void setLocationX(String locationX) {
        this.locationX = locationX;
    }

    public String getLocationY() {
        return locationY;
    }

    public void setLocationY(String locationY) {
        this.locationY = locationY;
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getEventKey() {
        return eventKey;
    }

    public void setEventKey(String eventKey) {
        this.eventKey = eventKey;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getPrecision() {
        return precision;
    }

    public void setPrecision(String precision) {
        this.precision = precision;
    }

    public String getRecognition() {
        return recognition;
    }

    public void setRecognition(String recognition) {
        this.recognition = recognition;
    }

    public Media getImage() {
        return image;
    }

    public void setImage(Media image) {
        this.image = image;
    }

    public Media getVoice() {
        return voice;
    }

    public void setVoice(Media voice) {
        this.voice = voice;
    }

    public Media getVideo() {
        return video;
    }

    public void setVideo(Media video) {
        this.video = video;
    }

    public Media getMusic() {
        return music;
    }

    public void setMusic(Media music) {
        this.music = music;
    }

    public List<Item> getArticles() {
        return articles;
    }

    public void setArticles(List<Item> articles) {
        this.articles = articles;
    }

    public Integer getArticleCount() {
        return articleCount;
    }

    public void setArticleCount(Integer articleCount) {
        this.articleCount = articleCount;
    }

    public String getUniqId() {
        return uniqId;
    }

    public void setUniqId(String uniqId) {
        this.uniqId = uniqId;
    }

    public String getPoiId() {
        return poiId;
    }

    public void setPoiId(String poiId) {
        this.poiId = poiId;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public Integer getGiveByFriend() {
        return giveByFriend;
    }

    public void setGiveByFriend(Integer giveByFriend) {
        this.giveByFriend = giveByFriend;
    }

    public String getFriendUserName() {
        return friendUserName;
    }

    public void setFriendUserName(String friendUserName) {
        this.friendUserName = friendUserName;
    }

    public String getUserCardCode() {
        return userCardCode;
    }

    public void setUserCardCode(String userCardCode) {
        this.userCardCode = userCardCode;
    }

    public String getOldUserCardCode() {
        return oldUserCardCode;
    }

    public void setOldUserCardCode(String oldUserCardCode) {
        this.oldUserCardCode = oldUserCardCode;
    }

    public String getOuterId() {
        return outerId;
    }

    public void setOuterId(String outerId) {
        this.outerId = outerId;
    }

    public String getConsumeSource() {
        return consumeSource;
    }

    public void setConsumeSource(String consumeSource) {
        this.consumeSource = consumeSource;
    }

    public String getMsgID() {
        return msgID;
    }

    public void setMsgID(String msgID) {
        this.msgID = msgID;
    }

    public ScanCodeInfo getScanCodeInfo() {
        return scanCodeInfo;
    }

    public void setScanCodeInfo(ScanCodeInfo scanCodeInfo) {
        this.scanCodeInfo = scanCodeInfo;
    }

    public SendPicsInfo getSendPicsInfo() {
        return sendPicsInfo;
    }

    public void setSendPicsInfo(SendPicsInfo sendPicsInfo) {
        this.sendPicsInfo = sendPicsInfo;
    }

    public SendLocationInfo getSendLocationInfo() {
        return sendLocationInfo;
    }

    public void setSendLocationInfo(SendLocationInfo sendLocationInfo) {
        this.sendLocationInfo = sendLocationInfo;
    }
}
