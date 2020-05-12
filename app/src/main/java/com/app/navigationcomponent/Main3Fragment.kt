package com.app.navigationcomponent

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.app.navigationcomponent.databinding.FragmentMain3Binding
import java.lang.ClassCastException

/**
 * A simple [Fragment] subclass.
 */
class Main3Fragment : Fragment() {

    private lateinit var headerListener: HeaderListener

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

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            headerListener = activity as HeaderListener
        }catch (ex: ClassCastException){
            throw ClassCastException(activity.toString() + "must implement HeaderListener")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeViews()
    }

    private fun initializeViews(){
        headerListener.setTitle("Title "+ this::class.java.simpleName)
        headerListener.isSubTitleVisible(true)
        headerListener.setSubTitle("Subtitle "+ this::class.java.simpleName)

        binding.textActivityName.text = activity!!::class.java.simpleName
        binding.textFragmentName.text = this::class.java.simpleName
        binding.footer.buttonNext.setText(R.string.done)

        binding.footer.buttonPrevious.setOnClickListener {
            if (!findNavController().popBackStack()){
                activity?.finish()
            }
        }

        binding.footer.buttonNext.setOnClickListener {
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
