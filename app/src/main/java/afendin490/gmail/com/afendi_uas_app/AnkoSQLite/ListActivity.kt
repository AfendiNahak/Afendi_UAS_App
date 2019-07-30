package afendin490.gmail.com.afendi_uas_app.AnkoSQLite

import afendin490.gmail.com.afendi_uas_app.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_list.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class ListActivity : AppCompatActivity() {

    //mendeklarasikan objek latenit untuk adapter dan
    // juga objek arrayList untuk data yang akan diinputkan
    private lateinit var adapter: RVAdapter
    private var mahasiswaList = ArrayList<ModelAnko>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        // menginisialisasikan adapater menjadi ArrayList yang akan ditampilkan dalam recyclerView
        adapter = RVAdapter(this, mahasiswaList)
        recycler_view.adapter = adapter

        //memanggil fungsi getData untuk memanggil data yang sudah diinput pada database SQLite
        //setelah itu ditampilkan didalam recyclerView
        getData()
        recycler_view.layoutManager = LinearLayoutManager(this)
    }

    //fungsi getData untuk mengambil atau mendapatkan data dari tabel mahasiswa melalui kelas Model SQLite
    private fun getData() {
        database.use {
            mahasiswaList.clear()
            var result = select(ModelAnko.Table_Mhs)
            var dataMhs = result.parseList(classParser<ModelAnko>())
            mahasiswaList.addAll(dataMhs)
            adapter.notifyDataSetChanged()
        }
    }


}
