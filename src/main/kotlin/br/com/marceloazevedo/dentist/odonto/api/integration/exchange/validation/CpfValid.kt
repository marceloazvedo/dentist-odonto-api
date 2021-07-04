package br.com.marceloazevedo.dentist.odonto.api.integration.exchange.validation

import br.com.marceloazevedo.dentist.odonto.api.util.isCpfValid
import javax.validation.Constraint
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext
import javax.validation.Payload
import kotlin.reflect.KClass


@MustBeDocumented
@Constraint(validatedBy = [CpfValidation::class])
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class CpfValid(
        val message: String = "your cpf is invalid",
        val groups: Array<KClass<*>> = [],
        val payload: Array<KClass<out Payload>> = []
)

class CpfValidation : ConstraintValidator<CpfValid, String> {
    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {
        return if (value.isNullOrBlank()) true else isCpfValid(value)
    }
}

