package ru.msokolov.onlineshop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import ru.msokolov.onlineshop.bottom_navigation.BottomNavigation
import ru.msokolov.onlineshop.dagger.findDependencies
import ru.msokolov.onlineshop.databinding.ActivityMainBinding
import ru.msokolov.onlineshop.di.DaggerMainActivityComponent
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var bottomNavigation: BottomNavigation

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerMainActivityComponent.builder()
            .deps(findDependencies())
            .build()
            .inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        binding.profileButton.setOnClickListener {
            findNavController(binding.fragmentContainerView.id)
                .navigate(bottomNavigation.toProfile.action)
        }
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController

        navController.addOnDestinationChangedListener{_, destination, _ ->
            when(destination.id){
                R.id.signInPageFragment -> { setBottomNavigationVisibility(false) }
                R.id.loginFragment -> { setBottomNavigationVisibility(false) }
                R.id.pageOneFragment -> { setBottomNavigationVisibility(true) }
                R.id.pageTwoFragment -> { setBottomNavigationVisibility(true) }
                R.id.profileButton -> { setBottomNavigationVisibility(true) }
            }
        }
        super.onStart()
    }

    private fun setBottomNavigationVisibility(isVisible: Boolean){
        if (isVisible){
            binding.bottomNavigationView.visibility = View.VISIBLE
        }
        else{
            binding.bottomNavigationView.visibility = View.GONE
        }
    }
}