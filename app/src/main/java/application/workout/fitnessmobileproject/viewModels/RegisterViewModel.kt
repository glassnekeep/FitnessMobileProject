package application.workout.fitnessmobileproject.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import application.workout.fitnessmobileproject.model.models.User
import application.workout.fitnessmobileproject.model.repository.repositories.UserRepository
import application.workout.fitnessmobileproject.utils.exceptions.EmptyFieldException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterViewModel: ViewModel() {
    /*private var _username = MutableLiveData<String>()
    val username get() = _username
    private var _password = MutableLiveData<String>()
    val password get() = _password
    private var _email = MutableLiveData<String>()
    val email get() = _email
    private var _name = MutableLiveData<String>()
    val name get() = _name*/
    private var _username = ""
    private var _password = ""
    private var _email = ""
    private var _name = ""
    private var _surname = ""
    private var _phoneNumber = ""
    private var _sex = ""
    private var _weight = 0
    private var _height = 0

    fun setMainInfo(username: String, password: String, email: String) {
        _username = username
        _password = password
        _email = email
    }

    fun setAdditionalInfo(name: String, surname: String, phoneNumber: String, sex: String, weight: Int, height: Int) {
        _name = name
        _surname = surname
        _phoneNumber = phoneNumber
        _sex = sex
        _weight = weight
        _height = height
    }

    fun registerUser() {
        if (_username.isNotEmpty() && _password.isNotEmpty() && _email.isNotEmpty() &&
            _name.isNotEmpty() && _surname.isNotEmpty() && _phoneNumber.isNotEmpty() &&
            _sex.isNotEmpty() && _weight != 0 && _height != 0) {
            val user = User(
                id = 0,
                username = _username,
                firstname = _name,
                lastname = _surname,
                phoneNumber = _phoneNumber,
                email= _email,
                password = _password,
                sex = _sex,
                growth = _height,
                weight = _weight
            )
            viewModelScope.launch(Dispatchers.Main) {
                try {
                    UserRepository.getInstance(username = _username, password = _password).createUser(user)
                } catch (exception: Exception) {
                    throw exception
                }
            }
        } else {
            throw EmptyFieldException("Not all fields filled")
        }
    }
}