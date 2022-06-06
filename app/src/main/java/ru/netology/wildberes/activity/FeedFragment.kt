package ru.netology.wildberes.activity


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.netology.wildberes.adapter.AirTravelAdapter
import ru.netology.wildberes.databinding.FeedFragmentBinding
import ru.netology.wildberes.viewModel.AirTravelViewModel

class FeedFragment : Fragment() {

    private val viewModel: AirTravelViewModel by viewModels(
        ownerProducer = ::requireParentFragment
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadAirTravels()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FeedFragmentBinding.inflate(inflater, container, false).also { binding ->
        val adapter = AirTravelAdapter(viewModel)
        binding.list.adapter = adapter
        viewModel.data.observe(viewLifecycleOwner) { state ->
            adapter.submitList(state.airTravels)
            binding.progress.isVisible = state.loading
            binding.errorGroup.isVisible = state.error
            binding.emptyText.isVisible = state.empty
        }

        binding.retryButton.setOnClickListener {
            viewModel.loadAirTravels()
        }

        viewModel.navigeteToThisAirTravelScreen.observe(viewLifecycleOwner) { airTravelId ->
            val directions =
                FeedFragmentDirections.actionFeedFragmentToViewAirTravelFragment(airTravelId)
            findNavController().navigate(directions)
        }
    }.root

}


