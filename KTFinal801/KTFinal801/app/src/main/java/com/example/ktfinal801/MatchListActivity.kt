package com.example.ktfinal801

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MatchListActivity: AppCompatActivity() {
    private var auth: FirebaseAuth = FirebaseAuth.getInstance()
    private lateinit var userDB: DatabaseReference
    private val adapter = MatchedUserAdapter()
    private val cardItems = mutableListOf<CardItem>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_list)

        userDB = Firebase.database.reference.child("Users")
        initMatchedUserRecyclerView()
        getMatchUsers()

    }
    private fun initMatchedUserRecyclerView(){
        val recylerView = findViewById<RecyclerView>(R.id.matchedUserRecyclerView)

        recylerView.layoutManager = LinearLayoutManager(this)
        recylerView.adapter = adapter
    }

    private fun getUserByKey(userId: String) {
        val matchedDb = userDB.child(userId)
        matchedDb.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                //키값으로 name을 가져 온다
                cardItems.add(CardItem(userId, snapshot.child("name").value.toString()))
                adapter.submitList(cardItems)
            }
            override fun onCancelled(error: DatabaseError) {}

        })
    }


    private fun getMatchUsers() {
        val matchedDB = userDB.child(getCurrentUserID()).child("likedBy").child("match")

        matchedDB.addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(dataSnapshot: DataSnapshot, previousChildName: String?) {
                if(dataSnapshot.key?.isNotEmpty() == true) {
                    getUserByKey(dataSnapshot.key.orEmpty())
                }
            }

            override fun onChildChanged(dataSnapshot: DataSnapshot, s: String?) {}
            override fun onChildRemoved(dataSnapshot: DataSnapshot) {}
            override fun onChildMoved(dataSnapshot: DataSnapshot, s: String?) {}
            override fun onCancelled(databaseError: DatabaseError) {}
        })

    }
    private fun getCurrentUserID(): String {
        //로그인이 풀리면 널일 수 있으니까 널에 대한 예외처리하기
        if(auth.currentUser == null){
            Toast.makeText(this, "로그인이 되어있지않습니다.", Toast.LENGTH_SHORT).show()
            //MainActivity로 돌아가도록  finish() 호출함.
            finish()
        }
        return auth.currentUser!!.uid
    }


}