package ru.devivanov.wisher

import android.app.Application
import ru.devivanov.wisher.data.WishRepository
import ru.devivanov.wisher.domain.Interactor
//Наследуемся от класса application
class App : Application() {
    //В этих переменных мы будм хранить экземпляры репозитория и интерактора
    lateinit var interactor: Interactor
    lateinit var repository: WishRepository

    override fun onCreate() {
        super.onCreate()
        //Сохраняем ссылку на наш класс App
        instance = this
        //Инициализируем переменные
        repository = WishRepository()
        interactor = Interactor(repository)
    }

    companion object {
        //Чтобы мы могли получить доступ к перменные через единственный экземпляр класса App
        lateinit var instance: App
            private set
    }
}