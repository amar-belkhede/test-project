package com.example.broadcastsender.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.broadcastsender.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        binding.button.setOnClickListener {
            sendBroadcastToOtherApp()
            Toast.makeText(context, "sending",Toast.LENGTH_SHORT).show()
        }


        return root
    }


    private fun sendBroadcastToOtherApp() {
        var intent = Intent("com.example.broadcastreceiver.EXAMPLE_ACTION")
        intent.putExtra("com.example.broadcastreceiver.EXTRA_TEXT", "bROADCAST rECEIVED")
        activity?.sendBroadcast(intent)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}