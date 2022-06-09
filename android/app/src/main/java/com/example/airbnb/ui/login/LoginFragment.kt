package com.example.airbnb.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.airbnb.R
import com.example.airbnb.common.AccessToken
import com.example.airbnb.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var navigator: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigator = Navigation.findNavController(view)
        binding.btnLoginGithub.setOnClickListener {
            navigator.navigate(R.id.action_loginFragment_to_loginWebViewFragment)
        }

        binding.btnLoginSkip.setOnClickListener {
            //화면 이동 구현
            val sharedPreferences = requireActivity().getSharedPreferences("access_code", AppCompatActivity.MODE_PRIVATE)
            val token = sharedPreferences.getString("token", null)
            token?.let{
                AccessToken.JWT= it
            }
            navigator.navigate(R.id.action_loginFragment_to_homeFragment)
        }

    }

}