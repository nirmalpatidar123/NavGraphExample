package com.app.navigationcomponent

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.app.navigationcomponent.databinding.FragmentMain1Binding

/**
 * A simple [Fragment] subclass.
 */
class Main1Fragment : Fragment() {


    private var _binding: FragmentMain1Binding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMain1Binding.inflate(inflater, container, false)
        val view = binding.root
        // Inflate the layout for this fragment
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeViews()
    }

    private fun initializeViews(){
        binding.buttonNext.setOnClickListener {
            val action = Main1FragmentDirections.actionMain1FragmentToMain2Fragment()
            findNavController().navigate(action)
        }
        binding.textActivityName.text = activity!!::class.java.simpleName
        binding.textFragmentName.text = this::class.java.simpleName
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
