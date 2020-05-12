package com.app.navigationcomponent

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.app.navigationcomponent.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.app_bar_home.view.headerCL
import kotlinx.android.synthetic.main.header.view.*


class MainActivity : AppCompatActivity(), HeaderListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun setTitle(title: String) {
        binding.appBarHome.toolbarHome.headerCL.textTitle.text = title
    }

    override fun setSubTitle(subTitle: String) {
        binding.appBarHome.toolbarHome.headerCL.textSubTitle.text = subTitle
    }

    override fun isSubTitleVisible(isVisible: Boolean) {
        if (isVisible){
            binding.appBarHome.toolbarHome.headerCL.textSubTitle.visibility = View.VISIBLE
        }else{
            binding.appBarHome.toolbarHome.headerCL.textSubTitle.visibility = View.GONE
        }

    }


}
