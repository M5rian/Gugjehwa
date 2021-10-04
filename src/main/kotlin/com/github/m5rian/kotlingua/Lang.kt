package com.github.m5rian.kotlingua

import kotlinx.serialization.Serializable
import java.io.FileNotFoundException

@Serializable(with = LangSerializer::class)
enum class Lang(val iso: String) {
    AFRIKAANS("af"),
    ALBANIAN("sq"),
    ARABIC_ALGERIA("ar_DZ"),
    ARABIC_BAHRAIN("ar_BH"),
    ARABIC_EGYPT("ar_EG"),
    ARABIC_IRAQ("ar-iq"),
    ARABIC_JORDAN("ar-jo"),
    ARABIC_KUWAIT("ar-kw"),
    ARABIC_LEBANON("ar-lb"),
    ARABIC_LIBYA("ar-ly"),
    ARABIC_MOROCCO("ar-ma"),
    ARABIC_OMAN("ar-om"),
    ARABIC_QATAR("ar-qa"),
    ARABIC_SAUDI("ar-sa"),
    ARABIC_SYRIA("ar-sy"),
    ARABIC_TUNISIA("ar-tn"),
    ARABIC_UAE("ar-ae"),
    ARABIC_YEMEN("ar-ye"),
    BASQUE("eu"),
    BELARUSIAN("be"),
    BULGARIAN("bg"),
    CATALAN("ca"),
    CHINESE_HONG_KONG("zh_HK"),
    CHINESE_PRC("zh_CN"),
    CHINESE_SINGAPORE("zh_SG"),
    CHINESE_TAIWAN("zh_TW"),
    CROATIAN("hr"),
    CZECH("cs"),
    DANISH("da"),
    DUTCH_BELGIUM("nl-be"),
    DUTCH_STANDARD("nl"),
    ENGLISH("en"),
    ENGLISH_AUSTRALIA("en_AU"),
    ENGLISH_BELIZE("en_BZ"),
    ENGLISH_CANADA("en_CA"),
    ENGLISH_IRELAND("en_IE"),
    ENGLISH_JAMAICA("en_JM"),
    ENGLISH_NEW_ZEELAND("en-nz"),
    ENGLISH_SOUTH("en-za"),
    ENGLISH_TRINIDAD("en-tt"),
    ENGLISH_UNITED_KINGDOM("en_GB"),
    ENGLISH_UNITED_STATES("en_US"),
    ESTONIAN("et"),
    FAEROESE("fo"),
    FARSI("fa"),
    FINNISH("fi"),
    FRENCH_BELGIUM("fr-be"),
    FRENCH_CANADA("fr-ca"),
    FRENCH_LUXEMBOURG("fr-lu"),
    FRENCH_STANDARD("fr"),
    FRENCH_SWITZERLAND("fr-ch"),
    GAELIC_SCOTLAND("gd"),
    GERMAN_AUSTRIA("de_AT"),
    GERMAN_LIECHTENSTEIN("de_LI"),
    GERMAN_LUXEMBOURG("de_LU"),
    GERMAN_STANDARD("de"),
    GERMAN_SWITZERLAND("de-ch"),
    GREEK("el"),
    HEBREW("he"),
    HINDI("hi"),
    HUNGARIAN("hu"),
    ICELANDIC("is"),
    INDONESIAN("id"),
    IRISH("ga"),
    ITALIAN_STANDARD("it"),
    ITALIAN_SWITZERLAND("it-ch"),
    JAPANESE("ja"),
    KOREAN("ko"),
    KOREAN_JOHAB("ko"),
    KURDISH("ku"),
    LATVIAN("lv"),
    LITHUANIAN("lt"),
    MACEDONIAN_FYROM("mk"),
    MALAYALAM("ml"),
    MALAYSIAN("ms"),
    MALTESE("mt"),
    NORWEGIAN("no"),
    NORWEGIAN_BOKMAL("nb"),
    NORWEGIAN_NYNORSK("nn"),
    POLISH("pl"),
    PORTUGUESE_BRAZIL("pt-br"),
    PORTUGUESE_PORTUGAL("pt"),
    PUNJABI("pa"),
    RHAETO_ROMANIC("rm"),
    ROMANIAN("ro"),
    ROMANIAN_REPUBLIC_OF_MOLDOVA("ro-md"),
    RUSSIAN("ru"),
    RUSSIAN_REPUBLIC_OF_MOLDOVA("ru-md"),
    SERBIAN("sr"),
    SLOVAK("sk"),
    SLOVENIAN("sl"),
    SORBIAN("sb"),
    SPANISH_ARGENTINA("es-ar"),
    SPANISH_BOLIVIA("es-bo"),
    SPANISH_CHILE("es-cl"),
    SPANISH_COLOMBIA("es-co"),
    SPANISH_COSTA("es-cr"),
    SPANISH_DOMINICAN_REPUBLIC("es-do"),
    SPANISH_ECUADOR("es-ec"),
    SPANISH_EL("es-sv"),
    SPANISH_GUATEMALA("es-gt"),
    SPANISH_HONDURAS("es-hn"),
    SPANISH_MEXICO("es-mx"),
    SPANISH_NICARAGUA("es-ni"),
    SPANISH_PANAMA("es-pa"),
    SPANISH_PARAGUAY("es-py"),
    SPANISH_PERU("es-pe"),
    SPANISH_PUERTO_RICO("es-pr"),
    SPANISH_SPAIN("es"),
    SPANISH_URUGUAY("es-uy"),
    SPANISH_VENEZUELA("es-ve"),
    SWEDISH("sv"),
    SWEDISH_FINLAND("sv-fi"),
    THAI("th"),
    TSONGA("ts"),
    TSWANA("tn"),
    TURKISH("tr"),
    UKRAINIAN("uk"),
    URDU("ur"),
    VENDA("ve"),
    VIETNAMESE("vi"),
    WELSH("cy"),
    XHOSA("xh"),
    YIDDISH("ji"),
    ZULU("zu");

    fun get(key: String, builder: ArgumentBuilder.() -> Unit = {}): String {
        if (!Kotlingua.languages.containsKey(this)) throw FileNotFoundException("The language ${this.name} isn't loaded up")

        var translation: String? = Kotlingua.languages[this]?.getProperty(key)
        if (translation == null && Kotlingua.defaultLangIsSet()) translation = Kotlingua.languages[Kotlingua.defaultLang]?.getProperty(key)

        val args = ArgumentBuilder().apply(builder).args
        args.forEach { (key, value) -> translation = translation?.replace("{$key}", value.toString()) }

        return translation ?: throw NullPointerException("For the key \"$key\" doesn't exist any translation")
    }
}