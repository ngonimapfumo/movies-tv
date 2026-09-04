package zw.co.nm.moviedb.presentation.lists

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import zw.co.nm.moviedb.R
import zw.co.nm.moviedb.databinding.ActivityCustomListsBinding
import zw.co.nm.moviedb.databinding.DialogCreateListBinding
import zw.co.nm.moviedb.util.Constants
import zw.co.nm.moviedb.util.GeneralUtil.actionSnack

class CustomListsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCustomListsBinding
    private lateinit var viewModel: ListsViewModel
    private lateinit var adapter: CustomListsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomListsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.custom_lists)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { view, insets ->
            val innerPadding = insets.getInsets(
                WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.displayCutout()
            )
            view.setPadding(
                innerPadding.left,
                innerPadding.top,
                innerPadding.right,
                innerPadding.bottom
            )
            insets
        }

        viewModel = ViewModelProvider(this)[ListsViewModel::class.java]
        adapter = CustomListsAdapter(
            onClick = { list ->
                startActivity(
                    Intent(this, ListDetailActivity::class.java).apply {
                        putExtra(Constants.EXTRA_LIST_ID, list.id)
                        putExtra(Constants.EXTRA_LIST_NAME, list.name)
                    }
                )
            },
            onLongClick = { list -> confirmDelete(list.id, list.name) }
        )
        binding.recyclerView.adapter = adapter
        binding.createListFab.setOnClickListener { showCreateDialog() }

        viewModel.accountLists.observe(this) { response ->
            binding.progressBar.visibility = GONE
            when (response.data) {
                null -> actionSnack(binding.root, getString(R.string.lists_load_error), "Retry") {
                    loadLists()
                }

                else -> {
                    adapter.submitList(response.body.results)
                    if (response.body.results.isEmpty()) {
                        Toast.makeText(this, R.string.lists_empty, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        viewModel.createResult.observe(this) { response ->
            if (response.isSuccessful && response.body.success) {
                Toast.makeText(this, R.string.list_created, Toast.LENGTH_SHORT).show()
                loadLists()
            } else {
                Toast.makeText(this, R.string.list_create_error, Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.mutation.observe(this) { mutation ->
            if (mutation.success && mutation.type == ListMutation.Type.DELETE) {
                Toast.makeText(this, R.string.list_deleted, Toast.LENGTH_SHORT).show()
                loadLists()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        loadLists()
    }

    private fun loadLists() {
        binding.progressBar.visibility = VISIBLE
        viewModel.page = 1
        viewModel.getAccountLists()
    }

    private fun showCreateDialog() {
        val dialogBinding = DialogCreateListBinding.inflate(LayoutInflater.from(this))
        MaterialAlertDialogBuilder(this)
            .setTitle(R.string.create_list_title)
            .setView(dialogBinding.root)
            .setPositiveButton(R.string.create_list) { _, _ ->
                val name = dialogBinding.listNameInput.text?.toString()?.trim().orEmpty()
                val description =
                    dialogBinding.listDescriptionInput.text?.toString()?.trim().orEmpty()
                if (name.isBlank()) {
                    Toast.makeText(this, R.string.list_name_hint, Toast.LENGTH_SHORT).show()
                } else {
                    viewModel.createList(name, description)
                }
            }
            .setNegativeButton(R.string.cancel, null)
            .show()
    }

    private fun confirmDelete(listId: Int, name: String) {
        MaterialAlertDialogBuilder(this)
            .setTitle(R.string.list_delete)
            .setMessage(getString(R.string.list_delete_confirm, name))
            .setPositiveButton(R.string.remove) { _, _ -> viewModel.deleteList(listId) }
            .setNegativeButton(R.string.cancel, null)
            .show()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return super.onSupportNavigateUp()
    }
}
