package ru.netology.wildberes.activity


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import ru.netology.wildberes.adapter.AirTravelAdapter
import ru.netology.wildberes.databinding.FeedFragmentBinding
import ru.netology.wildberes.databinding.ViewAirTravelFragmentBinding
import ru.netology.wildberes.viewModel.AirTravelViewModel

class ViewAirTravelFragment : Fragment() {

    private val viewModel: AirTravelViewModel by viewModels(
        ownerProducer = ::requireParentFragment
    )
    private val args by navArgs<ViewAirTravelFragmentArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ViewAirTravelFragmentBinding.inflate(inflater, container, false).also { binding ->
        viewModel.data.observe(viewLifecycleOwner){airTravels->
            with(binding) {
                val airTravel = airTravels.airTravels.find{it.searchToken == args.airTravelId}?:return@observe
                arivialCity.text = airTravel.startCity
                departureCity.text = airTravel.endCity
                arivialDate.text = airTravel.startDate.toString()
                departureDate.text = airTravel.endDate.toString()
                price.text = airTravel.price.toString()
                like.text = airTravel.likes.toString()
                like.isChecked = airTravel.likeByMe
                like.setOnClickListener {
                    viewModel.onLike(airTravel)
                }
                home.setOnClickListener{
                    parentFragmentManager.popBackStack()
                }
            }
        }

    }.root

}


