package tech.mujtaba.optus.databinding

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import tech.mujtaba.network.model.external.User
import tech.mujtaba.optus.R
import tech.mujtaba.optus.feature.userlist.IUserClickListener
import tech.mujtaba.optus.feature.userlist.UserListAdapter
import tech.mujtaba.optus.view.ItemDecoration

@BindingAdapter("userList", "userClickListener")
fun setUserList(recyclerView: RecyclerView, userList: List<User>?, userClickListener: IUserClickListener?) {
    if (userList == null) {
        return
    }
    if (recyclerView.layoutManager == null) {
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context, LinearLayoutManager.VERTICAL, false)
    }
    if (recyclerView.adapter == null) {
        recyclerView.adapter = UserListAdapter(userList, userClickListener)
        recyclerView.addItemDecoration(ItemDecoration(recyclerView.context))
        recyclerView.setHasFixedSize(true)
    }
}

@BindingAdapter("imageUrl")
fun setImage(imageView: AppCompatImageView, imageUrl: String) {
    val options = RequestOptions()
            .placeholder(R.drawable.ic_photo_camera_24)
            .error(R.drawable.ic_photo_camera_24)
    Glide.with(imageView)
            .setDefaultRequestOptions(options)
            .load(imageUrl)
            .into(imageView)
}