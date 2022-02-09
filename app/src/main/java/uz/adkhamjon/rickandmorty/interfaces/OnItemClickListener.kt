package uz.adkhamjon.rickandmorty.interfaces

import uz.adkhamjon.rickandmorty.models.Result

interface OnItemClickListener{
    fun itemClick(result: Result)
}