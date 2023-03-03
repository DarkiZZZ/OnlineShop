package ru.msokolov.onlineshop.profile.presentation.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.Lazy
import ru.msokolov.onlineshop.profile.R
import ru.msokolov.onlineshop.profile.databinding.FragmentProfileBinding
import ru.msokolov.onlineshop.profile.presentation.navigation.ProfileCommandProvider
import javax.inject.Inject
import ru.msokolov.onlineshop.ui.R.string.shared_prefs_user_name_key

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private lateinit var binding: FragmentProfileBinding

    @Inject
    lateinit var viewModelFactory: Lazy<ProfileViewModel.Companion.ProfileViewModelFactory>
    private val viewModel: ProfileViewModel by viewModels { viewModelFactory.get() }

    @Inject
    lateinit var profileCommandProvider: ProfileCommandProvider

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setupClickListeners(){
        binding.logOutTextView.setOnClickListener {
            viewModel.logout(readFromSharedPrefs())
        }
    }

    private fun readFromSharedPrefs(): String? {
        val sharedPrefs = activity?.getPreferences(Context.MODE_PRIVATE) ?: return null
        return sharedPrefs.getString(getString(shared_prefs_user_name_key), "")
    }
}