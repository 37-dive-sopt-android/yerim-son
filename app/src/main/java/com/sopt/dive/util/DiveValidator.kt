package com.sopt.dive.util

object DiveValidator {
    data class ValidationResult(
        val isValid: Boolean,
        val message: String? = null
    )

    // 로그인 검증 로직
    fun validateLogin(id: String, pw: String): ValidationResult {
        return when {
            id.isBlank() ->
                ValidationResult(false, "ID를 입력해주세요.")

            pw.isBlank() ->
                ValidationResult(false, "비밀번호를 입력해주세요.")

            else -> ValidationResult(true)
        }
    }

    // 회원가입 검증 로직
    fun validateSignUp(id: String, pw: String, nickname: String, mbti: String): ValidationResult {
        return when {
            id.isBlank() || pw.isBlank() || nickname.isBlank() || mbti.isBlank() ->
                ValidationResult(false, "모든 정보를 입력해주세요.")

            id.length !in 6..10 ->
                ValidationResult(false, "ID는 6~10자 사이여야 합니다.")

            pw.length !in 8..12 ->
                ValidationResult(false, "비밀번호는 8~12자 사이여야 합니다.")

            nickname.trim().isEmpty() ->
                ValidationResult(false, "닉네임은 공백만으로 구성될 수 없습니다.")

            else -> ValidationResult(true)
        }
    }
}