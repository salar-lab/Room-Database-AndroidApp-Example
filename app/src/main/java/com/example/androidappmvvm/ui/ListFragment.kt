package com.example.androidappmvvm.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.androidappmvvm.R
import com.example.androidappmvvm.databinding.FragmentListBinding
import com.example.androidappmvvm.model.entity.User
import com.example.androidappmvvm.model.local.LocalRepository
import com.example.androidappmvvm.model.local.LocalRepositoryImp
import com.example.androidappmvvm.model.local.UserDatabase
import com.example.androidappmvvm.ui.adapter.OnListItemClick
import com.example.androidappmvvm.ui.adapter.UserRecyclerView
import kotlinx.coroutines.*


class ListFragment : Fragment(), OnListItemClick {

    var userList: List<User> = emptyList()
    var userName: String? = null

    val userRecyclerView: UserRecyclerView by lazy {
        UserRecyclerView()
    }

    lateinit var localRepositoryImp: LocalRepositoryImp

    lateinit var binding: FragmentListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userName = arguments?.getString("userName")


        var db = UserDatabase.getInstance(requireContext())

        localRepositoryImp = LocalRepositoryImp(db) // db as Parameter

        getAllUsers()

        Toast.makeText(context, "Welcome Mr. $userName", Toast.LENGTH_SHORT).show()

        binding.rvShowData.adapter = userRecyclerView



        binding.btnAdd.setOnClickListener {

            var msg = binding.edtMessage.text.toString()

            GlobalScope.launch(Dispatchers.IO) {
                localRepositoryImp.insertOrUpdateUser(
                    User(
                        0,
                        userName.toString(),
                        msg,
                        R.drawable.user_img
                    )
                )
            }

            // Call the Function
            getAllUsers()

            // userRecyclerView.setList(userList) MOVED++
            binding.edtMessage.setText("")
        }

        userRecyclerView.onListItemClick = this

    }

    private fun getAllUsers() {
        GlobalScope.launch(Dispatchers.IO) {
            var returnedUser = async {
                localRepositoryImp.getUsers()
            }
            withContext(Dispatchers.Main) {
                binding.progressBar.visibility = View.VISIBLE
                userList = returnedUser.await()
                binding.progressBar.visibility = View.GONE
                userRecyclerView.setList(userList) // MOVED HERE ++

                Toast.makeText(
                    context,
                    "is Added and Array size =" + userList.size.toString(),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }


    override fun onItemClick(user: User) {
        GlobalScope.launch(Dispatchers.IO) {
            localRepositoryImp.deleteUser(user)
        }
        Toast.makeText(context, "The User is deleted successfully", Toast.LENGTH_SHORT).show()

        getAllUsers()
    }
}