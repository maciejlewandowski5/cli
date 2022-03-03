package com.example.bbbbbb.stats

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import androidx.navigation.fragment.findNavController
import com.example.bbbbbb.R
import com.example.bbbbbb.databinding.FragmentStatsRepoBinding

import javax.inject.Inject

class StatsRepoFragment : Fragment() {
    private var _binding: FragmentStatsRepoBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var statsViewModel: StatsViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStatsRepoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as LoginActivity).loginComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListeners(binding.username, binding.login, binding.loading)
        statsViewModel.state.observe(viewLifecycleOwner) { handleState(it) }
    }

    private fun setListeners(
        usernameEditText: EditText,
        loginButton: Button,
        loadingProgressBar: ProgressBar
    ) {
        usernameEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                setPassword(usernameEditText)
            }
            false
        }

        loginButton.setOnClickListener {
            loadingProgressBar.visibility = View.VISIBLE
            setPassword(usernameEditText)
            statsViewModel.onButtonClick(resources.getString(R.string.API_KEY))
        }
    }

    private fun handleState(it: Result<StatsState?>) {
        when {
            it.isSuccess -> {
                Log.i(TAG, it.toString())
                findNavController().navigate(R.id.action_loginPasswordFragment_to_mainActivity2)
            }
            it.isFailure -> {
                Log.w(TAG, it.exceptionOrNull()?.message!!)
            }
        }
        binding.loading.visibility = View.INVISIBLE
    }

    private fun setPassword(usernameEditText: EditText) {
        statsViewModel.setPassword(
            usernameEditText.text.toString(),
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val TAG = "PasswordFragment"
    }
}