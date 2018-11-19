package com.tonilopezmr.androidtesting.got.model

import android.support.annotation.NonNull
import com.tonilopezmr.androidtesting.got.model.api.CharacterApi
import com.tonilopezmr.androidtesting.got.model.validator.CharacterValidator
import org.hamcrest.Matchers.contains
import org.junit.Assert.assertThat
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import java.util.Arrays

class CharacterRepositoryShould {

//  @Test
//  fun should_do_Anything(){
//    
//  }

  @Test
  fun return_a_ordered_character_list() {

    //Given - an CharacterApi that returns 3 characters
    val pepe = getCharacterNamed("Pepe")
    val ana = getCharacterNamed("Ana")
    val sara = getCharacterNamed("Sara")
    //Fake the CharacterApi class and make it return whatever we want
    val api = getMockApi(pepe, ana, sara)
    //Create an instance of CharacterValidator because we will make use of its classes
    val characterValidator = CharacterValidator()
    //Create an instance of CharacterRepository nad pass in the constructor the validator and fake api
    val characterRepository = CharacterRepository(characterValidator, api)

    //When I do - sort fake list by name
    val characters = characterRepository.sortByName

    //Then it is- expected to get fake list sorted
    assertThat(characters, contains(ana, pepe, sara))
  }

  @Test
  fun call_to_api_getAll_when_calls_repository_all() {
    //Given - a mocked api
    val api = mock(CharacterApi::class.java)
    val validator = CharacterValidator()
    //pass api and validator to repository
    val repository = CharacterRepository(validator, api)

    //When I do- get all()
    repository.all

    //Then it- verifies if api called get all
    verify(api).all
  }

  @Test
  fun return_character_list_from_house_stark() {
    val pepe = getCharacterNamedFromHouse("Pepe", "Stark")
    val ana = getCharacterNamedFromHouse("Ana", "Baratheon")
    val sara = getCharacterNamedFromHouse("Sara", "None")
    //Given - a mocked api
    val api = getMockApi(pepe, ana, sara)

    val validator = CharacterValidator()
    //pass api and validator to repository
    val repository = CharacterRepository(validator, api)

    //based on the repository info it calls getAllByHouse, and holds a list of GoTCharacter with specified house
    val characters =repository.getAllByHouse("Stark")

    //check that list contains 'Pepe'
    assertThat(characters, contains(pepe))
  }

  @Test
  fun add_character_to_list(){
    //Mock CharacterApi as we dont need anything from it
    val api = mock(CharacterApi::class.java)
    //Create an instance of CharacterValidator because we will make use of its classes- isValid()
    val characterValidator = CharacterValidator()
//    Create an instance of CharacterRepository nad pass in the constructor the validator and fake api
    val repository = CharacterRepository(characterValidator, api)

    val character = GoTCharacter("Isco", "url", "desc", "house")
    repository.create(character)
    assertThat(repository.goTCharacterList, contains(character))
  }

  @NonNull
  private fun getCharacterNamedFromHouse(name: String, house: String): GoTCharacter{
    //characterName holds a GoTCharacter with a given name
    val characterName = getCharacterNamed(name)
    //characterName add a house value
    given(characterName.houseName).willReturn(house)
    //return a GoTCharacter  with name and house
    return characterName
  }

  @NonNull
  fun getMockApi(pepe: GoTCharacter, ana: GoTCharacter, sara: GoTCharacter): CharacterApi {
    val pepeAnaSara = mutableListOf(pepe, ana, sara)
    val characterApi = mock(CharacterApi::class.java)
    given(characterApi.all).willReturn(pepeAnaSara)
    return characterApi
  }

  @NonNull
  fun getCharacterNamed(name: String): GoTCharacter {
    //Mock GoTCharacter class
    val character = mock(GoTCharacter::class.java)
    //gives character a name value
    given(character.name).willReturn(name)
    //gives character to string function a return value
    given(character.toString()).willReturn(name)
    //returns a GoTCharacter with a name value
    return character
  }
}