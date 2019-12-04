/*
 * Copyright 2018 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.samples.apps.iosched.ui.info

import android.content.Intent
import android.net.wifi.WifiConfiguration
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.google.samples.apps.iosched.R
import com.google.samples.apps.iosched.shared.analytics.AnalyticsActions
import com.google.samples.apps.iosched.shared.analytics.AnalyticsHelper
import com.google.samples.apps.iosched.shared.domain.logistics.LoadWifiInfoUseCase
import com.google.samples.apps.iosched.shared.result.Event
import com.google.samples.apps.iosched.shared.result.data
import com.google.samples.apps.iosched.shared.util.map
import com.google.samples.apps.iosched.shared.util.postValueIfNew
import com.google.samples.apps.iosched.ui.MapActivity
import com.google.samples.apps.iosched.ui.SnackbarMessage
import com.google.samples.apps.iosched.util.wifi.WifiInstaller
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

class EventInfoViewModel @Inject constructor(
    loadWifiInfoUseCase: LoadWifiInfoUseCase,
    private val wifiInstaller: WifiInstaller?,
    private val analyticsHelper: AnalyticsHelper
) : ViewModel() {

    private val _wifiConfig = liveData { emit(loadWifiInfoUseCase(Unit)) }
    val wifiSsid = _wifiConfig.map { it.data?.ssid }
    val wifiPassword = _wifiConfig.map { it.data?.password }

    private val _snackbarMessage = MutableLiveData<Event<SnackbarMessage>>()
    val snackBarMessage: LiveData<Event<SnackbarMessage>>
        get() = _snackbarMessage

    private val _openUrlEvent = MutableLiveData<Event<String>>()
    val openUrlEvent: LiveData<Event<String>>
        get() = _openUrlEvent

    private val _openSiteMapEvent = MutableLiveData<Event<String>>()
    val openSiteMapEvent: LiveData<Event<String>> get() = _openSiteMapEvent

    // TODO: Enable when final
    private val _showWifi = MutableLiveData<Boolean>().apply { value = true }
    val showWifi: LiveData<Boolean>
        get() = _showWifi

    fun onSiteMapOpen(){
        _openSiteMapEvent.postValueIfNew(Event("open"))
    }

    fun onWifiConnect() {
        val ssid = wifiSsid.value
        val password = wifiPassword.value
        var success = false
        if (ssid != null && password != null && wifiInstaller != null) {
            success = wifiInstaller.installConferenceWifi(WifiConfiguration().apply {
                SSID = ssid
                preSharedKey = password
            })
        }
        val snackbarMessage = if (success) {
            SnackbarMessage(R.string.wifi_install_success)
        } else {
            SnackbarMessage(
                messageId = R.string.wifi_install_clipboard_message, longDuration = true
            )
        }

        _snackbarMessage.postValue(Event(snackbarMessage))
        analyticsHelper.logUiEvent("Events", AnalyticsActions.WIFI_CONNECT)
    }
}
