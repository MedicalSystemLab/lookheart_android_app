package com.msl.lookheart.activity

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
//import com.msl.lookheart.app.composable.AppScreen
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.text.startsWith


class MainActivity : ComponentActivity() {
//    private val sharedViewModel: SharedViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        setContent {
//            com.msl.lookheart.app.composable.AppScreen()
//        }

//        observeEvents()
    }


//    override fun onNewIntent(intent: Intent) {
//        ComponentActivity.onNewIntent(intent)
//        handleRedirectUri(intent.data)
//    }


    private fun handleRedirectUri(uri: Uri?) {
        /** Google Login Handle **/
//        if (uri != null && uri.toString().startsWith("com.msl.lookheart://callback")) {
//            val userDataRepository: com.msl.lookheart.screens.signup.services.SocialUserRepository by inject()
//
//            lifecycleScope.launch {
//                val userDataEncoded = uri.getQueryParameter("userData")
//                com.msl.lookheart.screens.signup.services.SocialUserRepository.googleLogin(
//                    userDataEncoded
//                )
//            }
//        }

        /** Auth Email **/
//        if (uri != null && uri.toString().startsWith("com.msl.email://callback")) {
//            println("uri: $uri")
//        }
    }

//    private fun observeEvents() {
//        lifecycleScope.launch {
//            ComponentActivity.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
//                Flow.collect { event ->
//                    when (event) {
//                        com.msl.lookheart.app.SharedViewModel.UiEvent.ExitApp -> finishAffinity()
//                        com.msl.lookheart.app.SharedViewModel.UiEvent.UpdateApp -> openAppInPlayStore()
//                    }
//                }
//            }
//        }
//    }

//    private fun openAppInPlayStore() {
//        try {
//            // Play Store
//            this.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=${this.packageName}")))
//        } catch (exception: ActivityNotFoundException) {
//            println(Throwable.message)
//            // WEB
//            this.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=${this.packageName}")))
//        }
//    }
}