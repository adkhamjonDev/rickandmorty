package uz.adkhamjon.rickandmorty.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.adkhamjon.rickandmorty.App
import uz.adkhamjon.rickandmorty.R
import uz.adkhamjon.rickandmorty.adapters.RvAdapter
import uz.adkhamjon.rickandmorty.databinding.FragmentListBinding
import uz.adkhamjon.rickandmorty.interfaces.OnItemClickListener
import uz.adkhamjon.rickandmorty.models.Result
import uz.adkhamjon.rickandmorty.viewmodels.CharacterViewModel
import javax.inject.Inject

class ListFragment : Fragment() {
    private lateinit var binding:FragmentListBinding
    private lateinit var rvAdapter: RvAdapter
    @Inject
    lateinit var characterViewModel: CharacterViewModel
    private lateinit var gridLayoutManager: GridLayoutManager
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        App.appComponent.inject(this)
        binding=FragmentListBinding.inflate(inflater, container, false)

        gridLayoutManager= GridLayoutManager(context,1)
        rvAdapter = RvAdapter(requireContext(),gridLayoutManager, object : OnItemClickListener {
                override fun itemClick(result: Result) {
                    findNavController().navigate(R.id.webFragment)
                }
        })
        lifecycleScope.launch {
            characterViewModel.characters.collectLatest {
                rvAdapter.submitData(it)
            }
        }
        binding.recView.hasFixedSize()
        binding.recView.layoutManager=gridLayoutManager
        binding.recView.adapter = rvAdapter
        binding.grid1.setOnClickListener {
            binding.grid1.visibility= View.GONE
            binding.grid2.visibility= View.VISIBLE
            binding.grid3.visibility= View.GONE
            gridLayoutManager.spanCount = 1
            rvAdapter.notifyItemRangeChanged(0, rvAdapter.itemCount)
        }
        binding.grid2.setOnClickListener {
            binding.grid1.visibility= View.GONE
            binding.grid2.visibility= View.GONE
            binding.grid3.visibility= View.VISIBLE
            gridLayoutManager.spanCount = 2
            rvAdapter.notifyItemRangeChanged(0, rvAdapter.itemCount)

        }
        binding.grid3.setOnClickListener {
            binding.grid1.visibility= View.VISIBLE
            binding.grid2.visibility= View.GONE
            binding.grid3.visibility= View.GONE
            gridLayoutManager.spanCount = 3
            rvAdapter.notifyItemRangeChanged(0, rvAdapter.itemCount)


        }
        return binding.root
    }
}