package zw.co.nm.moviedb.util

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import zw.co.nm.moviedb.R
import zw.co.nm.moviedb.presentation.lists.ListMutation
import zw.co.nm.moviedb.presentation.lists.ListsViewModel

object AddToListPicker {

    fun show(activity: AppCompatActivity, movieId: Int) {
        if (!ConfigStore.isLoggedIn(activity)) {
            Toast.makeText(activity, R.string.login_required_lists, Toast.LENGTH_SHORT).show()
            return
        }

        val viewModel = ViewModelProvider(activity)[ListsViewModel::class.java]
        var handledLists = false

        viewModel.accountLists.observe(activity) { response ->
            if (handledLists) return@observe
            if (!response.isSuccessful || response.data == null) {
                Toast.makeText(activity, R.string.lists_load_error, Toast.LENGTH_SHORT).show()
                return@observe
            }
            handledLists = true
            val lists = response.body.results
            if (lists.isEmpty()) {
                Toast.makeText(activity, R.string.no_lists_yet_create, Toast.LENGTH_SHORT).show()
                return@observe
            }
            val names = lists.map { it.name }.toTypedArray()
            MaterialAlertDialogBuilder(activity)
                .setTitle(R.string.add_to_list)
                .setItems(names) { _, which ->
                    viewModel.addMovieToList(lists[which].id, movieId)
                }
                .setNegativeButton(R.string.cancel, null)
                .show()
        }

        viewModel.mutation.observe(activity) { mutation ->
            if (mutation.type != ListMutation.Type.ADD) return@observe
            if (mutation.success) {
                Toast.makeText(activity, R.string.added_to_list, Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(activity, R.string.add_to_list_error, Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.page = 1
        viewModel.getAccountLists()
    }
}
