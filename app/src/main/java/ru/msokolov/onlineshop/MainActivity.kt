package ru.msokolov.onlineshop

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.navigation.NavController
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
        //
        disableNavigationBar()
        //
        setBottomNavigationClickListeners()
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        setupNavControllerClickListener(navController)
        super.onStart()
    }

    private fun setBottomNavigationClickListeners(){
        binding.bottomNavigationView.profileButton.setOnClickListener {
            setBottomButtonsState(baseContext, PROFILE_BOTTOM_NAV_CLICKED)
            findNavController(binding.fragmentContainerView.id)
                .navigate(bottomNavigation.toProfile.action)
        }
        binding.bottomNavigationView.pageOneButton.setOnClickListener {
            setBottomButtonsState(baseContext, PAGE_ONE_BOTTOM_NAV_CLICKED)
            findNavController(binding.fragmentContainerView.id)
                .navigate(bottomNavigation.toPageOne.action)
        }
    }

    private fun setBottomNavigationVisibility(
        isVisible: Boolean,
        context: Context,
        isClickedIndex: Int
    ) {
        setBottomButtonsState(context = context, isClickedIndex = isClickedIndex)
        if (isVisible) {
            binding.bottomNavigationView.root.visibility = View.VISIBLE
        } else {
            binding.bottomNavigationView.root.visibility = View.GONE
        }
    }

    private fun setBottomButtonsState(context: Context, isClickedIndex: Int) {
        val background = AppCompatResources.getDrawable(context, R.drawable.bottom_item_background)
        val backgroundClicked =
            AppCompatResources.getDrawable(context, R.drawable.bottom_item_clicked_background)
        binding.bottomNavigationView.pageOneButton.background = background
        binding.bottomNavigationView.favouritesButton.background = background
        binding.bottomNavigationView.cartButton.background = background
        binding.bottomNavigationView.chatButton.background = background
        binding.bottomNavigationView.profileButton.background = background

        if (isClickedIndex == FRAGMENT_DO_NOT_HAVE_BOTTOM_NAV) {
            return
        } else {
            binding.bottomNavigationView.root.getChildAt(isClickedIndex).background = backgroundClicked
        }
    }

    private fun setupNavControllerClickListener(navController: NavController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.signInPageFragment -> {
                    setBottomNavigationVisibility(
                        false,
                        baseContext,
                        FRAGMENT_DO_NOT_HAVE_BOTTOM_NAV
                    )
                }
                R.id.loginFragment -> {
                    setBottomNavigationVisibility(
                        false,
                        baseContext,
                        FRAGMENT_DO_NOT_HAVE_BOTTOM_NAV
                    )
                }
                R.id.pageOneFragment -> {
                    setBottomNavigationVisibility(
                        true,
                        baseContext,
                        PAGE_ONE_BOTTOM_NAV_CLICKED
                    )
                }
                R.id.pageTwoFragment -> {
                    setBottomNavigationVisibility(
                        true,
                        baseContext,
                        FRAGMENT_DO_NOT_HAVE_BOTTOM_NAV
                    )
                }
                R.id.profileButton -> {
                    setBottomNavigationVisibility(
                        true,
                        baseContext,
                        PROFILE_BOTTOM_NAV_CLICKED
                    )
                }
            }
        }
    }

    private fun setNavigationBarItemsVisibility(fragmentId: Int){
        when(fragmentId){

        }
    }

    private fun disableNavigationBar(){
        binding.navBar.root.visibility = View.GONE
    }

    private fun enableNavigationBar(){
        binding.navBar.root.visibility = View.VISIBLE
    }

    private fun hideAllNavigationBarItems(){
        with(binding){
            navBar.locationTextView.visibility = View.GONE
            navBar.navDrawerButton.visibility = View.GONE
            navBar.backButton.visibility = View.GONE
            navBar.labelTextView.visibility = View.GONE
            navBar.avatarImageView.visibility = View.GONE
            navBar.locationTextView.visibility = View.GONE
            navBar.spinnerImageView.visibility = View.GONE
        }
    }


    companion object {
        private const val PAGE_ONE_BOTTOM_NAV_CLICKED = 0
        private const val FAVOURITES_BOTTOM_NAV_CLICKED = 1
        private const val CART_BOTTOM_NAV_CLICKED = 2
        private const val CHAT_BOTTOM_NAV_CLICKED = 3
        private const val PROFILE_BOTTOM_NAV_CLICKED = 4
        private const val FRAGMENT_DO_NOT_HAVE_BOTTOM_NAV = -1
    }
}