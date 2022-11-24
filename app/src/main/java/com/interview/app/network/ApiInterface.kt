package com.interview.app.network

import com.interview.app.base.Constants
import com.interview.app.model.CharacterItem
import io.reactivex.Observable
import retrofit2.http.GET

@JvmSuppressWildcards
interface ApiInterface {

    @GET(Constants.CHARS)
    fun getCharacters(): Observable<List<CharacterItem>>
}