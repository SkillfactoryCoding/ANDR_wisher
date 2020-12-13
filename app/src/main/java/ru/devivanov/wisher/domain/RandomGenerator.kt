package ru.devivanov.wisher.domain
//Делаем его object, чтобы использовать его методы без создания экземпляра этого класса
object RandomGenerator {
    //Выбираем случайные слова из списков
    fun generate(firstPartList: List<String>, secondPartList: List<String>): List<String> {
        return listOf(firstPartList.random(), secondPartList.random() )
    }
}