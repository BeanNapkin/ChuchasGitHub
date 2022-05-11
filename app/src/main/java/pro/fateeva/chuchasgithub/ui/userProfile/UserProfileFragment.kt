package pro.fateeva.chuchasgithub.ui.userProfile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DiffUtil
import coil.load
import pro.fateeva.chuchasgithub.*
import pro.fateeva.chuchasgithub.databinding.RepoItemBinding
import pro.fateeva.chuchasgithub.databinding.UserItemBinding
import pro.fateeva.chuchasgithub.databinding.UserProfileFragmentBinding
import pro.fateeva.chuchasgithub.domain.entities.Repo
import pro.fateeva.chuchasgithub.domain.usecase.UserListUseCase

class UserProfileFragment : DialogFragment() {

    private val userName: String?
        get() = requireArguments().getString(LOGIN_ARG)

    private var _binding: UserProfileFragmentBinding? = null
    val binding: UserProfileFragmentBinding
        get() = _binding!!

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private val viewModel: UserProfileViewModel by lazy {
        ViewModelProvider(this).get(UserProfileViewModel::class.java)
    }

    override fun getTheme(): Int {
        return R.style.FullScreenDialog;
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = UserProfileFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = RecyclerAdapter<Repo>(
            emptyList(),
            R.layout.repo_item
        ){ repo, _ ->
            RepoItemBinding.bind(this).reposTitleTextView.text = repo.name
        }

        binding.reposRecyclerView.adapter = adapter

        viewModel.userLiveData.observe(viewLifecycleOwner)
        {
            binding.nameTextView.text = it.name
            binding.photoImageView.load(it.avatar) {
                listener(onError = { _, result ->
                    Log.e("UserProfileFragment", "Failed to load avatar ${result.throwable}")
                })
            }
        }

        viewModel.reposLiveData.observe(viewLifecycleOwner){
            adapter.itemList = it
        }

        viewModel.getUser(userName ?: error("userName not found"))
        viewModel.getUsersRepo(userName ?: error("userName not found"))
    }

    companion object {
        const val TAG = "UserProfileFragment"
        const val LOGIN_ARG = "LOGIN_ARG"
        fun newInstance(login: String) = UserProfileFragment().apply {
            arguments = bundleOf(
                LOGIN_ARG to login
            )
        }
    }
}