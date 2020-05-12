package com.app.navigationcomponent

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.app.navigationcomponent.databinding.FragmentDetailNameBinding


/**
 * A simple [Fragment] subclass.
 */
class DetailNameFragment : Fragment() {

    private var _binding: FragmentDetailNameBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailNameBinding.inflate(inflater, container, false)
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
        binding.footer.buttonNext.setText(R.string.done)

        binding.footer.buttonNext.setOnClickListener {
            if (!findNavController().popBackStack()){
                activity?.finish()
            }
        }

        binding.headerCL.textTitle.text = "Name Title"
        binding.headerCL.textSubTitle.text = "Name SubTitle"
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}
