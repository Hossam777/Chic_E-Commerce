package com.app.chic_ecommerce.common.data.entities

class SliderItemModel(var image: Int, var imageURL: String, var caption: String, var onClick: () -> Unit) {
    constructor(image: Int, caption: String): this(image, "", caption, {}){

    }
    constructor(image: String, caption: String): this(0, image, caption, {}){

    }
    constructor(image: Int, caption: String, onClick: () -> Unit): this(image, "", caption, onClick){

    }
}