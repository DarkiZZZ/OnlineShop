package ru.msokolov.onlineshop.profile.presentation.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.Lazy
import ru.msokolov.onlineshop.dagger.findDependencies
import ru.msokolov.onlineshop.feature.profile.R
import ru.msokolov.onlineshop.feature.profile.databinding.FragmentProfileBinding
import ru.msokolov.onlineshop.livedata.observeEvent
import ru.msokolov.onlineshop.navigation.navigate
import ru.msokolov.onlineshop.profile.di.DaggerProfileComponent
import ru.msokolov.onlineshop.profile.presentation.navigation.ProfileCommandProvider
import ru.msokolov.onlineshop.ui.readFromSharedPrefs
import ru.msokolov.onlineshop.ui.showSnackBar
import javax.inject.Inject


@Suppress("DEPRECATION")
class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private lateinit var binding: FragmentProfileBinding

    @Inject
    lateinit var viewModelFactory: Lazy<ProfileViewModel.Companion.ProfileViewModelFactory>
    private val viewModel: ProfileViewModel by viewModels { viewModelFactory.get() }

    @Inject
    lateinit var profileCommandProvider: ProfileCommandProvider

    @Inject
    lateinit var bitmapHelper: BitmapHelper

    override fun onAttach(context: Context) {
        DaggerProfileComponent.builder()
            .profileDependencies(findDependencies())
            .build()
            .inject(this)
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
        viewModel.getAvatarPhoto(readFromSharedPrefs(getString(R.string.shared_prefs_user_name_key)))
        observeAvatarPhoto()
        observeEvents()
        observeSomeError()
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
    }

    @Deprecated("Deprecated in Java")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == NEED_SELECT_PHOTO_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                launchGalleryIntent()
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == SELECT_PHOTO_PERMISSION_CODE && resultCode == Activity.RESULT_OK && data != null) {
            val imageUri = data.data
            if (imageUri != null) {
                val rawBitmap = getRawBitmapFromImageUri(imageUri = imageUri)
                val avatarBitmap = bitmapHelper.getCircularBitmap(bitmap = rawBitmap)
                saveAvatarBlob(bitmapHelper.covertBitmapToBlob(bitmap = avatarBitmap))
                setBitmapToAvatarImageView(avatarBitmap = avatarBitmap)
            }
        }
    }

    private fun observeEvents() {
        viewModel.accountError.observeEvent(viewLifecycleOwner) {
            showSnackBar(binding.root, getString(R.string.non_existent_account))
        }
        viewModel.goToSignInPage.observeEvent(viewLifecycleOwner) {
            navigate(profileCommandProvider.toSignInPage)
        }
    }

    private fun observeSomeError() {
        viewModel.someError.observe(viewLifecycleOwner) {
            showSnackBar(binding.root, it)
        }
    }

    private fun observeAvatarPhoto() {
        viewModel.photo.observe(viewLifecycleOwner) {
            initAvatarPhoto(it)
        }
    }


    private fun setupClickListeners() {
        binding.logOutTextView.setOnClickListener {
            logout()
        }
        binding.changePhotoButton.setOnClickListener {
            pickPhoto()
        }
    }

    private fun logout(){
        viewModel.logout(readFromSharedPrefs(key = getString(R.string.shared_prefs_user_name_key)))
    }

    private fun initAvatarPhoto(photoBlob: ByteArray){
        if (photoBlob.isNotEmpty()) {
            val rawBitmap = bitmapHelper.convertBlobToBitmap(photoBlob = photoBlob)
            val avatarBitmap = bitmapHelper.getCircularBitmap(rawBitmap)
            setBitmapToAvatarImageView(avatarBitmap =  avatarBitmap)
        }
    }

    private fun pickPhoto() {
        if (Build.VERSION.SDK_INT < 28) {
            if (ContextCompat.checkSelfPermission(
                    requireContext(),
                    android.Manifest.permission.READ_EXTERNAL_STORAGE
                )
                != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    requireActivity(), arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                    NEED_SELECT_PHOTO_PERMISSION_CODE
                )
            } else {
                launchGalleryIntent()
            }
        } else {
            launchGalleryIntent()
        }
    }

    private fun getRawBitmapFromImageUri(imageUri: Uri): Bitmap {
        return MediaStore.Images.Media.getBitmap(
            requireActivity().contentResolver,
            imageUri
        )
    }

    private fun saveAvatarBlob(blob: ByteArray){
        viewModel.saveAvatarPhoto(
            readFromSharedPrefs(key = getString(R.string.shared_prefs_user_name_key)),
            blob
        )
    }

    private fun setBitmapToAvatarImageView(avatarBitmap: Bitmap){
        binding.avatarImageView.setImageBitmap(avatarBitmap)
    }

    private fun launchGalleryIntent() {
        val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(galleryIntent, SELECT_PHOTO_PERMISSION_CODE)
    }


    companion object {
        private const val SELECT_PHOTO_PERMISSION_CODE = 100
        private const val NEED_SELECT_PHOTO_PERMISSION_CODE = 101
    }
}