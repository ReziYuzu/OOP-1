package com.example.uas_oop2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.uas_oop2.adapter.EviAdapter
import com.example.uas_oop2.model.EviUser
import io.realm.Realm
import io.realm.exceptions.RealmException
import kotlinx.android.synthetic.main.activity_evi.*

class EviActivity : AppCompatActivity() {
    lateinit var EviAdapter:EviAdapter
    lateinit var realm:Realm
    val lm =LinearLayoutManager(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_evi)
        initview()

        btn_add.setOnClickListener {
            realm.beginTransaction()
            var count =0
            realm.where(EviUser::class.java).findAll().let {
                for (i in it){
                    count ++
                }
            }
            try {
                val user = realm.createObject(EviUser::class.java)
                user.setId(count+1)
                user.setNama(et_nama.text.toString())
                user.setNim(et_nim.text.toString())
                et_nama.setText("")
                et_nim.setText("")
                realm.commitTransaction()
                getAllUser()
            }catch (e: RealmException){

            }
        }
        btn_update.setOnClickListener {
            realm.beginTransaction()
            realm.where(EviUser::class.java).equalTo("id",et_id.text.toString().toInt()).findFirst().let {
                it!!.setNama(et_nama.text.toString())
                it!!.setNim(et_nim.text.toString())
            }
            realm.commitTransaction()
            getAllUser()
        }

        btn_delete.setOnClickListener {
            realm.beginTransaction()
            realm.where(EviUser::class.java).equalTo("id",et_id.text.toString().toInt()).findFirst().let {
                it!!.deleteFromRealm()
            }
            realm.commitTransaction()
            getAllUser()
        }

    }
    private fun initview(){
        rv_user.layoutManager = lm
        EviAdapter = EviAdapter(this)
        rv_user.adapter = EviAdapter
        Realm.init(applicationContext)
        realm = Realm.getDefaultInstance()
        getAllUser()
    }
    private fun getAllUser(){
        realm.where(EviUser::class.java).findAll().let {
            EviAdapter.setUser(it)
        }
    }
}