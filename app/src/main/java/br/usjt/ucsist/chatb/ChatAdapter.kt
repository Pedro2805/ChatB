package br.usjt.ucsist.chatb

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


//class ChatAdapter(mensagens: List<Mensagem>, context: Context) :
//    RecyclerView.Adapter<ChatViewHolder>() {
//    private val mensagens: List<Mensagem>
//    private val context: Context
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
//        val inflater = LayoutInflater.from(context)
//        val v: View = inflater.inflate(R.layout.list, parent, false)
//        return ChatViewHolder(v)
//    }
//
//    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
//        val m: Mensagem = mensagens[position]
//        holder.dataNomeTextView.setText(
//            context.getString(
//                R.string.data_nome,
//                DateHelper.format(m.getData()), m.getUsuario()
//            )
//        )
//        holder.mensagemTextView.setText(m.getTexto())
//        mensagemEditText.setText("")
//    }
//
//    override fun getItemCount(): Int {
//        return mensagens.size
//    }
//
//    init {
//        this.mensagens = mensagens
//        this.context = context
//    }
//}
//
//class ChatViewHolder(v: View) : RecyclerView.ViewHolder(v) {
//    var dataNomeTextView: TextView
//    var mensagemTextView: TextView
//    private var view: View = v
//
//    init {
//        dataNomeTextView = view.findViewById(R.id.)
//        mensagemTextView = view.findViewById(R.id.mensagemTextView)
//    }
//}


// -------------------------------------
class ChatAdapter(
    private val values: List<Mensagem>,
    private val context: Context
) : RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(br.usjt.ucsist.chatb.R.layout.list_item, parent, false)
        return ChatViewHolder(view)
    }


    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        val item = values[position]


        holder.dataNomeTextView.text = context.getString(
            R.string.data_nome,
            DateHelper.format(item.data), item.usuario
        )
        holder.mensagemTextView.text = item.texto
    }

    override fun getItemCount(): Int = values.size

    inner class ChatViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val dataNomeTextView: TextView = view.findViewById(br.usjt.ucsist.chatb.R.id.dataNomeTextView)
        val mensagemTextView: TextView = view.findViewById(br.usjt.ucsist.chatb.R.id.mensagemTextView)
    }
}