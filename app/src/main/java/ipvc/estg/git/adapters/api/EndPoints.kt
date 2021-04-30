package ipvc.estg.git.adapters.api

import retrofit2.Call
import retrofit2.http.*


interface EndPoints {

    @GET("/users/")
    fun getUsers(): Call<List<User>>

    @GET("/users{id}/")
    fun getUsersById(@Path("id") id: Int): Call<User>


    @FormUrlEncoded
    @POST("/posts/")
    fun postTest(@Field("title") first: String?): Call<OutputPost>



}