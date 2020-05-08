package com.app.navigationcomponent

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.app.navigationcomponent.databinding.FragmentDetailFeedbackBinding

/**
 * A simple [Fragment] subclass.
 */
class DetailFeedbackFragment : Fragment() {

    private var _binding: FragmentDetailFeedbackBinding? = null
    private val binding get() = _binding!!
    private val args: DetailFeedbackFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailFeedbackBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeViews()
    }

    private fun initializeViews(){
        binding.textActivityName.text = "Name: "+args.userName
        binding.textFragmentName.text = "Age: "+ args.userAge.toString()

        binding.buttonBack.setOnClickListener {
            if (!findNavController().popBackStack()){
                activity?.finish()
            }
        }

        binding.buttonDone.setOnClickListener {
            val action = DetailFeedbackFragmentDirections.actionDetailFeedbackFragmentToDetailHomeFragment()
            findNavController().navigate(action)
        }

        binding.appBarHome.textTitle.text = "Feedback Title"
        binding.appBarHome.textSubTitle.text = "Feedback Subtitle"
        binding.appBarHome.textSubTitle.visibility = View.VISIBLE
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
