package com.wrbug.developerhelper.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.v4.util.ArrayMap
import android.view.View
import com.wrbug.developerhelper.R
import com.wrbug.developerhelper.basecommon.BaseActivity
import com.wrbug.developerhelper.constant.ReceiverConstant
import com.wrbug.developerhelper.model.entry.HierarchyNode
import com.wrbug.developerhelper.ui.widget.hierarchyView.HierarchyView
import kotlinx.android.synthetic.main.activity_hierarchy.*

class HierarchyActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hierarchy)
        val list = intent.getParcelableArrayListExtra<HierarchyNode>("node")
        setFloatViewVisible(false)
        hierarchyView.setHierarchyNodes(list)
        hierarchyView.setOnHierarchyNodeClickListener(object : HierarchyView.OnHierarchyNodeClickListener {
            override fun onClick(node: HierarchyNode, parentNode: HierarchyNode?) {
                hierarchyDetailView.visibility = View.VISIBLE
                hierarchyDetailView.setNode(node, parentNode)
            }

        })
    }

    override fun onDestroy() {
        setFloatViewVisible(true)
        super.onDestroy()
    }

    override fun onBackPressed() {
        if (hierarchyDetailView.visibility == View.VISIBLE) {
            hierarchyDetailView.visibility = View.GONE
            return
        }
        super.onBackPressed()
    }


    private fun setFloatViewVisible(visible: Boolean) {
        val intent = Intent(ReceiverConstant.ACTION_SET_FLOAT_VIEW_VISIBLE)
        intent.putExtra("visible", visible)
        sendBroadcast(intent)
    }
}
