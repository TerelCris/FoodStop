package com.mobdeve.s11.group16.foodstop

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.mobdeve.s11.group16.foodstop.databinding.PostLayoutBinding

class PostActivity : AppCompatActivity() {
    private val commentList: ArrayList<Comment>  = DataHelper.initializeComment()

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewBinding : PostLayoutBinding = PostLayoutBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.postIv.setImageResource(intent.getIntExtra(Keys.IMAGE_KEY.name, 0))
        viewBinding.titleTv.text = intent.getStringExtra(Keys.TITLE_KEY.name)
        viewBinding.userTv.text = intent.getStringExtra(Keys.USER_KEY.name)
        viewBinding.datePostTv.text = intent.getStringExtra(Keys.DATE_KEY.name)
        viewBinding.bodyTv.text = intent.getStringExtra(Keys.BODY_KEY.name)

        val position = intent.getIntExtra(Keys.POSITION_KEY.name, 0)

        val intent : Intent = Intent()
        intent.putExtra(Keys.POSITION_KEY.name, position)

        viewBinding.likeBtn.isActivated = false

        viewBinding.likeBtn.setOnClickListener(View.OnClickListener {
            if(viewBinding.likeBtn.isActivated){
                viewBinding.likeBtn.setImageResource(R.drawable.fav)
            }

            else{
                viewBinding.likeBtn.setImageResource(R.drawable.favon)
            }

            viewBinding.likeBtn.isActivated = !viewBinding.likeBtn.isActivated
        })

        val snapHelper: SnapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(viewBinding.commentsRv)

        this.recyclerView = viewBinding.commentsRv
        this.recyclerView.adapter = MyAdapterComment(this.commentList)
        this.recyclerView.layoutManager = LinearLayoutManager(this)
    }
}