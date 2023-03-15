package com.rathalove.kunapheapadmin.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rathalove.kunapheapadmin.R

class SettingFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var root: ViewGroup = inflater.inflate(R.layout.setting_fragment, container, false) as ViewGroup
//        return super.onCreateView(inflater, container, savedInstanceState)
        return root
    }
}