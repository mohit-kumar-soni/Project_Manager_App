package com.example.passwordmanagerapp.data

import android.R.attr.password
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "password_table")
data class Password(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    @ColumnInfo(name = "password-title")
    val title: String= "",
    @ColumnInfo(name = "password-email")
    val email: String = "",
    @ColumnInfo(name = "password-password")
    val password: String = "",
)

/*object DummyList{
    val passwordList = listOf(
        Password(title = "Google"),
        Password(title = "Linkedin"),
        Password(title = "FaceBook"),
        Password(title = "Youtube"),
        Password(title = "Whatsapp")
    )

 */

