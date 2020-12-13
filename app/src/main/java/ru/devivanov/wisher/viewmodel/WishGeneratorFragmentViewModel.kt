package ru.devivanov.wisher.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.devivanov.wisher.App
import java.util.concurrent.Executors
//Наследуемся от ViewModel
class WishGeneratorFragmentViewModel : ViewModel() {
    //LiveData на которую будет подписываться фраагмент и получать данные
    val wishFirstPartLiveData: MutableLiveData<String> = MutableLiveData()
    val wishSecondPartLiveData: MutableLiveData<String> = MutableLiveData()
    val isWishGeneratingLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val spiningTimeLiveData: MutableLiveData<Long> = MutableLiveData()
    private val interactor = App.instance.interactor
    //Метод генерации случайного пожелания, вызывается из фрагмента
    fun generateWish() {
        //Делаем все в отдельном потоке
        Executors.newSingleThreadExecutor().execute {
            //Тут логика подсчета времени для анимации
            var speed = 1L
            val step = 5
            val iterations = 50
            var time = 0L

            for (i in 0..iterations) {
                speed += step
                time += speed
            }
            spiningTimeLiveData.postValue(time)
            speed = 1L

            var wishesList = emptyList<String>()
            isWishGeneratingLiveData.postValue(true)
            for (i in 0..iterations) {
                Thread.sleep(speed)
                speed += step
                val wishes = interactor.generateWishFromRepo()
                wishFirstPartLiveData.postValue(wishes[0])
                wishSecondPartLiveData.postValue(wishes[1])
                wishesList = wishes
            }
            interactor.putParts(wishesList)
            isWishGeneratingLiveData.postValue(false)
        }
    }
}