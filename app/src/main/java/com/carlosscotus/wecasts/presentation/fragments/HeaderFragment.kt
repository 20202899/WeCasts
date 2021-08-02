package com.carlosscotus.wecasts.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.carlosscotus.engine.model.Item
import com.carlosscotus.wecasts.databinding.HeaderItemListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HeaderFragment : Fragment() {
    private var _binding: HeaderItemListBinding? = null
    private val binding: HeaderItemListBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = HeaderItemListBinding.inflate(inflater)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (arguments?.getSerializable("data") as? Item)?.let {
            binding.title.text = it.title
            binding.subtitle.text = it.subTitle
            binding.time.text = it.timeStamp
            binding.content.text = it.contentMessage
            binding.titleContent.text = it.contentTitle
            Glide.with(requireContext()).load(it.image).centerCrop().into(binding.imageView)
        }
    }

    companion object {
        fun newInstance(data: Item) : Fragment {
            val args = Bundle()
            val fragment = HeaderFragment()

            args.putSerializable("data", data)

            fragment.arguments = args
            return fragment
        }
    }
}