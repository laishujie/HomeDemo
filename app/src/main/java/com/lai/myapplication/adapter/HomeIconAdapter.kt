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
 * @time 2019/9/16 1:04
 * @describe describe
 *
 */
class HomeIconAdapter(minorIconList: List<HomeResponse.DataBean.MinorIconListBean>) :
    BaseQuickAdapter<HomeResponse.DataBean.MinorIconListBean, BaseViewHolder>(
        R.layout.item_icon,
        minorIconList
    ) {

    private var mItemSize = 0

    init {
        mItemSize = (DisplayUtils.getScreenWidth() - DisplayUtils.dp2px(16f)) / 4
    }

    override fun convert(helper: BaseViewHolder?, item: HomeResponse.DataBean.MinorIconListBean?) {

        helper?.itemView?.layoutParams?.width = mItemSize

        helper?.setText(
            R.id.tv_title,
            item?.adName
        )
        GlideUtils.loadImage(
            mContext,
            item?.picUrl,
            helper!!.getView(R.id.iv_cover)
        )
    }
}