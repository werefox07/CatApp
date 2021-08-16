package com.werefox.core_presentation.fragment

import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.werefox.core_presentation.R
import moxy.MvpAppCompatDialogFragment


abstract class MvpBaseFragment : MvpAppCompatDialogFragment() {

    fun setupToolbar(
        inflatedFragment: View,
        titleId: Int?,
        subtitleId: Int?,
        homeIconId: Int?,
        backButtonEnabled: Boolean,
        toolbarNavigationButtonClickListener: View.OnClickListener?,
        vararg subtitleParams: String
    ) {
        val toolbar: Toolbar = inflatedFragment.findViewById(R.id.toolbar)
        if (titleId != null) {
            toolbar.setTitle(titleId)
        }
        if (subtitleId != null) {
            toolbar.subtitle = getString(subtitleId, * subtitleParams)
        }
        if (homeIconId != null) {
            toolbar.navigationIcon = ContextCompat.getDrawable(
                requireContext(),
                homeIconId
            )
        }
        toolbar.setNavigationOnClickListener(toolbarNavigationButtonClickListener)
    }
}