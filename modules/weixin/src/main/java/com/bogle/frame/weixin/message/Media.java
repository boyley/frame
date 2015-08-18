package com.bogle.frame.weixin.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.Serializable;


public class Media implements Serializable {

	/* 通过上传多媒体文件，得到的id。 */
	@XStreamAlias("MediaId")
	private String mediaId;

	// 音乐名称
	@XStreamAlias("Title")
	private String title;
	// 音乐描述
	@XStreamAlias("Description")
	private String description;
	// 音乐链接
	@XStreamAlias("MusicUrl")
	private String musicUrl;
	// 高质量音乐链接，WIFI环境优先使用该链接播放音乐
	@XStreamAlias("HQMusicUrl")
	private String hqmusicUrl;
	/* 缩略图的媒体id，通过上传多媒体文件，得到的id */
	@XStreamAlias("ThumbMediaId")
	private String thumbMediaId;

	// 图片链接，支持JPG、PNG格式，较好的效果为大图640*320，小图80*80，限制图片链接的域名需要与开发者填写的基本资料中的Url一致
	@XStreamAlias("PicUrl")
	private String picUrl;
	// 点击图文消息跳转链接
	@XStreamAlias("Url")
	private String url;

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
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

	public String getMusicUrl() {
		return musicUrl;
	}

	public void setMusicUrl(String musicUrl) {
		this.musicUrl = musicUrl;
	}

	public String getHqmusicUrl() {
		return hqmusicUrl;
	}

	public void setHqmusicUrl(String hqmusicUrl) {
		this.hqmusicUrl = hqmusicUrl;
	}

	public String getThumbMediaId() {
		return thumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		this.thumbMediaId = thumbMediaId;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
