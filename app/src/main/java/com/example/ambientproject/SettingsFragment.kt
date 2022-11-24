package com.example.ambientproject

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ambientproject.databinding.RecyclerMainBinding
import com.example.ambientproject.databinding.SettingsRecyclerMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class SettingsFragment: Fragment() {
    companion object {
        val TAG: String = SettingsFragment::class.java.simpleName
    }

    private var _binding: SettingsRecyclerMainBinding? = null
    private val binding get() = _binding!!
    private val db = Firebase.firestore

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SettingsRecyclerMainBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val user = FirebaseAuth.getInstance().currentUser
        Log.i("XXX", "user: $user")
        if (user == null) {
            binding.userName.text = ""
        }


        binding.logoutButton.setOnClickListener {
            binding.userName.text = ""
            FirebaseAuth.getInstance().signOut()
        }

        // Add a new document with a generated ID
//        db.collection("users")
//            .add(usertest)
//            .addOnSuccessListener { documentReference ->
//                Log.i("XXX", "DocumentSnapshot added with ID: ${documentReference.id}")
//            }
//            .addOnFailureListener { e ->
//                Log.i("XXX", "Error adding document", e)
//            }
//            .addOnCompleteListener { test ->
//                Log.i("XXX", "completeListener: $test")
//            }
//        Log.i("XXX", "reaches here?!")
        val docRef = db.collection("users").document("yQ99kBPBO6bJo69zPawwuNeBqcN2")
        docRef
            .get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    binding.userName.text = "${document.getString("name")}"
                } else {
                    Log.i("XXX", "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.i("XXX", "get failed with ", exception)
            }


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}