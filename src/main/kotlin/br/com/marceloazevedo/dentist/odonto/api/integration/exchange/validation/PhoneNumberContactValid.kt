package br.com.marceloazevedo.dentist.odonto.api.integration.exchange.validation

import br.com.marceloazevedo.dentist.odonto.api.enum.ContactType
import br.com.marceloazevedo.dentist.odonto.api.integration.exchange.request.ContactRequest
import javax.validation.Constraint
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext
import javax.validation.Payload
import kotlin.reflect.KClass

@MustBeDocumented
@Constraint(validatedBy = [PhoneNumberContactValidation::class])
@Target(AnnotationTarget.TYPE, AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class PhoneNumberContactValid(
    val message: String = "The phone number informed is invalid",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)

class PhoneNumberContactValidation : ConstraintValidator<PhoneNumberContactValid, ContactRequest?> {

    companion object {
        val REGEX_PHONE_NUMBER_VALIDATION =
            Regex("\\d{11}")
    }

    override fun isValid(contact: ContactRequest?, context: ConstraintValidatorContext?): Boolean =
        contact?.type != ContactType.PHONE_NUMBER ||
                (contact.type == ContactType.PHONE_NUMBER && REGEX_PHONE_NUMBER_VALIDATION.matches(contact.value ?: ""))

}

