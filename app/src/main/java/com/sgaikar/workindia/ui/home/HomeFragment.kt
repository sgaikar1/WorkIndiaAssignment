package com.sgaikar.workindia.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sgaikar.workindia.R
import com.sgaikar.workindia.dbutilities.FoodItem
import com.sgaikar.workindia.ui.adapters.FoodListAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*


class HomeFragment : Fragment(), java.util.Observer {

    private var foodViewModel: HomeViewModel? = null
    var foodMenuObserver: Observer<List<FoodItem>?>? = null
    var isFoodUpdateInProgressObserver: Observer<Boolean>? = null
    private var foodListAdapter: FoodListAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        foodViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)

        val mLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(activity)
        foodList.layoutManager = mLayoutManager
        foodListAdapter = FoodListAdapter(ArrayList())
        foodList.adapter = foodListAdapter
        foodList.scheduleLayoutAnimation()
    }


    companion object {
        fun newInstance(): HomeFragment {
            val fragment = HomeFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    override fun update(observable: Observable?, o: Any?) {
        val intent = o as Intent?
        if (intent != null && intent.action != null) {
            foodListAdapter?.notifyDataSetChanged()
        }
    }

    override fun onDestroy() {
        foodViewModel!!.foodDetailsMutableLiveData.removeObserver(foodMenuObserver!!)
        foodViewModel!!.isFoodUpdateInProgress.removeObserver(isFoodUpdateInProgressObserver!!)
        ObservableObject.getInstance().deleteObserver(this)
        super.onDestroy()
    }

    private fun showProgress(show: Boolean, showList: Boolean) {
        foodList.visibility = if (showList) View.VISIBLE else View.GONE
        t_loading.visibility = if (show) View.VISIBLE else View.GONE
        i_loading.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun subscribeToFoodObserver() {
        if (!foodViewModel!!.foodDetailsMutableLiveData.hasObservers()) {
            foodViewModel!!.foodDetailsMutableLiveData.observe(viewLifecycleOwner, foodMenuObserver!!)
        }
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        foodMenuObserver = Observer {
            if (it != null) {
                foodListAdapter!!.setData(it)
                foodListAdapter!!.notifyDataSetChanged()
            } else {
                Log.e("Food details", "null")
            }
        }
        isFoodUpdateInProgressObserver = Observer{ aBoolean ->
            if (aBoolean != null && !aBoolean) {
                showProgress(false, true)
                subscribeToFoodObserver()
            } else {
                showProgress(true, false)
            }
        }


        foodViewModel!!.isFoodUpdateInProgress.observe(viewLifecycleOwner, isFoodUpdateInProgressObserver!!)
        ObservableObject.getInstance().addObserver(this)
    }

}
