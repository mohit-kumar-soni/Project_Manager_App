package com.example.passwordmanagerapp

import android.app.Application
import com.example.passwordmanagerapp.data.Graph

class PasswordListApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Graph.provide(this)
    }
}