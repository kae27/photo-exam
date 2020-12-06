package com.cp.kpexam.features.photo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cp.kpexam.R
import com.cp.kpexam.common.loadImageUrl
import com.cp.kpexam.domain.model.Photo
import kotlinx.android.synthetic.main.item_photo.view.*

class PhotoAdapter(private val photos: List<Photo>, private  val onItemClick: (photo: Photo) ->Unit)
    : RecyclerView.Adapter<PhotoAdapter.ViewHolder>()  {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_photo, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return photos.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(photos[position])
        holder.itemView.setOnClickListener {
            onItemClick(photos[position])
        }
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(photo: Photo) {

            itemView.title.text = photo.title
            itemView.image.loadImageUrl(photo.imageUrl.trim(), R.drawable.placehoder)
        }
    }
}
