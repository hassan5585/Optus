package tech.mujtaba.optus.databinding

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.bumptech.glide.request.RequestOptions
import tech.mujtaba.network.model.external.User
import tech.mujtaba.network.model.external.UserAlbum
import tech.mujtaba.optus.R
import tech.mujtaba.optus.USER_AGENT_KEY
import tech.mujtaba.optus.USER_AGENT_VALUE
import tech.mujtaba.optus.feature.picturelist.IPictureClickListener
import tech.mujtaba.optus.feature.picturelist.PictureListAdapter
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

@BindingAdapter("pictureList", "pictureClickListener")
fun setPictureList(recyclerView: RecyclerView, pictureList: List<UserAlbum>?, pictureClickListener: IPictureClickListener?) {
    if (pictureList == null) {
        return
    }
    if (recyclerView.layoutManager == null) {
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context, LinearLayoutManager.VERTICAL, false)
    }
    if (recyclerView.adapter == null) {
        recyclerView.adapter = PictureListAdapter(pictureList, pictureClickListener)
        recyclerView.addItemDecoration(ItemDecoration(recyclerView.context))
        recyclerView.setHasFixedSize(true)
    }
}

@BindingAdapter("imageUrl")
fun setImage(imageView: AppCompatImageView, imageUrl: String?) {
    if (imageUrl == null) {
        return
    }
    val options = RequestOptions()
            .placeholder(R.drawable.ic_photo_camera_24)
            .error(R.drawable.ic_photo_camera_24)
    val glideUrl = GlideUrl(imageUrl, LazyHeaders.Builder()
            .addHeader(USER_AGENT_KEY, USER_AGENT_VALUE)
            .build())
    Glide.with(imageView.context)
            .setDefaultRequestOptions(options)
            .load(glideUrl)
            .into(imageView)
}