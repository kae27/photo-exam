package com.cp.kpexam.features.photo

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.cp.kpexam.R
import com.cp.kpexam.common.Failure
import com.cp.kpexam.common.ItemOffsetDecoration
import kotlinx.android.synthetic.main.fragment_photo.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class PhotoFragment : Fragment() {

    companion object{
        fun newInstance() = PhotoFragment()
    }

    private val viewModel: PhotoViewModel by sharedViewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_photo, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initRecyclerView()
        observerViewModel()
    }

    private fun observerViewModel() {
        viewModel.getListPhoto()

        viewModel.loadingProgressDialog.observe(viewLifecycleOwner, Observer { showProgress ->
            if(showProgress){
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.GONE
            }

        })

        viewModel.photosLiveData.observe(viewLifecycleOwner, Observer { photos ->
            recyclerView.visibility = View.VISIBLE
            recyclerView.adapter = PhotoAdapter(photos) {
                Intent().apply {
                    setClass(requireContext(), DetailActivity::class.java)
                    putExtra("imageUrl", it.imageUrl)
                    putExtra("title", it.title)
                    startActivity(this)
                }
            }
        })

        viewModel.failure.observe(viewLifecycleOwner, Observer {
            val failure = it as Failure.Error
            val builder = AlertDialog.Builder(context)
            builder.apply {
                setMessage(failure.description)
                setCancelable(false)
                setPositiveButton("retry"){ dialogInterface: DialogInterface, i: Int ->
                    dialogInterface.dismiss()
                    viewModel.getListPhoto()
                }
                setNegativeButton("cancel") { dialogInterface: DialogInterface, i: Int ->
                    dialogInterface.dismiss()
                }
                show()
            }
        })
    }

    private fun initRecyclerView() {
        val gridLayoutManager = GridLayoutManager(requireContext(), 2)
        recyclerView.layoutManager = gridLayoutManager
        recyclerView.addItemDecoration(
            ItemOffsetDecoration(
                requireContext(),
                R.dimen.default_margin_8,
                R.dimen.default_margin_8,
                R.dimen.default_margin_8,
                R.dimen.default_margin_8,
                R.dimen.default_margin_8,
                R.dimen.default_margin_8
            )
        )
    }


}