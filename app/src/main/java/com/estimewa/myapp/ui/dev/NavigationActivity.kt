package com.estimewa.myapp.ui.dev

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.estimewa.myapp.R
import com.estimewa.myapp.ui.dev.ui.main.NavigationFragment

class NavigationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.navigation_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, NavigationFragment.newInstance())
                .commitNow()
        }
    }
}