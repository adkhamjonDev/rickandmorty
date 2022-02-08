package uz.adkhamjon.rickandmorty.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.adkhamjon.rickandmorty.App
import uz.adkhamjon.rickandmorty.R
import uz.adkhamjon.rickandmorty.adapters.RvAdapter
import uz.adkhamjon.rickandmorty.databinding.FragmentListBinding
import uz.adkhamjon.rickandmorty.db.database.AppDatabase
import uz.adkhamjon.rickandmorty.db.entity.CharacterEntity
import uz.adkhamjon.rickandmorty.models.Result
import uz.adkhamjon.rickandmorty.viewmodels.CharacterViewModel
import javax.inject.Inject

class ListFragment : Fragment() {
    private lateinit var binding:FragmentListBinding
    private lateinit var rvAdapter: RvAdapter
    @Inject
    lateinit var characterViewModel: CharacterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        App.appComponent.inject(this)

        binding=FragmentListBinding.inflate(inflater, container, false)
        rvAdapter = RvAdapter(requireContext(),object:RvAdapter.OnItemClickListener{
            override  fun itemClick(result: Result) {

            }
        })
        binding.recView.adapter = rvAdapter
        lifecycleScope.launch {
            characterViewModel.characters.collectLatest {
                rvAdapter.submitData(it)
            }
        }

        return binding.root
    }

}