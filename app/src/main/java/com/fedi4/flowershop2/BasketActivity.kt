package com.fedi4.flowershop2


import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AbsListView
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.fedi4.flowershop2.db.BasketEntity
import com.fedi4.flowershop2.db.BasketViewModelFactory


class BasketActivity : AppCompatActivity(){

    var basket: HashMap<Int, Int> = HashMap<Int, Int>()
    var basketStr: MutableList<String> = MutableList(0, {""});

    var viewModel: BasketViewModel? = null



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_basket)


        var modelFactory: BasketViewModelFactory = BasketViewModelFactory(application)
        viewModel = ViewModelProvider(this, modelFactory).get(BasketViewModel::class.java)


        val listView = findViewById<ListView?>(R.id.listView)


// определяем строковый массив



// используем адаптер данных
        val adapter = ArrayAdapter<String?>(
            this,
            R.layout.basket_item, basketStr
        )

        listView.setAdapter(adapter)


        val buttonBack = findViewById<Button>(R.id.buttonBack)
        buttonBack.setOnClickListener {
            finish()
        }



        viewModel?.getData()?.observe(this) {
            Log.d("MainActivity", "Data changed: $it")
            basket = generateBasket(it)


            generate_str_basket()


            for (i in basketStr.indices) {

                Log.d("BasketActivity", "added entry to view: ${basketStr[i]}")
            }
            adapter.notifyDataSetChanged()


        }
        viewModel?.getData()


        //lvMain.setOnItemClickListener(object : OnItemClickListener {
        //    override fun onItemClick(
        //        parent: AdapterView<*>?, view: View?,
        //        position: Int, id: Long
        //    ) {
        //        Log.d(
        //            LOG_TAG, ("itemClick: position = " + position + ", id = "
        //                    + id)
        //        )
        //    }
        //})

        //lvMain.setOnItemSelectedListener(object : OnItemSelectedListener() {
        //    fun onItemSelected(
        //        parent: AdapterView<*>?, view: View?,
        //        position: Int, id: Long
        //    ) {
        //        Log.d(
        //            LOG_TAG, ("itemSelect: position = " + position + ", id = "
        //                    + id)
        //        )
        //    }

        //    fun onNothingSelected(parent: AdapterView<*>?) {
        //        Log.d(LOG_TAG, "itemSelect: nothing")
        //    }
        //})

        //lvMain.setOnScrollListener(object : OnScrollListener() {
        //    fun onScrollStateChanged(view: AbsListView?, scrollState: Int) {
        //        Log.d(LOG_TAG, "scrollState = " + scrollState)
        //    }

        //    fun onScroll(
        //        view: AbsListView?, firstVisibleItem: Int,
        //        visibleItemCount: Int, totalItemCount: Int
        //    ) {
        //        Log.d(
        //            LOG_TAG, ("scroll: firstVisibleItem = " + firstVisibleItem
        //                    + ", visibleItemCount" + visibleItemCount
        //                    + ", totalItemCount" + totalItemCount)
        //        )
        //    }
        //})
    }

    fun generateBasket(data: List<BasketEntity>) : HashMap<Int, Int> {
        var basket: HashMap<Int, Int> = HashMap()


        for (entry in data) {
            basket.put(entry.product_id, entry.count)
            Log.d("BasketActivity", "added entry to view: $entry")
        }


        return basket
    }

    fun generate_str_basket(){

        basket.forEach { (key, value) ->
            var product: Product = Products.getProductByIndex(key) as Product
            basketStr.add(product.name + " - " + value + "pcs - " + product.price*value + "$\n")
        }


    }

    //fun getAllBasketEntries(basketRepository: BasketRepository): List<BasketEntry> {
    //    var entries: List<BasketEntry> = listOf()
    //    lifecycleScope.launch {
    //        entries = basketRepository.getAllBasketEntries()
    //        Log.d("BasketActivity", "all entries: $entries")
    //    }
    //    Log.d("BasketActivity", "returned entries: $entries")
    //    return entries
//
    //}
}