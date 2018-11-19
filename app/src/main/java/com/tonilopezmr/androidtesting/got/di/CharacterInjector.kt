package com.tonilopezmr.androidtesting.got.di

import com.tonilopezmr.androidtesting.got.model.CharacterRepository
import com.tonilopezmr.androidtesting.got.model.api.CharacterApi
import com.tonilopezmr.androidtesting.got.model.validator.CharacterValidator

/**
 * CharacterInjector with Service Locator.
 *
 * http://martinfowler.com/articles/injection.html
 *
 */
class CharacterInjector {

  lateinit var characterRepository: CharacterRepository
  lateinit var characterValidator: CharacterValidator
  lateinit var characterApi: CharacterApi
  lateinit var endPoint: String

  private fun endPoint(): String {
    return if (endPoint == null) {
      "https://raw.githubusercontent.com/tonilopezmr/Game-of-Thrones/master/app/src/test/resources/data.json"
    } else endPoint
  }

  private fun characterApi(): CharacterApi {
    return if (characterApi == null) {
      CharacterApi(endPoint())
    } else characterApi
  }

  private fun characterValidator(): CharacterValidator {
    return if (characterValidator == null) {
      CharacterValidator()
    } else characterValidator
  }

  private fun characterRepository(): CharacterRepository {
    return if (characterRepository == null) {
      CharacterRepository(characterValidator(), characterApi())
    } else characterRepository
  }

  private fun configService(endPoint: String) {
    this.endPoint = endPoint
  }

  private fun configService(characterApi: CharacterApi) {
    this.characterApi = characterApi
  }

  private fun configService(characterValidator: CharacterValidator) {
    this.characterValidator = characterValidator
  }

  private fun configService(characterRepository: CharacterRepository) {
    this.characterRepository = characterRepository
  }

  companion object {

    private var injector: CharacterInjector? = null

    /////////////////////////////
    ////      Public API     ////
    /////////////////////////////

    fun load(injectorLocator: CharacterInjector) {
      injector = injectorLocator
    }

    fun config(characterRepository: CharacterRepository) {
      injector!!.configService(characterRepository)
    }

    fun config(characterValidator: CharacterValidator) {
      injector!!.configService(characterValidator)
    }

    fun config(characterApi: CharacterApi) {
      injector!!.configService(characterApi)
    }

    fun config(endPoint: String) {
      injector!!.configService(endPoint)
    }

    fun injectCharacterRepository(): CharacterRepository {
      return injector!!.characterRepository()
    }
  }
}

