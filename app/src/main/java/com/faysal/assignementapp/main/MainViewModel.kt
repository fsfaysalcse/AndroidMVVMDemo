package com.faysal.assignementapp.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.faysal.assignementapp.data.models.User
import com.faysal.assignementapp.utility.DispatcherProvider
import com.faysal.assignementapp.utility.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MainRepository,
    private val dispatchers: DispatcherProvider
) : ViewModel() {

    sealed class ResponseEvent {
        class Success(val data: List<User>) : ResponseEvent()
        class Failure(val message: String) : ResponseEvent()
        object Loading : ResponseEvent()
        object Empty : ResponseEvent()
    }

    private val _usersEvent = MutableStateFlow<ResponseEvent>(ResponseEvent.Empty)
    val user: StateFlow<ResponseEvent> = _usersEvent

    fun getRandomUserListResponse() {
        _usersEvent.value = ResponseEvent.Loading
        viewModelScope.launch(dispatchers.io) {
            when (val response = repository.getRandomUserListResponse()) {
                is Resource.Error -> _usersEvent.value = ResponseEvent.Failure(response.message!!)
                is Resource.Success -> {
                    val response = response.data?.results
                    if (response != null){
                        _usersEvent.value = ResponseEvent.Success(response)
                    }else{
                        _usersEvent.value = ResponseEvent.Empty
                    }
                }
            }
        }
    }

}