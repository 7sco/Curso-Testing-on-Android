package com.tonilopezmr.androidtesting.got.model.validator

import com.tonilopezmr.androidtesting.got.model.GoTCharacter

class CharacterValidator {

  private var validator: Validator? = null

  /**
   * No es valido:
   * - Vacio
   * - Solo espacios
   * - Solo en mayusculas
   *
   * @param goTCharacter
   * @return [Validator] con el resultado de la validación
   */
  fun valid(goTCharacter: GoTCharacter): Validator {
    validator = Validator(GoTCharacter::class.java.simpleName)

    isValid(goTCharacter.name, NAME_FIELD)
    isValid(goTCharacter.houseName, HOUSE_NAME_FIELD)
    isValid(goTCharacter.description, DESCRIPTION_FIELD)

    return validator as Validator
  }

  private fun isValid(fieldText: String, fieldKey: String) {
    if (isNotEmpty(fieldText)) {
      validator!!.addWrong(fieldKey, EMPTY_ERROR)
    } else if (isOnlySpaces(fieldText)) {
      validator!!.addWrong(fieldKey, ONLY_SPACES_ERROR)
    } else if (isOnlyUpperCase(fieldText)) {
      validator!!.addWrong(fieldKey, ONLY_UPPER_CASE_ERROR)
    }
  }

  private fun isOnlyUpperCase(fieldText: String): Boolean {
    val upperCase = withoutFirstCharacter(fieldText.toUpperCase())
    val realFieldText = withoutFirstCharacter(fieldText)
    return realFieldText == upperCase
  }

  private fun isOnlySpaces(fieldText: String): Boolean {
    return fieldText.trim { it <= ' ' }.isEmpty()
  }

  private fun isNotEmpty(fieldText: String): Boolean {
    return fieldText.isEmpty()
  }

  private fun withoutFirstCharacter(text: String): String {
    return text.substring(1, text.length)
  }

  companion object {

    //Fields
    const val NAME_FIELD = "_name"
    const val HOUSE_NAME_FIELD = "_house_name"
    const val DESCRIPTION_FIELD = "_description"

    //Errors
    const val EMPTY_ERROR = "_empty_"
    const val ONLY_SPACES_ERROR = "_only_spaces_"
    const val ONLY_UPPER_CASE_ERROR = "_only_upper_case_"
  }
}
