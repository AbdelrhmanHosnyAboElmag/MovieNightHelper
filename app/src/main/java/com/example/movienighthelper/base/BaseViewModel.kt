package com.example.movienighthelper.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.regex.Matcher
import java.util.regex.Pattern
import androidx.navigation.NavDirections
import com.example.movienighthelper.utils.NavigationCommand
import com.example.movienighthelper.utils.SingleLiveEvent

/**
 * Base class for View Models to declare the common LiveData objects in one place
 */
abstract class BaseViewModel() : ViewModel() {


    val navigationCommand: SingleLiveEvent<NavigationCommand> = SingleLiveEvent()
    val showErrorMessage: SingleLiveEvent<String> = SingleLiveEvent()
    val showSnackBar: SingleLiveEvent<String> = SingleLiveEvent()
    val showSnackBarInt: SingleLiveEvent<Int> = SingleLiveEvent()
    val showToast: SingleLiveEvent<String> = SingleLiveEvent()
    val showLoading: SingleLiveEvent<Boolean> = SingleLiveEvent()
    val showNoData: MutableLiveData<Boolean> = MutableLiveData()
    fun navigateTo(directions: NavDirections) {
        navigationCommand.postValue(NavigationCommand.To(directions))
    }

    fun navigateBack() {
        navigationCommand.postValue(NavigationCommand.Back)
    }

    fun navigateBackTo(destinationId: Int) {
        navigationCommand.postValue(NavigationCommand.BackTo(destinationId))
    }

    private val _btnEditClick = SingleLiveEvent<Boolean>()
    val btnEditClick: SingleLiveEvent<Boolean> = _btnEditClick

    fun btnEditClick(isClick: Boolean) {
        _btnEditClick.value = isClick
    }
    /**
     * Check email for validation
     */
    private val _emailValid = MutableLiveData<Boolean>()
    val emailValid: LiveData<Boolean> = _emailValid

    fun isEmailValid(email: String) {
        val expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
        val pattern: Pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
        val matcher: Matcher = pattern.matcher(email)
        _emailValid.value = matcher.matches()
    }

    /**
     * Check password for validation
     */

    val passwordValid: SingleLiveEvent<Boolean> = SingleLiveEvent()
    val SamePasswordValid: SingleLiveEvent<Boolean> = SingleLiveEvent()


    fun validPassword(password: String) {
        if (password.length >= 8) {
            passwordValid.postValue(true)
        } else {
            passwordValid.postValue(false)
        }
    }

    fun samePassword(password1: String, password2: String) {
        if (password1 == password2) {
            SamePasswordValid.postValue(true)
        } else {
            SamePasswordValid.postValue(false)
        }
    }

    /**
     * Check number for validation
     */

    private val _phoneNumberValid = MutableLiveData<Boolean>()
    val phoneNumberValid: LiveData<Boolean> = _phoneNumberValid

    //Note: just dummy valid because i didn't has requirement of validation
    fun isPhoneNumberValid(phoneNumberValid: Boolean) {
        _phoneNumberValid.postValue(phoneNumberValid)
    }
}