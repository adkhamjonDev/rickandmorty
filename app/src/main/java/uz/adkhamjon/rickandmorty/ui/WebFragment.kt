package uz.adkhamjon.rickandmorty.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.adkhamjon.rickandmorty.databinding.FragmentWebBinding

class WebFragment : Fragment() {
    private lateinit var binding:FragmentWebBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=FragmentWebBinding.inflate(inflater, container, false)

        binding.webView.loadUrl("https://rickandmortyapi.com/")

        return binding.root
    }

}