package pro.fateeva.chuchasgithub

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import pro.fateeva.chuchasgithub.databinding.UserItemBinding
import pro.fateeva.chuchasgithub.domain.entities.User

class UsersRecyclerAdapter(
    var userList: List<User>,
    private val userCardClickListener: (position: Int) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val bindingViewHolder = UserItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return UserViewHolder(bindingViewHolder.root)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as UserViewHolder).bind(userList[position])
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    inner class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(user: User) {
            UserItemBinding.bind(itemView).apply {
                userNameTextView.text = user.name

                userCardView.setOnClickListener {
                    userCardClickListener.invoke(this@UserViewHolder.adapterPosition)
                }
            }
        }
    }
}

class DiffUtilCallback(val oldList: List<User>, val newList: List<User>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size
    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldUser: User = oldList[oldItemPosition]
        val newUser: User = newList[newItemPosition]
        return oldUser.id == newUser.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldUser: User = oldList[oldItemPosition]
        val newUser: User = newList[newItemPosition]
        return oldUser == newUser
    }
}