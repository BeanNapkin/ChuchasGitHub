package pro.fateeva.chuchasgithub.ui.userProfile

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import pro.fateeva.chuchasgithub.R
import pro.fateeva.chuchasgithub.app
import pro.fateeva.chuchasgithub.databinding.UserProfileFragmentBinding

class UserProfileFragment : Fragment() {

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
            userProfileUseCase = requireContext().app.userProfileUseCase
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.user_profile_fragment, container, false)
    }

    companion object {
        fun newInstance() = UserProfileFragment()
    }
}