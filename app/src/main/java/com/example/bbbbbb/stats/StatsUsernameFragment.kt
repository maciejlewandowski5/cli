package com.example.bbbbbb.stats

import android.content.Context
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import androidx.navigation.fragment.findNavController
import com.example.bbbbbb.R
import com.example.bbbbbb.databinding.FragmentStatsUserBinding
import javax.inject.Inject

class StatsUsernameFragment : Fragment() {

    @Inject
    lateinit var statsViewModel: StatsViewModel
    private var _binding: FragmentStatsUserBinding? = null

    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as LoginActivity).loginComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStatsUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setOnEditListener(binding.username)
        setOnClickListener(binding.login, binding.loading, binding.username)
    }

    private fun setOnEditListener(usernameEditText: EditText) {
        usernameEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                statsViewModel.setUsername(
                    usernameEditText.text.toString(),
                )
            }
            false
        }
    }

    private fun setOnClickListener(
        loginButton: Button,
        loadingProgressBar: ProgressBar,
        usernameEditText: EditText
    ) {
        loginButton.setOnClickListener {
            loadingProgressBar.visibility = View.VISIBLE
            statsViewModel.setUsername(
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
