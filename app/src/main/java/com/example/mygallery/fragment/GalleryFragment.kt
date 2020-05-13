package com.example.mygallery.fragment

import android.Manifest
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mygallery.adapter.GalleryAdapter
import com.example.mygallery.database.getDatabase
import com.example.mygallery.databinding.FragmentGalleryBinding
import com.example.mygallery.repository.GalleryRepository
import com.example.mygallery.ui.SpacesItemDecoration
import com.example.mygallery.viewmodel.GalleryViewModel
import com.example.mygallery.viewmodel.GalleryViewModelFactory


/**
 * A simple [Fragment] subclass.
 */
class GalleryFragment : Fragment() {

    private var _binding : FragmentGalleryBinding? = null
    private val binding get() = _binding!!
    private val MY_PERMISSION_READ_EXTERNAL_STORAGE = 97

    private val repository by lazy {
        GalleryRepository(requireContext(), getDatabase(requireContext()))
    }

    private val viewModel by lazy {
        ViewModelProvider(this,
            GalleryViewModelFactory(repository)
        ).get(GalleryViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setGalleryListAdapter()
        checkReadStoragePermission()
    }

    private fun setGalleryListAdapter(){
        val adapter = GalleryAdapter(requireContext())
        binding.adapter = adapter
        binding.galleryRV.addItemDecoration(SpacesItemDecoration(3, 10, true))

        viewModel.galleryFileList.observe(viewLifecycleOwner, Observer { list->
            if (list!=null){
                adapter.setGalleryFiles(list)
            }
        })
    }

    private fun checkReadStoragePermission(){
        if (ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            if (ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)){
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (shouldShowRequestPermissionRationale(ACCESS_FINE_LOCATION)) {
                        showMessageOKCancel("You need to allow access to both the permissions",
                            DialogInterface.OnClickListener { dialog, which ->
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                    ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), MY_PERMISSION_READ_EXTERNAL_STORAGE)
                                }
                            })
                        return
                    }
                }
            }else{
                requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), MY_PERMISSION_READ_EXTERNAL_STORAGE)
            }
        }else{
            viewModel.fetchMediaFromStorage()
        }


    }

    private fun showMessageOKCancel(
        message: String,
        okListener: DialogInterface.OnClickListener
    ) {
        AlertDialog.Builder(context)
            .setMessage(message)
            .setPositiveButton("OK", okListener)
            .setNegativeButton("Cancel", null)
            .create()
            .show()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode){
            MY_PERMISSION_READ_EXTERNAL_STORAGE -> {
                if (grantResults.isNotEmpty()){
                    val readPermission = grantResults[0] == PackageManager.PERMISSION_GRANTED
                    if (readPermission){
                        viewModel.fetchMediaFromStorage()
                    }
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

}
