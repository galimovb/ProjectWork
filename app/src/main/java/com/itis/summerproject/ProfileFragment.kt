package com.itis.summerproject
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var beginningText : TextView
    private lateinit var editTextWeight: EditText
    private lateinit var editTextHeight: EditText
    private lateinit var editTextAge: EditText
    private lateinit var editTextName: EditText
    private lateinit var buttonSave: Button
    private lateinit var textViewCitate : TextView
    private val citates = arrayOf(
        " Многие люди терпят неудачу только потому, что сдаются в двух шагах от успеха. (Саймон Хартли)",
        "Брат, неважно, как  ты двигаешься, главное — не останавливаться.(Кама Пуля)",
        "Начните оттуда, где вы сейчас находитесь. Используйте то, что у вас есть, и делайте всё, что можете. (Артур Эш)",
        "Вы можете быть кем угодно, если вы уделите этому время. (Конор Макгрегор)",
        "Триумф не дарует настоящей силы, её формирует борьба. (Арнольд Шварценеггер)",
        "Люди ошибаются, считая спортсменами великих олимпийцев. Спортсменом является каждый. Если у вас есть тело, то вы — спортсмен. (Фил Найт)",
        " Если ты не собираешься идти до конца, тогда зачем ты вообще это делаешь? (Намат)",
        " Каждый из нас наделён врождёнными силой и способностями, которые нужно просто открыть, а не изобретать заново. (Ричард Брэнсон)",
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPreferences = requireActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE) //получаем экземпляр shared preference
        editTextWeight = view.findViewById(R.id.editTextWeight)
        editTextHeight = view.findViewById(R.id.editTextHeight)
        editTextAge = view.findViewById(R.id.editTextAge)
        editTextName = view.findViewById(R.id.editTextName)
        buttonSave = view.findViewById(R.id.buttonSave)
        textViewCitate = view.findViewById(R.id.textViewCitate)
        beginningText = view.findViewById(R.id.beginningText)

        buttonSave.setOnClickListener {
            if (checkFieldsNotEmpty()) {
                saveData()
                Toast.makeText(requireContext(), "Данные успешно сохранены", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Пожалуйста, заполните все поля", Toast.LENGTH_SHORT).show()
            }
        }
        loadSavedData()
        randomCitates()
    }

    private fun checkFieldsNotEmpty(): Boolean { //метод проверки на пустые поля
        return editTextWeight.text.isNotEmpty() &&
                editTextHeight.text.isNotEmpty() &&
                editTextAge.text.isNotEmpty() &&
                editTextName.text.isNotEmpty()
    }

    private fun saveData() { //сохранение данных в SharedPreference
        val editor = sharedPreferences.edit()
        editor.putString("weight", editTextWeight.text.toString())
        editor.putString("height", editTextHeight.text.toString())
        editor.putString("age", editTextAge.text.toString())
        editor.putString("name", editTextName.text.toString())
        editor.apply()
    }

    private fun loadSavedData() {  //при каждом новом запуске достает по ключу из ShPrefer. иначе дефолтная пустая строка
        val weight = sharedPreferences.getString("weight", "") //достаем
        val height = sharedPreferences.getString("height", "")
        val age = sharedPreferences.getString("age", "")
        val name = sharedPreferences.getString("name", "")

        editTextWeight.setText(weight)
        editTextHeight.setText(height)
        editTextAge.setText(age)
        editTextName.setText(name)
            /*if (weight.isNullOrEmpty() || height.isNullOrEmpty() || age.isNullOrEmpty() || name.isNullOrEmpty()) {
            beginningText.text = "Введите ваши данные"
        } else {
            beginningText.text = "Ваши данные"
        }*/
    }

    private fun randomCitates(){ //выбор случайно цитаты из массива
        val index = (0 until citates.size).random()
        val randomcitate = citates[index]
        textViewCitate.text = randomcitate
    }
}