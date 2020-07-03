package tech.mujtaba.optus.feature.picturelist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import tech.mujtaba.network.model.external.UserAlbum
import tech.mujtaba.optus.databinding.ItemImageBinding

class PictureListAdapter(private val pictureList: List<UserAlbum>, private val pictureClickListener: IPictureClickListener?) : RecyclerView.Adapter<PictureListAdapter.PictureListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PictureListViewHolder {
        return with(ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)) {
            PictureListViewHolder(root)
        }
    }

    override fun getItemCount() = pictureList.size

    override fun onBindViewHolder(holder: PictureListViewHolder, position: Int) {
        holder.binding?.picture = pictureList[position]
        pictureClickListener?.let {
            holder.binding?.clickListener = it
        }
        holder.binding?.executePendingBindings()
    }


    class PictureListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding: ItemImageBinding? = DataBindingUtil.bind(itemView)
    }
}