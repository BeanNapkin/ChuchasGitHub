package pro.fateeva.chuchasgithub.ui.userList

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import pro.fateeva.chuchasgithub.DiffUtilCallback
import pro.fateeva.chuchasgithub.UsersRecyclerAdapter
import pro.fateeva.chuchasgithub.app
import pro.fateeva.chuchasgithub.databinding.UserListFragmentBinding

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

        val adapter = UsersRecyclerAdapter(
            emptyList(),
            userCardClickListener = { position ->
                //Todo откытие другого фрагмента с инфой про юзера через вьюмодел
            }
        )

        binding.recyclerView.adapter = adapter

        viewModel.getUserListLiveData().observe(viewLifecycleOwner)
        {
            val diffUtilCallback = DiffUtilCallback(adapter.userList, it)
            val noteDiffResult = DiffUtil.calculateDiff(diffUtilCallback)

            adapter.userList = it
            noteDiffResult.dispatchUpdatesTo(adapter)
        }

        viewModel.getUserList()
    }

    companion object {
        fun newInstance() = UserListFragment()
    }
}