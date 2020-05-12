package com.app.navigationcomponent

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.app.navigationcomponent.databinding.FragmentMainBinding

/**
 * A simple [Fragment] subclass.
 */
class MainFragment : Fragment() {

    var _binding: FragmentMainBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigateToNextScreen()
    }

    private fun navigateToNextScreen(){
        activity?.let {
            val sharedPrefHelper = SharedPrefHelper(it)
            if (sharedPrefHelper.getUserExperience()){
                navigateToMain1Fragment()
            }else{
                navigateToMainFirstTimeFragment()
            }
        }
    }

    private fun navigateToMain1Fragment(){
        val action = MainFragmentDirections.actionMainFragmentToMain1Fragment()
        findNavController().navigate(action)
    }

    private fun navigateToMainFirstTimeFragment(){
        val action = MainFragmentDirections.actionMainFragmentToMainFirstTimeFragment()
        findNavController().navigate(action)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
