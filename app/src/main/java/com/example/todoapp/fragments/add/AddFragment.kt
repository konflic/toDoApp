package com.example.todoapp.fragments.add

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.todoapp.R
import com.example.todoapp.data.Converter
import com.example.todoapp.data.ToDoViewModel
import com.example.todoapp.data.models.ToDoData
import com.example.todoapp.databinding.FragmentAddBinding


class AddFragment : Fragment() {

    private val mToDoViewModel: ToDoViewModel by viewModels()

    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddBinding.inflate(layoutInflater, container, false)
        // Set menu
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_add) {
            insertDataIntoDb()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun insertDataIntoDb() {
        val mTitle = binding.newToDoTitle.text.toString()
        val mPriority = binding.newToDoPriority.selectedItem.toString()
        val mDescription = binding.newToDoDescription.text.toString()

        if (verifyDataFromUser(mTitle, mDescription)) {
            val newData = ToDoData(
                0,
                mTitle,
                Converter().fromString(mPriority),
                mDescription
            )
            mToDoViewModel.insertData(newData)
            Toast.makeText(requireContext(), "SUCCESS!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Input is empty!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun verifyDataFromUser(title: String, description: String): Boolean {
        return !(title.isEmpty() || description.isEmpty())
    }
}