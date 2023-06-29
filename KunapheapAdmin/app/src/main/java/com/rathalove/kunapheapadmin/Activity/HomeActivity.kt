package com.rathalove.kunapheapadmin.Activity

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rathalove.kunapheapadmin.Fragment.AddProductFragment
import com.rathalove.kunapheapadmin.Fragment.DashboardFragment
import com.rathalove.kunapheapadmin.Util.Components.drawerMenu.DrawerAdapter
import com.rathalove.kunapheapadmin.Util.Components.drawerMenu.DrawerItem
import com.rathalove.kunapheapadmin.Util.Components.drawerMenu.SimpleItem
import com.rathalove.kunapheapadmin.Util.Components.drawerMenu.SpaceItem
import com.rathalove.kunapheapadmin.Fragment.ProductList
import com.rathalove.kunapheapadmin.Fragment.SettingFragment
import com.rathalove.kunapheapadmin.MainActivity
import com.rathalove.kunapheapadmin.R
import com.rathalove.kunapheapadmin.RoomData.UserLogIn.UserDatabase
import com.yarolegovich.slidingrootnav.SlidingRootNav
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

@Suppress("INACCESSIBLE_TYPE")
class HomeActivity : AppCompatActivity(), DrawerAdapter.OnItemSelectedListener {
    private var screenTitles: Array<String> = arrayOf()
    private var screenIcons: Array<Drawable?> = arrayOf()
    private var slidingRootNav: SlidingRootNav? = null

    private val userDatabase: UserDatabase by lazy { UserDatabase.getDatabase(this) }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        slidingRootNav = SlidingRootNavBuilder(this)
            .withToolbarMenuToggle(toolbar)
            .withMenuOpened(false)
            .withContentClickableWhenMenuOpened(false)
            .withSavedState(savedInstanceState)
            .withMenuLayout(R.layout.menu_left_drawer)
            .inject()
        screenIcons = loadScreenIcons()
        screenTitles = loadScreenTitles()
        val adapter =
            DrawerAdapter(
                Arrays.asList(
                    createItemFor(POS_CLOSE).setChecked(false),
                    createItemFor(POS_PRODUCTLIST).setChecked(true),
                    createItemFor(POS_DASHBOARD),
                    createItemFor(POS_ADDPRODUCT),
                    createItemFor(POS_SETTING),
                    SpaceItem(
                        48
                    ),
                    createItemFor(POS_LOGOUT)
                )
            )
        adapter.setListener(this)
        val list = findViewById<RecyclerView>(R.id.list)
        list.isNestedScrollingEnabled = false
        list.layoutManager = LinearLayoutManager(this)
        list.adapter = adapter
        adapter.setSelected(POS_PRODUCTLIST)



    }

    override fun onItemSelected(position: Int) {
        var transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        if (position == POS_LOGOUT) {
            GlobalScope.launch(Dispatchers.IO){
                var user = userDatabase.userDao().getAllUser()
                var dele = userDatabase.userDao().deleteUser(user[0])
                launch (Dispatchers.Main){
                    var intent = Intent(this@HomeActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
        else if (position == POS_PRODUCTLIST){
            var productlistFragment = ProductList()
            transaction.replace(R.id.container, productlistFragment)
        }
        else if (position == POS_SETTING){
            var settingFragment = SettingFragment()
            transaction.replace(R.id.container, settingFragment)
        }
        else if(position == POS_ADDPRODUCT){
            var addproduct = AddProductFragment()
            transaction.replace(R.id.container, addproduct)
        }
        else if(position == POS_DASHBOARD){
            var dashboardFragment = DashboardFragment()
            transaction.replace(R.id.container, dashboardFragment)
        }

        slidingRootNav!!.closeMenu()
        transaction.addToBackStack(null)
        transaction.commit()

    }


    private fun createItemFor(position: Int): DrawerItem<*> {
        return SimpleItem(
            screenIcons[position],
            screenTitles[position]
        )
            .withIconTint(color(R.color.textColorSecondary))
            .withTextTint(color(R.color.textColorPrimary))
            .withSelectedIconTint(color(R.color.colorAccent))
            .withSelectedTextTint(color(R.color.colorAccent))
    }

    private fun loadScreenTitles(): Array<String> {
        return resources.getStringArray(R.array.ld_activityScreenTitles)
    }

    private fun loadScreenIcons(): Array<Drawable?> {
        val ta = resources.obtainTypedArray(R.array.ld_activityScreenIcons)
        val icons = arrayOfNulls<Drawable>(ta.length())
        for (i in 0 until ta.length()) {
            val id = ta.getResourceId(i, 0)
            if (id != 0) {
                icons[i] = ContextCompat.getDrawable(this, id)
            }
        }
        ta.recycle()
        return icons
    }

    override fun onBackPressed() {
        finish()
    }

    @ColorInt
    private fun color(@ColorRes res: Int): Int {
        return ContextCompat.getColor(this, res)
    }

    companion object {
        private const val POS_CLOSE = 0
        private const val POS_PRODUCTLIST = 1
        private const val POS_DASHBOARD = 2
        private const val POS_ADDPRODUCT = 3
        private const val POS_SETTING = 4
        private const val POS_LOGOUT = 6
    }
}