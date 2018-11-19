package com.tonilopezmr.androidtesting.got.model

class GoTCharacter(val name: String,
                   val imageUrl: String,
                   val description: String,
                   val houseName: String) {

  override fun equals(o: Any?): Boolean {
    if (this === o) return true
    if (o == null || javaClass != o.javaClass) return false

    val that = o as GoTCharacter?

    if (name != that!!.name) return false
    if (if (imageUrl != null) imageUrl != that.imageUrl else that.imageUrl != null) return false
    if (description != that.description) return false
    return if (houseName != null) houseName == that.houseName else that.houseName == null
  }

  override fun hashCode(): Int {
    var result = name.hashCode()
    result = 31 * result + imageUrl.hashCode()
    result = 31 * result + description.hashCode()
    result = 31 * result + houseName.hashCode()
    return result
  }
}
