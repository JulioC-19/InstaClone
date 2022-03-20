package com.instaclone

import android.app.Application
import com.parse.Parse
import com.parse.ParseObject

class InstaCloneApplication : Application(){

        override fun onCreate() {
            super.onCreate()

            // Set us up to use the Post class
            ParseObject.registerSubclass(Post::class.java)

            Parse.initialize(
                Parse.Configuration.Builder(this)
                    .applicationId(getString(R.string.back4app_app_id))
                    .clientKey(getString(R.string.back4app_client_key))
                    .server(getString(R.string.back4app_server_url))
                    .build())


        }

    }
