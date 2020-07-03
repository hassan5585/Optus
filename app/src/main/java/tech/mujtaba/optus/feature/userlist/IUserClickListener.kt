package tech.mujtaba.optus.feature.userlist

import tech.mujtaba.network.model.external.User

interface IUserClickListener {
    fun onUserClicked(user: User)
}