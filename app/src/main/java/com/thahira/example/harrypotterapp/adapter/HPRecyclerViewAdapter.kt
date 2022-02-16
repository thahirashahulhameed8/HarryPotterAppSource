package com.thahira.example.harrypotterapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.thahira.example.harrypotterapp.R
import com.thahira.example.harrypotterapp.model.CharactersItem
import com.thahira.example.harrypotterapp.utils.switchFragment
import com.thahira.example.harrypotterapp.view.DetailFragment
import com.thahira.example.harrypotterapp.view.FirstFragment.Companion.staff
import com.thahira.example.harrypotterapp.view.StudentFragment.Companion.house
import com.thahira.example.harrypotterapp.view.WandFragment

class HPRecyclerViewAdapter(
    private val listOfCharacters : MutableList<CharactersItem> = mutableListOf(),
    val parentFragmentManager: FragmentManager
): RecyclerView.Adapter<HPViewHolder>(){

    fun setCharacters(characters: List<CharactersItem>)
    {
        listOfCharacters.clear()
        listOfCharacters.addAll(characters)
        notifyDataSetChanged()
    }

    fun setStudentCharacters(characters: List<CharactersItem>)
    {
        listOfCharacters.clear()
        var i: Int = 0
        while(i < characters.size)
        {
            if (house == characters[i].house)
                listOfCharacters.add(characters[i])
            i++
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HPViewHolder {
        LayoutInflater.from(parent.context).inflate(R.layout.card_details,parent,false).apply{
            return HPViewHolder(this)
        }
    }

    override fun onBindViewHolder(holder: HPViewHolder, position: Int) {
    val card = listOfCharacters[position]

        holder.setInformationToTheViewHolder(card,parentFragmentManager)

    }

    override fun getItemCount(): Int = listOfCharacters.size
}


class HPViewHolder(itemView : View): RecyclerView.ViewHolder(itemView){
    private val characterName: TextView = itemView.findViewById(R.id.character_name)
   // private val dateOfBirth: TextView = itemView.findViewById(R.id.date_of_birth)
   // private val characterGender: TextView = itemView.findViewById(R.id.gender)
    private val characterHouse: TextView = itemView.findViewById(R.id.house)
    private val characterImage: ImageView = itemView.findViewById(R.id.char_image)

    private val cardClick: CardView = itemView.findViewById(R.id.card_click)

    fun setInformationToTheViewHolder(characterItem: CharactersItem, parentFragmentManager: FragmentManager)
    {
        characterName.text = characterItem.name
        //dateOfBirth.text = characterItem.dateOfBirth
       // characterGender.text = characterItem.gender
        characterHouse.text = characterItem.house

        Glide.with(itemView)
            .load(characterItem.image)
            .override(200,200)
            .centerCrop()
            .into(characterImage)

        cardClick.setOnClickListener{
            switchFragment(parentFragmentManager,WandFragment.newInstance(characterItem))
        }
    }
}