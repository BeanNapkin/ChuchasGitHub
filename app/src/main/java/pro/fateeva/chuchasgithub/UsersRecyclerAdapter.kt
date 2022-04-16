package pro.fateeva.chuchasgithub

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pro.fateeva.chuchasgithub.databinding.UserItemBinding
import pro.fateeva.chuchasgithub.domain.entities.User

class UsersRecyclerAdapter(
    var userList: List<User>,
    private val userCardClickListener: MyCallback
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
                    userCardClickListener.onClick(this@UserViewHolder.adapterPosition)
                }
            }
        }
    }
}