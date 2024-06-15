package com.example.msyql_example.common.annotation

import jakarta.validation.Constraint
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import jakarta.validation.Payload
import kotlin.reflect.KClass

/**
 * ENUM 클래스를 validation하기 위한 커스텀
 * Annotaion
 */
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Constraint(validatedBy = [ValidEnumValidator::class])
annotation class ValidEnum(
    val message : String = "Invalid Enum Value",
    val groups : Array<KClass<*>> = [],
    val payload : Array<KClass<out Payload>> = [],
    val enumClass : KClass<out Enum<*>>
)

class ValidEnumValidator : ConstraintValidator<ValidEnum, Any?> {
    private lateinit var enumValues : Array<out Enum<*>>

    override fun initialize(annotation: ValidEnum) {
        enumValues = annotation.enumClass.java.enumConstants
    }

    override fun isValid(value: Any?, context: ConstraintValidatorContext?): Boolean {
        if (value == null) {
            return true
        }

        return enumValues.any { it.name == value.toString() }
    }

}
