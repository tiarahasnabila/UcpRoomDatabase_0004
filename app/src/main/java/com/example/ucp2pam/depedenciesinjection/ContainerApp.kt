package com.example.ucp2pam.depedenciesinjection

import com.example.ucp2pam.repository.RepositoryDokter
import com.example.ucp2pam.repository.RepositoryJadwal

interface InterfacesContainerApp {
    val repositoryDokter : RepositoryDokter
    val repositoryJadwal : RepositoryJadwal
}