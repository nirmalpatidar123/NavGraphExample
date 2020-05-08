package com.app.navigationcomponent

import android.content.res.Configuration
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.core.widget.ImageViewCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.findNavController
import com.app.navigationcomponent.databinding.FragmentDetailHomeBinding
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.app_bar_home.view.*

/**
 * A simple [Fragment] subclass.
 */
class DetailHomeFragment : Fragment(), NavigationView.OnNavigationItemSelectedListener {

    private var _binding: FragmentDetailHomeBinding? = null
    private val binding get() = _binding!!


    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailHomeBinding.inflate(inflater, container, false)
        (activity as? AppCompatActivity)?.setSupportActionBar(binding.appBarHome.toolbarHome)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeViews()
    }

    private fun initializeViews(){
        binding.appBarHome.toolbarHome.drawer_icon.setOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.END)
        }
        binding.navView.setNavigationItemSelectedListener(this)

        binding.appBarHome.toolbarHome.text_title.text = "Home"

        binding.textActivityName.text = activity!!::class.java.simpleName
        binding.textFragmentName.text = this::class.java.simpleName

        binding.appBarHome.textSubTitle.visibility = View.GONE
        binding.appBarHome.drawerIcon.setImageDrawable(ContextCompat.getDrawable(context!!, R.drawable.ic_drawer))
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_home -> {}
            R.id.nav_name -> {
               findNavController().navigate(DetailHomeFragmentDirections.actionDetailHomeFragmentToDetailNameFragment())
            }
            R.id.nav_age -> {
                findNavController().navigate(DetailHomeFragmentDirections.actionDetailHomeFragmentToDetailAgeFragment())
            }
            R.id.nav_feedback -> {
                findNavController().navigate(DetailHomeFragmentDirections.actionDetailHomeFragmentToDetailFeedbackFragment(null, 25))
            }
        }
        binding.drawerLayout.closeDrawer(GravityCompat.END)
        return true
    }

}
