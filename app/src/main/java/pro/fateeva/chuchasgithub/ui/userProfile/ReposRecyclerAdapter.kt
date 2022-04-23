package pro.fateeva.chuchasgithub

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import pro.fateeva.chuchasgithub.databinding.RepoItemBinding
import pro.fateeva.chuchasgithub.databinding.UserItemBinding
import pro.fateeva.chuchasgithub.domain.entities.Repo
import pro.fateeva.chuchasgithub.domain.entities.User

class ReposRecyclerAdapter(
    var repoList: List<Repo>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val bindingViewHolder = RepoItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return RepoViewHolder(bindingViewHolder.root)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as RepoViewHolder).bind(repoList[position].name)
    }

    override fun getItemCount(): Int {
        return repoList.size
    }

    inner class RepoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(repo: String) {
            RepoItemBinding.bind(itemView).apply {
                reposTitleTextView.text = repo
            }
        }
    }
}

class RepoDiffUtilCallback(val oldList: List<Repo>, val newList: List<Repo>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size
    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldRepo: Repo = oldList[oldItemPosition]
        val newRepo: Repo = newList[newItemPosition]
        return oldRepo == newRepo
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldRepo: Repo = oldList[oldItemPosition]
        val newRepo: Repo = newList[newItemPosition]
        return oldRepo == newRepo
    }
}