package ro.ac.upt.filenavigatordemo

import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

class PlatformFileExplorer {

    fun listFiles(fileEntry: FileEntry): List<FileEntry> {
        // TODO("Return the files contained by the current directory (both plain files and directories)")
        var filesList: MutableList<FileEntry> = mutableListOf()

        File(fileEntry.path).walk().forEach {
            filesList.add(FileEntry(it.absolutePath, Files.isDirectory(it.toPath())))
        }

        return filesList
    }

    fun hasParent(fileEntry: FileEntry): Boolean {
        // TODO("Check if current file entry has a parent directory")
        return (Paths.get(fileEntry.path).getParent() != null)
    }

    fun getParent(fileEntry: FileEntry): FileEntry {
        // TODO("Return the parent of current file entry")
        return FileEntry(Paths.get(fileEntry.path).getParent().toAbsolutePath().toString(), true)
    }

}
