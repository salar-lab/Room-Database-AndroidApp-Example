package com.example.androidappmvvm.model.local

import com.example.androidappmvvm.model.entity.User

interface LocalRepository {
    suspend fun getUsers():List<User> // suspend is for stop and resume a function

    suspend fun insertOrUpdateUser(user: User)

    suspend fun deleteUser(user: User)
}