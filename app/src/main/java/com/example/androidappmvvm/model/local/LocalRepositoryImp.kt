package com.example.androidappmvvm.model.local

import com.example.androidappmvvm.model.entity.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocalRepositoryImp(private val db: UserDatabase) : LocalRepository {

    override suspend fun getUsers() =
        withContext(Dispatchers.IO) {
            db.userDAO().getUsers()
        }

    override suspend fun insertOrUpdateUser(user: User) {
        withContext(Dispatchers.IO) {
            db.userDAO().insertOrUpdateUser(user)
        }
    }

    override suspend fun deleteUser(user: User) {
        withContext(Dispatchers.IO) {
            db.userDAO().deleteUser(user)
        }
    }
}