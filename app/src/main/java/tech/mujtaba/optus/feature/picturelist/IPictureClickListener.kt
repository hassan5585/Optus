package tech.mujtaba.optus.feature.picturelist

import tech.mujtaba.network.model.external.UserAlbum

interface IPictureClickListener {
    fun onPictureClicked(picture: UserAlbum)
}