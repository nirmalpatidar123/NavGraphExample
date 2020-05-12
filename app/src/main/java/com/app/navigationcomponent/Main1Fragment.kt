package com.app.navigationcomponent

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.app.navigationcomponent.databinding.FragmentMain1Binding
import java.lang.ClassCastException

/**
 * A simple [Fragment] subclass.
 */
class Main1Fragment : Fragment() {

    private lateinit var headerListener: HeaderListener


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

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            headerListener = activity as HeaderListener
        }catch (ex: ClassCastException){
            throw ClassCastException(activity.toString() + "must implement HeaderListener")
        }
    }

    private fun initializeViews(){

        headerListener.setTitle("Title "+ this::class.java.simpleName)
        headerListener.isSubTitleVisible(true)
        headerListener.setSubTitle("Subtitle "+ this::class.java.simpleName)

        binding.footer.buttonPrevious.visibility= View.GONE
        binding.footer.buttonNext.setText(R.string.next)
        binding.footer.buttonNext.setOnClickListener {
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
