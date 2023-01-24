package me.saechimdaeki.study

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

class ConfigurationTest {

    @Test
    fun configuration() {
        val common = Common()
        assertThat(Common()).isNotSameAs(Common())
        assertThat(common).isSameAs(common)

        val myConfig = MyConfig()
        val bean1 = myConfig.bean1()
        val bean2 = myConfig.bean2()

        assertThat(bean1.getCommon).isNotSameAs(bean2.getCommon)

        val ac = AnnotationConfigApplicationContext()
        ac.register(MyConfig::class.java)
        ac.refresh()

        val bean1Ac = ac.getBean(Bean1::class.java)
        val bean2Ac = ac.getBean(Bean2::class.java)

        assertThat(bean1Ac.getCommon).isEqualTo(bean2Ac.getCommon)
    }

    @Test
    fun proxyCommonMethod(){
        val myConfigProxy = MyConfigProxy()
        val bean1 = myConfigProxy.bean1()
        val bean2 = myConfigProxy.bean2()

        assertThat(bean1.getCommon).isSameAs(bean2.getCommon)
    }

    class MyConfigProxy : MyConfig() {
        private var common:Common? = null

        override fun common(): Common {
            when (this.common) {
                null -> this.common = super.common()
            }
            return this.common!!

        }
    }


    @Configuration(/*proxyBeanMethods = false*/)
    class MyConfig {
        @Bean
        fun common(): Common {
            return Common()
        }

        @Bean
        fun bean1(): Bean1 {
            return Bean1(common())
        }

        @Bean
        fun bean2(): Bean2 {
            return Bean2(common())
        }
    }

    class Bean1(private val common: Common){
         val getCommon = common
    }

    class Bean2(private val common: Common) {
        val getCommon = common
    }

    class Common {

    }

    // Bean 1 <-- Common 이라는 빈에 의존
    // Bean 2 <-- Common ""
}