package com.example.currentweatherfragments

import android.app.Dialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class AlertDialogFragments : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle("Choose visualization")
                .setMessage("Only icon or only text?")
                .setIcon(R.drawable.icon03d)
                .setCancelable(true)
                .setPositiveButton("Icon") { dialog, id ->
                    (activity as MainActivity?)?.onIconFragment()
                }
                .setNegativeButton("Text") { dialog, id ->
                    (activity as MainActivity?)?.onTextFragment()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}