package pro.fateeva.chuchasgithub.ui.userProfile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import okhttp3.internal.notifyAll
import pro.fateeva.chuchasgithub.R
import pro.fateeva.chuchasgithub.ReposRecyclerAdapter
import pro.fateeva.chuchasgithub.app
import pro.fateeva.chuchasgithub.databinding.UserProfileFragmentBinding

class UserProfileFragment(position: Int) : DialogFragment() {

    private var position = position

    private var _binding: UserProfileFragmentBinding? = null
    val binding: UserProfileFragmentBinding
        get() {
            return _binding!!
        }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private val viewModel: UserProfileViewModel by lazy {
        ViewModelProvider(this).get(UserProfileViewModel::class.java).apply {
            useCase = requireContext().app.userListUseCase
        }
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

        val adapter = ReposRecyclerAdapter(
            emptyList()
        )

        binding.reposRecyclerView.adapter = adapter

        viewModel.getUserLiveData().observe(viewLifecycleOwner)
        {
            binding.nameTextView.text = it.name
            binding.photoImageView.load(it.avatar) {
                listener(onError = { _, result ->
                    Log.e("UserProfileFragment", "Failed to load avatar ${result.throwable}")
                })
            }
            adapter.repoList = it.listOfRepos
            adapter.notifyDataSetChanged()
        }

        viewModel.getUser(position)
    }

    companion object {
        const val TAG = "UserProfileFragment"
        fun newInstance(position: Int) = UserProfileFragment(position)
    }
}