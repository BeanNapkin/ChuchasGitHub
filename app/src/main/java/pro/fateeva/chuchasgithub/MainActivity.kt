package pro.fateeva.chuchasgithub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import pro.fateeva.chuchasgithub.databinding.ActivityMainBinding
import pro.fateeva.chuchasgithub.ui.userList.UserListFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, UserListFragment.newInstance())
            .commit()
    }
}