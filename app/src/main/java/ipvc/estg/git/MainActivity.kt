package ipvc.estg.git

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.buttonNotas)
        button.setOnClickListener{
            val intent = Intent(this, Notas::class.java)
            startActivity(intent)
        }


        val buttonMapa = findViewById<Button>(R.id.buttonMapa)
        buttonMapa.setOnClickListener{
            val intent = Intent(this, MapsActivity::class.java)
            startActivity(intent)
        }

        val button2 = findViewById<Button>(R.id.buttonRetrofit)
        button2.setOnClickListener{
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }
    }

    // -- Create Development Branch
}