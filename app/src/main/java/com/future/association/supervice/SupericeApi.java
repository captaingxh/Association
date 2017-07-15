package com.future.association.supervice;

import android.content.Context;

import com.future.association.supervice.model.SupericeDetail;
import com.future.association.supervice.model.SupericerTypeList;
import com.future.baselib.utils.HttpRequest;

/**
 * Created by rain on 2017/7/14.
 */

public class SupericeApi {

    public static final String SUPERICE_TYPE_LIST_APICODE = "_jdfenlei_001";
    public static final String SUPERICE_LIST_APICODE = "_jdliebiao_001";
    public static final String SUPERICE_TYPE_DETAIL_APICODE = "_jdxiangqing_001";
    public static final String SUPERICE_TYPE_PUBLISH_APICODE = "_jdfabu_001";

    private static SupericeApi instance = null;

    private SupericeApi (){}

    public synchronized static SupericeApi getInstance(){
        if (instance == null){
            instance = new SupericeApi();
        }
        return instance;
    }

    /*
* 监督分类
* */
    public HttpRequest getSupericeTypeList(Context context,String token){
        return new HttpRequest<SupericerTypeList>()
                .with(context)
                .addParam("apiCode",SUPERICE_TYPE_LIST_APICODE);
    }

/*
* 监督列表
* */
    public HttpRequest getSupericeList(Context context,String page){
        return new HttpRequest<SupericerTypeList>()
                .with(context)
                .addParam("apiCode",SUPERICE_LIST_APICODE)
                .addParam("page",page)
                .addParam("size","20");
    }
/*
* 监督详情
* */
    public HttpRequest getSupericeTypeDetail(Context context,String id){
        return new HttpRequest<SupericeDetail>()
                .with(context)
                .addParam("apiCode",SUPERICE_TYPE_DETAIL_APICODE)
                .addParam("id",id);
    }
/*
* 监督发布
* */
    public HttpRequest getSupericeTypePublish(Context context,String token,String id){
        return new HttpRequest<SupericerTypeList>()
                .with(context)
                .addParam("apiCode",SUPERICE_TYPE_PUBLISH_APICODE)
                .addParam("token",token)
                .addParam("id",id);
    }
}
