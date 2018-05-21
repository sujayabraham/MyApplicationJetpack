package com.demo.jetpackdemo

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import com.demo.jetpackdemo.R
import kotlinx.android.synthetic.main.timer_fragment.*

class TimerFragment : Fragment() {

    private val TAG = "TimerFragment"
    private lateinit var mViewModel: TimerViewModel

    companion object {
        fun newInstance() = TimerFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.timer_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel = ViewModelProviders.of(this).get(TimerViewModel::class.java)
        subscribePrintTime()
        view?.findViewById<Button>(R.id.btn_next_timer)?.setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.action_timerPage_to_mainPage, null)
        )
    }

    private fun subscribePrintTime() {
        val elapsedTimeObserver = Observer<Long> { time ->
            val newText = "Seconds :"+time
            tv_timer.text = newText
            Log.e(TAG, newText)
        }
        mViewModel.getElapsedTime().observe(this, elapsedTimeObserver)
    }

}
