package ru.devivanov.wisher.view

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import ru.devivanov.wisher.R
import ru.devivanov.wisher.view.fragments.CardFragment
import ru.devivanov.wisher.view.fragments.InputNameFragment
import ru.devivanov.wisher.view.fragments.WishGeneratorFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Если это первый запуск приложения - запускаем фрагмент с вводом имени
        if (savedInstanceState == null) {
            launchNameInputFragment()
        }
    }
    //Запуск фрагмент с генерацией
    fun launchWishGeneratorFragment() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_placeholder, WishGeneratorFragment())
            .addToBackStack(null)
            .commit()
    }
    //Запуск фргмента с вводом имени
    fun launchNameInputFragment() {
        clearBackStack()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_placeholder, InputNameFragment())
            .addToBackStack(null)
            .commit()
    }
    //Запуск фрагмента с открыткой
    fun launchCardFragment() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_placeholder, CardFragment())
            .addToBackStack(null)
            .commit()
    }
    //Чистим бэкстек, когда идем по второму кругу
    private fun clearBackStack() {
        val manager: FragmentManager = supportFragmentManager
        if (manager.backStackEntryCount > 0) {
            val first: FragmentManager.BackStackEntry = manager.getBackStackEntryAt(0)
            manager.popBackStack(first.id, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
    }
}