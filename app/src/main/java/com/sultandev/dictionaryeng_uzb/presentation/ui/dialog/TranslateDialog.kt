package com.sultandev.dictionaryeng_uzb.presentation.ui.dialog

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.sultandev.dictionaryeng_uzb.R
import com.sultandev.dictionaryeng_uzb.databinding.DialogTranslateBinding
import com.sultandev.dictionaryeng_uzb.presentation.ui.dialog.impl.TranslateViewModelImpl
import com.sultandev.dictionaryeng_uzb.utils.setSelectImage
import com.sultandev.dictionaryeng_uzb.utils.updateDictionaryListener
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class TranslateDialog: BottomSheetDialogFragment() {

    private var savedViewInstance: View? = null
    private val args: TranslateDialogArgs by navArgs()
    private val binding: DialogTranslateBinding by viewBinding()
    private val viewModel: TranslateViewModelImpl by viewModel()
    private lateinit var mTTS: TextToSpeech

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return if (savedInstanceState != null) {
            savedViewInstance
        } else {
            savedViewInstance = inflater.inflate(R.layout.dialog_translate, container, true)
            savedViewInstance
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data = args.wordItem

        binding.apply {
            tvEngWord.text = data.english

            if(data.is_favourite == 1) setSelectImage(true, binding.btnSelect)
            else setSelectImage(false, binding.btnSelect)

            tvCountable.text = data.countable
            tvType.text = data.type
            tvTranscription.text = data.transcript
            tvTranslation.text = data.uzbek

            btnSelect.setOnClickListener {
                if(data.is_favourite == 1) {
                    data.is_favourite = 0
                    setSelectImage(false, binding.btnSelect)
                }
                else {
                    data.is_favourite = 1
                    setSelectImage(true, binding.btnSelect)
                }
                viewModel.updateDictionary(data)
                updateDictionaryListener.value = args.positionDictionary
            }

            btnSpeak.setOnClickListener {
                speak(data.english)
            }
        }
    }

    private fun speak(english: String) {
        mTTS = TextToSpeech(requireContext()) {
            if (it == TextToSpeech.SUCCESS) {
                mTTS.language = Locale.ENGLISH
                mTTS.speak(english, TextToSpeech.QUEUE_FLUSH, null, null)
            }
        }
    }
}