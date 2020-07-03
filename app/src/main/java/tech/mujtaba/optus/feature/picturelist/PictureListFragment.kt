package tech.mujtaba.optus.feature.picturelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.android.support.DaggerFragment
import tech.mujtaba.network.model.external.UserAlbum
import tech.mujtaba.optus.R
import tech.mujtaba.optus.databinding.FragmentPictureListBinding
import tech.mujtaba.optus.databinding.FragmentUserListBinding
import tech.mujtaba.optus.viewmodel.ViewModelFactory
import javax.inject.Inject

class PictureListFragment : DaggerFragment(), IPictureClickListener {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: PictureListViewModel by viewModels { viewModelFactory }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return FragmentPictureListBinding.inflate(inflater, container, false).let {
            it.viewModel = viewModel
            it.clickListener = this
            it.lifecycleOwner = viewLifecycleOwner
            it.root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getUserAlbums(PictureListFragmentArgs.fromBundle(requireArguments()).userId)
    }

    override fun onPictureClicked(picture: UserAlbum) {
        val action = PictureListFragmentDirections.actionAlbumListFragmentToAlbumDetailsFragment(picture.id, picture.albumId)
        findNavController().navigate(action)
    }
}