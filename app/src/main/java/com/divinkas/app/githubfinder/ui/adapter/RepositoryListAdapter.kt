package com.divinkas.app.githubfinder.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.divinkas.app.githubfinder.R
import com.divinkas.app.githubfinder.databinding.ItemRepositoryBinding
import com.divinkas.app.githubmodule.bean.api.Repository

class RepositoryListAdapter : RecyclerView.Adapter<RepositoryListAdapter.RepositoryViewHolder>() {
    private val repositories: MutableList<Repository> = ArrayList()
    var isLocalRepository: Boolean = false
    var removeAction: (repository: Repository) -> Unit = {}
    var saveAction: (repository: Repository) -> Unit = {}

    fun addAll(newRepositories: List<Repository>) {
        newRepositories.forEach {
            repositories.add(it)
            notifyItemInserted(repositories.indexOf(it))
        }
    }

    fun remove(repository: Repository) {
        val index = repositories.indexOf(repository)
        repositories.remove(repository)
        notifyItemRemoved(index)
        notifyItemRangeChanged(index, repositories.size)
    }

    fun clean() {
        repositories.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_repository, parent, false)
        return RepositoryViewHolder(DataBindingUtil.bind(view)!!)
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        holder.binding.repository = repositories[position]
        holder.binding.owner = repositories[position].owner
        holder.binding.isLocalRepository = isLocalRepository
        holder.binding.btnAction.setOnClickListener {
            if (isLocalRepository) {
                removeAction(repositories[position])
            } else {
                saveAction(repositories[position])
            }
        }
    }

    override fun getItemCount() = repositories.count()

    class RepositoryViewHolder(val binding: ItemRepositoryBinding) :
        RecyclerView.ViewHolder(binding.root)
}