package com.tonilopezmr.androidtesting.got.model.validator

import java.util.ArrayList

/**
 * Clase contenedora de campos erroneos para manejar los datos de una validación.
 *
 * Y devuelve si es valido o no.
 */
class Validator(val objectName: String) {

  internal var wrongFieldList: MutableList<WrongField> = ArrayList()

  /**
   * Si no hay campos erroneos, significa que no hay errores.
   *
   * @return si es valido el conjunto de campos
   */
  val isValid: Boolean
    get() = wrongFieldList.isEmpty()

  fun addWrong(fieldKey: String, errorKey: String) {
    wrongFieldList.add(WrongField(fieldKey, errorKey))
  }

  inner class WrongField(val fieldKey: String, val errorKey: String)
}
