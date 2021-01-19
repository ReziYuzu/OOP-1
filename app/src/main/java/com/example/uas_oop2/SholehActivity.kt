package com.example.uas_oop2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.uas_oop2.adapter.SholehAdapter
import com.example.uas_oop2.model.SholehDosen
import io.realm.Realm
import io.realm.exceptions.RealmException
import kotlinx.android.synthetic.main.activity_sholeh.*

class SholehActivity : AppCompatActivity() {
    lateinit var SholehAdapter: SholehAdapter //
    lateinit var realm: Realm //pemanggilan library realm
    val lm = LinearLayoutManager(this)//defaul dari adapter , contex this karena konteknnya beda-beda
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sholeh)
        initview()

        btn_add.setOnClickListener {//botton untuk tambah data
            realm.beginTransaction()
            var count = 0
            realm.where(SholehDosen::class.java).findAll().let {
                for (i in it) {
                    count++
                }
            }
            try {

                if(validate()){
                    val dosens = realm.createObject(SholehDosen::class.java)
                    val nama = et_nama.text.toString().trim()
                    val nipy = et_nipy.text.toString().trim()
                    dosens.setId(count + 1)
                    dosens.setNama(nama)
                    dosens.setNipy(nipy)
                    et_nama.setText("")//untuk menginputkan edit data
                    et_nipy.setText("")
                    realm.commitTransaction()
                    getAllDosen()
                }

            } catch (e: RealmException) {

           }
        }

        btn_update.setOnClickListener {
            realm.beginTransaction()
            realm.where(SholehDosen::class.java).equalTo("id",et_id.text.toString().toInt()).findFirst().let { it ->
                it!!.setNama(et_nama.text.toString())
                it!!.setNipy(et_nipy.text.toString())//it !! =
            }

            realm.commitTransaction()
            getAllDosen() //
        }

        btn_delete.setOnClickListener {
            realm.beginTransaction()
            realm.where(SholehDosen::class.java).equalTo("id", et_id.text.toString().toInt()).findFirst().let {
                it!!.deleteFromRealm()
            }
            realm.commitTransaction()
            getAllDosen()
        }
    }

    private fun initview() {
        //mengatur tampilan recyclerview
        rv_dosen.layoutManager = lm
        SholehAdapter = SholehAdapter(this)
        rv_dosen.adapter = SholehAdapter
        Realm.init(applicationContext)
        realm = Realm.getDefaultInstance()
        getAllDosen()

    }

    private fun getAllDosen() { //menyambungkan  antara tabel dan adapter
        realm.where(SholehDosen::class.java).findAll().let {
            SholehAdapter.setDosen(it)
        }
    }

    private fun setNameError(err: String?){et_nama.error = err}
    private fun setNIPYError(err: String?){et_nipy.error = err}

    private fun validate() : Boolean { //memastikan nama dan nipy harus diisi
        setNameError(null)
        setNIPYError(null)

        if(et_nama.text.toString().trim().isEmpty()){
            setNameError("Nama tidak boleh kosong!")
            return false
        }

        if(et_nipy.text.toString().trim().isEmpty()){
            setNIPYError("NIPY tidak boleh kosong")
            return false
        }

        return true
    }
}