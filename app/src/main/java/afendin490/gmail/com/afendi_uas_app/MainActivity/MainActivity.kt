package afendin490.gmail.com.afendi_uas_app.MainActivity

import afendin490.gmail.com.afendi_uas_app.AnkoSQLite.MainAnkoSQLite
import afendin490.gmail.com.afendi_uas_app.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {
    //deklarasi objek latenit dari button untuk intent
    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //melakukan intent ke Activity Main SQLite untuk input data
        ankoSQLite.setOnClickListener {
            startActivity<MainAnkoSQLite>()
        }
    }
}
