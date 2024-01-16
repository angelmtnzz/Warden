package vistas

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ListView
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import java.clases.R
import java.clases.databinding.FragmentMainPageBinding


class SearchFragment : Fragment(R.layout.fragment_search), SearchView.OnQueryTextListener {

    private lateinit var binding : FragmentMainPageBinding
    private lateinit var searchView: SearchView
    private lateinit var listView: ListView
    private lateinit var fragmentContainer: LinearLayout


    override fun onCreate(savedInstanceState : Bundle?){
        super.onCreate(savedInstanceState)
        binding = FragmentMainPageBinding.inflate(layoutInflater)

    }

    override fun onCreateView(inflater:LayoutInflater, container:ViewGroup?, savedInstancesState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchView = view.findViewById(R.id.searchBar)
        fragmentContainer = view.findViewById(R.id.fragmentContainer)

        searchView.setOnQueryTextListener(this)

        searchView.addOnLayoutChangeListener { _, _, _, _, bottom, _, _, _, oldBottom ->
            if (bottom < oldBottom) {
                fragmentContainer.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.color6G
                    )
                )
            } else {
                fragmentContainer.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.color7G
                    )
                )
            }
        }

        val searchManager = requireActivity().getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView.setSearchableInfo(searchManager.getSearchableInfo(requireActivity().componentName))
    }
    //START code for search


    override fun onQueryTextSubmit(query: String?): Boolean {
        // Handle search query submission here
        searchView.clearFocus()
        return false

    }

    override fun onQueryTextChange(newText: String?): Boolean {
        // Handle search query text changes here
        return false
    }

}
