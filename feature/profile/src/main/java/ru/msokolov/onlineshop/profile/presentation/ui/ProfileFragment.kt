package ru.msokolov.onlineshop.profile.presentation.ui

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.*
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.Lazy
import ru.msokolov.onlineshop.dagger.findDependencies
import ru.msokolov.onlineshop.livedata.observeEvent
import ru.msokolov.onlineshop.navigation.navigate
import ru.msokolov.onlineshop.profile.R
import ru.msokolov.onlineshop.profile.databinding.FragmentProfileBinding
import ru.msokolov.onlineshop.profile.di.DaggerProfileComponent
import ru.msokolov.onlineshop.profile.presentation.navigation.ProfileCommandProvider
import ru.msokolov.onlineshop.ui.R.string.shared_prefs_user_name_key
import ru.msokolov.onlineshop.ui.readFromSharedPrefs
import ru.msokolov.onlineshop.ui.showSnackBar
import java.io.ByteArrayOutputStream
import javax.inject.Inject


class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private lateinit var binding: FragmentProfileBinding

    @Inject
    lateinit var viewModelFactory: Lazy<ProfileViewModel.Companion.ProfileViewModelFactory>
    private val viewModel: ProfileViewModel by viewModels { viewModelFactory.get() }

    @Inject
    lateinit var profileCommandProvider: ProfileCommandProvider



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
        viewModel.getAvatarPhoto(readFromSharedPrefs(getString(shared_prefs_user_name_key)))
        observePhotoPath()
        observeEvents()
        observeSomeError()
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
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
            Log.d("SOMEERROR", it)
            showSnackBar(binding.root, it)
        }
    }

    private fun observePhotoPath(){
        viewModel.photo.observe(viewLifecycleOwner){
            if (it.isNotEmpty()) setPhotoToAvatar(photoBlob = it)
        }
    }

    private fun setupClickListeners() {
        binding.logOutTextView.setOnClickListener {
            viewModel.logout(readFromSharedPrefs(key = getString(shared_prefs_user_name_key)))
        }
        binding.changePhotoButton.setOnClickListener {
            handleStoragePermission()
        }
    }

    private fun handleStoragePermission() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.P) {
            openImageChooser()
        }
        else {
            if (ContextCompat.checkSelfPermission(
                    requireActivity(),
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    requireActivity(),
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    1
                )
            } else {
                openImageChooser()
            }
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            SELECT_PICTURE -> {
                for (permission in permissions){
                    val index = permissions.indexOf(permission)
                    if (grantResults.isNotEmpty() && grantResults[index] == PackageManager.PERMISSION_DENIED){
                        val showRationale = ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(), permission);
                        if (!showRationale) {
                            showSettingsAlert();
                        }
                    } else if(grantResults.isNotEmpty() && grantResults[index] == PackageManager.PERMISSION_GRANTED) {
                        openImageChooser()
                    }
                }
            }
            1 -> if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openImageChooser()
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private fun showSettingsAlert() {
        val alertDialog = AlertDialog.Builder(requireContext()).create()
        alertDialog.setTitle("Alert")
        alertDialog.setMessage("App needs to access the Camera.")
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "DON NOT ALLOW"
        ) { dialog, _ ->
            dialog.dismiss()
            //finish();
        }
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "SETTINGS"
        ) { dialog, _ ->
            dialog.dismiss()
            openAppSettings(requireActivity())
        }
        alertDialog.show()
    }

    private fun openAppSettings(context: Activity?) {
        if (context == null) {
            return
        }
        val i = Intent()
        i.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
        i.addCategory(Intent.CATEGORY_DEFAULT)
        i.data = Uri.parse("package:" + context.packageName)
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
        i.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS)
        context.startActivity(i)
    }

    private fun openImageChooser() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && data != null) {
            if (requestCode == SELECT_PICTURE) {
                // Get the url from data
                val selectedImageUri = data.data;
                if (selectedImageUri != null) {
                    val bitmap =
                        MediaStore.Images.Media.getBitmap(requireActivity().contentResolver, selectedImageUri)
                    val output = BitmapHelper().getCircularBitmap(bitmap)
                    val photoBlob = covertBitmapToBlob(output)
                    viewModel.saveAvatarPhoto(readFromSharedPrefs(key = getString(shared_prefs_user_name_key)), photoBlob)
                    binding.avatarImageView.setImageBitmap(output)
                    super.onActivityResult(requestCode, resultCode, data)
                }
            }
        }
    }

    private fun covertBitmapToBlob(bitmap: Bitmap): ByteArray {
        val bos = ByteArrayOutputStream()
        val newBitmap = Bitmap.createScaledBitmap(bitmap, 200, 200, false)
        newBitmap.compress(Bitmap.CompressFormat.PNG, 10, bos)
        return bos.toByteArray()
    }

    private fun setPhotoToAvatar(photoBlob: ByteArray){
        val bitmap = BitmapFactory.decodeByteArray(photoBlob, 0, photoBlob.size)
        val output = BitmapHelper().getCircularBitmap(bitmap)
        binding.avatarImageView.setImageBitmap(output)
    }

    companion object {
        private const val SELECT_PICTURE = 100
    }
}