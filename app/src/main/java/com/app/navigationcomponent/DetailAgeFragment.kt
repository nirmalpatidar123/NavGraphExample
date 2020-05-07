package com.app.navigationcomponent

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        binding.buttonNext.setOnClickListener {
            val action = DetailAgeFragmentDirections.actionDetailAgeFragmentToDetailFeedbackFragment("Rahul", 22)
            findNavController().navigate(action)
        }

        binding.headerMain.textTitle.text = "Age Title"
        binding.headerMain.textSubtitle.text = "Age SubTitle"
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
