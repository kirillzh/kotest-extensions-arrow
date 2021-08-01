package io.kotest.assertions.arrow.validation

import arrow.core.Invalid
import arrow.core.Valid
import arrow.core.invalid
import arrow.core.valid
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class ValidatedMatchersTest : StringSpec({

   "Validated shouldBe Valid" {

      shouldThrow<AssertionError> {
         Invalid("error") should beValid()
      }.message shouldBe "Validated.Invalid(error) should be Valid"

      Valid("ok") should beValid()
      Valid("ok").shouldBeValid()
      Valid("ok") shouldBeValid "ok"
      Valid("ok").shouldBeValid { it.value shouldBe "ok" }
      shouldThrow<AssertionError> {
         Valid("ok") shouldBeValid { it.value shouldNotBe "ok" }
      }

      shouldThrow<AssertionError> {
         Valid("ok") shouldNotBeValid "ok"
      }.message shouldBe "Validated.Valid(ok) should not be Valid(ok)"

      shouldThrow<AssertionError> {
         Invalid("error") should beValid("error")
      }.message shouldBe "Validated.Invalid(error) should be Valid(error)"
   }

   "Validated should use contracts to smart cast Valids" {
      val e = "boo".valid()
      e.shouldBeValid()
      e.value shouldBe "boo"
   }

   "Validated shouldBe Invalid" {

      shouldThrow<AssertionError> {
         Valid("foo") should beInvalid()
      }.message shouldBe "Validated.Valid(foo) should be Invalid"

      Invalid("error") should beInvalid()
      Invalid("error").shouldBeInvalid()
      Invalid("error") shouldBeInvalid "error"
      Invalid("error") shouldBeInvalid { it.value shouldBe "error" }
      shouldThrow<AssertionError> {
         Invalid("error") shouldBeInvalid { it.value shouldNotBe "error" }
      }
     Invalid("error").shouldBeInvalid()
   }

   "use contracts to smart cast Invalids" {
      val e = "boo".invalid()
      e.shouldBeInvalid()
      e.value shouldBe "boo"
   }
})
