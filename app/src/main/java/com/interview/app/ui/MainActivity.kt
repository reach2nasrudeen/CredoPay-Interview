package com.interview.app.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.interview.app.R
import com.interview.app.adapter.CharactersAdapter
import com.interview.app.databinding.ActivityMainBinding
import com.interview.app.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val mainViewModel by viewModel<MainViewModel>()

    private lateinit var binding: ActivityMainBinding

    private val charactersAdapter by lazy {
        CharactersAdapter(emptyList())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding  = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.rvCharacters.adapter = charactersAdapter

        mainViewModel.characters.observe(this) {
            charactersAdapter.updateData(it.orEmpty())
        }

        mainViewModel.getCharacters()
    }
}