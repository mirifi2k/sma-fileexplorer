package ro.ac.upt.filenavigatordemo

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_file_explorer.*
import androidx.core.app.ActivityCompat
import android.content.pm.PackageManager
import android.os.Build
import android.widget.Button
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton


class FileExplorerActivity : AppCompatActivity() {

    private lateinit var fileExplorerViewModel : FileExplorerViewModel
    private val rvFiles: RecyclerView by lazy { findViewById<RecyclerView>(R.id.rcv_list_dir)}
    private val backButton: FloatingActionButton by lazy { findViewById<FloatingActionButton>(R.id.fab_back)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file_explorer)

        fileExplorerViewModel = ViewModelProviders.of(this).get(FileExplorerViewModel::class.java)

        requestStoragePermission()

        initWidgets()

        initViewModelObservers()

        initFromRootDirectory()
    }

    private fun initViewModelObservers() {
        // TODO("Observe currentPath's live data from view model and update txv_current_dir on changes")
        fileExplorerViewModel.currentPath.observe(this, Observer<FileEntry> { fileEntry ->
            txv_current_dir.text = fileEntry.path
        })

        // TODO("Observe listedFiles's live data from view model and update the recycler view adapter on changes")
        fileExplorerViewModel.listedFiles.observe(this, Observer<List<FileEntry>> { files ->

        })
    }

    private fun initWidgets() {
        // TODO("Initialize the layout manager and adapter of the recycler view widget")
        rvFiles.layoutManager = LinearLayoutManager(this)
        rvFiles.adapter = FilesRecyclerViewAdapter()

        // TODO("Initialize a click listener for back button such that once clicked, it would navigate one level up in current directory hierarchy")
        backButton.setOnClickListener {
            fileExplorerViewModel.onBack()
        }
    }

    private fun initFromRootDirectory() {
        val root = Environment.getExternalStorageDirectory()
        fileExplorerViewModel.openFile(FileEntry(root.absolutePath, true))
    }

    private fun requestStoragePermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            } else {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 1)
            }
        }
    }

}
