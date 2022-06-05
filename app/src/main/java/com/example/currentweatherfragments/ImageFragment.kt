package com.example.currentweatherfragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

private const val ARG_PARAM1 = "textIcon"

class ImageFragment : Fragment() {
    private var param1: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_image, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imageViewFragment: ImageView = view.findViewById(R.id.image_view_fragment)
        when(param1){
            "01d" -> imageViewFragment.setImageResource(R.drawable.icon01d)
            "02d" -> imageViewFragment.setImageResource(R.drawable.icon02d)
            "03d" -> imageViewFragment.setImageResource(R.drawable.icon03d)
            "04d" -> imageViewFragment.setImageResource(R.drawable.icon04d)
            "09d" -> imageViewFragment.setImageResource(R.drawable.icon09d)
            "10d" -> imageViewFragment.setImageResource(R.drawable.icon10d)
            "11d" -> imageViewFragment.setImageResource(R.drawable.icon11d)
            "13d" -> imageViewFragment.setImageResource(R.drawable.icon13d)
            "50d" -> imageViewFragment.setImageResource(R.drawable.icon50d)
            "01n" -> imageViewFragment.setImageResource(R.drawable.icon01n)
            "02n" -> imageViewFragment.setImageResource(R.drawable.icon02n)
            "03n" -> imageViewFragment.setImageResource(R.drawable.icon03n)
            "04n" -> imageViewFragment.setImageResource(R.drawable.icon04n)
            "09n" -> imageViewFragment.setImageResource(R.drawable.icon09n)
            "10n" -> imageViewFragment.setImageResource(R.drawable.icon10n)
            "11n" -> imageViewFragment.setImageResource(R.drawable.icon11n)
            "13n" -> imageViewFragment.setImageResource(R.drawable.icon13n)
            "50n" -> imageViewFragment.setImageResource(R.drawable.icon50n)
        }
    }

    companion object {
        fun newInstance(param1: String) =
            ImageFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }
}