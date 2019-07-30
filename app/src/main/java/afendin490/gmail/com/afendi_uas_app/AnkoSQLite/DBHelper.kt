package afendin490.gmail.com.afendi_uas_app.AnkoSQLite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

//mendeklarasikan kelas database Helper menggunakan SQLite
//nama database serta versinya dideklarasikan bersama dengan kelas
class DBHelper(ctx : Context) : ManagedSQLiteOpenHelper(ctx, "anko_sqlite", null, 1) {

    //melakukan pengecekan terhadap database jika masih belum ada tabel didalamnya
    companion object {
        private var intance: DBHelper? = null
        @Synchronized
        fun getIntance(ctx: Context): DBHelper{
            if (intance==null){
                intance = DBHelper(ctx.applicationContext)
            }
            return intance as DBHelper
        }
    }

    //melakukan Query untuk menciptakan tabel,
    //pada ANKO Query hanya perlu memanggil nama kelas Model nya,
    //kemudian bisa diikuti dengan nama tabel dan kolomnya
    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(ModelAnko.Table_Mhs, true,
            ModelAnko.nim to TEXT + PRIMARY_KEY,
            ModelAnko.nama to TEXT,
            ModelAnko.jurusan to TEXT,
            ModelAnko.email to TEXT
        )
    }

    //melakukan upgade maka tabel akan didrop
    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        if (db != null) {
            db.dropTable(ModelAnko.Table_Mhs, true)
        }
    }
}

// membuat konteks untuk kelas DBHelper
val Context.database : DBHelper
get() = DBHelper.getIntance(applicationContext)
