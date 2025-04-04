package com.msl.lookheart.activity

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.msl.lookheart.app.composable.AppScreen
import com.msl.lookheart.app.viewModel.SharedViewModel
import com.msl.lookheart.utils.LOOK_HEART
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : ComponentActivity() {
    private val sharedViewModel: SharedViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppScreen(sharedViewModel)
        }

        observeEvents()
    }


    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        handleRedirectUri(intent.data)
    }


    private fun handleRedirectUri(uri: Uri?) {
        /** Google Login Handle **/
//        if (uri != null && uri.toString().startsWith("com.msl.lookheart://callback")) {
//            val userDataRepository: SocialUserRepository by inject()
//
//            lifecycleScope.launch {
//                val userDataEncoded = uri.getQueryParameter("userData")
//                userDataRepository.googleLogin(userDataEncoded)
//            }
//        }

        /** Auth Email **/
//        if (uri != null && uri.toString().startsWith("com.msl.email://callback")) {
//            Log.i(LOOK_HEART, "Auth Email RedirectUri")
//        }
    }

    private fun observeEvents() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                sharedViewModel.uiEvent.filterNotNull().collect { event ->
                    when (event) {
                        SharedViewModel.UiEvent.ExitApp -> finishAffinity()
                        SharedViewModel.UiEvent.UpdateApp -> openAppInPlayStore()
                    }
                }
            }
        }
    }

    private fun openAppInPlayStore() {
        try {
            // Play Store
            this.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=${this.packageName}")))
        } catch (exception: ActivityNotFoundException) {
            Log.e(LOOK_HEART, "openAppInPlayStore: ${exception.message}")

            // WEB
            this.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=${this.packageName}")))
        }
    }
}