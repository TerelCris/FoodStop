package com.mobdeve.s11.group16.foodstop

import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.mobdeve.s11.group16.foodstop.databinding.PostLayoutBinding
import org.w3c.dom.Text

class PostActivity : AppCompatActivity() {
    private val commentList = ArrayList<Comment>()

    private lateinit var database: FirebaseDatabase
    private lateinit var ref: DatabaseReference
    private lateinit var storage: FirebaseStorage
    private lateinit var image : ImageView
    private lateinit var title : TextView
    private lateinit var date : TextView
    private lateinit var body : TextView
    var currentUsername: String? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewBinding : PostLayoutBinding = PostLayoutBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.postIv.setImageResource(intent.getIntExtra(Keys.IMAGE_KEY.name, 0))
        viewBinding.titleTv.text = intent.getStringExtra(Keys.TITLE_KEY.name)
        viewBinding.userTv.text = intent.getStringExtra(Keys.USERNAME_KEY.name)
        viewBinding.datePostTv.text = intent.getStringExtra(Keys.DATE_KEY.name)
        viewBinding.bodyTv.text = intent.getStringExtra(Keys.BODY_KEY.name)

        val position = intent.getIntExtra(Keys.POSITION_KEY.name, 0)

        val intent : Intent = Intent()
        intent.putExtra(Keys.POSITION_KEY.name, position)


        val snapHelper: SnapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(viewBinding.commentsRv)

        this.recyclerView = viewBinding.commentsRv
        this.recyclerView.adapter = MyAdapterComment(this.commentList)
        this.recyclerView.layoutManager = LinearLayoutManager(this)
    }
}