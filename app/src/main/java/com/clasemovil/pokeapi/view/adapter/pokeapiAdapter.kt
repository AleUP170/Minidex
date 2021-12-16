package com.clasemovil.pokeapi.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.clasemovil.pokeapi.databinding.ListElementBinding
import com.clasemovil.pokeapi.model.PokemonList
import com.clasemovil.pokeapi.model.Results


class pokeapiAdapter(context: Context, pokeList : List<Results>, onItemListener: OnItemListener):
    RecyclerView.Adapter<pokeapiAdapter.ViewHolder>(){

    private val pokeList = pokeList
    private val onItemListener = onItemListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): pokeapiAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListElementBinding.inflate(layoutInflater)

        return ViewHolder(binding, onItemListener)
    }

    override fun onBindViewHolder(holder: pokeapiAdapter.ViewHolder, position: Int) {
        holder.bindData(pokeList[position])
    }

    override fun getItemCount(): Int {
        return pokeList.size
    }

    interface OnItemListener{
        fun OnItemClick(poke: Results)
    }

    class ViewHolder(binding: ListElementBinding, onItemListener: OnItemListener):
        RecyclerView.ViewHolder(binding.root), View.OnClickListener{

        private val name = binding.pokeName
        private val context = binding.root.context
        private val onItemListener = onItemListener

        private lateinit var pokemon: Results

        init{
            binding.root.setOnClickListener(this)
        }

        override fun onClick(p0: View?){
            onItemListener.OnItemClick(pokemon)
        }

        fun bindData(item: Results){
            //Glide.with(context).load(item.icon).into(icon)

            name.text = item.name!!.capitalize()

            pokemon = item
        }
    }

}