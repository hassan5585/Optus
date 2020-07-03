package tech.mujtaba.optus.feature.picturedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.android.support.DaggerFragment
import tech.mujtaba.optus.R
import tech.mujtaba.optus.databinding.FragmentPictureDetailsBinding
import tech.mujtaba.optus.databinding.FragmentPictureListBinding
import tech.mujtaba.optus.viewmodel.ViewModelFactory
import javax.inject.Inject

class PictureDetailsFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: PictureDetailsViewModel by viewModels { viewModelFactory }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return FragmentPictureDetailsBinding.inflate(inflater, container, false).let {
            it.viewModel = viewModel
            it.lifecycleOwner = viewLifecycleOwner
            it.root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = PictureDetailsFragmentArgs.fromBundle(requireArguments())
        viewModel.getUserPicture(args.albumId, args.pictureId)
    }
}