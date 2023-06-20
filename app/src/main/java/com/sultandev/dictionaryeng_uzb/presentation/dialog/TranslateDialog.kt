package com.sultandev.dictionaryeng_uzb.presentation.dialog

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.sultandev.dictionaryeng_uzb.R
import com.sultandev.dictionaryeng_uzb.data.entity.WordEntity
import com.sultandev.dictionaryeng_uzb.databinding.DialogTranslateBinding
import com.sultandev.dictionaryeng_uzb.presentation.dialog.impl.TranslateViewModelImpl
import com.sultandev.dictionaryeng_uzb.utils.setSelectImage
import com.sultandev.dictionaryeng_uzb.utils.updateDictionaryListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class TranslateDialog : BottomSheetDialogFragment() {

    private var savedViewInstance: View? = null
    private val args: TranslateDialogArgs by navArgs()
    private val binding: DialogTranslateBinding by viewBinding()
    private val viewModel: TranslateViewModelImpl by viewModel()
    private lateinit var mTTS: TextToSpeech
    private var data: WordEntity? = null

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

        data = args.wordItem

        binding.apply {
            if (data != null) {
                tvEngWord.text = data!!.english
                if (data!!.is_favourite == 1) setSelectImage(true, binding.btnSelect)
                else setSelectImage(false, binding.btnSelect)

                setButtonClickListeners()

            }
        }
    }

    private fun setButtonClickListeners() {

        if (data != null) {

            binding.apply {
                tvCountable.text = data!!.countable
                tvType.text = data!!.type
                tvTranscription.text = data!!.transcript
                tvTranslation.text = data!!.uzbek

                btnSelect.isClickable = false

                btnSelect.setOnClickListener {
                    startAnimZoomIn(it)
                    if (data!!.is_favourite == 1) {
                        data!!.is_favourite = 0
                        setSelectImage(false, binding.btnSelect)
                    } else {
                        data!!.is_favourite = 1
                        setSelectImage(true, binding.btnSelect)
                    }
                    viewModel.updateDictionary(data!!)
                    updateDictionaryListener.value = args.positionDictionary
                }

                btnSpeak.setOnClickListener {
                    startAnimZoomIn(it)
                    speak(data!!.english)
                }
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

    private fun startAnimZoomIn(view: View) {
        YoYo.with(Techniques.ZoomIn)
            .delay(0)
            .duration(500)
            .playOn(view)
    }
}