package com.refactory.test_refactory.view.auth

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import com.refactory.test_refactory.database.DatabaseUser
import com.refactory.test_refactory.model.User
import java.io.ByteArrayOutputStream

class AuthPresenter(private val listener: Listener) {
    interface Listener {
        fun showLoginFragment()
        fun showRegisterFragment()
        fun moveToMain()

        fun showNotif(msg: String)
    }

    private lateinit var db: DatabaseUser

    fun initDB(dBase: DatabaseUser) {
        db = dBase
    }

    fun goRegisterPage() {
        listener.showRegisterFragment()
    }

    fun goLoginPage() {
        listener.showLoginFragment()
    }

    fun register(user: User) {
        Log.d("user", user.toString())

        val userCheck = db.userDao().checkUser(user.email)

        Log.d("register", userCheck.toString())

        if (userCheck != null) {
            listener.showNotif("Email ${userCheck.email} telah terdaftar dan login sebanyak ${userCheck.count}")
        } else {
            val totalSave = db.userDao().add(user)
            if (totalSave > 0) {
                listener.showNotif("Register Berhasil")
            } else {
                listener.showNotif("Register Gagal")
            }
        }
    }

    fun login(email: String, password: String) {
        Log.d("login", email + password)

        val user = db.userDao().getAll()

        Log.d("login", user.toString())

        user.forEach {
            if (it.email == email && it.password == password) {
                val cLog = it.count + 1

                it.apply {
                    count = cLog
                }

                val userUpdate = db.userDao().update(it)
                if (userUpdate > 0) {
                    listener.showNotif("Email ${it.email} telah login sebanyak $cLog")
                    listener.moveToMain()
                } else {
                    listener.showNotif("Akun Tidak Tedaftar")
                }
            }
        }
//        if (user != null) {
//            val cLog = user.count + 1
//
//            user.apply {
//                count = cLog
//            }
//
//            val userUpdate = db.userDao().update(user)
//            if (userUpdate > 0) {
//                listener.showNotif("Email ${user.email} telah login sebanyak $cLog")
////                listener.moveToMain()
//            } else {
//                listener.showNotif("Akun Tidak Tedaftar")
//            }
//
//        }
    }

    fun encodeImage(bm: Bitmap): String? {
        val baos = ByteArrayOutputStream()
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val b = baos.toByteArray()
        return Base64.encodeToString(b, Base64.DEFAULT)
    }

    fun decodeImage(base64Str: String): Bitmap {
        val imageByteArray = Base64.decode(base64Str, Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(imageByteArray, 0, imageByteArray.size)
    }


}