package afendin490.gmail.com.afendi_uas_app.AnkoSQLite

import afendin490.gmail.com.afendi_uas_app.R
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.itemlist.view.*
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

//kelas untuk RecyclerView Adapter
class RVAdapter(val context: Context, val items: ArrayList<ModelAnko>)
    :RecyclerView.Adapter<RVAdapter.ViewHolder>(){

    //RecyclerView yang akan menampilkan data menggunakan ViewHolder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position])
    }

    // fungsi untuk mendapatkan jumlah item
    override fun getItemCount(): Int = items.size

    //fungsi untuk membuat ViewHolder yang akan menampilkan data yang sudah diinputkan
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.itemlist, parent, false))
    }

    //kelas ViewHolder yang akan menampilkan data yang inputkan
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        fun  bindItem(items: ModelAnko){
            itemView.hasilTxtNIM.text = items.nim
            itemView.hasilTxtNama.text = items.nama
            itemView.hasilTxtJur.text = items.jurusan
            itemView.hasilTxtEmail.text = items.email

            //even pada button Edit yang akan digunakan untuk update data pada tabel
            //intentnya kembali pada menu input data pada activity MainAnkoSQLite
            itemView.btnEdit.setOnClickListener {
                itemView.context.startActivity<MainAnkoSQLite>(
                    "oldNIM" to items.nim,
                    "oldNama" to items.nama,
                    "oldJurusan" to items.jurusan,
                    "oldEmail" to items.email
                )
            }

            //even pada button Hapus untuk hapus data pada tabel
            //data dihapus berdasarkan id nya yaitu nim, lalu akan mengirimkan intent jika data berhasil dihapus
            itemView.btnHapus.setOnClickListener {
                itemView.context.database.use {
                    delete(ModelAnko.Table_Mhs, "(${ModelAnko.nim} = {nim})",
                        "nim" to items.nim.toString())
                }

                itemView.context.toast("Data Berhasil diHapus")
            }
        }
    }
}