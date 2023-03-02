package ru.msokolov.onlineshop.login.presentation.ui

import android.content.Context
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.annotation.StringRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.Lazy
import ru.msokolov.onlineshop.dagger.findDependencies
import ru.msokolov.onlineshop.livedata.observeEvent
import ru.msokolov.onlineshop.login.R
import ru.msokolov.onlineshop.login.databinding.FragmentLoginBinding
import ru.msokolov.onlineshop.login.di.DaggerLoginComponent
import ru.msokolov.onlineshop.login.presentation.navigation.LoginCommandProvider
import ru.msokolov.onlineshop.navigation.navigate
import ru.msokolov.onlineshop.ui.R.*
import ru.msokolov.onlineshop.ui.showSnackBar
import javax.inject.Inject

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    @Inject
    lateinit var viewModelFactory: Lazy<LoginViewModel.Companion.LoginViewModelFactory>
    private val viewModel: LoginViewModel by viewModels { viewModelFactory.get() }

    @Inject
    lateinit var loginCommandProvider: LoginCommandProvider

    override fun onAttach(context: Context) {
        DaggerLoginComponent.builder()
            .loginDependencies(findDependencies())
            .build()
            .inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        observePasswordVisibility()
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
        observeState()
        observeEvents()
        observeDatabaseError()
    }

    private fun setupClickListeners() {
        binding.passwordConditionButton.setOnClickListener {
            viewModel.handlePasswordVisibility()
        }
        binding.loginButton.setOnClickListener {
            viewModel.login(getFirstName(), getPassword())
        }
    }

    private fun observePasswordVisibility() {
        viewModel.isPasswordVisible.observe(viewLifecycleOwner) {
            setPasswordVisibility(isVisible = it)
        }
    }

    private fun observeState() {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            fillError(binding.firstNameEditText, state.firstNameErrorMessageRes)
            fillError(binding.passwordEditText, state.passwordErrorMessageRes)
        }
    }

    private fun observeEvents(){
        viewModel.goToPageOne.observeEvent(viewLifecycleOwner){
            writeToSharedPrefs(getFirstName())
            navigate(loginCommandProvider.toPageOne)

        }
        viewModel.accountError.observeEvent(viewLifecycleOwner){
            showSnackBar(binding.root, getString(R.string.account_have_not_created_yet))
        }
        viewModel.setSignInButtonActive.observeEvent(viewLifecycleOwner){
            binding.loginButton.isClickable = true
        }
    }

    private fun fillError(input: EditText, @StringRes stringResId: Int, extraMessage: String = "") {
        if (stringResId == LoginViewModel.NO_ERROR_MESSAGE) {
            input.error = null
        } else {
            input.error = getString(stringResId, extraMessage)
        }
    }

    private fun observeDatabaseError(){
        viewModel.someError.observe(viewLifecycleOwner){
            showSnackBar(binding.root, it)
        }
    }

    private fun setPasswordVisibility(isVisible: Boolean){
        if (isVisible) {
            binding.passwordConditionButton.background = AppCompatResources.getDrawable(
                requireContext(),
                drawable.ic_password_view_disable
            )
            with(binding.passwordEditText) {
                transformationMethod = null
                setSelection(binding.passwordEditText.length())
            }
        } else {
            binding.passwordConditionButton.background = AppCompatResources.getDrawable(
                requireContext(),
                drawable.ic_password_view_enable
            )
            with(binding.passwordEditText) {
                transformationMethod = PasswordTransformationMethod()
                setSelection(binding.passwordEditText.length())
            }
        }
    }

    private fun getFirstName() = binding.firstNameEditText.text.toString().trim()
    private fun getPassword() = binding.passwordEditText.text.toString().trim()

    private fun writeToSharedPrefs(firstName: String){
        val sharedPrefs = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        with(sharedPrefs.edit()){
            putString(getString(string.shared_prefs_user_name_key), firstName)
            apply()
        }
    }
}