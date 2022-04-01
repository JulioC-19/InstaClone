package com.instaclone

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.os.ParcelFileDescriptor
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.instaclone.fragments.ComposeFragment
import com.instaclone.fragments.FeedFragment
import com.instaclone.fragments.ProfileFragment
import com.parse.*
import java.io.File
import java.util.*

/*
* Let user create a post by taking a photo with their camera
* */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentManager: FragmentManager = supportFragmentManager

        // Doing logic on Bottom Navigation Buttons
        findViewById<BottomNavigationView>(R.id.bottom_navigation).setOnItemSelectedListener {
            item ->

            var fragmentToShow: Fragment? = null
            when(item.itemId){

                // This is coming from the menu_bottom_navigation.xml
                R.id.action_home->{
                    fragmentToShow = FeedFragment()
                }
                R.id.action_compose->{
                    fragmentToShow = ComposeFragment()
                }
                R.id.action_profile->{
                    fragmentToShow = ProfileFragment()
                }
            }

            if(fragmentToShow != null){
                fragmentManager.beginTransaction().replace(R.id.flContainer, fragmentToShow).commit()
            }


            // Return true to say that we've handled this user interaction on the item
            true
        }
        // Set default selection
        findViewById<BottomNavigationView>(R.id.bottom_navigation).selectedItemId = R.id.action_home
        //queryPosts()
    }

    companion object{
        const val TAG = "MainActivity"
    }
}