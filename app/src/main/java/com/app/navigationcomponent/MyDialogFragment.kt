package com.app.navigationcomponent

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.animation.TranslateAnimation
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.transition.Scene
import androidx.transition.TransitionManager
import com.app.navigationcomponent.databinding.FragmentMyDialogBinding
import kotlinx.android.synthetic.main.layout_scene_1.view.*


/**
 * A simple [Fragment] subclass.
 */
class MyDialogFragment : DialogFragment() {

    var _binding: FragmentMyDialogBinding? = null
    val binding get() = _binding!!

    private lateinit var scene1: Scene
    private lateinit var scene2: Scene

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMyDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeView()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window?.requestFeature(Window.FEATURE_NO_TITLE);
        return dialog

    }

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.90).toInt()
        val height = (resources.displayMetrics.heightPixels * 0.70).toInt()
        dialog?.window?.let {
            it.setLayout(width, height)
            it.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }

    private fun initializeView() {
        binding.footer.buttonPrevious.visibility = View.GONE

        scene1 = Scene(binding.sceneRoot, binding.sceneRoot.container)
        scene2 = Scene.getSceneForLayout(binding.sceneRoot, R.layout.layout_scene_2, requireActivity())

        binding.footer.buttonNext.setOnClickListener {
            binding.footer.buttonPrevious.visibility = View.VISIBLE
            binding.footer.buttonNext.visibility = View.GONE
            TransitionManager.go(scene2)
        }

        binding.footer.buttonPrevious.setOnClickListener {
            binding.footer.buttonPrevious.visibility = View.GONE
            binding.footer.buttonNext.visibility = View.VISIBLE
            TransitionManager.go(scene1)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }





}
