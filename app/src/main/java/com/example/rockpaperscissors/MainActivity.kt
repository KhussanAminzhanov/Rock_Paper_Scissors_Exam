package com.example.rockpaperscissors

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.rockpaperscissors.databinding.ActivityMainBinding
import com.example.rockpaperscissors.databinding.AdSelectObjectBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val model by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setObservers()

        model.pcObject.value = getDrawable(R.drawable.im_question)
        binding.btnSelectObject.setOnClickListener { onBtnSelectObjectClick() }
        binding.btnFight.setOnClickListener { onBtnFightClick() }
    }

    private fun onBtnSelectObjectClick() {
        val binding = AdSelectObjectBinding.inflate(layoutInflater)
        val builder = AlertDialog.Builder(this).setView(binding.root).create()

        binding.ivPaper.setOnClickListener {
            onObjectClick(
                "Paper",
                it as ImageView
            ) { builder.dismiss() }
        }
        binding.ivRock.setOnClickListener {
            onObjectClick(
                "Rock",
                it as ImageView
            ) { builder.dismiss() }
        }
        binding.ivScissor.setOnClickListener {
            onObjectClick(
                "Scissor",
                it as ImageView
            ) { builder.dismiss() }
        }

        builder.show()
    }

    private fun onBtnFightClick() {
        model.fight(this)
        binding.btnFight.setBackgroundResource(R.drawable.shape_btn_fight_restart)
        binding.btnFight.text = getString(R.string.again)
    }

    private fun onObjectClick(objectName: String, imageView: ImageView, dismiss: () -> Unit) {
        binding.btnSelectObject.visibility = View.INVISIBLE
        binding.ivUsersObject.visibility = View.VISIBLE
        binding.btnFight.visibility = View.VISIBLE

        model.userObjectName = objectName
        model.userObject.value = imageView.drawable
        model.gameState.value = MainViewModel.GameState.OBJECT_SELECTED
        dismiss()
    }

    private fun setObservers() {
        model.gameState.observe(this) { gameState ->
            var textColor = R.color.black
            binding.hint.text = when (gameState) {
                MainViewModel.GameState.PC_WON -> {
                    Log.i("activity", "pc won")
                    textColor = R.color.red
                    getString(R.string.lost)
                }
                MainViewModel.GameState.USER_WON -> {
                    Log.i("activity", "user won")
                    textColor = R.color.green
                    getString(R.string.won)
                }
                MainViewModel.GameState.DRAW -> {
                    Log.i("activity", "Draw")
                    textColor = R.color.purple
                    getString(R.string.draw)
                }
                MainViewModel.GameState.SELECT_OBJECT -> {
                    getString(R.string.select_object)
                }
                MainViewModel.GameState.OBJECT_SELECTED -> {
                    getString(R.string.object_selected, model.userObjectName)
                }
                null -> ""
            }
            binding.hint.setTextColor(textColor)

        }
        model.userObject.observe(this) {
            if (it == null) return@observe
            binding.ivUsersObject.setImageDrawable(it)
        }
        model.pcObject.observe(this) {
            if (it == null) return@observe
            binding.ivOpponentsObject.setImageDrawable(it)
        }
    }
}