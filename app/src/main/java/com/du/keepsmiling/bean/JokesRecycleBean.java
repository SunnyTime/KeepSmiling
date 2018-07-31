package com.du.keepsmiling.bean;

import com.du.keepsmiling.base.BaseBean;

import java.util.List;

/**
 * ClassName: JokesRecycleBean
 * Function: JokesRecycleBean
 * date: 2018/5/8.
 * author dushiguang
 * version 0.1.
 */
public class JokesRecycleBean extends BaseBean {
    String ret_code;
    PageBean pagebean;

    public String getRet_code() {
        return ret_code;
    }

    public void setRet_code(String ret_code) {
        this.ret_code = ret_code;
    }

    public PageBean getPagebean() {
        return pagebean;
    }

    public void setPagebean(PageBean pagebean) {
        this.pagebean = pagebean;
    }

    public class PageBean {
        String allPages;//所有页
        String currentPage;//当前页
        String allNum;//所有数量
        List<contentBeanlist> contentlist;
        String maxResult;//每页最大数量

        public String getAllPages() {
            return allPages;
        }

        public void setAllPages(String allPages) {
            this.allPages = allPages;
        }

        public String getCurrentPage() {
            return currentPage;
        }

        public void setCurrentPage(String currentPage) {
            this.currentPage = currentPage;
        }

        public String getAllNum() {
            return allNum;
        }

        public void setAllNum(String allNum) {
            this.allNum = allNum;
        }

        public List<contentBeanlist> getContentlist() {
            return contentlist;
        }

        public void setContentlist(List<contentBeanlist> contentlist) {
            this.contentlist = contentlist;
        }

        public String getMaxResult() {
            return maxResult;
        }

        public void setMaxResult(String maxResult) {
            this.maxResult = maxResult;
        }
    }

    public class contentBeanlist {
        String text;
        String hate;
        String videotime;
        String voicetime;
        String weixin_url;
        String profile_image;
        String width;
        String voiceuri;
        String type;
        String love;
        String id;
        String height;
        String voicelength;
        String name;
        String create_time;
        String _id;
        String video_uri;
        String cdn_img;
        String image0;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getHate() {
            return hate;
        }

        public void setHate(String hate) {
            this.hate = hate;
        }

        public String getVideotime() {
            return videotime;
        }

        public void setVideotime(String videotime) {
            this.videotime = videotime;
        }

        public String getVoicetime() {
            return voicetime;
        }

        public void setVoicetime(String voicetime) {
            this.voicetime = voicetime;
        }

        public String getWeixin_url() {
            return weixin_url;
        }

        public void setWeixin_url(String weixin_url) {
            this.weixin_url = weixin_url;
        }

        public String getProfile_image() {
            return profile_image;
        }

        public void setProfile_image(String profile_image) {
            this.profile_image = profile_image;
        }

        public String getWidth() {
            return width;
        }

        public void setWidth(String width) {
            this.width = width;
        }

        public String getVoiceuri() {
            return voiceuri;
        }

        public void setVoiceuri(String voiceuri) {
            this.voiceuri = voiceuri;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getLove() {
            return love;
        }

        public void setLove(String love) {
            this.love = love;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
        }

        public String getVoicelength() {
            return voicelength;
        }

        public void setVoicelength(String voicelength) {
            this.voicelength = voicelength;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getVideo_uri() {
            return video_uri;
        }

        public void setVideo_uri(String video_uri) {
            this.video_uri = video_uri;
        }

        public String getCdn_img() {
            return cdn_img;
        }

        public void setCdn_img(String cdn_img) {
            this.cdn_img = cdn_img;
        }

        public String getImage0() {
            return image0;
        }

        public void setImage0(String image0) {
            this.image0 = image0;
        }
    }
}
