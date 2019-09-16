package com.lai.myapplication.utils;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.lai.myapplication.adapter.HomeAdapter;
import com.lai.myapplication.bean.HomeListInfo;

public class HomeItemDecoration extends RecyclerView.ItemDecoration {

    private int space;

    private int headSpace;
    private int footSpace;
    private int topAndBottom = DisplayUtils.dp2px(15f);

    public HomeItemDecoration(int space, int headSpace, int footSpace) {
        this.space = space / 2;
        this.headSpace = headSpace;
        this.footSpace = footSpace;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        GridLayoutManager layoutManager = (GridLayoutManager) parent.getLayoutManager();
        int itemPosition = parent.getChildAdapterPosition(view);
        if (layoutManager != null) {
            int spanSize = layoutManager.getSpanSizeLookup().getSpanSize(itemPosition);
            //一列的时候
            if (spanSize == layoutManager.getSpanCount()) {
                if (layoutManager.getItemViewType(view) != HomeListInfo.HEARD_TYPE &&
                        layoutManager.getItemViewType(view) != HomeListInfo.LIST_SQUARE_TYPE &&
                        layoutManager.getItemViewType(view) != HomeListInfo.LIST_RECTANGLE_TYPE) {
                    outRect.left = headSpace;
                    outRect.right = footSpace;
                }
            }
            //三列的时候
            else if (spanSize == 2) {
                int infoListIndex = getListPosition(parent, view);
                //如果是在左边
                if (isFirstColumn(3, infoListIndex)) {
                    outRect.left = headSpace;
                    outRect.right = space;
                    //如果是在右边
                } else if (isLastColumn(parent, infoListIndex, 3)) {
                    outRect.left = space;
                    outRect.right = footSpace;
                } else {
                    //在中间
                    outRect.left = space;
                    outRect.right = space;
                }
            }
            //二列的时候怎么
            else if (spanSize == 3) {
                int infoListIndex = getListPosition(parent, view);
                //如果在左边
                if (isFirstColumn(2, infoListIndex)) {
                    outRect.left = headSpace;
                    outRect.right = space;
                } else {
                    //右边
                    outRect.left = space;
                    outRect.right = footSpace;
                }
            }
        }

        if (itemPosition == 0) {
            outRect.top = topAndBottom;
        }

        outRect.bottom = topAndBottom;
    }


    /**
     * 获取view对于的坐标
     *
     * @param parent
     * @param view
     * @return
     */
    private int getListPosition(RecyclerView parent, View view) {
        int childLayoutPosition = parent.getChildLayoutPosition(view);
        HomeAdapter homeAdapter = (HomeAdapter) parent.getAdapter();
        HomeListInfo homeListInfo = homeAdapter.getData().get(childLayoutPosition);
        return homeListInfo.getListIndex();
    }


    /**
     * 是否是第一列
     *
     * @param spanCount
     * @param readPosition
     * @return
     */
    private boolean isFirstColumn(int spanCount, int readPosition) {
        return readPosition % spanCount == 0;
    }

    /**
     * 判断是否是最后一列
     *
     * @param parent
     * @param pos
     * @param spanCount
     * @return
     */
    private boolean isLastColumn(RecyclerView parent, int pos, int spanCount) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            // 如果是最后一列，则不需要绘制右边
            return (pos + 1) % spanCount == 0;
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            int orientation = ((StaggeredGridLayoutManager) layoutManager)
                    .getOrientation();
            if (orientation == StaggeredGridLayoutManager.VERTICAL) {
                // 最后一列
                return (pos + 1) % spanCount == 0;
            }
        }
        return false;
    }


}
