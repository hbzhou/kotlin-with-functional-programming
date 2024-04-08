package com.itsz.fp
class TruthyEvaluator<L, R, F>(
    private val leftCondition: LeftCondition<L>,
    private val rightCondition: RightCondition<R>,
    private val reconciler: (L, R) -> F
) {

    fun evaluate(): F {
        val leftResult = if (leftCondition.shouldEvaluate()) leftCondition.getResult() else leftCondition.getDefault()
        val rightResult = if (rightCondition.shouldEvaluate()) rightCondition.getResult() else rightCondition.getDefault()
        return reconciler(leftResult, rightResult)
    }

    interface LeftCondition<L> {
        fun shouldEvaluate(): Boolean
        fun getResult(): L
        fun getDefault(): L
    }

    interface RightCondition<R> {
        fun shouldEvaluate(): Boolean
        fun getResult(): R
        fun getDefault(): R
    }
}