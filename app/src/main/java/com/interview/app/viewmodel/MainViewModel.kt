package com.interview.app.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.interview.app.network.ApiInterface
import com.interview.app.model.CharacterItem
import com.interview.app.db.CharactersDao
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber

class MainViewModel(
    application: Application,
    private val apiInterface: ApiInterface,
    private val charactersDao: CharactersDao
) : AndroidViewModel(application) {

    private val _characters: MutableLiveData<List<CharacterItem>> = MutableLiveData(emptyList())
    val characters: LiveData<List<CharacterItem>>
        get() = _characters


    init {
        viewModelScope.launch {
            charactersDao.getCharacters().collectLatest {
                _characters.postValue(it.orEmpty())
            }
        }
    }

    fun getCharacters() {
        apiInterface.getCharacters()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                viewModelScope.launch {
                    response?.let { charactersDao.insertCharacters(it) }
                }
            }, {
                Timber.e(it)
            })?.let {
                disposable.add(it)
            }
    }

    private val disposable by lazy {
        CompositeDisposable()
    }

    override fun onCleared() {
        super.onCleared()
        if (!disposable.isDisposed) disposable.dispose()
    }
}