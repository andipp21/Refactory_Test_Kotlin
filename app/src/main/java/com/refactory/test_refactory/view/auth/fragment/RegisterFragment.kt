package com.refactory.test_refactory.view.auth.fragment

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.refactory.test_refactory.databinding.FragmentRegisterBinding
import com.refactory.test_refactory.model.User
import com.refactory.test_refactory.view.auth.AuthPresenter
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RegisterFragment(private val presenter: AuthPresenter) : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding: FragmentRegisterBinding

    private var imageString : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLogin.setOnClickListener {
            presenter.goLoginPage()
        }

        binding.ivProfile.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 211)
        }

        binding.btnRegister.setOnClickListener {
            val user = User(
                    namaUser = binding.etName.text.toString(),
                    email = binding.etEmail.text.toString(),
                    password = binding.etPassword.text.toString(),
                    fotoProfil = imageString,
                    count = 0,
                    id = null
            )
            GlobalScope.launch {
                presenter.register(user)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == AppCompatActivity.RESULT_OK) {
            if (requestCode == 211) {
                val uri = data?.data

                if (uri != null) {
                    val stream = activity?.contentResolver?.openInputStream(uri)

                    val bitmapImage = BitmapFactory.decodeStream(stream)

                    imageString = presenter.encodeImage(bitmapImage).toString()

                    val img = presenter.decodeImage(imageString)
                    activity?.let { Glide.with(it).load(img).into(binding.ivProfile) }
                }
            }
        }
    }



//    companion object {
//        /**
//         * Use this factory method to create a new instance of
//         * this fragment using the provided parameters.
//         *
//         * @param param1 Parameter 1.
//         * @param param2 Parameter 2.
//         * @return A new instance of fragment RegisterFragment.
//         */
//        // TODO: Rename and change types and number of parameters
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            RegisterFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
//    }
}