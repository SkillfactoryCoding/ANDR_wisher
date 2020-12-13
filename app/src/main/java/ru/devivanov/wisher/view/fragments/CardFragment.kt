package ru.devivanov.wisher.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.devivanov.wisher.R
import ru.devivanov.wisher.databinding.FragmentCardBinding
import ru.devivanov.wisher.view.MainActivity
import ru.devivanov.wisher.viewmodel.CardFragmentViewModel


class CardFragment : Fragment() {
    //биндинг для нахождения view в верстке
    private lateinit var binding: FragmentCardBinding
    //Ленивая инициализация view модели для фрагмента
    private val viewModel by lazy {
        ViewModelProvider.NewInstanceFactory().create(CardFragmentViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Устанавливаем картнку для открытки
        binding.backgroundHolder.setImageResource(R.drawable.pic_1)
        //Подписываемся на изменения и устанвливаем значения в поля
        viewModel.cardLiveData.observe(viewLifecycleOwner) {
            binding.name.text = it[0]
            val wish = "${it[1]} ${it[2]}"
            binding.wish.text = wish
        }
        //КНопка перезапуска
        binding.buttonReplay.setOnClickListener {
            (activity as MainActivity).launchNameInputFragment()
        }
    }
}