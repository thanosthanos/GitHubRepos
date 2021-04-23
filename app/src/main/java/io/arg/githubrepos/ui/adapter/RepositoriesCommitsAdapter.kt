package io.arg.githubrepos.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.arg.githubrepos.R
import io.arg.githubrepos.data.server.model.GeneralCommitInfo

class RepositoriesCommitsAdapter (private val context: Context, private val commitsList: List<GeneralCommitInfo>) :
    RecyclerView.Adapter<RepositoriesCommitsAdapter.CommitViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommitViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cell_commit, parent, false)
        return CommitViewHolder(view)
    }

    override fun onBindViewHolder(holder: CommitViewHolder, position: Int) {
        val commit = commitsList[position]

        holder.messageTextView.text = commit.message
        holder.shaTextView.text = commit.sha
        holder.authorTextView.text = commit.name
    }

    override fun getItemCount(): Int = commitsList.size

    class CommitViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val messageTextView: TextView = itemView.findViewById(R.id.messageTextView)
        val shaTextView: TextView = itemView.findViewById(R.id.shaTextView)
        val authorTextView: TextView = itemView.findViewById(R.id.authorTextView)
    }
}
