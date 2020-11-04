package br.usjt.ucsist.chatb


import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.android.synthetic.main.activity_chat.*
import java.util.*
import kotlin.collections.ArrayList

class ChatActivity : AppCompatActivity() {


    private var adapter: ChatAdapter? = null
    private var mensagens: MutableList<Mensagem>? = null

    private var fireUser: FirebaseUser? = null
    private var mMsgsReference: CollectionReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        mensagens = ArrayList()
        adapter = ChatAdapter(mensagens!!,this)
        mensagensRecyclerView.setAdapter(adapter)
        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.reverseLayout = true
        mensagensRecyclerView.setLayoutManager(linearLayoutManager)


    }

    override fun onStart() {
        super.onStart()
        setupFirebase()
    }

    fun enviarMensagem(view: View?) {
        val mensagem = mensagemEditText.text.toString()
        val m = Mensagem(
            fireUser!!.email!!, Date(),
            mensagem
        )
        esconderTeclado(view!!)
        mMsgsReference!!.add(m)
        Log.d("MESSAGES:","enviada")
    }

    private fun setupFirebase() {
        fireUser = FirebaseAuth.getInstance().currentUser
        mMsgsReference = FirebaseFirestore.getInstance().collection("mensagens")
        getRemoteMsgs()
    }

    private fun getRemoteMsgs() {
        mMsgsReference!!.addSnapshotListener(EventListener<QuerySnapshot?>() { querySnapshot: QuerySnapshot?, firebaseFirestoreException: FirebaseFirestoreException? ->
            mensagens!!.clear()
            for (doc in querySnapshot!!.documents) {
                val incomingMsg = doc.toObject(Mensagem::class.java)
                Log.d("MESSAGES:",""+incomingMsg!!.texto)
                mensagens!!.add(incomingMsg!!)
            }
            Collections.sort(mensagens)
            adapter!!.notifyDataSetChanged()
        })

    }

    private fun esconderTeclado(v: View) {
        val ims: InputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        ims.hideSoftInputFromWindow(v.windowToken, 0)
    }
}