package com.example.room.ui.actives

import android.os.Bundle
import android.view.ViewGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateLayoutParams
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.room.R
import com.example.room.databinding.ActivityMain2Binding

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMain2Binding by lazy {
        ActivityMain2Binding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        (supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main2) as? NavHostFragment)?.let {
            val navController = it.navController
            binding.navView.setupWithNavController(it.navController)
            binding.navView.setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.navigation_list -> {
                        navController.navigate(R.id.navigation_list)
                        true
                    }

                    R.id.navigation_new_task -> {
                        navController.navigate(R.id.navigation_new_task)
                        true
                    }

                    else -> false
                }
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(binding.navView) { view, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                leftMargin = insets.left
                bottomMargin = insets.bottom
                rightMargin = insets.right
            }
            WindowInsetsCompat.CONSUMED
        }
    }
}
