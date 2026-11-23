package me.weishu.kernelsu.ui.viewmodel

import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import me.weishu.kernelsu.Natives
import me.weishu.kernelsu.R
import me.weishu.kernelsu.data.repository.SettingsRepository
import me.weishu.kernelsu.data.repository.SettingsRepositoryImpl
import me.weishu.kernelsu.ksuApp
import me.weishu.kernelsu.ui.screen.settings.SettingsScreenActions
import me.weishu.kernelsu.ui.screen.settings.SettingsUiState
import me.weishu.kernelsu.ui.util.execKsud

class SettingsViewModel : ViewModel() {

    private val repo: SettingsRepository = SettingsRepositoryImpl()
    private val _uiState = MutableStateFlow(SettingsUiState())
    val uiState: StateFlow<SettingsUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            val checkUpdate = repo.checkUpdate
            val checkModuleUpdate = repo.checkModuleUpdate
            val themeMode = repo.themeMode
            val keyColor = repo.keyColor
            val enablePredictiveBack = repo.enablePredictiveBack
            val enableSwipeDismiss = repo.enableSwipeDismiss
            val pagerInterceptionMode = repo.pagerInterceptionMode
            val enableNavigationBadge = repo.enableNavigationBadge
            val pageScale = repo.pageScale
            val moduleDescriptionMaxLines = repo.moduleDescriptionMaxLines
            val enableWebDebugging = repo.enableWebDebugging
            val colorStyle = repo.colorStyle
            val colorSpec = repo.colorSpec
            val isLkmMode = repo.isLkmMode()

            // Async loading for natives/features
            val suCompatStatus = repo.getSuCompatStatus()
            val suCompatPersistValue = repo.getSuCompatPersistValue()
            val isSuEnabled = repo.isSuEnabled()

            // Forced features show the value in effect, the persisted one is ignored
            val suCompatMode = when {
                suCompatStatus == "forced" -> if (isSuEnabled) 0 else 2
                suCompatPersistValue == 0L -> 2
                !isSuEnabled -> 1
                else -> 0
            }

            val kernelUmountStatus = repo.getKernelUmountStatus()
            val isKernelUmountEnabled = repo.isKernelUmountEnabled()
            val selinuxHideStatus = repo.getSelinuxHideStatus()
            val isSelinuxHideEnabled = repo.isSelinuxHideEnabled()
            val sulogStatus = repo.getSulogStatus()
            val isSulogEnabled = if (sulogStatus == "forced") {
                repo.getSulogValue() == 1L
            } else {
                repo.getSulogPersistValue() == 1L
            }
            val adbRootStatus = repo.getAdbRootStatus()
            val isAdbRootEnabled = if (adbRootStatus == "forced") {
                repo.getAdbRootValue() == 1L
            } else {
                repo.getAdbRootPersistValue() == 1L
            }
            val isDefaultUmountModules = repo.isDefaultUmountModules()
            val autoJailbreak = repo.autoJailbreak
            val useSoftReboot = repo.useSoftReboot
            val isLateLoadMode = Natives.isLateLoadMode

            _uiState.value = _uiState.value.copy(
                checkUpdate = checkUpdate,
                checkModuleUpdate = checkModuleUpdate,
                themeMode = themeMode,
                keyColor = keyColor,
                enablePredictiveBack = enablePredictiveBack,
                enableSwipeDismiss = enableSwipeDismiss,
                pagerInterceptionMode = pagerInterceptionMode,
                enableNavigationBadge = enableNavigationBadge,
                pageScale = pageScale,
                moduleDescriptionMaxLines = moduleDescriptionMaxLines,
                enableWebDebugging = enableWebDebugging,
                colorStyle = colorStyle,
                colorSpec = colorSpec,
                suCompatStatus = suCompatStatus,
                suCompatMode = suCompatMode,
                isSuEnabled = isSuEnabled,
                adbRootStatus = adbRootStatus,
                isAdbRootEnabled = isAdbRootEnabled,
                kernelUmountStatus = kernelUmountStatus,
                isKernelUmountEnabled = isKernelUmountEnabled,
                selinuxHideStatus = selinuxHideStatus,
                isSelinuxHideEnabled = isSelinuxHideEnabled,
                sulogStatus = sulogStatus,
                isSulogEnabled = isSulogEnabled,
                isDefaultUmountModules = isDefaultUmountModules,
                isLkmMode = isLkmMode,
                autoJailbreak = autoJailbreak,
                useSoftReboot = useSoftReboot,
                isLateLoadMode = isLateLoadMode,
            )
        }
    }

    fun setCheckUpdate(enabled: Boolean) {
        repo.checkUpdate = enabled
        _uiState.value = _uiState.value.copy(checkUpdate = enabled)
    }

    fun setCheckModuleUpdate(enabled: Boolean) {
        repo.checkModuleUpdate = enabled
        _uiState.value = _uiState.value.copy(checkModuleUpdate = enabled)
    }

    fun setThemeMode(mode: Int) {
        repo.themeMode = mode
        _uiState.value = _uiState.value.copy(themeMode = mode)
    }

    fun setKeyColor(color: Int) {
        repo.keyColor = color
        _uiState.value = _uiState.value.copy(keyColor = color)
    }

    fun setColorStyle(style: String) {
        repo.colorStyle = style
        _uiState.value = _uiState.value.copy(colorStyle = style)
    }

    fun setColorSpec(spec: String) {
        repo.colorSpec = spec
        _uiState.value = _uiState.value.copy(colorSpec = spec)
    }

    fun setEnablePredictiveBack(enabled: Boolean) {
        repo.enablePredictiveBack = enabled
        _uiState.value = _uiState.value.copy(enablePredictiveBack = enabled)
    }

    fun setEnableSwipeDismiss(enabled: Boolean) {
        repo.enableSwipeDismiss = enabled
        _uiState.value = _uiState.value.copy(enableSwipeDismiss = enabled)
    }

    fun setPagerInterceptionMode(mode: Int) {
        repo.pagerInterceptionMode = mode
        _uiState.value = _uiState.value.copy(pagerInterceptionMode = mode.coerceIn(0, 2))
    }

    fun setEnableNavigationBadge(enabled: Boolean) {
        repo.enableNavigationBadge = enabled
        _uiState.value = _uiState.value.copy(enableNavigationBadge = enabled)
    }

    fun setPageScale(scale: Float) {
        repo.pageScale = scale
        _uiState.value = _uiState.value.copy(pageScale = scale)
    }

    fun setModuleDescriptionMaxLines(lines: Int) {
        repo.moduleDescriptionMaxLines = lines
        _uiState.value = _uiState.value.copy(moduleDescriptionMaxLines = lines)
    }

    fun setEnableWebDebugging(enabled: Boolean) {
        repo.enableWebDebugging = enabled
        _uiState.value = _uiState.value.copy(enableWebDebugging = enabled)
    }

    fun setSuCompatMode(mode: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.setSuCompatModePref(mode)
            repo.execKsudFeatureSave()
            _uiState.value = _uiState.value.copy(suCompatMode = mode)
        }
    }

    fun setKernelUmountEnabled(enabled: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            if (repo.setKernelUmountEnabled(enabled)) {
                repo.execKsudFeatureSave()
                _uiState.value = _uiState.value.copy(isKernelUmountEnabled = enabled)
            }
        }
    }

    fun setSelinuxHideEnabled(enabled: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            val status = repo.setSelinuxHideEnabled(enabled)
            repo.execKsudFeatureSave()
            when (status) {
                0 -> {
                    _uiState.value = _uiState.value.copy(isSelinuxHideEnabled = enabled)
                }
                1 -> {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(ksuApp, R.string.settings_selinux_hide_reboot_required,
                            Toast.LENGTH_LONG).show()
                    }
                }
                else -> {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(ksuApp, R.string.settings_selinux_hide_failed,
                            Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    fun setSulogEnabled(enabled: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            if (repo.setSulogEnabled(enabled)) {
                repo.execKsudFeatureSave()
                _uiState.value = _uiState.value.copy(isSulogEnabled = enabled)
            }
        }
    }

    fun setAdbRootEnabled(enabled: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            if (repo.setAdbRootEnabled(enabled)) {
                repo.execKsudFeatureSave()
                _uiState.value = _uiState.value.copy(isAdbRootEnabled = enabled)
            }
        }
    }

    fun setDefaultUmountModules(enabled: Boolean) {
        repo.setDefaultUmountModules(enabled)
        _uiState.value = _uiState.value.copy(isDefaultUmountModules = enabled)
    }

    fun setAutoJailbreak(enabled: Boolean) {
        repo.autoJailbreak = enabled
        _uiState.value = _uiState.value.copy(autoJailbreak = enabled)
    }

    fun setUseSoftReboot(enabled: Boolean) {
        repo.useSoftReboot = enabled
        _uiState.value = _uiState.value.copy(useSoftReboot = enabled)
    }

    val screenActions = SettingsScreenActions(
        onSetCheckUpdate = ::setCheckUpdate,
        onSetCheckModuleUpdate = ::setCheckModuleUpdate,
        onOpenTheme = {},
        onOpenProfileTemplate = {},
        onSetSuCompatMode = ::setSuCompatMode,
        onSetKernelUmountEnabled = ::setKernelUmountEnabled,
        onSetSelinuxHideEnabled = ::setSelinuxHideEnabled,
        onSetSulogEnabled = ::setSulogEnabled,
        onSetAdbRootEnabled = ::setAdbRootEnabled,
        onSetDefaultUmountModules = ::setDefaultUmountModules,
        onSetEnableWebDebugging = ::setEnableWebDebugging,
        onSetAutoJailbreak = ::setAutoJailbreak,
        onSetUseSoftReboot = ::setUseSoftReboot,
        onOpenAbout = {},
    )
}
