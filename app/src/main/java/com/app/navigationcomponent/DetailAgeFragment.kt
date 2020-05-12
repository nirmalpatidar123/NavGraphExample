package com.app.navigationcomponent

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.app.navigationcomponent.databinding.FragmentDetailAgeBinding

/**
 * A simple [Fragment] subclass.
 */
class DetailAgeFragment : Fragment() {

    private var _binding: FragmentDetailAgeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailAgeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeView()
    }

    private fun initializeView(){
        binding.textActivityName.text = activity!!::class.java.simpleName
        binding.textFragmentName.text = this::class.java.simpleName

        binding.footer.buttonPrevious.visibility = View.GONE
        
        binding.footer.buttonNext.setOnClickListener {
            val action = DetailAgeFragmentDirections.actionDetailAgeFragmentToDetailFeedbackFragment("Rahul", 22)
            findNavController().navigate(action)
        }

        binding.headerCL.headerCL.setBackgroundColor(ContextCompat.getColor(requireContext(), android.R.color.holo_blue_dark))

        binding.headerCL.textTitle.text = "Age Title"
        binding.headerCL.textSubTitle.text = "Age SubTitle"
        binding.headerCL.textSubTitle.visibility = View.VISIBLE
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
