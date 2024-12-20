package com.example.ucp2pam.depedenciesinjection

import android.content.Context
import com.example.ucp2pam.database.jadwaldrDatabase
import com.example.ucp2pam.repository.LocalRepositoryDokter
import com.example.ucp2pam.repository.LocalRepositoryJadwal
import com.example.ucp2pam.repository.RepositoryDokter
import com.example.ucp2pam.repository.RepositoryJadwal

interface InterfacesContainerApp {
    val repositoryDokter : RepositoryDokter
    val repositoryJadwal : RepositoryJadwal
}

class ContainerApp(private val context:Context):InterfacesContainerApp{
    override val repositoryDokter by lazy {
        LocalRepositoryDokter(jadwaldrDatabase.getDatabase(context).dokterDao())
    }
    override val repositoryJadwal by lazy {
        LocalRepositoryJadwal(jadwaldrDatabase.getDatabase(context).jadwalDao())
    }
}