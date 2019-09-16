package com.lai.myapplication.bean

import com.chad.library.adapter.base.entity.MultiItemEntity

/**
 *
 * @author  Lai
 *
 * @time 2019/9/14 17:55
 * @describe describe
 *
 */
class HomeListInfo : MultiItemEntity {


    companion object {
        const val TITLE_TYPE = 1
        const val CONTENT_STYLE_1 = 2
        const val CONTENT_STYLE_2 = 3
        const val BANNER_TYPE = 4
        const val LIST_SQUARE_TYPE = 5
        const val LIST_RECTANGLE_TYPE = 6
        const val HEARD_TYPE = 7
    }

    var url: String = ""
    var title: String = ""
    var listIndex = 0
    var describe: String = ""
    var videoTemplatesBean: HomeResponse.DataBean.VideosBean.VideoTemplatesBean? = null
    var middleBanner: List<HomeResponse.DataBean.BannerBean>? = null
    var heardBanner: List<HomeResponse.DataBean.BannerBean>? = null
    var squareVideos: List<HomeResponse.DataBean.VideosBean.VideoTemplatesBean>? = null
    var minorIconList: List<HomeResponse.DataBean.MinorIconListBean>? = null
    var rectangleVideos: List<HomeResponse.DataBean.VideosBean.VideoTemplatesBean>? = null

    var currType = TITLE_TYPE


    override fun getItemType(): Int {
        return currType
    }

}