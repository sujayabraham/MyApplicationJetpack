package com.demo.jetpackdemo

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.Navigation

class TextViewFragment : Fragment() {

    companion object {
        fun newInstance() = TextViewFragment()
    }

    private lateinit var viewModel: TextViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.start_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TextViewModel::class.java)

        val messageTv: TextView = view!!.findViewById(R.id.message)
        viewModel.data.observe(this, Observer { text -> text?.let { messageTv.setText(viewModel.data.value) } })

        view?.findViewById<Button>(R.id.btn_next_text)?.setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.action_textPage_to_timerPage, null)
        )
    }

}
