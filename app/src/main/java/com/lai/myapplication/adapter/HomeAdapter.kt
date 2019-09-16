package com.lai.myapplication.adapter

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseQuickAdapter.SpanSizeLookup
import com.chad.library.adapter.base.BaseViewHolder
import com.lai.myapplication.R
import com.lai.myapplication.bean.HomeListInfo
import com.lai.myapplication.bean.HomeResponse
import com.lai.myapplication.utils.DisplayUtils
import com.lai.myapplication.utils.GlideUtils
import com.lai.myapplication.utils.HomeSortItemDecoration
import com.stx.xhb.androidx.XBanner


/**
 *
 * @author  Lai
 *
 * @time 2019/9/14 17:55
 * @describe describe
 *
 */
class HomeAdapter(list: List<HomeListInfo>) :
    BaseMultiItemQuickAdapter<HomeListInfo, BaseViewHolder>(list) {

    private val overSizeWidth: Int = DisplayUtils.dp2px(164f)
    private val overSizeHeight: Int = DisplayUtils.dp2px(245f)

    private var contentStyleWidth2: Int = DisplayUtils.dp2px(108f)
    private var contentStyleWidth1: Int = DisplayUtils.dp2px(164f)


    init {
        contentStyleWidth1 = (DisplayUtils.getScreenWidth() - DisplayUtils.dp2px(40f)) / 2
        contentStyleWidth2 = (DisplayUtils.getScreenWidth() - DisplayUtils.dp2px(48f)) / 3
        addItemType(HomeListInfo.HEARD_TYPE, R.layout.item_home_heard)
        addItemType(HomeListInfo.BANNER_TYPE, R.layout.item_type_banner)
        addItemType(HomeListInfo.TITLE_TYPE, R.layout.item_type_title)
        addItemType(HomeListInfo.CONTENT_STYLE_1, R.layout.item_type_content_1)
        addItemType(HomeListInfo.CONTENT_STYLE_2, R.layout.item_type_content_2)
        addItemType(HomeListInfo.LIST_SQUARE_TYPE, R.layout.item_type_list)
        addItemType(HomeListInfo.LIST_RECTANGLE_TYPE, R.layout.item_type_list)
    }


    init {
        setSpanSizeLookup(SpanSizeLookup { gridLayoutManager, position ->
            return@SpanSizeLookup when (getDefItemViewType(position)) {
                //每行的item个数= spanCount/?
                //这个直接占满全格子
               HomeListInfo.HEARD_TYPE, HomeListInfo.TITLE_TYPE, HomeListInfo.BANNER_TYPE, HomeListInfo.LIST_SQUARE_TYPE, HomeListInfo.LIST_RECTANGLE_TYPE -> 6
                //每个item占位2个
                HomeListInfo.CONTENT_STYLE_1 -> 3
                //每个item占位3个
                else -> 2
            }
        })
    }

    override fun convert(helper: BaseViewHolder?, item: HomeListInfo?) {
        helper?.also { holder ->
            item?.apply {
                when (this.itemType) {
                    HomeListInfo.HEARD_TYPE->{
                        val recyclerView = holder.getView<RecyclerView>(R.id.rv_list)
                        if (recyclerView.adapter == null || recyclerView.adapter !is HomeIconAdapter) {
                            val iconAdapter = HomeIconAdapter(minorIconList!!)
                            recyclerView.addItemDecoration(HomeSortItemDecoration(0, DisplayUtils.dp2px(8f), DisplayUtils.dp2px(8f)))
                            recyclerView.layoutManager = LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false)
                            iconAdapter.bindToRecyclerView(recyclerView)
                        }else{
                            (recyclerView.adapter as HomeIconAdapter).setNewData(minorIconList)
                        }
                        heardBanner?.also { banner ->
                            val bannerView =
                                holder.getView<XBanner>(R.id.xbanner)
                            bannerView.setBannerData(banner)
                            bannerView.loadImage { _, model, view, _ ->
                                val bannerMiddleBean =
                                    model as HomeResponse.DataBean.BannerBean
                                GlideUtils.loadImage(
                                    mContext,
                                    bannerMiddleBean.picUrl,
                                    view as ImageView
                                )
                            }
                        }
                    }
                    HomeListInfo.BANNER_TYPE -> {
                        middleBanner?.also { banner ->
                            val bannerView =
                                holder.getView<XBanner>(R.id.xbanner)
                            bannerView.setBannerData(banner)
                            bannerView.loadImage { _, model, view, _ ->
                                val bannerMiddleBean =
                                    model as HomeResponse.DataBean.BannerBean
                                GlideUtils.loadImage(
                                    mContext,
                                    bannerMiddleBean.picUrl,
                                    view as ImageView
                                )
                            }
                        }
                    }
                    HomeListInfo.TITLE_TYPE -> {
                        holder.setText(R.id.tv_title, title)

                    }
                    HomeListInfo.CONTENT_STYLE_2, HomeListInfo.CONTENT_STYLE_1 -> {
                        holder.setText(
                            R.id.tv_title,
                            videoTemplatesBean?.name
                        )
                        GlideUtils.loadImage(
                            mContext,
                            videoTemplatesBean?.coverUrl,
                            holder.getView(R.id.iv_cover),
                            overSizeWidth,
                            overSizeHeight
                        )

                        val width = if (currType == HomeListInfo.CONTENT_STYLE_2) {
                            contentStyleWidth2
                        } else {
                            contentStyleWidth1
                        }
                        holder.getView<View>(R.id.iv_cover)
                            .layoutParams.width = width
                    }
                    HomeListInfo.LIST_SQUARE_TYPE -> {
                        val recyclerView =
                            holder.getView<RecyclerView>(R.id.rv_list)
                        if (recyclerView.adapter == null || recyclerView.adapter !is HomeSquareAdapter) {
                            val adapter = HomeSquareAdapter(squareVideos!!)
                            recyclerView.addItemDecoration(
                                HomeSortItemDecoration(
                                    DisplayUtils.dp2px(
                                        8f
                                    ), DisplayUtils.dp2px(16f), DisplayUtils.dp2px(16f)
                                )
                            )
                            recyclerView.layoutManager =
                                LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false)
                            adapter.bindToRecyclerView(recyclerView)
                        } else {
                            val adapter = recyclerView.adapter as HomeSquareAdapter
                            adapter.setNewData(squareVideos)
                        }
                    }
                    HomeListInfo.LIST_RECTANGLE_TYPE -> {
                        val recyclerView =
                            holder.getView<RecyclerView>(R.id.rv_list)
                        if (recyclerView.adapter == null || recyclerView.adapter !is HomeRectangleAdapter) {
                            val adapter = HomeRectangleAdapter(rectangleVideos!!)
                            recyclerView.addItemDecoration(
                                HomeSortItemDecoration(
                                    DisplayUtils.dp2px(
                                        8f
                                    ), DisplayUtils.dp2px(16f), DisplayUtils.dp2px(16f)
                                )
                            )
                            recyclerView.layoutManager =
                                LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false)
                            adapter.bindToRecyclerView(recyclerView)
                        } else {
                            val adapter = recyclerView.adapter as HomeRectangleAdapter
                            adapter.setNewData(rectangleVideos)
                        }
                    }
                }
            }

        }

    }


}