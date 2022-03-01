package com.example.bbbbbb.loginactivity.ui.login

import android.content.Context
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.bbbbbb.R
import com.example.bbbbbb.databinding.FragmentLoginUserNameBinding
import com.example.bbbbbb.loginactivity.LoginActivity
import com.example.bbbbbb.loginactivity.LoginViewModel
import javax.inject.Inject


class LoginUserNameFragment : Fragment() {

    @Inject
    lateinit var loginViewModel: LoginViewModel
    private var _binding: FragmentLoginUserNameBinding? = null

    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as LoginActivity).loginComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentLoginUserNameBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val usernameEditText = binding.username
        val loginButton = binding.login
        val loadingProgressBar = binding.loading

        usernameEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                loginViewModel.setUsername(
                    usernameEditText.text.toString(),
                )
            }
            false
        }

        loginButton.setOnClickListener {
            loadingProgressBar.visibility = View.VISIBLE
            loginViewModel.setUsername(
                usernameEditText.text.toString(),
            )
findNavController().navigate(R.id.action_loginUserNameFragment_to_loginPasswordFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}