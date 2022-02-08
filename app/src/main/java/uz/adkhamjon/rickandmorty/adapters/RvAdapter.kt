package uz.adkhamjon.rickandmorty.adapters
import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.adkhamjon.rickandmorty.databinding.LinearItemBinding
import uz.adkhamjon.rickandmorty.models.Result

class RvAdapter(
    var context: Context,
    var onItemClickListener: OnItemClickListener) :
    PagingDataAdapter<Result, RecyclerView.ViewHolder>(MyDiffUtill()) {
    class MyDiffUtill : DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.id == newItem.id
        }
        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }
    }
    inner class Linear(var linearItemBinding: LinearItemBinding) :
        RecyclerView.ViewHolder(linearItemBinding.root) {
        @SuppressLint("SetTextI18n")
        fun onBind(result:Result) {
            linearItemBinding.tittle.text=result.name
            linearItemBinding.date.text="Status: ${result.status}"
            Glide.with(context).load(result.image)
                .into(linearItemBinding.icon)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
             return  Linear( LinearItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
             )
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val fromVh = holder as Linear
        getItem(position)?.let { movie->
            fromVh.onBind(movie)
            holder.itemView.setOnClickListener {
                onItemClickListener.itemClick(movie)
            }
        }

    }

    interface OnItemClickListener{
         fun itemClick(result: Result)
    }
}