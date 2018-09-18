package me.mahfud.example.model

import javax.persistence.*

@Entity
@Table(name="apps")
data class App(@Id
               @GeneratedValue(strategy = GenerationType.AUTO) val id: Int? = null,
               @Column(name="app_name") val appName: String? = null,
               @Column(name="app_key") val appKey: String? = null,
               @Column(name="app_secret") val appSecret: String? = null) {
}