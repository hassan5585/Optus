package tech.mujtaba.optus.feature.userlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import tech.mujtaba.network.model.external.User
import tech.mujtaba.optus.databinding.ItemUserBinding

class UserListAdapter(private val userList: List<User>, private val userClickListener: IUserClickListener?) : RecyclerView.Adapter<UserListAdapter.UserListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolder {
        return with(ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)) {
            UserListViewHolder(root)
        }
    }

    override fun getItemCount() = userList.size

    override fun onBindViewHolder(holder: UserListViewHolder, position: Int) {
        holder.binding?.user = userList[position]
        userClickListener?.let {
            holder.binding?.clickListener = it
        }
        holder.binding?.executePendingBindings()
    }

    class UserListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding: ItemUserBinding? = DataBindingUtil.bind(itemView)
    }
}