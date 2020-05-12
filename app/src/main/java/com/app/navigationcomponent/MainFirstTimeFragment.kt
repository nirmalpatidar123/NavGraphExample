package com.app.navigationcomponent

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.app.navigationcomponent.databinding.FragmentMainFirstTimeBinding
import java.lang.ClassCastException

/**
 * A simple [Fragment] subclass.
 */
class MainFirstTimeFragment : Fragment() {

    private lateinit var headerListener: HeaderListener

    var _binding: FragmentMainFirstTimeBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainFirstTimeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeViews()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            headerListener = activity as HeaderListener
        }catch (ex: ClassCastException){
            throw ClassCastException(activity.toString() + "must implement HeaderListener")
        }
    }


    private fun initializeViews(){

        headerListener.setTitle("Welcome "+ this::class.java.simpleName)
        headerListener.isSubTitleVisible(false)

        binding.footer.buttonPrevious.visibility = View.GONE
        binding.footer.buttonNext.setText(R.string.next)
        binding.footer.buttonNext.setOnClickListener {
            userFirstTimeExperienceCompleted()
            navigateToNextScreen()
        }
        binding.textActivityName.text = activity!!::class.java.simpleName
        binding.textFragmentName.text = this::class.java.simpleName
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun userFirstTimeExperienceCompleted(){
        activity?.let {
            val sharedPrefHelper = SharedPrefHelper(it)
            sharedPrefHelper.setUserExperience(true)
        }
    }

    fun navigateToNextScreen(){
        val action = MainFirstTimeFragmentDirections.actionMainFirstTimeFragmentToMain1Fragment()
        findNavController().navigate(action)
    }

}
