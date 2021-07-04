package br.com.marceloazevedo.dentist.odonto.api.integration.exchange.validation

import br.com.marceloazevedo.dentist.odonto.api.util.isCpfValid
import br.com.marceloazevedo.dentist.odonto.api.util.toLocalDate
import javax.validation.Constraint
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext
import javax.validation.Payload
import kotlin.reflect.KClass

@MustBeDocumented
@Constraint(validatedBy = [DateTimeValidation::class])
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class DateTimeValid(
        val message: String = "The value informed to this date is invalid",
        val dateFormat: String,
        val groups: Array<KClass<*>> = [],
        val payload: Array<KClass<out Payload>> = []
)

class DateTimeValidation : ConstraintValidator<DateTimeValid, String> {
    private lateinit var dateFormat: String

    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {
        return if (value.isNullOrBlank()) true else {
            return try {
                value.toLocalDate(dateFormat)
                true
            } catch (exception: Exception) {
                false
            }
        }
    }

    override fun initialize(constraintAnnotation: DateTimeValid?) {
        dateFormat = constraintAnnotation?.dateFormat ?: ""
        super.initialize(constraintAnnotation)
    }

}
