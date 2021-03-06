@file:Suppress("unused", "MayBeConstant")

/**
 *
 * Where's defined all dependencies used in project modules.
 *
 * */

const val KOTLIN_VERSION = "1.2.51"
const val SUPPORT_LIBRARY_VERSION = "27.1.1"
const val CONSTRAINT_LAYOUT_VERSION = "1.1.2"
const val RX_KOTLIN_VERSION = "2.2.0"
const val RX_ANDROID_VERSION = "2.2.0"
const val RX_JAVA_VERSION = "2.1.17"
const val RX_BINDING_VERSION = "2.1.1"
const val JUNIT_VERSION = "4.12"
const val TEST_RUNNER_VERSION = "1.0.2"
const val ESPRESSO_VERSION = "3.0.2"
const val MOCKITO_VERSION = "0.9.0"
const val TEST_RULES_VERSION = "1.0.2"
const val RETROFIT_VERSION = "2.4.0"
const val OKHTTP_VERSION = "3.10.0"
const val KODEIN_VERSION = "4.1.0"
const val GROUPIE_VERSION = "2.1.0"
const val GRADLE_VERSION = "3.1.3"
const val KOTLIN_LINT_VERSION = "1.15.1"
const val VIEW_MODEL_INJECTOR_VERSION = "0.3.0"
const val BARISTA_VERSION = "2.4.0"

object Kotlin {
    val standardLibrary = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$KOTLIN_VERSION"
}

object Android {
    val appCompat = "com.android.support:appcompat-v7:$SUPPORT_LIBRARY_VERSION"
    val cardView = "com.android.support:cardview-v7:$SUPPORT_LIBRARY_VERSION"
    val recyclerView = "com.android.support:recyclerview-v7:$SUPPORT_LIBRARY_VERSION"
    val constraintLayout =
        "com.android.support.constraint:constraint-layout:$CONSTRAINT_LAYOUT_VERSION"
}

object Rx {
    val android = "io.reactivex.rxjava2:rxandroid:$RX_ANDROID_VERSION"
    val binding = "com.jakewharton.rxbinding2:rxbinding-kotlin:$RX_BINDING_VERSION"
    val java = "io.reactivex.rxjava2:rxjava:$RX_JAVA_VERSION"
    val kotlin = "io.reactivex.rxjava2:rxkotlin:$RX_KOTLIN_VERSION"
}

object Retrofit {
    val base = "com.squareup.retrofit2:retrofit:$RETROFIT_VERSION"
    val gsonConverter = "com.squareup.retrofit2:converter-gson:$RETROFIT_VERSION"
    val callAdapter = "com.squareup.retrofit2:adapter-rxjava2:$RETROFIT_VERSION"
    val logging = "com.squareup.okhttp3:logging-interceptor:$OKHTTP_VERSION"
    val mockWebServer = "com.squareup.okhttp3:mockwebserver:$OKHTTP_VERSION"
}

object AndroidTest {
    val runner = "com.android.support.test:runner:$TEST_RUNNER_VERSION"
    val espresso = "com.android.support.test.espresso:espresso-core:$ESPRESSO_VERSION"
    val rules = "com.android.support.test:rules:$TEST_RULES_VERSION"
    val espressoIntents = "com.android.support.test.espresso:espresso-intents:$ESPRESSO_VERSION"
    val espressoContrib = "com.android.support.test.espresso:espresso-contrib:$ESPRESSO_VERSION"
}

object UnitTest {
    val jUnit = "junit:junit:$JUNIT_VERSION"
    val mockito = "com.nhaarman:mockito-kotlin:$MOCKITO_VERSION"
}

object Kodein {
    val base = "com.github.salomonbrys.kodein:kodein:$KODEIN_VERSION"
    val conf = "com.github.salomonbrys.kodein:kodein-conf:$KODEIN_VERSION"
}

object Groupie {
    val base = "com.xwray:groupie:$GROUPIE_VERSION"
    val kotlinExtensions = "com.xwray:groupie-kotlin-android-extensions:$GROUPIE_VERSION"
}

object ClasspathDependencies {
    val gradle = "com.android.tools.build:gradle:$GRADLE_VERSION"
    val kotlinPlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$KOTLIN_VERSION"
    val kotlinLintPlugin = "gradle.plugin.org.jmailen.gradle:kotlinter-gradle:$KOTLIN_LINT_VERSION"
}

object ViewModelInjector {
    val base = "com.github.AllanHasegawa:kodein-viewmodel-injector:$VIEW_MODEL_INJECTOR_VERSION"
}
object Barista {
    val base = "com.schibsted.spain:barista:$BARISTA_VERSION"
}
