/**
 * Metrolist Project (C) 2026
 * Licensed under GPL-3.0 | See git history for contributors
 */

package com.metrolist.music.ui.screens.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.metrolist.music.LocalPlayerAwareWindowInsets
import com.metrolist.music.R
import com.metrolist.music.constants.*
import com.metrolist.music.ui.component.ActionPromptDialog
import com.metrolist.music.ui.component.IconButton
import com.metrolist.music.ui.component.Material3SettingsGroup
import com.metrolist.music.ui.component.Material3SettingsItem
import com.metrolist.music.ui.utils.backToMain
import com.metrolist.music.utils.rememberPreference

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RomanizationSettings(
    navController: NavController,
    scrollBehavior: TopAppBarScrollBehavior,
) {
    val (lyricsRomanizeAsMain, onLyricsRomanizeAsMainChange) = rememberPreference(LyricsRomanizeAsMainKey, false)
    val (lyricsRomanizeJapanese, onLyricsRomanizeJapaneseChange) = rememberPreference(LyricsRomanizeJapaneseKey, true)
    val (lyricsRomanizeKorean, onLyricsRomanizeKoreanChange) = rememberPreference(LyricsRomanizeKoreanKey, true)
    val (lyricsRomanizeChinese, onLyricsRomanizeChineseChange) = rememberPreference(LyricsRomanizeChineseKey, true)
    val (lyricsRomanizeHindi, onLyricsRomanizeHindiChange) = rememberPreference(LyricsRomanizeHindiKey, true)
    val (lyricsRomanizePunjabi, onLyricsRomanizePunjabiChange) = rememberPreference(LyricsRomanizePunjabiKey, true)
    val (lyricsRomanizeRussian, onLyricsRomanizeRussianChange) = rememberPreference(LyricsRomanizeRussianKey, true)
    val (lyricsRomanizeUkrainian, onLyricsRomanizeUkrainianChange) = rememberPreference(LyricsRomanizeUkrainianKey, true)
    val (lyricsRomanizeSerbian, onLyricsRomanizeSerbianChange) = rememberPreference(LyricsRomanizeSerbianKey, true)
    val (lyricsRomanizeBulgarian, onLyricsRomanizeBulgarianChange) = rememberPreference(LyricsRomanizeBulgarianKey, true)
    val (lyricsRomanizeBelarusian, onLyricsRomanizeBelarusianChange) = rememberPreference(LyricsRomanizeBelarusianKey, true)
    val (lyricsRomanizeKyrgyz, onLyricsRomanizeKyrgyzChange) = rememberPreference(LyricsRomanizeKyrgyzKey, true)
    val (lyricsRomanizeMacedonian, onLyricsRomanizeMacedonianChange) = rememberPreference(LyricsRomanizeMacedonianKey, true)
    val (lyricsRomanizeCyrillicByLine, onLyricsRomanizeCyrillicByLineChange) = rememberPreference(LyricsRomanizeCyrillicByLineKey, false)
    
    val (showDialog, setShowDialog) = remember { mutableStateOf(false) }

    Column(
        Modifier
            .windowInsetsPadding(LocalPlayerAwareWindowInsets.current)
            .verticalScroll(rememberScrollState())
    ) {
        Material3SettingsGroup(
            title = stringResource(R.string.general),
            items = listOf(
                Triple(R.string.lyrics_romanize_as_main, R.drawable.lyrics, lyricsRomanizeAsMain to onLyricsRomanizeAsMainChange),
                Triple(R.string.lyrics_romanize_japanese, R.drawable.language_japanese_latin, lyricsRomanizeJapanese to onLyricsRomanizeJapaneseChange),
                Triple(R.string.lyrics_romanize_korean, R.drawable.language_korean_latin, lyricsRomanizeKorean to onLyricsRomanizeKoreanChange),
                Triple(R.string.lyrics_romanize_chinese, R.drawable.language, lyricsRomanizeChinese to onLyricsRomanizeChineseChange),
                Triple(R.string.lyrics_romanize_hindi, R.drawable.language, lyricsRomanizeHindi to onLyricsRomanizeHindiChange),
                Triple(R.string.lyrics_romanize_punjabi, R.drawable.language, lyricsRomanizePunjabi to onLyricsRomanizePunjabiChange)
            ).map { (title, icon, pref) ->
                Material3SettingsItem(
                    title = { Text(stringResource(title)) },
                    icon = painterResource(icon),
                    trailingContent = { Switch(pref.first, pref.second) },
                    onClick = { pref.second(!pref.first) }
                )
            }
        )

        Material3SettingsGroup(
            title = stringResource(R.string.lyrics_romanization_cyrillic),
            items = buildList {
                listOf(
                    R.string.lyrics_romanize_russian to (lyricsRomanizeRussian to onLyricsRomanizeRussianChange),
                    R.string.lyrics_romanize_ukrainian to (lyricsRomanizeUkrainian to onLyricsRomanizeUkrainianChange),
                    R.string.lyrics_romanize_serbian to (lyricsRomanizeSerbian to onLyricsRomanizeSerbianChange),
                    R.string.lyrics_romanize_bulgarian to (lyricsRomanizeBulgarian to onLyricsRomanizeBulgarianChange),
                    R.string.lyrics_romanize_belarusian to (lyricsRomanizeBelarusian to onLyricsRomanizeBelarusianChange),
                    R.string.lyrics_romanize_kyrgyz to (lyricsRomanizeKyrgyz to onLyricsRomanizeKyrgyzChange),
                    R.string.lyrics_romanize_macedonian to (lyricsRomanizeMacedonian to onLyricsRomanizeMacedonianChange)
                ).forEach { (title, pref) ->
                    add(Material3SettingsItem(
                        title = { Text(stringResource(title)) },
                        icon = painterResource(R.drawable.alphabet_cyrillic),
                        trailingContent = { Switch(pref.first, pref.second) },
                        onClick = { pref.second(!pref.first) }
                    ))
                }
                add(Material3SettingsItem(
                    title = { Text(stringResource(R.string.line_by_line_option_title)) },
                    description = { Text(stringResource(R.string.line_by_line_option_desc)) },
                    icon = painterResource(R.drawable.warning),
                    trailingContent = {
                        Switch(lyricsRomanizeCyrillicByLine, { if (it) setShowDialog(true) else onLyricsRomanizeCyrillicByLineChange(false) })
                    },
                    onClick = { if (!lyricsRomanizeCyrillicByLine) setShowDialog(true) else onLyricsRomanizeCyrillicByLineChange(false) }
                ))
            }
        )
    }

    if (showDialog) {
        ActionPromptDialog(
            title = stringResource(R.string.line_by_line_dialog_title),
            onDismiss = { setShowDialog(false) },
            onConfirm = { onLyricsRomanizeCyrillicByLineChange(true); setShowDialog(false) },
            onCancel = { setShowDialog(false) },
            content = { Text(stringResource(R.string.line_by_line_dialog_desc)) }
        )
    }

    TopAppBar(
        title = { Text(stringResource(R.string.lyrics_romanize_title)) },
        navigationIcon = {
            IconButton(onClick = navController::navigateUp, onLongClick = navController::backToMain) {
                Icon(painterResource(R.drawable.arrow_back), null)
            }
        }
    )
}
