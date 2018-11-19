package com.tonilopezmr.androidtesting.got.model.validator

class InvalidException(validator: Validator) : Exception("Invalid " + validator.objectName)
