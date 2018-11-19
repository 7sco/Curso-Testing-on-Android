package com.tonilopezmr.androidtesting.got.model

import com.tonilopezmr.androidtesting.got.model.api.CharacterApi
import com.tonilopezmr.androidtesting.got.model.validator.CharacterValidator
import com.tonilopezmr.androidtesting.got.model.validator.InvalidException
import java.util.Comparator
import java.util.LinkedList

class CharacterRepository(private val characterValidator: CharacterValidator,
                          private val characterApi: CharacterApi) {

  val goTCharacterList: LinkedList<GoTCharacter> = LinkedList()

  /**
   * Debe devolver una lista de personajes de juego de tronos de internet **junto
   * con el contenido en la lista [.goTCharacterList] **
   *
   *
   * **Escribir los test para este metodo, si el metodo no cumple su fin,
   * terminarlo para que lo haga**
   *
   * @return Lista de personajes de juego de tronos
   * @throws Exception
   */
  val all: MutableList<GoTCharacter>
    @Throws(Exception::class)
    get() {
      val characterList = characterApi.all
      characterList.addAll(goTCharacterList)
      return characterList
    }

  val sortByName: List<GoTCharacter>
    @Throws(Exception::class)
    get() {
      val characters = all
      characters.sortWith(Comparator { character, t1 -> character.name.compareTo(t1.name) })

      return characters
    }

  @Throws(Exception::class)
  fun getAllByHouse(houseName: String): MutableList<GoTCharacter> {
    val characterList = all
    val iterator = characterList.iterator()
    while (iterator.hasNext()) {
      val goTCharacter = iterator.next();
      if (goTCharacter.houseName.toLowerCase() != houseName.toLowerCase()) {
        iterator.remove()
      }
    }
    return characterList
  }

  /**
   *
   * Debe añadir a la lista el nuevo personaje que se le pasa por parametros.
   *
   * @param goTCharacter
   * @throws InvalidException Si no el personaje no es valido
   */
  @Throws(InvalidException::class)
  fun create(goTCharacter: GoTCharacter) {
    val validator = characterValidator.valid(goTCharacter)
    if (!validator.isValid) {
      throw InvalidException(validator)
    }

    goTCharacterList.add(goTCharacter)
  }
}
