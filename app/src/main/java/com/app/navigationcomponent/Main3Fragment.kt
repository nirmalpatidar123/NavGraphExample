package com.app.navigationcomponent

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.app.navigationcomponent.databinding.FragmentMain3Binding

/**
 * A simple [Fragment] subclass.
 */
class Main3Fragment : Fragment() {

    var _binding: FragmentMain3Binding? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMain3Binding.inflate(inflater, container, false)
        val view = binding.root
        // Inflate the layout for this fragment
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeViews()
    }

    private fun initializeViews(){
        binding.textActivityName.text = activity!!::class.java.simpleName
        binding.textFragmentName.text = this::class.java.simpleName

        binding.buttonBack.setOnClickListener {
            if (!findNavController().popBackStack()){
                activity?.finish()
            }
        }

        binding.buttonDone.setOnClickListener {
            activity?.let {
                val intent = Intent(it, DetailActivity::class.java)
                it.startActivity(intent)
                val sharedPrefHelper = SharedPrefHelper(it)
                sharedPrefHelper.setUserLoggedInState(true)
                it.finish()
            }

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
