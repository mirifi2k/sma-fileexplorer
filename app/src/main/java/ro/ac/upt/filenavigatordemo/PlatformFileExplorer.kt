package ro.ac.upt.filenavigatordemo

import java.io.File

class PlatformFileExplorer {

    fun listFiles(fileEntry: FileEntry): List<FileEntry> {
        // TODO("Return the files contained by the current directory (both plain files and directories)")
        var filesList: List<FileEntry> = emptyList()
        
        File(FileEntry.path).walk().forEach {
            filesList.add(FileEntry(it, Files.isDirectory(it)))
        }

        return filesList
    }

    fun hasParent(fileEntry: FileEntry): Boolean {
        // TODO("Check if current file entry has a parent directory")
        return (File(FileEntry.path).walkBottomUp() != null)
    }

    fun getParent(fileEntry: FileEntry): FileEntry {
        // TODO("Return the parent of current file entry")
        return FileEntry(File(FileEntry.path).walkBottomUp().toString(), true)
    }

}