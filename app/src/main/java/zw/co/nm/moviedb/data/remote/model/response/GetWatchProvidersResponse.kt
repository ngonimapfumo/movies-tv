package zw.co.nm.moviedb.data.remote.model.response


import com.google.gson.annotations.SerializedName

data class GetWatchProvidersResponse(
    val id: Int?,
    val results: Results?
) {
    data class Results(
        @SerializedName("AD")
        val aD: AD?,
        @SerializedName("AE")
        val aE: AE?,
        @SerializedName("AL")
        val aL: AL?,
        @SerializedName("AO")
        val aO: AO?,
        @SerializedName("AR")
        val aR: AR?,
        @SerializedName("AT")
        val aT: AT?,
        @SerializedName("AU")
        val aU: AU?,
        @SerializedName("BA")
        val bA: BA?,
        @SerializedName("BE")
        val bE: BE?,
        @SerializedName("BF")
        val bF: BF?,
        @SerializedName("BG")
        val bG: BG?,
        @SerializedName("BO")
        val bO: BO?,
        @SerializedName("BR")
        val bR: BR?,
        @SerializedName("BZ")
        val bZ: BZ?,
        @SerializedName("CA")
        val cA: CA?,
        @SerializedName("CH")
        val cH: CH?,
        @SerializedName("CL")
        val cL: CL?,
        @SerializedName("CO")
        val cO: CO?,
        @SerializedName("CR")
        val cR: CR?,
        @SerializedName("CY")
        val cY: CY?,
        @SerializedName("CZ")
        val cZ: CZ?,
        @SerializedName("DE")
        val dE: DE?,
        @SerializedName("DK")
        val dK: DK?,
        @SerializedName("DO")
        val dO: DO?,
        @SerializedName("EC")
        val eC: EC?,
        @SerializedName("EE")
        val eE: EE?,
        @SerializedName("EG")
        val eG: EG?,
        @SerializedName("ES")
        val eS: ES?,
        @SerializedName("FI")
        val fI: FI?,
        @SerializedName("FR")
        val fR: FR?,
        @SerializedName("GB")
        val gB: GB?,
        @SerializedName("GR")
        val gR: GR?,
        @SerializedName("GT")
        val gT: GT?,
        @SerializedName("HK")
        val hK: HK?,
        @SerializedName("HN")
        val hN: HN?,
        @SerializedName("HR")
        val hR: HR?,
        @SerializedName("HU")
        val hU: HU?,
        @SerializedName("ID")
        val iD: ID?,
        @SerializedName("IE")
        val iE: IE?,
        @SerializedName("IL")
        val iL: IL?,
        @SerializedName("IN")
        val iN: IN?,
        @SerializedName("IS")
        val iS: IS?,
        @SerializedName("IT")
        val iT: IT?,
        @SerializedName("JP")
        val jP: JP?,
        @SerializedName("KR")
        val kR: KR?,
        @SerializedName("LI")
        val lI: LI?,
        @SerializedName("LT")
        val lT: LT?,
        @SerializedName("LU")
        val lU: LU?,
        @SerializedName("LV")
        val lV: LV?,
        @SerializedName("ME")
        val mE: ME?,
        @SerializedName("MK")
        val mK: MK?,
        @SerializedName("MT")
        val mT: MT?,
        @SerializedName("MX")
        val mX: MX?,
        @SerializedName("MY")
        val mY: MY?,
        @SerializedName("NI")
        val nI: NI?,
        @SerializedName("NL")
        val nL: NL?,
        @SerializedName("NO")
        val nO: NO?,
        @SerializedName("NZ")
        val nZ: NZ?,
        @SerializedName("PA")
        val pA: PA?,
        @SerializedName("PE")
        val pE: PE?,
        @SerializedName("PH")
        val pH: PH?,
        @SerializedName("PL")
        val pL: PL?,
        @SerializedName("PT")
        val pT: PT?,
        @SerializedName("PY")
        val pY: PY?,
        @SerializedName("RO")
        val rO: RO?,
        @SerializedName("RS")
        val rS: RS?,
        @SerializedName("RU")
        val rU: RU?,
        @SerializedName("SA")
        val sA: SA?,
        @SerializedName("SE")
        val sE: SE?,
        @SerializedName("SG")
        val sG: SG?,
        @SerializedName("SI")
        val sI: SI?,
        @SerializedName("SK")
        val sK: SK?,
        @SerializedName("SM")
        val sM: SM?,
        @SerializedName("SV")
        val sV: SV?,
        @SerializedName("TH")
        val tH: TH?,
        @SerializedName("TR")
        val tR: TR?,
        @SerializedName("TW")
        val tW: TW?,
        @SerializedName("TZ")
        val tZ: TZ?,
        @SerializedName("UA")
        val uA: UA?,
        @SerializedName("US")
        val uS: US?,
        @SerializedName("UY")
        val uY: UY?,
        @SerializedName("VE")
        val vE: VE?,
        @SerializedName("ZA")
        val zA: ZA?,
        @SerializedName("ZW")
        val zW: ZW?
    ) {
        data class AD(
            val flatrate: List<Flatrate?>?,
            val link: String?
        ) {
            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )
        }

        data class AE(
            val buy: List<Buy?>?,
            val link: String?,
            val rent: List<Rent?>?
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )
        }

        data class AL(
            val flatrate: List<Flatrate?>?,
            val link: String?
        ) {
            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )
        }

        data class AO(
            val buy: List<Buy?>?,
            val link: String?,
            val rent: List<Rent?>?
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )
        }

        data class AR(
            val buy: List<Buy?>?,
            val flatrate: List<Flatrate?>?,
            val link: String?,
            val rent: List<Rent?>?
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )

            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )
        }

        data class AT(
            val buy: List<Buy?>?,
            val flatrate: List<Flatrate?>?,
            val link: String?,
            val rent: List<Rent?>?
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )

            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )
        }

        data class AU(
            val buy: List<Buy?>?,
            val flatrate: List<Flatrate?>?,
            val link: String?
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )

            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )
        }

        data class BA(
            val flatrate: List<Flatrate?>?,
            val link: String?
        ) {
            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )
        }

        data class BE(
            val buy: List<Buy?>?,
            val flatrate: List<Flatrate?>?,
            val link: String?,
            val rent: List<Rent?>?
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )

            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )
        }

        data class BF(
            val buy: List<Buy?>?,
            val link: String?,
            val rent: List<Rent?>?
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )
        }

        data class BG(
            val buy: List<Buy?>?,
            val flatrate: List<Flatrate?>?,
            val link: String?,
            val rent: List<Rent?>?
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )

            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )
        }

        data class BO(
            val flatrate: List<Flatrate?>?,
            val link: String?
        ) {
            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )
        }

        data class BR(
            val flatrate: List<Flatrate?>?,
            val link: String?
        ) {
            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )
        }

        data class BZ(
            val flatrate: List<Flatrate?>?,
            val link: String?
        ) {
            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )
        }

        data class CA(
            val buy: List<Buy?>?,
            val flatrate: List<Flatrate?>?,
            val link: String?,
            val rent: List<Rent?>?
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )

            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )
        }

        data class CH(
            val buy: List<Buy?>?,
            val flatrate: List<Flatrate?>?,
            val link: String?,
            val rent: List<Rent?>?
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )

            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )
        }

        data class CL(
            val buy: List<Buy?>?,
            val flatrate: List<Flatrate?>?,
            val link: String?,
            val rent: List<Rent?>?
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )

            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )
        }

        data class CO(
            val buy: List<Buy?>?,
            val flatrate: List<Flatrate?>?,
            val link: String?,
            val rent: List<Rent?>?
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )

            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )
        }

        data class CR(
            val flatrate: List<Flatrate?>?,
            val link: String?
        ) {
            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )
        }

        data class CY(
            val buy: List<Buy?>?,
            val link: String?,
            val rent: List<Rent?>?
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )
        }

        data class CZ(
            val buy: List<Buy?>?,
            val flatrate: List<Flatrate?>?,
            val link: String?,
            val rent: List<Rent?>?
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )

            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )
        }

        data class DE(
            val buy: List<Buy?>?,
            val flatrate: List<Flatrate?>?,
            val link: String?,
            val rent: List<Rent?>?
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )

            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )
        }

        data class DK(
            val buy: List<Buy?>?,
            val flatrate: List<Flatrate?>?,
            val link: String?,
            val rent: List<Rent?>?
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )

            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )
        }

        data class DO(
            val flatrate: List<Flatrate?>?,
            val link: String?
        ) {
            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )
        }

        data class EC(
            val buy: List<Buy?>?,
            val flatrate: List<Flatrate?>?,
            val link: String?,
            val rent: List<Rent?>?
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )

            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )
        }

        data class EE(
            val buy: List<Buy?>?,
            val flatrate: List<Flatrate?>?,
            val link: String?,
            val rent: List<Rent?>?
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )

            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )
        }

        data class EG(
            val buy: List<Buy?>?,
            val flatrate: List<Flatrate?>?,
            val link: String?,
            val rent: List<Rent?>?
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )

            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )
        }

        data class ES(
            val buy: List<Buy?>?,
            val flatrate: List<Flatrate?>?,
            val link: String?,
            val rent: List<Rent?>?
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )

            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )
        }

        data class FI(
            val buy: List<Buy?>?,
            val flatrate: List<Flatrate?>?,
            val link: String?,
            val rent: List<Rent?>?
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )

            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )
        }

        data class FR(
            val buy: List<Buy?>?,
            val flatrate: List<Flatrate?>?,
            val link: String?,
            val rent: List<Rent?>?
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )

            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )
        }

        data class GB(
            val buy: List<Buy?>?,
            val flatrate: List<Flatrate?>?,
            val link: String?,
            val rent: List<Rent?>?
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )

            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )
        }

        data class GR(
            val buy: List<Buy?>?,
            val flatrate: List<Flatrate?>?,
            val link: String?,
            val rent: List<Rent?>?
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )

            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )
        }

        data class GT(
            val flatrate: List<Flatrate?>?,
            val link: String?
        ) {
            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )
        }

        data class HK(
            val flatrate: List<Flatrate?>?,
            val link: String?
        ) {
            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )
        }

        data class HN(
            val flatrate: List<Flatrate?>?,
            val link: String?
        ) {
            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )
        }

        data class HR(
            val buy: List<Buy?>?,
            val flatrate: List<Flatrate?>?,
            val link: String?,
            val rent: List<Rent?>?
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )

            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )
        }

        data class HU(
            val buy: List<Buy?>?,
            val flatrate: List<Flatrate?>?,
            val link: String?,
            val rent: List<Rent?>?
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )

            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )
        }

        data class ID(
            val flatrate: List<Flatrate?>?,
            val link: String?
        ) {
            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )
        }

        data class IE(
            val buy: List<Buy?>?,
            val flatrate: List<Flatrate?>?,
            val link: String?,
            val rent: List<Rent?>?
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )

            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )
        }

        data class IL(
            val buy: List<Buy?>?,
            val link: String?,
            val rent: List<Rent?>?
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )
        }

        data class IN(
            val buy: List<Buy?>?,
            val flatrate: List<Flatrate?>?,
            val link: String?,
            val rent: List<Rent?>?
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )

            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )
        }

        data class IS(
            val buy: List<Buy?>?,
            val flatrate: List<Flatrate?>?,
            val link: String?,
            val rent: List<Rent?>?
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )

            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )
        }

        data class IT(
            val buy: List<Buy?>?,
            val flatrate: List<Flatrate?>?,
            val link: String?,
            val rent: List<Rent?>?
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )

            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )
        }

        data class JP(
            val buy: List<Buy?>?,
            val flatrate: List<Flatrate?>?,
            val link: String?,
            val rent: List<Rent?>?
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )

            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )
        }

        data class KR(
            val buy: List<Buy?>?,
            val flatrate: List<Flatrate?>?,
            val link: String?
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )

            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )
        }

        data class LI(
            val flatrate: List<Flatrate?>?,
            val link: String?
        ) {
            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )
        }

        data class LT(
            val buy: List<Buy?>?,
            val flatrate: List<Flatrate?>?,
            val link: String?,
            val rent: List<Rent?>?
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )

            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )
        }

        data class LU(
            val buy: List<Buy?>?,
            val flatrate: List<Flatrate?>?,
            val link: String?,
            val rent: List<Rent?>?
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )

            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )
        }

        data class LV(
            val buy: List<Buy?>?,
            val flatrate: List<Flatrate?>?,
            val link: String?,
            val rent: List<Rent?>?
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )

            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )
        }

        data class ME(
            val flatrate: List<Flatrate?>?,
            val link: String?
        ) {
            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )
        }

        data class MK(
            val flatrate: List<Flatrate?>?,
            val link: String?
        ) {
            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )
        }

        data class MT(
            val flatrate: List<Flatrate?>?,
            val link: String?
        ) {
            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )
        }

        data class MX(
            val flatrate: List<Flatrate?>?,
            val link: String?
        ) {
            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )
        }

        data class MY(
            val flatrate: List<Flatrate?>?,
            val link: String?
        ) {
            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )
        }

        data class NI(
            val flatrate: List<Flatrate?>?,
            val link: String?
        ) {
            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )
        }

        data class NL(
            val buy: List<Buy?>?,
            val flatrate: List<Flatrate?>?,
            val link: String?,
            val rent: List<Rent?>?
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )

            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )
        }

        data class NO(
            val buy: List<Buy?>?,
            val flatrate: List<Flatrate?>?,
            val link: String?,
            val rent: List<Rent?>?
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )

            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )
        }

        data class NZ(
            val buy: List<Buy?>?,
            val flatrate: List<Flatrate?>?,
            val link: String?
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )

            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )
        }

        data class PA(
            val flatrate: List<Flatrate?>?,
            val link: String?
        ) {
            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )
        }

        data class PE(
            val buy: List<Buy?>?,
            val flatrate: List<Flatrate?>?,
            val link: String?,
            val rent: List<Rent?>?
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )

            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )
        }

        data class PH(
            val flatrate: List<Flatrate?>?,
            val link: String?
        ) {
            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )
        }

        data class PL(
            val buy: List<Buy?>?,
            val flatrate: List<Flatrate?>?,
            val link: String?,
            val rent: List<Rent?>?
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )

            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )
        }

        data class PT(
            val buy: List<Buy?>?,
            val flatrate: List<Flatrate?>?,
            val link: String?,
            val rent: List<Rent?>?
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )

            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )
        }

        data class PY(
            val flatrate: List<Flatrate?>?,
            val link: String?
        ) {
            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )
        }

        data class RO(
            val flatrate: List<Flatrate?>?,
            val link: String?
        ) {
            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )
        }

        data class RS(
            val flatrate: List<Flatrate?>?,
            val link: String?
        ) {
            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )
        }

        data class RU(
            val buy: List<Buy?>?,
            val flatrate: List<Flatrate?>?,
            val link: String?
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )

            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )
        }

        data class SA(
            val buy: List<Buy?>?,
            val link: String?,
            val rent: List<Rent?>?
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )
        }

        data class SE(
            val buy: List<Buy?>?,
            val flatrate: List<Flatrate?>?,
            val link: String?,
            val rent: List<Rent?>?
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )

            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )
        }

        data class SG(
            val flatrate: List<Flatrate?>?,
            val link: String?
        ) {
            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )
        }

        data class SI(
            val buy: List<Buy?>?,
            val flatrate: List<Flatrate?>?,
            val link: String?,
            val rent: List<Rent?>?
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )

            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )
        }

        data class SK(
            val buy: List<Buy?>?,
            val flatrate: List<Flatrate?>?,
            val link: String?,
            val rent: List<Rent?>?
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )

            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )
        }

        data class SM(
            val flatrate: List<Flatrate?>?,
            val link: String?
        ) {
            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )
        }

        data class SV(
            val flatrate: List<Flatrate?>?,
            val link: String?
        ) {
            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )
        }

        data class TH(
            val flatrate: List<Flatrate?>?,
            val link: String?
        ) {
            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )
        }

        data class TR(
            val buy: List<Buy?>?,
            val flatrate: List<Flatrate?>?,
            val link: String?,
            val rent: List<Rent?>?
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )

            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )
        }

        data class TW(
            val flatrate: List<Flatrate?>?,
            val link: String?
        ) {
            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )
        }

        data class TZ(
            val buy: List<Buy?>?,
            val link: String?,
            val rent: List<Rent?>?
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )
        }

        data class UA(
            val buy: List<Buy?>?,
            val link: String?,
            val rent: List<Rent?>?
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )
        }

        data class US(
            val buy: List<Buy?>?,
            val flatrate: List<Flatrate?>?,
            val link: String?,
            val rent: List<Rent?>?
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )

            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )
        }

        data class UY(
            val flatrate: List<Flatrate?>?,
            val link: String?
        ) {
            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )
        }

        data class VE(
            val buy: List<Buy?>?,
            val flatrate: List<Flatrate?>?,
            val link: String?,
            val rent: List<Rent?>?
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )

            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )
        }

        data class ZA(
            val buy: List<Buy?>?,
            val link: String?,
            val rent: List<Rent?>?
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )
        }

        data class ZW(
            val buy: List<Buy?>?,
            val link: String?,
            val rent: List<Rent?>?
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int?,
                @SerializedName("logo_path")
                val logoPath: String?,
                @SerializedName("provider_id")
                val providerId: Int?,
                @SerializedName("provider_name")
                val providerName: String?
            )
        }
    }
}