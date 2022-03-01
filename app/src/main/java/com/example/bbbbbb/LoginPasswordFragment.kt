package com.example.bbbbbb

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.navigation.fragment.findNavController
import com.example.bbbbbb.databinding.FragmentLoginPasswordBinding
import com.example.bbbbbb.loginactivity.LoginActivity
import com.example.bbbbbb.loginactivity.LoginViewModel
import javax.inject.Inject


class LoginPasswordFragment : Fragment() {
    private var _binding: FragmentLoginPasswordBinding? = null

    private val binding get() = _binding!!


    @Inject
    lateinit var loginViewModel: LoginViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as LoginActivity).loginComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val usernameEditText = binding.username
        val loginButton = binding.login
        val loadingProgressBar = binding.loading

        usernameEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                loginViewModel.setPassword(
                    usernameEditText.text.toString(),
                )
            }
            false
        }

        loginButton.setOnClickListener {
            loadingProgressBar.visibility = View.VISIBLE
            loginViewModel.setPassword(
                usernameEditText.text.toString(),
            )
            loginViewModel.metrics()

            loginViewModel.createPost(resources.getString(R.string.API_KEY))
        }
        loginViewModel.metrics.observe(viewLifecycleOwner) {
            Log.i("TAG", it.toString())
        }
        loginViewModel.post.observe(viewLifecycleOwner) {
            Log.i("TAG", it.toString())
        }
        loginViewModel.nav.observe(viewLifecycleOwner) {
            if (it.first && it.second) {
                findNavController().navigate(R.id.action_loginPasswordFragment_to_mainActivity2)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LoginPasswordFragment().apply {
            }
    }
}