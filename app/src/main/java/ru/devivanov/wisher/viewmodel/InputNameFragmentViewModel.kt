package ru.devivanov.wisher.viewmodel

import androidx.lifecycle.ViewModel
import ru.devivanov.wisher.App
//Наследуемся от ViewModel
class InputNameFragmentViewModel : ViewModel() {
    private val interactor = App.instance.interactor
    //Метод который вызываем, для того чтобы положить имя в репозиторий
    fun putName(name: String) {
        interactor.putNameToRepo(name)
    }
}