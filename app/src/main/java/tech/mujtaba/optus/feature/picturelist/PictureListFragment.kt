package tech.mujtaba.optus.feature.picturelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.android.support.DaggerFragment
import tech.mujtaba.optus.R
import tech.mujtaba.optus.viewmodel.ViewModelFactory
import javax.inject.Inject

class PictureListFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: PictureListViewModel by viewModels { viewModelFactory }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_picture_list, container, false)
    }
}