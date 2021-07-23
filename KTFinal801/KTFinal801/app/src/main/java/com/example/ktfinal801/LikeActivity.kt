package com.example.ktfinal801

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import com.yuyakaido.android.cardstackview.CardStackListener
import com.yuyakaido.android.cardstackview.CardStackView
import com.yuyakaido.android.cardstackview.Direction

class LikeActivity: AppCompatActivity(), CardStackListener {
    private var auth: FirebaseAuth = FirebaseAuth.getInstance()
    private lateinit var userDB: DatabaseReference
    //CardItem관련 추가
    private val adapter = CardItemAdapter()
    private val cardItems = mutableListOf<CardItem>()
    private val manager by lazy{
        CardStackLayoutManager(this,this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_like)

        userDB = Firebase.database.reference.child("Users")
        val currentUserDB = userDB.child(getCurrentUserID())
        currentUserDB.addListenerForSingleValueEvent(object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.child("name").value == null){
                    //널이면 팝업을 열어서 EditText에 이름을 받아온다.
                    showNameInputPopup()
                    return
                }
                //한번도 선택하지 않은 유저 정보 가져오기
                getUnSelectedUsers()
            }

            override fun onCancelled(error: DatabaseError) {}
        })
        initCardStackView()
        initSignOutButton()
        initMatchedListButton()
    }///////////////end of onCreate
    //매치 리스트 보기
    private fun initMatchedListButton() {
        val matchedListButton = findViewById<Button>(R.id.matchListButton)
        matchedListButton.setOnClickListener {
            startActivity(Intent(this, MatchListActivity::class.java))
        }
    }

    private fun initCardStackView(){
        val stackView = findViewById<CardStackView>(R.id.cardStackView)
        //stackView.layoutManager = CardStackLayoutManager(this, this)
        stackView.layoutManager = manager
        stackView.adapter = adapter
    }


    private fun getCurrentUserID(): String {
        //로그인이 풀리면 널일 수 있으니까 널에 대한 예외처리하기
        if(auth.currentUser == null){
            Toast.makeText(this, "로그인이 되어있지않습니다.", Toast.LENGTH_SHORT).show()
            //MainActivity로 돌아가도록  finish() 호출함.
            finish()
        }
        return auth.currentUser?.uid!!
    }

    //한번도 선택하지 않은 유저 정보 가져와라
    fun getUnSelectedUsers() {
        userDB.addChildEventListener(object : ChildEventListener {
            //우리가 선택하지 않은 유저 정보만 가져오기
            //초기에 데이터를 불러오는 과정이나 화면을 보고 있는데 새로운 유저가 등록을 했을 때 호출
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                //지금 보고 있는 유저 아이디가 나와같지 않고 상대방의 likedBy에 like에 내가 없고
                //상대방의 likedBy에 disLike에 내가 없다. 그러면 이 유저는 내가 한번도 선택한 적이 없는 유저이다.
                if (snapshot.child("userId").value != getCurrentUserID()
                    && snapshot.child("likedBy").child("like").hasChild(getCurrentUserID()).not()
                    && snapshot.child("likedBy").child("disLike").hasChild(getCurrentUserID()).not()
                ) {

                    val userId = snapshot.child("userId").value.toString()
                    var name = "undecided"
                    if (snapshot.child("name").value != null) {
                        name = snapshot.child("name").value.toString()
                    }
                    //미리 만들어둔 리스트에 넣어주고
                    cardItems.add(CardItem(userId, name))
                    adapter.submitList(cardItems)
                    //리사이클러 뷰를 갱신해라
                    adapter.notifyDataSetChanged()
                }
            }
            //이름이 바뀌었을 때나 다른 유저가 다른 유저를 like했을 때 호출
            override fun onChildChanged(dataSnapshot: DataSnapshot, s: String?) {
                //가져온 유저아이디와 스냅샷에 키와 동일하면 -그러나 없을 수도 있으니까 ?로 처리
                cardItems.find { it.userId == dataSnapshot.key }?.let {
                    //변경이된 카드유저아이디를 찾는다.
                    it.name = dataSnapshot.child("name").value.toString()
                }
                //만일 수정이 되었다면 데이터가 최신정보로 갱신되도록 해준다.
                adapter.submitList(cardItems)
                adapter.notifyDataSetChanged()
            }
            override fun onChildRemoved(dataSnapshot: DataSnapshot) {}
            override fun onChildMoved(dataSnapshot: DataSnapshot, s: String?) {}
            override fun onCancelled(databaseError: DatabaseError) {}
        })
    }


    //AlterDialog를 만들어서 이름값 받아오기
    private fun showNameInputPopup() {
        val editText = EditText(this)
        AlertDialog.Builder(this)
            .setTitle("이름을 입력해 주세요")
            .setView(editText)
            .setPositiveButton("저장") { _, _ ->//인자가 두개인 람다를 의미함
                if (editText.text.isEmpty()) {
                    //저장을 누르는 순간 팝업이 꺼지니까 다시 호출하면 된다.
                    showNameInputPopup()
                } else {
                    //다이얼로그에 입력한 이름을 받아서 파라미터로 넘김
                    saveUserName(editText.text.toString())
                }
            }
            .setCancelable(false)//취소를 못하도록 설정하기, 백스택버튼
            .show()
    }/////////////end of showNameInputPopup

    private fun saveUserName(name: String) {
        val userId = getCurrentUserID()
        val currentUserDB = userDB.child(userId)
        val user = mutableMapOf<String, Any>()
        user["userId"] = userId
        user["name"] = name
        //맵을 다시 만들어서 다시 저장하기
        currentUserDB.updateChildren(user)
        //여기에 유저 정보를 가져와라 함수 호출(DB연동하기로 추가부분)
        //한번도 선택하지 않은 유저정보를 가져와라.
        getUnSelectedUsers()
    }

    //로그아웃 보기
    private fun initSignOutButton() {
        val signOutButton = findViewById<Button>(R.id.signOutButton)
        signOutButton.setOnClickListener {
            auth.signOut()
            startActivity(Intent(this, MainActivity::class.java))
            //그런데  finish를 하면 MainActivity로 돌아가서 onStart()를 호출
            //MainActivity에서 LikeActivity로 가는 else문 맨끝에 finish를 추가해줌.
            finish()
        }
    }

    override fun onCardDragging(direction: Direction?, ratio: Float) {}
    override fun onCardSwiped(direction: Direction?) {
        when(direction){
            Direction.Right -> like()
            Direction.Left -> disLike()
            else ->{

            }
        }
    }
    override fun onCardRewound() {}
    override fun onCardCanceled() {}
    override fun onCardAppeared(view: View?, position: Int) {}
    override fun onCardDisappeared(view: View?, position: Int) {}

    //내 DB에도 상대방 DB에도 매치가 된 정보가 담긴다.
    private fun saveMatchIfOtherUserLikedMe(userId: String){
        //상대방의 유저의 아이디를 가져온다.
        //true이면 상대방이 나를 좋아요 한거고 없거나 널이면 상대방이 나를 좋아한 적이 없는 것이다.
        val otherUserDB = userDB.child(getCurrentUserID()).child("likedBy").child("like").child(userId)
        otherUserDB.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.value == true) {
                    userDB.child(getCurrentUserID())
                        .child("likedBy")
                        .child("match")
                        .child(userId)
                        .setValue(true)

                    userDB.child(userId)
                        .child("likedBy")
                        .child("match")
                        .child(getCurrentUserID())
                        .setValue(true)
                }
            }
            override fun onCancelled(error: DatabaseError) {}
        })
    }/////////////////////end of saveMatchIfOtherUserLikedMe


    private fun like(){
        //카드 가져오기
        val card = cardItems[manager.topPosition -1]
        //처리한 카드가 필요없으니까 지운다.
        //이렇게 하면 CardStackLayoutManager의 포지션은 항상 0이 될것이다. 여기서는...
        cardItems.removeFirst()
        //가져온 카드를 저장해야 한다. - 선택된 유저아이디에(즉 상대 아이디를 말함)
        userDB.child(card.userId)
            .child("likedBy")
            .child("like")
            .child(getCurrentUserID())//getCurrentUserID는 키가 될것이고
            .setValue(true)//위 키의 실질적인  value가 true가 됨.
        saveMatchIfOtherUserLikedMe(card.userId)//상대방의이름은 카드 유저아이디로 가져간다.
        //todo 이사람과 매칭이 되었다는 것을 알 수 없다. 매칭이 된 시점을 봐야한다.
        Toast.makeText(this, "${card.name}님을 Like 하셨습니다.",Toast.LENGTH_SHORT).show()
    }

    private fun disLike(){
        //카드 가져오기
        val card = cardItems[manager.topPosition -1]
        //처리한 카드가 필요없으니까 지운다.
        //이렇게 하면 CardStackLayoutManager의 포지션은 항상 0이 될것이다. 여기서는...
        cardItems.removeFirst()
        //가져온 카드를 저장해야 한다. - 선택된 유저아이디에(즉 상대 아이디를 말함)
        userDB.child(card.userId)
            .child("likedBy")
            .child("disLike")
            .child(getCurrentUserID())//getCurrentUserID는 키가 될것이고
            .setValue(true)//위 키의 실질적인  value가 true가 됨.
        //todo 이사람과 매칭이 되었다는 것을 알 수 없다. 매칭이 된 시점을 볼 필요가 없다 왜냐면 disLike했으니까
        Toast.makeText(this, "${card.name}님을 disLike 하셨습니다.",Toast.LENGTH_SHORT).show()
    }

}