package com.example.bbbbbb

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.navigation.fragment.findNavController
import com.example.bbbbbb.databinding.FragmentLoginPasswordBinding
import com.example.bbbbbb.databinding.FragmentMBinding
import com.example.bbbbbb.loginactivity.LoginActivity
import com.example.bbbbbb.loginactivity.LoginViewModel
import javax.inject.Inject


class MFragment : Fragment() {
    private var _binding: FragmentMBinding? = null

    private val binding get() = _binding!!

    @Inject
    lateinit var viewModel: ViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as MainActivity).mainComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.floatingActionButton.setOnClickListener() {
            findNavController().navigate(R.id.action_MFragment_to_loginActivity)
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MFragment().apply {

            }
    }
}