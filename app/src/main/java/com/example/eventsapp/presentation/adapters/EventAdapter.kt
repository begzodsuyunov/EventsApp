package com.example.eventsapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.eventsapp.R
import com.example.eventsapp.data.room.entity.EventEntity
import com.example.eventsapp.databinding.ListItemEventBinding
import com.example.eventsapp.utils.Constants

class EventAdapter : ListAdapter<EventEntity, EventAdapter.ViewHolder>(CallBack) {

    private var switchChangedListener: ((EventEntity) -> Unit)? = null

    fun setSwitchChangedListener(block: (EventEntity) -> Unit) {
        switchChangedListener = block
    }

    inner class ViewHolder(private val binding: ListItemEventBinding) :
        RecyclerView.ViewHolder(binding.root) {


        init {
            binding.eventIsActive.setOnClickListener {
                val data = getItem(absoluteAdapterPosition)
                switchChangedListener?.invoke(data.copy(status = 1 - data.status))
            }
        }

        fun onBind() {
            val data = getItem(absoluteAdapterPosition)
            binding.apply {
                if (data.status == 1){
                    tv1.text = "Активно"
                    liner.setBackgroundResource(R.drawable.activ)
                }else{
                    tv1.text = "Не активно"
                    liner.setBackgroundResource(R.drawable.no_activ)
                }
                tvEventName.text = data.voice
                tvEventSpeakName.text = getSpeakName(data.name)
                eventIsActive.isChecked = data.status == 1
                shapeImageEvent.setImageResource(Constants.images[absoluteAdapterPosition])
            }
        }
    }

    private companion object {
        fun getSpeakName(name: String) = "$name"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder (
        ListItemEventBinding.bind(
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_event, parent, false)
        )
    )


    override fun onBindViewHolder(holder: EventAdapter.ViewHolder, position: Int) {
        holder.onBind()
    }
}

private val CallBack = object : DiffUtil.ItemCallback<EventEntity>() {
    override fun areItemsTheSame(oldItem: EventEntity, newItem: EventEntity) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: EventEntity, newItem: EventEntity) =
        oldItem.name == newItem.name &&
                oldItem.status == newItem.status

}