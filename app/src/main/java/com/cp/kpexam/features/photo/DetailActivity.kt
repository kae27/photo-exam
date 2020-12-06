package com.cp.kpexam.features.photo


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cp.kpexam.R
import com.cp.kpexam.common.loadImageUrl
import kotlinx.android.synthetic.main.detail_activity.*

class DetailActivity : AppCompatActivity() {

    private val imageUrl: String by lazy {
        intent.getStringExtra("imageUrl") ?: ""
    }
    private val title: String by lazy {
        intent.getStringExtra("title") ?: ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_activity)

        backIcon.setOnClickListener{
            finish()
        }

        titleTextView.text = title
        imageDetail.loadImageUrl(imageUrl)

    }
}