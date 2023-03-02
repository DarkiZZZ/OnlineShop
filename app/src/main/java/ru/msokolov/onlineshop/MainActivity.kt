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
        setBottomNavigationClickListeners()
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        setupNavControllerClickListener(navController)
        super.onStart()
    }

    private fun setBottomNavigationClickListeners(){
        binding.profileButton.setOnClickListener {
            setBottomButtonsState(baseContext, PROFILE_BOTTOM_NAV_CLICKED)
            findNavController(binding.fragmentContainerView.id)
                .navigate(bottomNavigation.toProfile.action)
        }
        binding.pageOneButton.setOnClickListener {
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
            binding.bottomNavigationView.visibility = View.VISIBLE
        } else {
            binding.bottomNavigationView.visibility = View.GONE
        }
    }

    private fun setBottomButtonsState(context: Context, isClickedIndex: Int) {
        val background = AppCompatResources.getDrawable(context, R.drawable.bottom_item_background)
        val backgroundClicked =
            AppCompatResources.getDrawable(context, R.drawable.bottom_item_clicked_background)
        binding.bottomNavigationView.getChildAt(PAGE_ONE_BOTTOM_NAV_CLICKED).background = background
        binding.bottomNavigationView.getChildAt(FAVOURITES_BOTTOM_NAV_CLICKED).background = background
        binding.bottomNavigationView.getChildAt(CART_BOTTOM_NAV_CLICKED).background = background
        binding.bottomNavigationView.getChildAt(CHAT_BOTTOM_NAV_CLICKED).background = background
        binding.bottomNavigationView.getChildAt(PROFILE_BOTTOM_NAV_CLICKED).background = background

        if (isClickedIndex == FRAGMENT_DO_NOT_HAVE_BOTTOM_NAV) {
            return
        } else {
            binding.bottomNavigationView.getChildAt(isClickedIndex).background = backgroundClicked
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

    companion object {
        private const val PAGE_ONE_BOTTOM_NAV_CLICKED = 0
        private const val FAVOURITES_BOTTOM_NAV_CLICKED = 1
        private const val CART_BOTTOM_NAV_CLICKED = 2
        private const val CHAT_BOTTOM_NAV_CLICKED = 3
        private const val PROFILE_BOTTOM_NAV_CLICKED = 4
        private const val FRAGMENT_DO_NOT_HAVE_BOTTOM_NAV = -1
    }
}