package afendin490.gmail.com.afendi_uas_app.AnkoSQLite

import afendin490.gmail.com.afendi_uas_app.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main_anko_sqlite.*
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.update
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class MainAnkoSQLite : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_anko_sqlite)

        //mendeklarasikan variabel yang diinisialisasikan dengan form input data
        //ini dilakan untuk kebutuhan update data pada tabel
        var oldNIM = intent.getStringExtra("oldNIM")
        var oldNama = intent.getStringExtra("oldNama")
        var oldJurusan = intent.getStringExtra("oldJurusan")
        var oldEmail = intent.getStringExtra("oldEmail")

        //memeriksa jika form Nim masih kosong maka button Update mati atau Disable
        //jika form nim sudah terisi maka button Simpan yang mati dan button Update aktif kembali
        //setiap form juga dilakukan set Text sehingga data dari tabel akan tampil di form dan bisa diedit
        if(oldNIM.isNullOrEmpty()){
            buttonUpdate.isEnabled = false
        }
        else{
            buttonSimpan.isEnabled = false
            editTextNIM.setText(oldNIM)
            editTextNama.setText(oldNama)
            editTextJurusan.setText(oldJurusan)
            editTextEmail.setText(oldEmail)
        }

        //even pada button Simpan, dengan memanggil fungsi addDataMhs dan fungsi clearData
        buttonSimpan.setOnClickListener {
            addDataMhs()

            clearData()
        }

        //even pada button Lihat yang melakukan intent ke activity List untuk melihat data yang sudah disimpan
        //atau sudah diinsert ke dalam tabel
        buttonLihat.setOnClickListener {
            startActivity<ListActivity>()
        }

        //even pada button Update
        //pada Anko hanya perlu menuliskan Query seperti ini tanpa harus melakukan Query rumit seperti pada Java
        //setelah diupdate maka form akan dikosongkan dengan fungsi clearData
        buttonUpdate.setOnClickListener {
            database.use {
                update(ModelAnko.Table_Mhs,
                    ModelAnko.nim to editTextNIM.text.toString(),
                    ModelAnko.nama to editTextNama.text.toString(),
                    ModelAnko.jurusan to editTextJurusan.text.toString(),
                    ModelAnko.email to editTextEmail.text.toString()
                    ).whereArgs("${ModelAnko.nim} = {nim}",
                    "nim" to oldNIM
                ).exec()
            }
            clearData()
            toast("Data diUpdate")
        }
    }

    //fungsi addDataMhs untuk melakukan insert data pada tabel
    //fungsi ini dipanggil dalam even pada button Simpan
    //setelah data disimpan maka akan menampilkan pesan menggunakan intent
    private fun addDataMhs() {
        database.use {
            insert(ModelAnko.Table_Mhs,
                ModelAnko.nim to editTextNIM.text.toString(),
                ModelAnko.nama to editTextNama.text.toString(),
                ModelAnko.jurusan to editTextJurusan.text.toString(),
                ModelAnko.email to editTextEmail.text.toString()
                )
            toast("Simpan Berhasil")
        }
    }

    //fungsi ini digunakan untuk menghapus isi form
    //fungsi ini dijalankan jika data berhasil disimpan atau diupdate
    //form dikosongkan dengan memanggil method clear()
    fun clearData(){
        editTextNIM.text.clear()
        editTextNama.text.clear()
        editTextJurusan.text.clear()
        editTextEmail.text.clear()
    }
}
