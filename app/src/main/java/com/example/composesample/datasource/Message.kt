package com.example.composesample.datasource

import android.os.SystemClock

data class Message(val name:String,val message:String,val sentTime:Long=System.currentTimeMillis())
