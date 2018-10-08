package me.mahfud.example.response.vo

data class BankDetail(var id: Long,
                      var name: String,
                      var number: String,
                      var listUser: List<User>) {

    constructor() : this(1, "", "", listOf())

    data class User(var id: Long,
                    var name: String) {

        constructor() : this(1, "")
    }
}
