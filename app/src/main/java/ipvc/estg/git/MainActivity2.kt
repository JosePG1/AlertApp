package ipvc.estg.git

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity.apply
import android.view.View
import android.widget.Toast
import androidx.core.view.GravityCompat.apply
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ipvc.estg.git.adapters.UserAdapter
import ipvc.estg.git.adapters.api.EndPoints
import ipvc.estg.git.adapters.api.OutputPost
import ipvc.estg.git.adapters.api.ServiceBuilder
import ipvc.estg.git.adapters.api.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        /*
        val request= ServiceBuilder.buildService(EndPoints::class.java)
        val call=request.getUsers()
        call.enqueue(object : Callback<List<User>> {
            override fun onResponse(call:Call<List<User>>,response: Response<List<User>>){
                if(response.isSuccessful){
                    recyclerview.apply{
                        setHasFixedSize(true)
                        layoutManager = LinearLayoutManager(this@MainActivity2)
                        adapter = UserAdapter(response.body()!!)

                    }
                }
            }2
            override fun onFailure(call:Call<List<User>>,t:Throwable){
                Toast.makeText(this@MainActivity2,"${t.message}",Toast.LENGTH_SHORT).show()
            }

        })
*/

    }

fun post(view: View) {

    val request= ServiceBuilder.buildService(EndPoints::class.java)
    val call=request.postTest("teste")

    call.enqueue(object : Callback<OutputPost> {
        override fun onResponse(call:Call<OutputPost>,response: Response<OutputPost>){
            if(response.isSuccessful){
               val c: OutputPost = response.body()!!
                Toast.makeText(this@MainActivity2, c.id.toString() + "-" + c.title, Toast.LENGTH_SHORT).show()
                }
            }
        override fun onFailure(call:Call<OutputPost>,t:Throwable){
            Toast.makeText(this@MainActivity2,"${t.message}",Toast.LENGTH_SHORT).show()
        }

    })

}

}