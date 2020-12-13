package ru.devivanov.wisher.domain

import ru.devivanov.wisher.data.WishRepository
//В конструктор класса мы принимаеи экземпляр нашего репозитория
class Interactor(private val repository: WishRepository) {
    //Этот метод кладет имя в репозиторий
    fun putNameToRepo(name: String) {
        repository.name = name
    }
    //Этот метод кладет части поздравления в репозиторий
    fun putParts(list: List<String>) {
        repository.firstPart = list[0]
        repository.secondPart = list[1]
    }
    //Этот метод забирает из репозитория все данные для открытки
    fun getDataForCard(): List<String> {
        return listOf(repository.name, repository.firstPart, repository.secondPart)
    }
    //Этот метод генеририруте случайные слова из тех двух списков в рпеозитории
    fun generateWishFromRepo(): List<String> {
        return RandomGenerator.generate(repository.wishFirstPartRepo, repository.wishSecondPartRepo)
    }
}