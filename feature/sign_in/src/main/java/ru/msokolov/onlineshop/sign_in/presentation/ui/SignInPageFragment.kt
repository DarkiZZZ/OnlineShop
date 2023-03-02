package ru.msokolov.onlineshop.sign_in.presentation.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import dagger.Lazy
import ru.msokolov.onlineshop.dagger.findDependencies
import ru.msokolov.onlineshop.livedata.observeEvent
import ru.msokolov.onlineshop.navigation.navigate
import ru.msokolov.onlineshop.sign_in.R
import ru.msokolov.onlineshop.sign_in.databinding.FragmentSignInPageBinding
import ru.msokolov.onlineshop.sign_in.di.DaggerSignInPageComponent
import ru.msokolov.onlineshop.sign_in.presentation.navigation.SignInPageCommandProvider
import ru.msokolov.onlineshop.ui.R.*
import ru.msokolov.onlineshop.ui.R.string.shared_prefs_user_name_key
import javax.inject.Inject

class SignInPageFragment : Fragment(R.layout.fragment_sign_in_page) {

    @Inject
    lateinit var viewModelFactory: Lazy<SignInPageViewModel.Companion.SignInPageViewModelFactory>
    private val viewModel: SignInPageViewModel by viewModels { viewModelFactory.get() }

    private lateinit var binding: FragmentSignInPageBinding

    @Inject
    lateinit var signInPageCommandProvider: SignInPageCommandProvider

    override fun onAttach(context: Context) {
        DaggerSignInPageComponent.builder()
            .signInPageDependencies(findDependencies())
            .build()
            .inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignInPageBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
        observeState()
        observeEvents()
        observeDatabaseError()
    }

    private fun setupClickListeners() {
        binding.loginButton.setOnClickListener {
            writeToSharedPrefs(getFirstName())
            navigate(signInPageCommandProvider.toLogin)
        }
        binding.signInButton.setOnClickListener {
            binding.signInButton.isClickable = false
            viewModel.signIn(getFirstName(), getLastName(), getEmail())
        }
    }

    private fun observeState() {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            fillError(binding.firstNameEditText, state.firstNameErrorMessageRes)
            fillError(binding.lastNameEditText, state.lastNameErrorMessageRes)
            if (state.emailErrorMessageRes != SignInPageViewModel.NO_ERROR_MESSAGE) {
                fillError(binding.emailEditText, state.emailErrorMessageRes)
            }
            if (state.emailIsNotProperErrorMessageRes != SignInPageViewModel.NO_ERROR_MESSAGE) {
                fillError(binding.emailEditText, state.emailIsNotProperErrorMessageRes, getEmail())
            }
        }
    }

    private fun observeEvents(){
        viewModel.goToPageOne.observeEvent(viewLifecycleOwner){
            navigate(signInPageCommandProvider.toPageOne)

        }
        viewModel.accountError.observeEvent(viewLifecycleOwner){
            showSnackBar(getString(string.account_already_exists))
        }
        viewModel.setSignInButtonActive.observeEvent(viewLifecycleOwner){
            binding.signInButton.isClickable = true
        }
    }

    private fun observeDatabaseError(){
        viewModel.someError.observe(viewLifecycleOwner){
            showSnackBar(it)
        }
    }

    private fun fillError(input: EditText, @StringRes stringResId: Int, extraMessage: String = "") {
        if (stringResId == SignInPageViewModel.NO_ERROR_MESSAGE) {
            input.error = null
        } else {
            input.error = getString(stringResId, extraMessage)
        }
    }

    private fun getFirstName() = binding.firstNameEditText.text.toString().trim()
    private fun getLastName() = binding.lastNameEditText.text.toString().trim()
    private fun getEmail() = binding.emailEditText.text.toString().trim()


    private fun showSnackBar(message: String){
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
    }

    private fun writeToSharedPrefs(firstName: String){
        val sharedPrefs = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        with(sharedPrefs.edit()){
            putString(getString(shared_prefs_user_name_key), firstName)
            apply()
        }
    }

    /*private fun readFromSharedPrefs(){
        val sharedPrefs = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        val firstName = sharedPrefs.getString(getString(shared_prefs_user_name_key), "")
        Log.d("TAGTAGTAG", firstName.toString())
    }*/
}