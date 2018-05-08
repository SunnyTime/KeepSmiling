package com.du.keepsmiling.base;

import com.du.keepsmiling.utils.GsonUtil;

/**
 * ClassName: BaseBean
 * Function: BaseBean
 * date: 2018/5/8.
 * author dushiguang
 * version 0.1
 */
public class BaseBean {
    String showapi_res_error;
    String showapi_res_code;

    public String getShowapi_res_error() {
        return showapi_res_error;
    }

    public void setShowapi_res_error(String showapi_res_error) {
        this.showapi_res_error = showapi_res_error;
    }

    public String getShowapi_res_code() {
        return showapi_res_code;
    }

    public void setShowapi_res_code(String showapi_res_code) {
        this.showapi_res_code = showapi_res_code;
    }

    @Override
    public String toString() {
        return GsonUtil.toJsonString(this);
    }
}
