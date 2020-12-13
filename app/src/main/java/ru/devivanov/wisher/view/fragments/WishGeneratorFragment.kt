package ru.devivanov.wisher.view.fragments

import android.opengl.Visibility
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.devivanov.wisher.databinding.FragmentWishGeneratorBinding
import ru.devivanov.wisher.view.MainActivity
import ru.devivanov.wisher.viewmodel.WishGeneratorFragmentViewModel

class WishGeneratorFragment : Fragment() {
    //биндинг для нахождения view в верстке
    private lateinit var binding: FragmentWishGeneratorBinding
    //Ленивая инициализация view модели для фрагмента
    private val viewModel by lazy {
        ViewModelProvider.NewInstanceFactory().create(WishGeneratorFragmentViewModel::class.java)
    }
    //Дефолтное время анимации на случай если из view модели данные не придут
    private var rotationDuration = 5000L
    //Поле которое содержит перменную генерируется ли у нас сейчас фраза или нет
    private var isGenerating = false
        set(value) {
            //если придет одно и тоже занчение то будет выход из блока и код далее выполнятся не будет
            if (field == value) return
            field = value
            //если true
            if (field) {
                //скрываем кнопку далее, мы не хотим что бы по ней можнго было нахать в момент генерации
                binding.buttonNext.visibility = View.GONE
                //отключаем кнопку запуска генерации
                binding.buttonGenerate.apply {
                    isEnabled = false
                    alpha = 0.5f
                }
                //анимируем верхнюю снежинку
                binding.snowflakeTop.apply {
                    rotation = 0f
                    animate()
                        .rotation(360f)
                        .setDuration(rotationDuration)
                }
                //анимируем нижнюю снежинку
                binding.snowflakeBottom.apply {
                    rotation = 0f
                    animate()
                        .rotation(360f)
                        .setDuration(rotationDuration)
                }
            } else {
                //когда false - включаем все кнопки
                binding.buttonNext.visibility = View.VISIBLE
                binding.buttonGenerate.apply {
                    isEnabled = true
                    alpha = 1f
                }
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWishGeneratorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Подписываемся на изменение первой части фразы
        viewModel.wishFirstPartLiveData.observe(viewLifecycleOwner) {
            binding.firstPart.text = it
        }
        //Подписываемся на изменение второй части фразы
        viewModel.wishSecondPartLiveData.observe(viewLifecycleOwner) {
            binding.secondPart.text = it
        }
        //Подписываемся на то генерируется ли сейчас фраза или нет
        viewModel.isWishGeneratingLiveData.observe(viewLifecycleOwner) {
            isGenerating = it
        }
        //Подписываемся на рассчетное время анимации, чтобы снежинки пересатли крутиться ровно тогда,
        //Когда перестанет генерироваться фраза
        viewModel.spiningTimeLiveData.observe(viewLifecycleOwner) {
            rotationDuration = it
        }
        //По нажатии на кнопку генерации, послаем запрос во view модель на генерацию
        binding.buttonGenerate.setOnClickListener {
            viewModel.generateWish()
        }
        //Кнопка далее - переходим на  следующий фрагмент
        binding.buttonNext.setOnClickListener {
            (activity as MainActivity).launchCardFragment()
        }
    }
}
