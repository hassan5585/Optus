package tech.mujtaba.optus.feature.userlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.android.support.DaggerFragment
import tech.mujtaba.network.model.external.User
import tech.mujtaba.optus.databinding.FragmentUserListBinding
import tech.mujtaba.optus.viewmodel.ViewModelFactory
import javax.inject.Inject

class UserListFragment : DaggerFragment(), IUserClickListener {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: UserListViewModel by viewModels { viewModelFactory }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return FragmentUserListBinding.inflate(inflater, container, false).let {
            it.viewModel = viewModel
            it.clickListener = this
            it.lifecycleOwner = viewLifecycleOwner
            it.root
        }
    }

    override fun onUserClicked(user: User) {
        val action = UserListFragmentDirections.actionUserListFragmentToAlbumListFragment(user.id)
        findNavController().navigate(action)
    }
}