package ru.devivanov.wisher.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.devivanov.wisher.App

class CardFragmentViewModel : ViewModel() {
    val cardLiveData: MutableLiveData<List<String>> = MutableLiveData()
    private val interactor = App.instance.interactor

    init {
        //При создании view модели сразу передаем данные во фрагмент из репозитория
        val cardData = interactor.getDataForCard()
        cardLiveData.postValue(cardData)
    }
}