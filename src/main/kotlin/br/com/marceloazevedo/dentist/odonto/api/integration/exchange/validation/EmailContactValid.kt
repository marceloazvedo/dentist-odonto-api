package br.com.marceloazevedo.dentist.odonto.api.integration.exchange.validation

import br.com.marceloazevedo.dentist.odonto.api.enum.ContactType
import br.com.marceloazevedo.dentist.odonto.api.integration.exchange.request.ContactRequest
import javax.validation.Constraint
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext
import javax.validation.Payload
import kotlin.reflect.KClass

@MustBeDocumented
@Constraint(validatedBy = [EmailContactValidation::class])
@Target(AnnotationTarget.TYPE, AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class EmailContactValid(
    val message: String = "The e-mail informed is invalid",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)

class EmailContactValidation : ConstraintValidator<EmailContactValid, ContactRequest?> {

    companion object {
        val REGEX_EMAIL_VALIDATION =
            Regex("(?:[a-z0-9!#\$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#\$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")
    }

    override fun isValid(contact: ContactRequest?, context: ConstraintValidatorContext?): Boolean =
        contact?.type != ContactType.EMAIL ||
                (contact.type == ContactType.EMAIL && REGEX_EMAIL_VALIDATION.matches(contact.value ?: ""))

}
