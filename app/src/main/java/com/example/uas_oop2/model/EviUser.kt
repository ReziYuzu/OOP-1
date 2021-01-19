package com.example.uas_oop2.model

import io.realm.RealmObject
import io.realm.annotations.RealmClass

@RealmClass
open class EviUser: RealmObject() {
    private var id: Int=0
    private var nama:String=""
    private var nim:String=""

    fun setId(id:Int){
        this.id =id
    }
    fun getId():Int{
        return id
    }
    fun setNama(nama:String){
        this.nama=nama
    }
    fun getNama():String{
        return nama
    }
    fun setNim(nim:String){
        this.nim=nim
    }
    fun getNim():String{
        return nim
    }
}