package com.lai.myapplication.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.lai.myapplication.R
import com.lai.myapplication.bean.HomeResponse
import com.lai.myapplication.utils.DisplayUtils
import com.lai.myapplication.utils.GlideUtils

/**
 *
 * @author  Lai
 *
 * @time 2019/9/16 0:12
 * @describe describe
 *
 */
class HomeRectangleAdapter(list: List<HomeResponse.DataBean.VideosBean.VideoTemplatesBean>) :
    BaseQuickAdapter<HomeResponse.DataBean.VideosBean.VideoTemplatesBean, BaseViewHolder>(
        R.layout.item_content_1, list
    ) {

    private val overSizeWidth: Int = DisplayUtils.dp2px(284f)
    private val overSizeHeight: Int = DisplayUtils.dp2px(150f)


    override fun convert(helper: BaseViewHolder?, item: HomeResponse.DataBean.VideosBean.VideoTemplatesBean?) {
        helper?.setText(
            R.id.tv_title,
            item?.name
        )
        GlideUtils.loadImage(
            mContext,
            item?.coverUrl,
            helper!!.getView(R.id.iv_cover),
            overSizeWidth,
            overSizeHeight
        )
    }

}