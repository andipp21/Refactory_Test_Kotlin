package com.refactory.test_refactory.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class User(
        @PrimaryKey(autoGenerate = true) var id:Int?,
        @ColumnInfo(name = "namaUser") var namaUser: String,
        @ColumnInfo(name = "fotoProfil") var fotoProfil: String,
        @ColumnInfo(name = "email") var email: String,
        @ColumnInfo(name = "password") var password: String,
        @ColumnInfo(name = "count") var count: Int
): Parcelable