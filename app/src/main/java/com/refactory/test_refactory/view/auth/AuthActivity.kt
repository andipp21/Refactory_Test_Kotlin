package com.refactory.test_refactory.view.auth

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.FragmentTransaction
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.refactory.test_refactory.R
import com.refactory.test_refactory.database.DatabaseUser
import com.refactory.test_refactory.databinding.ActivityAuthBinding
import com.refactory.test_refactory.view.auth.fragment.LoginFragment
import com.refactory.test_refactory.view.auth.fragment.RegisterFragment
import com.refactory.test_refactory.view.main.MainActivity

class AuthActivity : AppCompatActivity(), AuthPresenter.Listener {
    private lateinit var binding: ActivityAuthBinding
    private lateinit var presenter: AuthPresenter

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = AuthPresenter(this)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        DatabaseUser.getInstance(this)?.let {
            presenter.initDB(it)
        }

        setContentView(binding.root)

        getLoc()

        showLoginFragment()
    }

    private fun getLoc() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        if (ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION), 201)
            getLoc()
        }

        fusedLocationClient.lastLocation
                .addOnSuccessListener { location: Location? ->
                    // Got last known location. In some rare situations this can be null.

                    binding.tvLongitude.text = location?.longitude.toString()
                    binding.tvLatitude.text = location?.latitude.toString()
                }
    }

    override fun showLoginFragment() {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.frameAuthContainer, LoginFragment(presenter))
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                .commit()
    }

    override fun showRegisterFragment() {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.frameAuthContainer, RegisterFragment(presenter))
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                .commit()
    }

    override fun moveToMain() {
        startActivity(Intent(this,MainActivity::class.java))
    }

    override fun showNotif(msg: String) {
        runOnUiThread {
            Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
        }
    }
}