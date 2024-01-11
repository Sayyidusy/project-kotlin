package com.org.proyektingkat2.siagabanjir

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.org.proyektingkat2.UserAdapter
import com.org.proyektingkat2.db.DatabaseHandler

class UserListActivity : AppCompatActivity() {

    private lateinit var recyclerViewUsers: RecyclerView
    private lateinit var userAdapter: UserAdapter
    private lateinit var databaseHandler: DatabaseHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)

        recyclerViewUsers = findViewById(R.id.recyclerViewUsers)
        recyclerViewUsers.layoutManager = LinearLayoutManager(this)

        databaseHandler = DatabaseHandler(this)
        val userList = databaseHandler.getAllUsers()

        userAdapter = UserAdapter(userList)
        recyclerViewUsers.adapter = userAdapter
    }
}