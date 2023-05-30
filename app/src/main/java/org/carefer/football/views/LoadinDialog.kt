package org.carefer.football.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import org.carefer.football.R
import org.carefer.football.databinding.DialogLoadingBinding
import org.carefer.football.views.extentions.setDefaultWindowTheme

open class LoadingDialog : DialogFragment() {

    private var _binding: DialogLoadingBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun getInstance(): LoadingDialog {
            return LoadingDialog()
        }
    }


    override fun onStart() {
        super.onStart()
        dialog?.setDefaultWindowTheme()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setStyle(STYLE_NO_TITLE, R.style.LoadingDialogTransparent)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogLoadingBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loLoading.playAnimation()

        dialog?.setCancelable(false)
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

    fun isShowing(): Boolean {
        return if (dialog != null) {
            dialog?.isShowing ?: false
        } else {
            dismiss()
            false
        }
    }
}