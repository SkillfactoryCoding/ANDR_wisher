package ru.devivanov.wisher.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.devivanov.wisher.databinding.FragmentInputNameBinding
import ru.devivanov.wisher.view.MainActivity
import ru.devivanov.wisher.viewmodel.InputNameFragmentViewModel

class InputNameFragment : Fragment() {
    //биндинг для нахождения view в верстке
    private lateinit var binding: FragmentInputNameBinding
    //Ленивая инициализация view модели для фрагмента
    private val viewModel by lazy {
        ViewModelProvider.NewInstanceFactory().create(InputNameFragmentViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInputNameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //По нажатию на кнопку у нас кладется имя в рпеозиторий и осуществляется переход на следующий фрагмент
        binding.buttonNext.setOnClickListener {
            viewModel.putName(binding.nameInput.text.toString())
            (activity as MainActivity).launchWishGeneratorFragment()
        }
    }
}