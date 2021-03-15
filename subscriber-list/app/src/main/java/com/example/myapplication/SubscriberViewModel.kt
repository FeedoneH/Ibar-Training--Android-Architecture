package com.example.myapplication

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.db.Subscriber
import com.example.myapplication.db.SubscriberRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class SubscriberViewModel(val repository: SubscriberRepository) : ViewModel(), Observable {
    val subscribers = repository.subscribers
    var deleteOrUpdate = false;
    lateinit var selectedSubscriber: Subscriber
    private var message = MutableLiveData<Event<String>>();
    val messageText: LiveData<Event<String>>
        get() = message

    @Bindable
    val inputName = MutableLiveData<String>()

    @Bindable
    val inputEmail = MutableLiveData<String>()

    @Bindable
    val saveOrUpdateButton = MutableLiveData<String>()

    @Bindable
    val clearAllOrDeleteButton = MutableLiveData<String>()

    init {
        saveOrUpdateButton.value = "Save"
        clearAllOrDeleteButton.value = "Clear All"
    }

    fun initEdit(selectedItem: Subscriber) {
        saveOrUpdateButton.value = "Update"
        clearAllOrDeleteButton.value = "Delete"
        deleteOrUpdate = true
        inputName.value = selectedItem.name
        inputEmail.value = selectedItem.email
        selectedSubscriber = selectedItem
    }

    private fun stopEdit() {
        saveOrUpdateButton.value = "Save"
        clearAllOrDeleteButton.value = "Clear all"
        deleteOrUpdate = false
        inputName.value = null
        inputEmail.value = null

    }

    fun updateOrSave() {
        if (!deleteOrUpdate) {
            val name = inputName.value!!
            val email = inputEmail.value!!
            insertSubscriber(Subscriber(0, name, email))

        } else {
            selectedSubscriber.name = inputName.value!!
            selectedSubscriber.email = inputEmail.value!!
            updateSubscriber(selectedSubscriber)

        }

    }

    fun clearAllOrDelete() {
        if (deleteOrUpdate) {
            deleteSubscriber(selectedSubscriber)


        } else {
            clearAllData()

        }
    }

    fun insertSubscriber(subscriber: Subscriber) {

        viewModelScope.launch {
            var newId = repository.insert(subscriber)
            if (newId > -1) {
                message.value = Event("Successfull $newId row updated")
            } else {
                message.value = Event("error occured")
            }
            inputName.value = null
            inputEmail.value = null

        }
    }

    fun updateSubscriber(subscriber: Subscriber) {
        viewModelScope.launch {
            var numofRow = repository.update(subscriber)
            if (numofRow > 0) {
                message.value = Event("Successfull $numofRow row updated")
            } else {
                message.value = Event("error occured")
            }
            stopEdit()
            message.value = Event("Successfull updated")
        }
    }

    fun deleteSubscriber(subscriber: Subscriber) {
        viewModelScope.launch {
            repository.delete(subscriber)
            stopEdit()
        }
    }

    fun clearAllData() = viewModelScope.launch {

        val noOfRowsDeleted = repository.deleteAll()
        if (noOfRowsDeleted > 0) {
            message.value = Event("$noOfRowsDeleted Subscribers Deleted Successfully")
        } else {
            message.value = Event("Error Occurred")
        }


    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

}