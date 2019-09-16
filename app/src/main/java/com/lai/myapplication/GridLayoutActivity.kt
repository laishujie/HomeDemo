package com.lai.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.gyf.barlibrary.ImmersionBar
import com.lai.myapplication.adapter.HomeAdapter
import com.lai.myapplication.model.RequestService
import com.lai.myapplication.utils.DisplayUtils
import com.lai.myapplication.utils.HomeItemDecoration
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.activity_grid.*

/**
 *
 * @author  Lai
 *
 * @time 2019/9/14 17:51
 * @describe describe
 *
 */
class GridLayoutActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_grid)
        initStatusBar()

        //模拟请求数据
        RequestService.requestInfo(Consumer {
            val homeAdapter = HomeAdapter(it)
            rv_list.layoutManager = GridLayoutManager(this@GridLayoutActivity, 6)
            rv_list.addItemDecoration(
                HomeItemDecoration(
                    DisplayUtils.dp2px(8f),
                    DisplayUtils.dp2px(16f),
                    DisplayUtils.dp2px(16f)
                )
            )
            homeAdapter.bindToRecyclerView(rv_list)
        })
    }

    private fun initStatusBar() {
        ImmersionBar.with(this)
            .flymeOSStatusBarFontColor(R.color.black)  //修改flyme OS状态栏字体颜色
            .statusBarDarkFont(true)
            .transparentStatusBar()
            .keyboardEnable(true).init()
    }

    override fun onDestroy() {
        super.onDestroy()
        ImmersionBar.with(this).destroy()
    }


}