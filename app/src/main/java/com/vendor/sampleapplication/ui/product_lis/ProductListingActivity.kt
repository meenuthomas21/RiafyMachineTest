package com.vendor.sample

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import com.vendor.sampleapplication.ui.product_lis.ProductListAdapter


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import com.vendor.sampleapplication.data.model.DatalistModel
import com.vendor.sampleapplication.databinding.ActivityProductListingBinding
import eexpo_.appn.alnaqel.data.viewModel.MainViewModel
import java.util.ArrayList
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.textfield.TextInputEditText
import com.vendor.sampleapplication.R
import com.vendor.sampleapplication.data.api.*
import com.vendor.sampleapplication.data.model.UploadParams

class ProductListingActivity : AppCompatActivity() {
    lateinit var viewModel: MainViewModel
    lateinit var binding: ActivityProductListingBinding
    private var dataList: ArrayList<DatalistModel>? = ArrayList()
    var loadingDialog: Dialog? = null
    val params=UploadParams()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelperNew(RetrofitBuilder.apiService))
        ).get(MainViewModel::class.java)
        binding = ActivityProductListingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recyproducts.adapter= ProductListAdapter(this,dataList)
        loadingDialog=defaultLoadingDialog(this)
        getData()
        binding.upload.setOnClickListener {
            showDialog()
        }
      /*  vm.modelProduct._categories.observe(this, Observer {
            Log.e("jj","kkk"+ vm.modelProduct._categories.value!!.size)
        })*/
    }


    private fun getData() {
        dataList?.clear()
        viewModel.getData().observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        if(loadingDialog!=null) {
                            loadingDialog?.dismiss()
                            loadingDialog=null
                        }
                        if(it.data?.size!! >0) {
                            binding.recyproducts.visibility=View.VISIBLE
                            binding.noData.visibility=View.GONE
                            it.data?.let { it1 -> dataList?.addAll(it1) }
                            binding.recyproducts.adapter?.notifyDataSetChanged()
                        }else{
                            binding.recyproducts.visibility=View.GONE
                            binding.noData.visibility=View.VISIBLE

                        }
                    }
                    Status.ERROR -> {
                        if(loadingDialog!=null) {
                            loadingDialog?.dismiss()
                            loadingDialog=null

                        }
                        binding.recyproducts.visibility=View.GONE
                        binding.noData.visibility=View.VISIBLE
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {

                    }
                }
            }
        })

    }

    private fun uploadData() {

        viewModel.uploadData(params).observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        if(loadingDialog!=null) {
                            loadingDialog?.dismiss()
                            loadingDialog=null
                        }
                        Toast.makeText(this,it.data.toString(),Toast.LENGTH_SHORT).show()
                    }
                    Status.ERROR -> {
                        if(loadingDialog!=null) {
                            loadingDialog?.dismiss()
                            loadingDialog=null

                        }
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {

                    }
                }
            }
        })

    }
    private fun showDialog() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.collect_data)
        val continueTv = dialog.findViewById(R.id.upload) as TextView
        val title = dialog.findViewById(R.id.title) as TextInputEditText
        val desc = dialog.findViewById(R.id.desc) as TextInputEditText


        val close = dialog.findViewById(R.id.close) as ImageView

        close.setOnClickListener {
            dialog.dismiss()
        }
        continueTv.setOnClickListener {
            //getCityApi()
            if(!title.text.toString().trim().equals("")){
                if(!desc.text.toString().trim().equals("")){
                    params.title=title.text.toString().trim()
                    params.desc=desc.text.toString().trim()
                    dialog.dismiss()
                    loadingDialog=defaultLoadingDialog(this)

                    uploadData()
                }else{
                    Toast.makeText(this,"Please enter description",Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this,"Please enter title",Toast.LENGTH_SHORT).show()
            }

         }
        dialog.show()

    }


    fun defaultLoadingDialog(context: Context): Dialog? {
        var dialog: Dialog? = Dialog(context, android.R.style.Theme_Black)
        try {
            dialog?.setCancelable(true)
            dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            val mInflater: LayoutInflater = LayoutInflater.from(context)
            val mView: View = mInflater.inflate(R.layout.loading_dialogue, null)
            dialog?.setContentView(mView)
            dialog?.show()

        } catch (e: java.lang.Exception) {
            Log.e("API", "defaultLoadingDialog: ", e)
            dialog = null
        }
        return dialog
    }

}