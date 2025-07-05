package io.example.readcast

import androidx.recyclerview.widget.DiffUtil
import io.example.readcast.domain.model.Card
import io.example.readcast.domain.model.Section

object SectionDiffCallback : DiffUtil.ItemCallback<Section<Card>>() {

    override fun areItemsTheSame(
        oldItem: Section<Card>,
        newItem: Section<Card>,
    ): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: Section<Card>,
        newItem: Section<Card>,
    ): Boolean = oldItem == newItem
}