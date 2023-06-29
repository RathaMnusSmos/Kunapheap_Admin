package com.rathalove.kunapheapadmin.Fragment

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.application.isradeleon.notify.Notify
import com.rathalove.kunapheapadmin.API.RetrofitClientInstance
import com.rathalove.kunapheapadmin.API.RetrofitInterface
import com.rathalove.kunapheapadmin.ActionListener.OnclickListener
import com.rathalove.kunapheapadmin.Adapter.ItemAdapter
import com.rathalove.kunapheapadmin.DataModel.AllItemData.AllItemData
import com.rathalove.kunapheapadmin.DataModel.AllProductData.AllProductData
import com.rathalove.kunapheapadmin.R
import com.rathalove.kunapheapadmin.databinding.ProductlistFragmentBinding
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.LinkedList

class ProductList: Fragment(), OnclickListener {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var root: ViewGroup = inflater.inflate(R.layout.productlist_fragment, container, false) as ViewGroup
//        return super.onCreateView(inflater, container, savedInstanceState)
        return root
    }
    private var allItemData: LinkedList<AllItemData> = LinkedList()
    private lateinit var allItemRec: RecyclerView
    private lateinit var fragmentBinding :ProductlistFragmentBinding
    private var allProductData: LinkedList<AllProductData> = LinkedList()
    private var allProData : LinkedList<AllProductData>? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var binding = ProductlistFragmentBinding.bind(view)
        fragmentBinding = binding

        //==========================All Item RecyclerView ================================
        val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        allItemRec = binding.recycleItem
        allItemRec.layoutManager = linearLayoutManager
        getAllItem()

        //==========================Notification ================================







    }

    private fun getAllProductData() {
        var retrofit = RetrofitClientInstance.getRetrofitInstance().create(RetrofitInterface::class.java)
        retrofit.getProductData().enqueue(object : Callback<ArrayList<AllProductData>>{
            override fun onResponse(
                call: Call<ArrayList<AllProductData>>,
                response: Response<ArrayList<AllProductData>>
            ) {
                if (response.isSuccessful){
                    for (i in response.body()!!){
                        allProductData.add(i)
                    }
                    allProData = allProductData
                }
                else{
                    Log.d("errorRes", "something went wrong!!")
                }
            }

            override fun onFailure(call: Call<ArrayList<AllProductData>>, t: Throwable) {
                Log.d("fail", "${t.message}")
            }

        })
    }


    private fun getAllItem(){
        var retrofit = RetrofitClientInstance.getRetrofitInstance().create(RetrofitInterface::class.java)
        retrofit.getItemData().enqueue(object : Callback<ArrayList<AllItemData>>{
            override fun onResponse(
                call: Call<ArrayList<AllItemData>>,
                response: Response<ArrayList<AllItemData>>
            ) {
                if (response.isSuccessful){
                    Log.d("allItem","Data = ${response.body()}")
                    for (i in response.body()!!){
                        allItemData.add(i)
                    }
                    for (i in allItemData.indices){
                        for(j in i + 1 until allItemData.size){
                            if (allItemData[i].itemAmount!! >= allItemData[j].itemAmount!!){
                                var tmp = allItemData[i]
                                allItemData[i] = allItemData[j]
                                allItemData[j] = tmp
                            }
                        }
                    }
                    var itemListAdapter = ItemAdapter(requireContext(), allItemData, this@ProductList)
                    allItemRec.adapter = itemListAdapter

                    //check and push notification
//                    for(i in allItemData){
//                        if (i.itemAmount!!.toInt() <= 3){
//                            Notify.build(requireContext().getApplicationContext())
//
//                                /*
//                                 * Set notification title and content
//                                 * */
//                                .setTitle("Jill Zhao")
//                                .setContent("Hi! So I meet you today?")
//
//                                /*
//                                 * Set small icon from drawable resource
//                                 * */
//                                .setSmallIcon(R.drawable.baseline_notifications_none_24)
//                                .setColor(R.color.colorAccent)
//
//                                /*
//                                 * Set notification large icon from drawable resource or URL
//                                 * (make sure you added INTERNET permission to AndroidManifest.xml)
//                                 * */
//                                .setLargeIcon("https://images.pexels.com/photos/139829/pexels-photo-139829.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=150&w=440")
//
//                                /*
//                                 * Circular large icon
//                                 * */
//                                .largeCircularIcon()
//
//                                /*
//                                 * Add a picture from drawable resource or URL
//                                 * (INTERNET permission needs to be added to AndroidManifest.xml)
//                                 * */
//                                .setPicture("https://images.pexels.com/photos/1058683/pexels-photo-1058683.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940")
//
//                                .show(); // Show notification
//
//                        }
//                    }

                }
                else{
                    Log.d("allItem","Data = error")
                }
            }

            override fun onFailure(call: Call<ArrayList<AllItemData>>, t: Throwable) {
                Log.d("allProductErr", "error = ${t.message}")
            }

        })
    }

//    private fun getAllProductData(): LinkedList<AllProductData>{
//        var retrofit = RetrofitClientInstance.getRetrofitInstance().create(RetrofitInterface::class.java)
//        retrofit.getProductData().enqueue(object : Callback<ArrayList<AllProductData>>{
//            override fun onResponse(
//                call: Call<ArrayList<AllProductData>>,
//                response: Response<ArrayList<AllProductData>>
//            ) {
//               if (response.isSuccessful){
//                   for (i in response.body()!!){
//                       allProductData.add(i)
//                   }
//                   Log.d("product_data", " product data = $allProductData")
//
//               }
//
//                else{
//                    Log.d("error", "Something went wrong ")
//               }
//
//                Log.d("product_data2222", " product data = $allProductData")
//            }
//
//            override fun onFailure(call: Call<ArrayList<AllProductData>>, t: Throwable) {
//                Log.d("fail", "${t.message}")
//            }
//
//        })
//
//
//        return allProductData
//
//    }



    override fun noItemClick(pos: Int, data: AllItemData) {
        Toast.makeText(context, "item ${pos+1} clicked", Toast.LENGTH_SHORT).show()
        //show detail on specific
        val dialogBinding = layoutInflater.inflate(R.layout.dailog_alertitem_layout, null)
        val myDialog = Dialog(this.requireContext())
        myDialog.setContentView(dialogBinding)
        myDialog.setCancelable(true)
        myDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        myDialog.show()

        var color = data.ColorOnSide!!.color!!.colorName.toString()
        var name = data.product!!.productName.toString()
        var size = data.ColorOnSide!!.size!!.sizeName.toString()
        var price = data.product!!.productPrice.toString()
        var productId = data.productId.toString()
        var itemId = data.itemId.toString()

        var pro_name = dialogBinding.findViewById<TextView>(R.id.productName)
        pro_name.text = name
        var pro_price = dialogBinding.findViewById<TextView>(R.id.productPrice)
        pro_price.text = price
        var pro_color = dialogBinding.findViewById<TextView>(R.id.productColor)
        pro_color.setBackgroundColor(Color.parseColor(color))
        var pro_size = dialogBinding.findViewById<TextView>(R.id.size)
        pro_size.text = "Size:  ${size}"

        var imageVw = dialogBinding.findViewById<ImageView>(R.id.product_image_detail)

        //set img
                    //now we should get data from product for compare

        var retrofit = RetrofitClientInstance.getRetrofitInstance().create(RetrofitInterface::class.java)
        retrofit.getProductData().enqueue(object : Callback<ArrayList<AllProductData>>{

            override fun onResponse(
                call: Call<ArrayList<AllProductData>>,
                response: Response<ArrayList<AllProductData>>
            ) {
                if (response.isSuccessful){
                    Log.d("data", response.body().toString())
                    for (i in response.body()!!){
                        allProductData.add(i)
                    }
                    for(i in allProductData.indices){
                        var proId = allProductData[i].productId
                        Log.d("product_instane", "$proId = $productId")
                        if(allProductData[i].productId.toString().equals(productId)){
                            var imgUrl = allProductData[i].item[0].image[0].imageLink.toString()
                            Picasso.get().load(imgUrl).into(imageVw)
                            Log.d("product_instane", "dernv ng ot ttt")
                        }

                    }
                }
                else{
                    Log.d("errorRes", "something went wrong!!")
                }
            }
            override fun onFailure(call: Call<ArrayList<AllProductData>>, t: Throwable) {
                Log.d("fail", "${t.message}")
            }

        })

        var btn = dialogBinding.findViewById<Button>(R.id.btn_ok)
        btn.setOnClickListener{
            myDialog.dismiss()
        }


    }


}