package me.saechimdaeki.study

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.runner.ApplicationContextRunner
import org.springframework.context.annotation.*
import org.springframework.core.type.AnnotatedTypeMetadata

class ConditionalTest {

    @Test
    fun conditional() {
        // true
        val contextRunner = ApplicationContextRunner()
        contextRunner.withUserConfiguration(Config1::class.java)
            .run { context ->
                run {
                    assertThat(context).hasSingleBean(MyBean::class.java)
                    assertThat(context).hasSingleBean(Config1::class.java)
                }
            }
        // false
        ApplicationContextRunner().withUserConfiguration(Config2::class.java)
            .run { context ->
                run {
                    assertThat(context).doesNotHaveBean(MyBean::class.java)
                    assertThat(context).doesNotHaveBean(Config1::class.java)
                }
            }

    }

    @Conditional(BooleanCondition::class)
    @Retention(AnnotationRetention.RUNTIME)
    @Target(AnnotationTarget.CLASS)
    annotation class BooleanConditional(val value:Boolean){ }


    @Conditional(TrueCondition::class)
    @Retention(AnnotationRetention.RUNTIME)
    @Target(AnnotationTarget.CLASS)
    annotation class TrueConditional{}

    @Configuration
    @BooleanConditional(true)
    class Config1 {
        @Bean
        fun myBean(): MyBean{
            return MyBean()
        }
    }

    @Conditional(FalseCondition::class)
    @Retention(AnnotationRetention.RUNTIME)
    @Target(AnnotationTarget.CLASS)
    annotation class FalseConditional{}

    @Configuration
    @BooleanConditional(false)
    class Config2 {
        @Bean
        fun myBean(): MyBean{
            return MyBean()
        }
    }

    class MyBean {
    }



    class TrueCondition :Condition{
        override fun matches(context: ConditionContext, metadata: AnnotatedTypeMetadata): Boolean {
            return true;
        }
    }

    class FalseCondition :Condition{
        override fun matches(context: ConditionContext, metadata: AnnotatedTypeMetadata): Boolean {
            return false;
        }
    }

    class BooleanCondition : Condition {
        override fun matches(context: ConditionContext, metadata: AnnotatedTypeMetadata): Boolean {
            val annotationAttributes = metadata.getAnnotationAttributes(BooleanConditional::class.java.name)
            return annotationAttributes?.get("value") as Boolean
        }

    }
}