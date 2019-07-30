package afendin490.gmail.com.afendi_uas_app.AnkoSQLite

//membuat kelas model untuk database SQlite untuk input data mahasiswa
//ini mencakup nama database berserta jumlah kolomnya sebanyak 4
//semua data dalam bentuk teks atau String
data class ModelAnko(var nim:String?, var nama:String?, var jurusan:String?, var email:String?) {
    companion object {
        const val Table_Mhs: String = "Table_Mhs"
        const val nim: String = "nim"
        const val nama: String = "nama"
        const val jurusan: String = "jurusan"
        const val email: String = "email"
    }
}