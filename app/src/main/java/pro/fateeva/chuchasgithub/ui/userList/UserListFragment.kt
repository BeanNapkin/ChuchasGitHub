package pro.fateeva.chuchasgithub.ui.userList

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import pro.fateeva.chuchasgithub.*
import pro.fateeva.chuchasgithub.databinding.UserItemBinding
import pro.fateeva.chuchasgithub.databinding.UserListFragmentBinding
import pro.fateeva.chuchasgithub.domain.entities.User
import pro.fateeva.chuchasgithub.ui.userProfile.UserProfileFragment

class UserListFragment : Fragment() {

    private var _binding: UserListFragmentBinding? = null
    val binding: UserListFragmentBinding
        get() {
            return _binding!!
        }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private val viewModel: UserListViewModel by lazy {
        ViewModelProvider(this).get(UserListViewModel::class.java).apply {
            userListUseCase = requireContext().app.userListUseCase
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = UserListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = RecyclerAdapter<User>(
            emptyList(),
            R.layout.user_item
        ) { user, _ ->
            UserItemBinding.bind(this).userNameTextView.text = user.name
            setOnClickListener {
                UserProfileFragment.newInstance(user.name)
                    .show(requireActivity().supportFragmentManager, UserProfileFragment.TAG)
            }
        }

        binding.recyclerView.adapter = adapter

        viewModel.getUserListLiveData().observe(viewLifecycleOwner)
        {
            adapter.itemList = it
        }

        viewModel.getUserList()
    }

    companion object {
        fun newInstance() = UserListFragment()
    }
}